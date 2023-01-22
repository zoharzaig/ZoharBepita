package com.example.zoharbepita

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationWithSombrero()
    }

    private fun animationWithSombrero() {

        val meWithSombrero = findViewById<ImageView>(R.id.me_with_simbrero_img)
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)

        meWithSombrero.animation = rotate
    }


    fun showNavigationMenu(view: View) {
        val menu = PopupMenu(this, view)

        menu.inflate(R.menu.nav_menu)

        menu.setOnMenuItemClickListener { item: MenuItem? ->
                when (item?.itemId) {
                    R.id.nav_home ->
                        startActivity(Intent(this, MainActivity::class.java))

                    R.id.nav_menu ->
                        startActivity(Intent(this, ZoharBeTacoMenu::class.java))

                    R.id.nav_seatsReservation ->
                        startActivity(Intent(this, ReserveASeat::class.java))

                }

                true
            }

        menu.show()
        }


}

