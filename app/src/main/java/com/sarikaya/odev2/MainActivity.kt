package com.sarikaya.odev2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.sarikaya.odev2.adapter.OnBoardingViewPagerAdapter
import com.sarikaya.odev2.model.OnBoardingData

class MainActivity : AppCompatActivity() {

    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onboardingViewPager: ViewPager? = null
    var sonraki: TextView? = null
    var position = 0
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(restorePreferencesData()){
            val i= Intent(applicationContext,HomeActivity::class.java)
            startActivity(i)
            finish()
        }

        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_indicator)
        sonraki = findViewById(R.id.sonraki)

        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Taze Yemekler","Her zaman taze yiyecekler.",R.drawable.img1))
        onBoardingData.add(OnBoardingData("Hızlı Teslimat","Sıcacık yemeklerle hızlı teslimat.",R.drawable.img2))
        onBoardingData.add(OnBoardingData("Kolay Ödeme","Online ve Kapıda ödeme yöntemleri.",R.drawable.img3))

        setOnboardingViewPagerAdapter(onBoardingData)
        position = onboardingViewPager!!.currentItem
        sonraki?.setOnClickListener {
            if(position < onBoardingData.size){
                position++
                onboardingViewPager!!.currentItem = position
            }
            if(position == onBoardingData.size){
                savePreferencesData()
                val i = Intent(applicationContext, HomeActivity::class.java)
                startActivity(i)
            }
        }

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if(tab.position == onBoardingData.size - 1){
                    sonraki!!.text = "Hadi Başlayalım"
                }
                else{
                    sonraki!!.text = "Sonraki"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setOnboardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){
        onboardingViewPager = findViewById(R.id.screenViewPager);
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this,onBoardingData)
        onboardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onboardingViewPager)

    }

    private fun savePreferencesData(){
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun",true)
        editor.apply()
    }

    private fun restorePreferencesData(): Boolean{
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun",false)
    }
}