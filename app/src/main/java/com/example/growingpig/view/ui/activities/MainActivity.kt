package com.example.growingpig.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.growingpig.R
import com.example.growingpig.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bnvMenu = binding.bnvMenu
        val navController = findNavController(R.id.fragment)


        bnvMenu.setupWithNavController(navController)

    }






}