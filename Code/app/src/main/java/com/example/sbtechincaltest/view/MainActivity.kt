package com.example.sbtechincaltest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.sbtechincaltest.databinding.ActivityMainBinding
import com.example.sbtechincaltest.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.loginButton.setOnClickListener {
            Log.i("email", "email is ${binding.emailTextField.text}")
            Log.i("password", "password is ${binding.passwordTextField.text}")

//            var isEmailEmpty = viewModel.isFieldTextBlank(binding.emailTextField.text)
            var isEmailEmpty = binding.emailTextField.text.isNullOrBlank()
//            var isPwdEmpty = viewModel.isFieldTextBlank(binding.passwordTextField.text)
            var isPwdEmpty = binding.passwordTextField.text.isNullOrBlank()

            when (!isEmailEmpty && !isPwdEmpty) {
                true -> {
                    Log.i("successful_login", "email and password were valid")

                }
                false -> {
                    Log.e("unsuccessful_login", "email and password were invalid or null")
                }
            }
            val intent = Intent(this, PhotoActivity::class.java)
            startActivity(intent)
        }
    }

}
