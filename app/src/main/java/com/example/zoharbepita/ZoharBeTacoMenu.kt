package com.example.zoharbepita

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity

class ZoharBeTacoMenu : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
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

