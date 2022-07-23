package com.example.viewsandlayouts1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewsandlayouts1.databinding.ActivityCalculatorLinearLayoutBinding

class CalculatorActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(intent.extras?.getInt(CALCULATOR_EXTRA_KEY) ?: R.layout.activity_calculator_constraint_layout)

    }
}