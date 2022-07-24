package com.example.viewsandlayouts1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

class CalculatorActivity : AppCompatActivity() {

    private lateinit var previousCalculation: TextView
    private lateinit var display: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            intent.extras?.getInt(CALCULATOR_EXTRA_KEY)
                ?: R.layout.activity_calculator_constraint_layout
        )

        previousCalculation = findViewById(R.id.previousCalculationView)
        display = findViewById(R.id.displayEditText)
        display.showSoftInputOnFocus = false

        setClickListeners()
    }

    private fun setClickListeners() {
        fun strFromRes(@IdRes id: Int) = resources.getString(id)

        fun updateText(stringToAdd: String) {
            val oldText = display.text.toString()
            val cursorPosition = display.selectionStart
            display.setText(String.format("%s%s%s",oldText.substring(0, cursorPosition), stringToAdd, oldText.substring(cursorPosition)))
            display.setSelection(cursorPosition + stringToAdd.length)
        }

        findViewById<Button>(R.id.decimalButton).setOnClickListener { updateText(strFromRes(R.string.decimalText)) }
        findViewById<Button>(R.id.button0).setOnClickListener { updateText(strFromRes(R.string.zeroText)) }
        findViewById<Button>(R.id.button1).setOnClickListener { updateText(strFromRes(R.string.oneText)) }
        findViewById<Button>(R.id.button2).setOnClickListener { updateText(strFromRes(R.string.twoText)) }
        findViewById<Button>(R.id.button3).setOnClickListener { updateText(strFromRes(R.string.threeText)) }
        findViewById<Button>(R.id.button4).setOnClickListener { updateText(strFromRes(R.string.fourText)) }
        findViewById<Button>(R.id.button5).setOnClickListener { updateText(strFromRes(R.string.fiveText)) }
        findViewById<Button>(R.id.button6).setOnClickListener { updateText(strFromRes(R.string.sixText)) }
        findViewById<Button>(R.id.button7).setOnClickListener { updateText(strFromRes(R.string.sevenText)) }
        findViewById<Button>(R.id.button8).setOnClickListener { updateText(strFromRes(R.string.eightText)) }
        findViewById<Button>(R.id.button9).setOnClickListener { updateText(strFromRes(R.string.nineText)) }
        findViewById<Button>(R.id.openParenthesesButton).setOnClickListener { updateText(strFromRes(R.string.parenthesesOpenText)) }
        findViewById<Button>(R.id.closeParenthesesButton).setOnClickListener { updateText(strFromRes(R.string.parenthesesCloseText)) }

        findViewById<Button>(R.id.divideButton).setOnClickListener { updateText(strFromRes(R.string.divideText)) }
        findViewById<Button>(R.id.multiplyButton).setOnClickListener { updateText(strFromRes(R.string.multiplyText)) }
        findViewById<Button>(R.id.addButton).setOnClickListener { updateText(strFromRes(R.string.addText)) }
        findViewById<Button>(R.id.subtractButton).setOnClickListener { updateText(strFromRes(R.string.subtractText)) }
        findViewById<Button>(R.id.equalsButton).setOnClickListener {
            previousCalculation.text = display.text
            val expression = Expression(display.text.toString()
                .replace(strFromRes(R.string.multiplyText), "*")
                .replace(strFromRes(R.string.divideText), "/"))
            display.setText(expression.calculate().toString())
            display.setSelection(display.text.length)
        }

        findViewById<Button>(R.id.clearButton).setOnClickListener {
            display.setText("")
            previousCalculation.text = ""
        }
        findViewById<ImageButton>(R.id.backspaceButton).setOnClickListener {
            val cursorPosition = display.selectionStart
            if (cursorPosition == 0) return@setOnClickListener
            display.setText(display.text.toString().removeRange(cursorPosition - 1, cursorPosition))
            display.setSelection(cursorPosition - 1)
        }

    }
}