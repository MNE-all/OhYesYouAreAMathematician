package com.mne4.ohyesyouareamathematician

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mne4.ohyesyouareamathematician.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val signs = arrayOf("+", "-", "*", "/")
    private var rightCount = 0
    private var totalCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun nextButtonClick(view: View) {
        randomize()

        binding.buttonCheck.isEnabled = true
        binding.buttonNext.isEnabled = false

        binding.editTextUserAnswer.setText("")
    }

    private fun randomize() {
        binding.textViewSign.text = signs.random()
        if (binding.textViewSign.text == "/") {
            // За идею с делением спасибо Калиниченко Т.М.!
            val operand = (1..99).random()
            binding.textViewFirstOperand.text = (operand  * (1..99).random()).toString()
            binding.textViewSecondOperand.text = operand.toString()
        }
        else {
            binding.textViewFirstOperand.text = (1..99).random().toString()
            binding.textViewSecondOperand.text = (1..99).random().toString()
        }
    }


    fun checkButtonClick(view: View) {
        when (binding.textViewSign.text) {
            "+" -> {
                if ((binding.textViewFirstOperand.text.toString().toInt() + binding.textViewSecondOperand.text.toString().toInt()) == binding.editTextUserAnswer.text.toString().toInt()) {
                    rightCount += 1
                }
            }
            "-" -> {
                if ((binding.textViewFirstOperand.text.toString().toInt() - binding.textViewSecondOperand.text.toString().toInt()) == binding.editTextUserAnswer.text.toString().toInt()) {
                    rightCount += 1
                }
            }
            "*" -> {
                if ((binding.textViewFirstOperand.text.toString().toInt() * binding.textViewSecondOperand.text.toString().toInt()) == binding.editTextUserAnswer.text.toString().toInt()) {
                    rightCount += 1
                }
            }
            "/" -> {
                if ((binding.textViewFirstOperand.text.toString().toFloat() / binding.textViewSecondOperand.text.toString().toFloat()) == binding.editTextUserAnswer.text.toString().toFloat()) {
                    rightCount += 1
                }
            }
        }
        totalCount += 1

        binding.textViewTotalCount.text = totalCount.toString()
        binding.textViewRightCount.text = rightCount.toString()
        binding.textViewLoseCount.text = (totalCount - rightCount).toString()
        binding.textViewPercents.text = "${String.format("%.2f", (100f / totalCount.toFloat() * rightCount.toFloat()))}%"
        binding.buttonCheck.isEnabled = false
        binding.buttonNext.isEnabled = true
    }
}
