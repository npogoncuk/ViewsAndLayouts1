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

    private fun AppCompatActivity.findButton(@IdRes id: Int, onClickEvent: () -> Unit) =
        findViewById<Button>(id).setOnClickListener { onClickEvent() }

    private fun setClickListeners() {
        fun strFromRes(@IdRes id: Int) = resources.getString(id)

        fun updateText(@IdRes id: Int) {
            val stringToAdd: String = strFromRes(id)
            val oldText = display.text.toString()
            val cursorPosition = display.selectionStart
            display.setText(
                String.format(
                    "%s%s%s",
                    oldText.substring(0, cursorPosition),
                    stringToAdd,
                    oldText.substring(cursorPosition)
                )
            )
            display.setSelection(cursorPosition + stringToAdd.length)
        }

        findButton(R.id.decimalButton) { updateText(R.string.decimalText) }
        findButton(R.id.button0) { updateText(R.string.zeroText) }
        findButton(R.id.button1) { updateText(R.string.oneText) }
        findButton(R.id.button2) { updateText(R.string.twoText) }
        findButton(R.id.button3) { updateText(R.string.threeText) }
        findButton(R.id.button4) { updateText(R.string.fourText) }
        findButton(R.id.button5) { updateText(R.string.fiveText) }
        findButton(R.id.button6) { updateText(R.string.sixText) }
        findButton(R.id.button7) { updateText(R.string.sevenText) }
        findButton(R.id.button8) { updateText(R.string.eightText) }
        findButton(R.id.button9) { updateText(R.string.nineText) }
        findButton(R.id.openParenthesesButton) { updateText(R.string.parenthesesOpenText) }
        findButton(R.id.closeParenthesesButton) { updateText(R.string.parenthesesCloseText) }

        findButton(R.id.divideButton) { updateText(R.string.divideText) }
        findButton(R.id.multiplyButton) { updateText(R.string.multiplyText) }
        findButton(R.id.addButton) { updateText(R.string.addText) }
        findButton(R.id.subtractButton) { updateText(R.string.subtractText) }

        findButton(R.id.equalsButton) {
            previousCalculation.text = display.text
            val expression = Expression(
                display.text.toString()
                    .replace(strFromRes(R.string.multiplyText), "*")
                    .replace(strFromRes(R.string.divideText), "/")
            )
            display.setText(expression.calculate().toString())
            display.setSelection(display.text.length)
        }

        findButton(R.id.clearButton) {
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