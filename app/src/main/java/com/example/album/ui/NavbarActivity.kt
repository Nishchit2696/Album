package com.example.album.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.album.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavbarActivity : AppCompatActivity() {

    lateinit var discovery: DiscoveryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar)

        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomnavbar)

        discovery = DiscoveryFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayout,discovery)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()



        bottomnav.setOnNavigationItemReselectedListener{ item ->

            when(item.itemId){
                R.id.discovery -> {
                    discovery = DiscoveryFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.framelayout,discovery)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

        }
    }
}