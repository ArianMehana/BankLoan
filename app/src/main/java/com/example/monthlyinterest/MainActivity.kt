package com.example.monthlyinterest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var shumaEditText: EditText
    lateinit var interesiEditText: EditText
    lateinit var kohezgjatjaEditText: EditText
    lateinit var kestiTextView: TextView
    lateinit var totaliTextView: TextView
    lateinit var kalkuloButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()


    }

    private fun initUI(){
       shumaEditText = findViewById(R.id.shuma_edittext)
       interesiEditText = findViewById(R.id.interesi_edittext)
       kohezgjatjaEditText = findViewById(R.id.kohezgjatja_edittext)
       kestiTextView = findViewById(R.id.kesti)
       totaliTextView = findViewById(R.id.totali)
       kalkuloButton = findViewById(R.id.kalkulo_button)

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.UP
        kalkuloButton.setOnClickListener{
            kestiTextView.text = "Kesti: ${df.format(calculateMonthlyPayments())}"
            totaliTextView.text = "Totali: ${df.format(kalkuloTotalin())}"
        }
    }

    //pv = shuma, i = interesi, n = muajt -------- returns payment per month or a double
    private fun calculateMonthlyPayments() : Double{
        val PV = getShuma()
        val i = getInteresi()
        val n = getKohezgjatja()
        return (PV*i*Math.pow((1+i), n)) / (Math.pow((1+i), n) - 1)
    }
    private fun kalkuloTotalin(): Double{
        val PMT = calculateMonthlyPayments()
        val n = getKohezgjatja()
        return PMT * n
    }

    private fun getShuma() = shumaEditText.text.toString().toDouble()
    private fun getInteresi() = interesiEditText.text.toString().toDouble() /1200
    private fun getKohezgjatja() = kohezgjatjaEditText.text.toString().toDouble()
}