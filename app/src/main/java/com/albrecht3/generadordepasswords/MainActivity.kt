package com.albrecht3.generadordepasswords

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.albrecht3.generadordepasswords.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)

        generaPassword()
    }

    private fun generaPassword() {
        binding.apply{
            btnGenera.setOnClickListener {
                val selectedOptions = mutableListOf<Char>()
                if (lowerCase.isChecked)  selectedOptions.addAll(('a'..'z'))
                if (upperCase.isChecked)  selectedOptions.addAll(('A'..'Z'))
                if (numbers.isChecked)  selectedOptions.addAll(('0'..'9'))
                if (symbols.isChecked)  selectedOptions.addAll("|!#$%&/()><,.-;:_*[]{}?".toList())
                if (selectedOptions.isEmpty()){
                Toast.makeText(this@MainActivity, buildString { append(getString(R.string.toast)) },Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

                val passLength = 8
                val randomPass = buildString {
                    repeat (passLength){
                        val randomIndex = Random.nextInt(0, selectedOptions.size)
                        append(selectedOptions[randomIndex])
                    }
                }
                tvPassword.text = randomPass
            }
            tvPassword.setOnClickListener {
                val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = ClipData.newPlainText("TextViewText",tvPassword.text)
                clipBoard.setPrimaryClip(clip)
                Toast.makeText(this@MainActivity, getString(R.string.Copy), Toast.LENGTH_SHORT).show()
            }
        }
    }
}