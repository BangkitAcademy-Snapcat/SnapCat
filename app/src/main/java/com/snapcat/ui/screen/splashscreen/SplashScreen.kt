package com.snapcat.ui.screen.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.snapcat.R
import com.snapcat.databinding.ActivitySplashScreenBinding
import com.snapcat.ui.screen.MainActivity
import com.snapcat.ui.screen.welcome.Welcome
import com.snapcat.util.Constants
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.SplashScreenImage.alpha = 0f
        binding.SplashScreenImage.animate().setDuration(Constants.ANIMATION_DURATION_SPLASHSCREEN).alpha(1f).withEndAction {
            lifecycleScope.launch {

                val nextIntent = Intent(this@SplashScreen, Welcome::class.java)
                val options = ActivityOptionsCompat.makeCustomAnimation(this@SplashScreen, android.R.anim.fade_in, android.R.anim.fade_out)
                startActivity(nextIntent, options.toBundle())
                finish()

//                userDataStore.getUserData().collect { data ->
//                    nextIntent = if (data.userId.isNotEmpty() && data.name.isNotEmpty() && data.token.isNotEmpty()) {
//                        Intent(this@SplashScreen, MainActivity::class.java)
//                    } else {
//                        Intent(this@SplashScreen, StartActivity::class.java)
//                    }
//                    val options = ActivityOptionsCompat.makeCustomAnimation(this@SplashScreen, android.R.anim.fade_in, android.R.anim.fade_out)
//                    startActivity(nextIntent, options.toBundle())
//                    finish()
//                }
            }
        }
    }
}