package com.ahmetov.adventurefriend

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ahmetov.adventurefriend.model.Ballance
import com.ahmetov.adventurefriend.sound.ErrorSoundPlayer
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val userBalance: Ballance = Ballance(50.0, 0)

    fun buy(view: View) {
        val course = changeCourse()
        val amount = findViewById<EditText>(R.id.amountEditText).text.toString().toInt()
        changeCourseField(course)

        if (userBalance.commonMoney < course * amount) {
            ErrorSoundPlayer.play(this)
        } else {
            val spend = course * amount
            userBalance.commonMoney -= spend
            userBalance.sunMoney += amount
            val commonMoneyField = findViewById<TextView>(R.id.ballanceCommonMoney)
            val sunMoneyField = findViewById<TextView>(R.id.ballanceSunMoney)
            commonMoneyField.text = "%.2f".format(userBalance.commonMoney)
            sunMoneyField.text = userBalance.sunMoney.toString()
        }
    }

    fun sell(view: View) {
        val course = changeCourse()
        val amount = findViewById<EditText>(R.id.amountEditText).text.toString().toInt()
        changeCourseField(course)

        if (userBalance.sunMoney < amount) {
            ErrorSoundPlayer.play(this)
        } else {
            val spend = course * amount
            userBalance.sunMoney -= amount
            userBalance.commonMoney += spend
            val commonMoneyField = findViewById<TextView>(R.id.ballanceCommonMoney)
            val sunMoneyField = findViewById<TextView>(R.id.ballanceSunMoney)
            commonMoneyField.text = "%.2f".format(userBalance.commonMoney)
            sunMoneyField.text = userBalance.sunMoney.toString()
        }
    }

    private fun changeCourseField(course: Double) {
        val courseField = findViewById<TextView>(R.id.course)
        courseField.text = "%.2f".format(course)
    }

    private fun changeCourse(): Double {
        return Random.nextDouble(0.0, 10.0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}