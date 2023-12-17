package com.snapcat.ui.screen.scan

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.snapcat.databinding.FragmentScanBinding
import com.snapcat.util.Object
import java.io.File

class ScanFragment : Fragment(), OnDialogDismissListener {

    private val viewModel: ScanViewModel by viewModels()
    private lateinit var binding: FragmentScanBinding
    private val cameraProviderFuture by lazy { ProcessCameraProvider.getInstance(requireActivity()) }
    private val cameraSelector by lazy { CameraSelector.DEFAULT_BACK_CAMERA }
    private var imageCapture: ImageCapture? = null
    private var preview: Preview? = null

    private var isFrontCameraSelected = false

    private val cameraProviderResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permissionGranted ->
            if (permissionGranted) {
                initializeCamera()
            } else {
                Snackbar.make(
                    binding.root,
                    "The camera permission is required",
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            resultUri(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraProviderResult.launch(Manifest.permission.CAMERA)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shutter.setOnClickListener {
            takePicture()
        }

        binding.flash.setOnClickListener {
            toggleFlash()
        }

        binding.gallery.setOnClickListener {
            startGallery()
        }

        binding.mode.setOnClickListener {
            toggleCamera()
        }
    }

    override fun onResume() {
        super.onResume()
        if (imageCapture == null || preview == null) {
            initializeCamera()
        }
    }

    private fun toggleCamera() {
        isFrontCameraSelected = !isFrontCameraSelected

        releaseCamera()
        initializeCamera()
    }

    @SuppressLint("RestrictedApi")
    private fun initializeCamera() {
        Log.d("ScanFragment", "Initializing camera...")
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()


            val resolutionSelector = ResolutionSelector.Builder().setAspectRatioStrategy(
                AspectRatioStrategy(
                    AspectRatio.RATIO_16_9,
                    AspectRatioStrategy.FALLBACK_RULE_AUTO
                )
            ).build()


            preview = Preview.Builder()
                .setResolutionSelector(resolutionSelector)
                .build()
                .also {
                    it.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
                }

            val cameraSelector = if (isFrontCameraSelected) {
                CameraSelector.DEFAULT_FRONT_CAMERA
            } else {
                CameraSelector.DEFAULT_BACK_CAMERA
            }

            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview!!, imageCapture!!)
                Log.d("ScanFragment", "Camera initialized successfully.")
            } catch (e: Exception) {
                Log.e("ScanFragment", "Use case binding failed: ${e.message}")
            }

        }, ContextCompat.getMainExecutor(requireActivity()))
    }


    private fun releaseCamera() {
        imageCapture = null
        preview = null
        try {
            cameraProviderFuture.get()?.unbindAll()
        } catch (e: Exception) {
            Log.d("ScanFragment", "Error releasing camera: ${e.message}")
        }
    }

    private fun takePicture() {
        val file = File(
            requireContext().getExternalFilesDirs(null)[0],
            "${System.currentTimeMillis()}.jpg"
        )
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
        imageCapture?.takePicture(
            outputFileOptions,
            ContextCompat.getMainExecutor(requireActivity()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri ?: Uri.fromFile(file)
                    Log.d("ScanFragment", "Photo capture succeeded: $savedUri")

                    resultUri(savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("ScanFragment", "Photo capture failed: ${exception.message}", exception)
                }
            })
    }

    private fun resultUri(savedUri: Uri) {
        val args = Bundle().apply {
            putString("image_uri", savedUri.toString())
        }

        val resultDialog = Object.newInstanceFragmentResultScan(this@ScanFragment).apply {
            arguments = args
        }

        releaseCamera()

        resultDialog.show(
            (context as AppCompatActivity).supportFragmentManager,
            "ResultScanDialog"
        )
    }

    private fun toggleFlash() {
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            val torchState = camera.cameraInfo.torchState.value

            if (torchState == TorchState.ON) {
                camera.cameraControl.enableTorch(false)
            } else {
                camera.cameraControl.enableTorch(true)
            }
        }, ContextCompat.getMainExecutor(requireActivity()))
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    override fun onDialogDismissed() {
        initializeCamera()
    }

}
