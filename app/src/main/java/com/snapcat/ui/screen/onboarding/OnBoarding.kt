package com.snapcat.ui.screen.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.view.WindowCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.snapcat.R
import com.snapcat.databinding.ActivityOnBoardingBinding
import com.snapcat.databinding.ContentOnBoardingBinding
import com.snapcat.ui.screen.auth.AuthViewModel
import com.snapcat.ui.screen.auth.login.LoginDialogFragment
import com.snapcat.ui.screen.auth.register.RegisterDialogFragment
import dagger.hilt.android.AndroidEntryPoint

class OnBoarding : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            introViewPager.adapter = OnboardingAdapter(this@OnBoarding)
            TabLayoutMediator(tabs, introViewPager) { _, _ -> }.attach()

            introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    buttonBack.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
                    buttonNext.visibility = if (position == 1) View.INVISIBLE else View.VISIBLE
                }
            })

            buttonNext.setOnClickListener{
                val nextIndex = introViewPager.currentItem + 1
                if(nextIndex < (introViewPager.adapter?.itemCount ?: 0)){
                    introViewPager.currentItem = nextIndex
                }
            }

            buttonBack.setOnClickListener{
                val prevIndex = introViewPager.currentItem - 1
                if(prevIndex >= 0){
                    introViewPager.currentItem = prevIndex
                }
            }

        }
        /*binding.apply{
            buttonNext.setOnClickListener {
                buttonBack.visibility = View.VISIBLE
                buttonNext.visibility = View.INVISIBLE
                dot1.setImageResource(R.drawable.default_dot)
                dot2.setImageResource(R.drawable.selected_dot)
                logInContent.visibility = View.VISIBLE
                signUpContent.visibility = View.VISIBLE
                imageOnboarding.setImageResource(R.drawable.onboarding_2)
                titleContent.text = getString(R.string.title_on_boarding)
                descContent.text = getString(R.string.desc2_onboarding)
            }

            buttonBack.setOnClickListener {
                buttonBack.visibility = View.INVISIBLE
                buttonNext.visibility = View.VISIBLE
                dot1.setImageResource(R.drawable.selected_dot)
                dot2.setImageResource(R.drawable.default_dot)
                logInContent.visibility = View.INVISIBLE
                signUpContent.visibility = View.INVISIBLE
                imageOnboarding.setImageResource(R.drawable.onboarding_1)
                titleContent.text = getString(R.string.title_on_boarding)
                descContent.text = getString(R.string.desc1_onboarding)
            }

            logInContent.setOnClickListener {
                val loginDialog = LoginDialogFragment(authViewModel)
                loginDialog.show(supportFragmentManager, "LoginDialog")
            }

            signUpContent.setOnClickListener {
                val regsiterDialog = RegisterDialogFragment(authViewModel)
                regsiterDialog.show(supportFragmentManager, "RegisterDialog")
            }
        }*/
//        val sectionsPagerAdapter = SectionsPagerAdapter(this)
//        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = findViewById(R.id.tabs)
//        TabLayoutMediator(tabs, viewPager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()
//        supportActionBar?.elevation = 0f
    }
}