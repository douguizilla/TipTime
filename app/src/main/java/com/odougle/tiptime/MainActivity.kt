package com.odougle.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.odougle.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListeners()

    }

    private fun setListeners() {
        binding.btnCalculate.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringOnPlainTextInput = binding.plainTextInput.text.toString()
        val cost = stringOnPlainTextInput.toDouble()

        val tipPercentage = when(binding.rgTipOptions.checkedRadioButtonId){
            R.id.rb_twenty -> 0.20
            R.id.rb_eighteen -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost

        if(binding.swRoundUpTip.isChecked){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResultText.text = getString(R.string.tip_amount, formattedTip)
    }
}