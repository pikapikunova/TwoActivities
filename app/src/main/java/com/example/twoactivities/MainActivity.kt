package com.example.twoactivities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var i = 0
        var res = " "
        var str = randomQuestion()
        res = str.substringAfter("= ")
        str = str.substringBefore("=") + "= "
        textView.text = str
        var count = 0.0


        button2.setOnClickListener {
            if (i < 5) {
                if (editText.text.toString() == res)
                    count+=1

                str = randomQuestion()
                res = str.substringAfter("= ")
                str = str.substringBefore("=") + "= "
                textView.text = str
                i++
                editText.setText("")
            }
            else {
                button2.visibility = View.INVISIBLE
                button.visibility = View.INVISIBLE
                editText.visibility = View.INVISIBLE
                textView.visibility = View.INVISIBLE
                val statistic = TextView(this)
                textView.text = "Ваш результат: " + count.toString()
                textView.visibility = View.VISIBLE
            }
        }

        button.setOnClickListener {
            val dia = AlertDialog.Builder(this)
            dia.setMessage("Вы точно хотите получить подсказку? Вы получите на полбалла меньше")
            dia.setPositiveButton("Да") { dialog, which ->
                if (count > 0.0)
                    count-=0.5
                val rr = Intent(this, Activity2::class.java)
                rr.putExtra(Activity2.EXTRA_FIELD, res)
                startActivity(rr)

            }
            dia.setNegativeButton("Нет") { dialog, which ->

            }
            dia.show()
        }
    }

    fun randomQuestion(): String{
        var num1 = 0
        var num2 = 0
        var res = 0
        val sign = listOf('-', '+', '/', '*')
        val t = sign[(0..sign.size-1).random()]
        when(t){
            '-' -> {
                num1 = (1..100).random()
                num2 = (1..100).random()
                while(num1<=num2)
                    num1 = (1..100).random()
                res = num1 - num2
            }
            '+' -> {
                num1 = (1..50).random()
                num2 = (1..99).random()
                while(num1+num2>100) {
                    num1 = (1..50).random()
                    num2 = (1..99).random()
                }
                res = num1+num2
            }
            '*' -> {
                num1 = (2..10).random()
                num2 = (2..50).random()
                while (num1 * num2 > 100) {
                    num1 = (2..10).random()
                    num2 = (2..50).random()
                }
                res = num1*num2
            }
            '/' -> {
                num2 = (2..50).random()
                var num3 = (2..10).random()
                while (num2 * num3 > 100) {
                    num2 = (2..50).random()
                    num3 = (2..10).random()
                }
                num1 = num2 * num3
                res = num3
            }
        }
        val str = num1.toString() + ' ' + t + ' ' + num2.toString() + " = " + res.toString()
        return str
    }


}