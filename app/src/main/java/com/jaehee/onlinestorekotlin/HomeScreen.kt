package com.jaehee.onlinestorekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaehee.onlinestorekotlin.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}