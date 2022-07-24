package com.example.viewsandlayouts1

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.viewsandlayouts1.databinding.ActivityMainBinding

private const val LinearLayoutString = "LinearLayout"
private const val RelativeLayoutString = "RelativeLayout"
private const val GridLayoutString = "GridLayout"
private const val ConstraintLayoutString = "ConstraintLayout"
const val CALCULATOR_EXTRA_KEY = "CALCULATOR_EXTRA_KEY"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSpannableToTextView()
    }

    private fun setSpannableToTextView() {
        val text = binding.spannableString.text.toString()
        val spannedString = SpannableString(text)

        fun setClickableSpan(layoutString: String) {
            val clickableSpanLinearLayoutString = object : ClickableSpan() {
                override fun onClick(view: View) {
                    startActivity(Intent(
                        this@MainActivity,
                        CalculatorActivity::class.java
                    ).apply {
                        putExtra(
                            CALCULATOR_EXTRA_KEY, when (layoutString) {
                                LinearLayoutString -> R.layout.activity_calculator_linear_layout
                                RelativeLayoutString -> R.layout.activity_calculator_relative_layout
                                GridLayoutString -> R.layout.activity_calculator_grid_layout
                                ConstraintLayoutString -> R.layout.activity_calculator_constraint_layout
                                else -> R.layout.activity_calculator_constraint_layout
                            }
                        )
                    })
                }
            }
            val indexOfStartLayoutString = text.indexOf(layoutString, 0, true)
            spannedString.setSpan(
                clickableSpanLinearLayoutString,
                indexOfStartLayoutString,
                indexOfStartLayoutString + layoutString.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )

        }

        setClickableSpan(LinearLayoutString)
        setClickableSpan(RelativeLayoutString)
        setClickableSpan(GridLayoutString)
        setClickableSpan(ConstraintLayoutString)

        binding.spannableString.text = spannedString
        binding.spannableString.movementMethod = LinkMovementMethod.getInstance()
    }
}