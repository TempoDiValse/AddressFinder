package com.example.addressfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.addressfinder.databinding.ActivityMainBinding
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding){
            activity = this@MainActivity
        }

        setContentView(binding.root)
    }

    fun openAddressFinder(){
        AddressFinder.open { b ->
            val zipCode = b.getString(AddressFinder.ZIPCODE)
            val address = b.getString(AddressFinder.ADDRESS)

            binding.tvResult.text = "주소: [$zipCode] $address"
        }
    }

    override fun onStart() {
        AddressFinder.register(this)

        super.onStart()
    }

    override fun onDestroy() {
        AddressFinder.unregister()

        super.onDestroy()
    }
}