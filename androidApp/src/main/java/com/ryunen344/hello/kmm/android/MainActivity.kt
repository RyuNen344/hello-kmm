package com.ryunen344.hello.kmm.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.ryunen344.hello.kmm.Greeting
import com.ryunen344.hello.kmm.randomUUID

fun greet() : String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by lazy {
        defaultViewModelProviderFactory.create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv : TextView = findViewById(R.id.text_view)
        tv.text = randomUUID()

        findViewById<AppCompatButton>(R.id.button_call)?.run {
            setOnClickListener {
                viewModel.fetchEvents()
            }
        }
    }
}
