package com.example.cacl_c

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvone.setOnClickListener { append("1", true) }
        tvtwo.setOnClickListener { append("2", true) }
        tvthree.setOnClickListener { append("3", true) }
        tvfour.setOnClickListener { append("4", true) }
        tvfive.setOnClickListener { append("5", true) }
        tvsix.setOnClickListener { append("6", true) }
        tvseven.setOnClickListener { append("7", true) }
        tveight.setOnClickListener { append("8", true) }
        tvnine.setOnClickListener { append("9", true) }
        tvpoint.setOnClickListener { append(".", true) }
        tvzero.setOnClickListener { append("0", true) }

        tvplus.setOnClickListener { append("+", false) }
        tvsub.setOnClickListener { append("-", false) }
        tvmul.setOnClickListener { append("*", false) }
        tvdivide.setOnClickListener { append("/", false) }
        tvopen.setOnClickListener { append("(", false) }
        tvclose.setOnClickListener { append(")", false) }

        tvclear.setOnClickListener {
            tvCalculate.text = ""
            tvResult.text = ""
        }

        ivback.setOnClickListener {
            val string = tvCalculate.text.toString()
            if (string.isNotEmpty()) {
                tvCalculate.text = string.substring(0, string.length - 1)
            }

        }


        tvequals.setOnClickListener {
            if (tvCalculate.text.toString().isEmpty()) {
                tvResult.text = "0"
            } else {
                try {
                    val expression = ExpressionBuilder(tvCalculate.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if (result == longResult.toDouble()) {
                        tvResult.text = longResult.toString()
                    } else {
                        tvResult.text = result.toString()
                    }
                } catch (e: Exception) {
                    tvResult.text = "ERROR"

                }

            }
            tvCalculate.text = ""
        }
    }
    private fun append(string:String,canClear:Boolean){
        if(canClear){
            tvResult.text=""
            tvCalculate.append(string)
        }else {
            if (tvResult.text.toString().contains("ERROR")) {
                tvCalculate.append(string)
                tvResult.text=""
            } else {
                tvCalculate.append(tvResult.text)
                tvCalculate.append(string)
                tvResult.text=""
            }
        }

    }
}



