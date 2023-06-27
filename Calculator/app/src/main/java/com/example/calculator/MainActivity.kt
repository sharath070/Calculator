package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var input: TextView?=null

    var lastNumeric: Boolean= false
    var lastdot: Boolean= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input= findViewById(R.id.ip)
    }
    fun onDigit(view: View){
        input?.append((view as Button).text)
        lastNumeric=true
    }
    fun onClear(view: View){
        input?.text=""
    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastdot)
            input?.append(".")

        lastNumeric=false
        lastdot=true
    }

    fun onoperator(view: View){
        input?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                input?.append((view as Button).text)
                lastNumeric=false
                lastdot=false
            }
        }
    }

    fun onEqual(view: View){
        if (lastNumeric){
            var txtValue = input?.text.toString()
            var prefix = ""

            try {
                if (txtValue.startsWith("-")){
                    prefix = "-"
                    txtValue=txtValue.substring(1)
                }
                if (txtValue.contains("-")){
                    val splitValue = txtValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    input?.text=removeZero((one.toDouble() - two.toDouble()).toString())
                }
                else if (txtValue.contains("+")){
                    val splitValue = txtValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    input?.text=removeZero((one.toDouble() + two.toDouble()).toString())
                }
                else if (txtValue.contains("*")){
                    val splitValue = txtValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    input?.text=removeZero((one.toDouble() * two.toDouble()).toString())
                }
                else if (txtValue.contains("/")){
                    val splitValue = txtValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    input?.text=removeZero((one.toDouble() / two.toDouble()).toString())
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZero(result : String):String{
        var value =result
        if(result.contains(".0"))
            value = result.substring(0,result.length-2)
        return value
    }

    private fun isOperatorAdded(value: String):Boolean{
        return if(value.startsWith("-"))
        {    false }
        else {
            value.contains("+")
                    ||value.contains("-")
                    ||value.contains("*")
                    ||value.contains("/")
        }
    }
}