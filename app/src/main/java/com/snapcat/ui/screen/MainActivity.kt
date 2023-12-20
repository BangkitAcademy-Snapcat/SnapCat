package com.snapcat.ui.screen

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.snapcat.R
import com.snapcat.databinding.ActivityMainBinding
import com.snapcat.ui.screen.shop.ShopFragment
import com.snapcat.ui.screen.home.HomeFragment
import com.snapcat.ui.screen.profile.ProfileFragment
import com.snapcat.ui.screen.scan.ScanFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityMainBinding
    private var currentItemId = R.id.home
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = binding.container
        viewPager.adapter = ScreenSlidePagerAdapter(this)
        binding.bottomNav.setOnItemSelectedListener { item ->
            if (currentItemId != item.itemId) {
                currentItemId = item.itemId
                when (item.itemId) {
                    R.id.home -> {
                        viewPager.setCurrentItem(0, false)
                        true
                    }
                    R.id.shop -> {
                        viewPager.setCurrentItem(1, false)
                        true
                    }
                    R.id.scan -> {
                        viewPager.setCurrentItem(2, false)
                        true
                    }
                    R.id.profile -> {
                        viewPager.setCurrentItem(3, false)
                        true
                    }
                    else -> false
                }
            } else {
                false
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SourceLockedOrientationActivity")
            override fun onPageSelected(position: Int) {
                val menu = binding.bottomNav.menu
                val menuItem = menu.getItem(position)
                menuItem.isChecked = true

                if (position == 2) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                } else {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
            }
        })
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> ShopFragment()
                2 -> ScanFragment()
                3 -> ProfileFragment()
                else -> Fragment()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Call finish() to close the activity
        finish()
    }
}



