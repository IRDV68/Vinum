package com.m600.alumnos.cice.vinum

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SplashActivity : AppCompatActivity() {

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        button = findViewById(R.id.button)

        button.setOnClickListener {
            val intent: Intent = Intent(
                applicationContext,
                LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
