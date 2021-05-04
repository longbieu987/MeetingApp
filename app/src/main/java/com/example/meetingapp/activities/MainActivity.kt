package com.example.meetingapp.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.meetingapp.R
import com.example.meetingapp.adapter.ViewPagerAdapter
import com.example.meetingapp.database.SharedPreferencesData
import com.example.meetingapp.databinding.ActivityMainBinding
import com.example.meetingapp.fragments.HomeFragment
import com.example.meetingapp.fragments.PersonalFragment
import com.example.meetingapp.viewmodel.AccountViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var model : AccountViewModel
    lateinit var sharedPreferences: SharedPreferencesData
    lateinit var binding: ActivityMainBinding
    lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()
        initView()
        model.getSharedPreferences(this).getUser().observe(this, { user ->
            Log.d(
                "BBB",
                "ActivityMain: name : ${user.name}\nemail : ${user.email}\npass : ${user.password}\nid : ${user.id}"
            )
        })


    }

    private fun initialization() {
        model = ViewModelProvider(this).get(AccountViewModel::class.java)
        sharedPreferences = SharedPreferencesData(this)

        pagerAdapter = ViewPagerAdapter(supportFragmentManager,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)



    }
    fun initView(){
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> {
                        binding.btNav.menu.findItem(R.id.menuHome).isChecked = true
                    }
                    1 -> {
                        binding.btNav.menu.findItem(R.id.menuPersonal).isChecked = true
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        binding.btNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHome -> {
                    loadFragment(HomeFragment.newInstance("1", "2"))
                    binding.viewPager.currentItem = 0
                    true
                }
                R.id.menuPersonal -> {
                    loadFragment(PersonalFragment.newInstance("1", "2"))
                    binding.viewPager.currentItem = 1
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
    private fun loadFragment(fragment: Fragment) {
        // load Fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}