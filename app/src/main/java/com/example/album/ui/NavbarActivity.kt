package com.example.album.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.ToggleButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import com.example.album.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class NavbarActivity : AppCompatActivity() {

    private val discoveryFragment = DiscoveryFragment()
    private val profileFragment = ProfileFragment()
    private val libraryFragment = LibraryFragment()
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var media: RelativeLayout
    private lateinit var root_layout: androidx.coordinatorlayout.widget.CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar)
        replaceFragment(discoveryFragment)

        root_layout = findViewById(R.id.root_layout)





        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.discovery -> replaceFragment(discoveryFragment)
                R.id.profile -> replaceFragment(profileFragment)
                R.id.library -> replaceFragment(libraryFragment)

            }
            true
        }


        media = findViewById(R.id.media)

        media.isVisible = true


        media.setOnClickListener {

//

            //starting popup
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val width: Int = size.x

            // Initialize a new layout inflater instance
            val inflater: LayoutInflater =
                getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // Inflate a custom view using layout inflater
            val view = inflater.inflate(R.layout.activity_media, null)

            // Initialize a new instance of popup window
            val popupWindow = PopupWindow(
                view, // Custom view to show in popup window
                LinearLayout.LayoutParams.MATCH_PARENT, // Width of popup window
                1740, // Window height
            )
            popupWindow.setWidth(width - 50);
            popupWindow.setFocusable(true);

            // Set an elevation for the popup window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }


            // If API level 23 or higher then execute the code
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Create a new slide animation for popup window enter transition
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.BOTTOM
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.BOTTOM
                popupWindow.exitTransition = slideOut

            }

            TransitionManager.beginDelayedTransition(root_layout)
            popupWindow.showAtLocation(
                root_layout, // Location to display popup window
                Gravity.BOTTOM, // Exact position of layout to display popup
                0, // X offset
                350 // Y offset

            )
        }

    }


    private fun replaceFragment(fragment: Fragment){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framelayout, fragment)
            transaction.commit()

    }

}

