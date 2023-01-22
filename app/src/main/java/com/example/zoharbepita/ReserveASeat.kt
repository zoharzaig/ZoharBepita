package com.example.zoharbepita

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.util.*

class ReserveASeat : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reserve_a_seat)

        pickDate()

        //set an alertDialog if 0 people are chosen.
        // else, display reservation info
        val reserveButton = findViewById<Button>(R.id.send_reservstion_button)

        val alpha_animation : Animator = ObjectAnimator.ofFloat(reserveButton, "alpha",0f,1f).setDuration(1000)

        val animatorSet = AnimatorSet()
        reserveButton.setOnClickListener {

            animatorSet.play(alpha_animation)
            animatorSet.start()

            val dialog = AlertDialog.Builder(this)

            if (curNumberOfPeople>0 && dayWasSelected && hourWasSelected){
                dialog.setTitle("THANK YOU!")
                dialog.setMessage("Your reservation for " + curNumberOfPeople + " people on the " +
                        selectedDay + "/" + selectedMonth + "/" + selectedYear + " at " + selectedHour + ":" + selectedMinute + " is accepted"  )
                dialog.setNeutralButton("Back to home screen")
                { dialogInterface, which ->
                    val intent=Intent(this@ReserveASeat, MainActivity::class.java)
                    startActivity(intent)
//                    setContentView(R.layout.activity_main)

                }
            }
            if (curNumberOfPeople==0){
                dialog.setTitle("Yo!")
                dialog.setMessage("You can must choose at least 1 guest")
                dialog.setNeutralButton("Back To Reservation")
                {dialogInterface, which ->
                    Toast.makeText(applicationContext,"Select how many people", Toast.LENGTH_SHORT).show()
                }
            }
            else {

                if (!dayWasSelected) {
                    dialog.setTitle("Yo!")
                    dialog.setMessage("You forgot to select the day")
                    dialog.setNeutralButton("Back To Reservation")
                    { dialogInterface, which ->
                        Toast.makeText(applicationContext, "Select the day", Toast.LENGTH_SHORT).show()
                    }
                }

                else if (!hourWasSelected) {
                    dialog.setTitle("Yo!")
                    dialog.setMessage("You forgot to select the hour")
                    dialog.setNeutralButton("Back To Reservation")
                    { dialogInterface, which ->
                        Toast.makeText(applicationContext, "Select the hour", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            val alertDialog : AlertDialog = dialog.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }

    var curNumberOfPeople= 0

    fun incrementNumOfPeople(view: View) {
        val numView = findViewById<TextView>(R.id.num_of_people_display)
        curNumberOfPeople++
        numView.setText("$curNumberOfPeople people")
    }

    fun decrementNumOfPeople(view: View) {
        val numView = findViewById<TextView>(R.id.num_of_people_display)
        curNumberOfPeople--
        if(curNumberOfPeople < 0) {curNumberOfPeople = 0}
        numView.setText("$curNumberOfPeople people")
    }

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var selectedDay = 0
    var selectedMonth = 0
    var selectedYear = 0
    var selectedHour = 0
    var selectedMinute = 0
    var dayWasSelected = false
    var hourWasSelected = false

    private fun getDateTimeCalender(){

        //initialize a calender
        val calendar = Calendar.getInstance()

        //set the calender to current day and time
        day=calendar.get(Calendar.DAY_OF_MONTH)
        month=calendar.get(Calendar.MONTH)
        year=calendar.get(Calendar.YEAR)
        hour=calendar.get(Calendar.HOUR)
        minute=calendar.get(Calendar.MINUTE)
    }

    private fun pickDate(){

        //initialize the time button variable
        val timeButton = findViewById<Button>(R.id.reservation_time)


//        on click- go first to DateTimeCalender to get the current date and hour displayed,
//        then proceed to a Date dialog
        timeButton.setOnClickListener{
            getDateTimeCalender()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    //save the selected day, month, and year to be the last setted by the user
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        selectedDay = day
        selectedMonth = month
        selectedYear = year
        dayWasSelected = true

        //proceed to clock dialog
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        selectedHour = hour
        selectedMinute = minute
        hourWasSelected = true

        //display the chosen time
        val timeDisplay = findViewById<TextView>(R.id.time_display)
        timeDisplay.text = "$selectedDay/$selectedMonth/$selectedYear at $selectedHour:$selectedMinute"

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

