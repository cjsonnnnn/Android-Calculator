package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Double.parseDouble


class MainActivity : AppCompatActivity() {
    private val TAG: String = "Main Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        class Operator() {
            fun add(num1: Number, num2: Number): Number {
                return num1.toFloat() + num2.toFloat()
            }

            fun min(num1: Number, num2: Number): Number {
                return num1.toFloat() - num2.toFloat()
            }

            fun div(num1: Number, num2: Number):  Number{
                return num1.toFloat() / num2.toFloat()
            }

            fun mul(num1: Number, num2: Number):  Number{
                return num1.toFloat() * num2.toFloat()
            }
        }

        Log.d(TAG, Operator().add(1.231,3).toString())

        val operator = Operator()
        val textView = findViewById<TextView>(R.id.textView)
        var memNumber: Number? = null
        var memOperation: String? = null

        fun isNumeric(s:String): Boolean {
            try{
                parseDouble(s)
                return true
            }catch (e:Exception){
                return false
            }
        }

        val clickListener = View.OnClickListener {
            var theButton = it as Button
            var inputText = theButton.getText().toString()
            var curText = textView.text.toString()
            Log.d(TAG,curText)
            Log.d(TAG,inputText)
            Log.d(TAG," ")
            if(isNumeric(inputText).or(inputText == ".") ){                           // condition when numbers
                textView.text = (curText+inputText).toFloat().toString()
            } else {
                if(inputText == "="){                           // condition when "="
                    if(memOperation == "+"){
                        textView.text = memNumber?.let { operator.add(it.toFloat(), curText.toFloat()).toString() }
                    } else if (memOperation == "-") {
                        textView.text = memNumber?.let { operator.min(it.toFloat(), curText.toFloat()).toString() }
                    } else if (memOperation == "x") {
                        textView.text = memNumber?.let { operator.mul(it.toFloat(), curText.toFloat()).toString() }
                    } else if (memOperation == ":") {
                        textView.text = memNumber?.let { operator.div(it.toFloat(), curText.toFloat()).toString() }
                    }
                    // update memory number
                    memNumber = (textView.text as String?)?.toFloat()
                    memOperation = null
                } else if (inputText == "C"){
                    memNumber = null
                    memOperation = null
                    textView.text = "0"
                } else {                                        // condition when "+, -, x, :"
                    memOperation = inputText                    // update memory operation
                    memNumber = curText.toFloat()               // update memory number
                    textView.text = "0"
                }
            }
//            Log.d(TAG,"Jason Caleb")
        }

        findViewById<Button>(R.id.button0).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button1).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button2).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button3).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button4).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button5).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button6).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button7).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button8).setOnClickListener(clickListener)
        findViewById<Button>(R.id.button9).setOnClickListener(clickListener)
        findViewById<Button>(R.id.buttonAdd).setOnClickListener(clickListener)
        findViewById<Button>(R.id.buttonMin).setOnClickListener(clickListener)
        findViewById<Button>(R.id.buttonMul).setOnClickListener(clickListener)
        findViewById<Button>(R.id.buttonDiv).setOnClickListener(clickListener)
        findViewById<Button>(R.id.buttonC).setOnClickListener(clickListener)
        findViewById<Button>(R.id.buttonRes).setOnClickListener(clickListener)
        findViewById<Button>(R.id.buttonDot).setOnClickListener(clickListener)
    }
}