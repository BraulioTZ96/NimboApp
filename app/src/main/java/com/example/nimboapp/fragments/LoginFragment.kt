package com.example.nimboapp.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.nimboapp.R
import com.example.nimboapp.activities.MainActivity
import com.example.nimboapp.databinding.FragmentLoginBinding
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var isEmailOkey: Boolean = false
    private var isPasswordOkey: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val email = binding.etEmail.text.toString()
                if (email.isNotEmpty()) {
                    checkEmailAddress()
                } else {
                    binding.imCheckEmail.visibility = View.INVISIBLE
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val password = binding.etPassword.text.toString()
                if (password.isNotEmpty()) {
                    checkPasswords()
                } else {
                    isPasswordOkey = false
                    binding.imCheckPassword.visibility = View.INVISIBLE
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.bttnLogin.setOnClickListener {
            if(isEmailOkey && isPasswordOkey){
                runLogin()
            }
        }

        return binding.root
    }

    private fun checkEmailAddress() {
        val patternMatches = Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text).matches()
        setTickOrCross(binding.imCheckEmail, patternMatches)
        isEmailOkey = patternMatches
    }

    private fun checkPasswords() {
        val password: String = binding.etPassword.text.toString()
        if (password.isNotEmpty()) {
            val passwordValid = isValidPassword(password)
            setTickOrCross(binding.imCheckPassword, passwordValid)
            modifyAlert(passwordValid)
            isPasswordOkey = passwordValid
        } else {
            isPasswordOkey = false
        }
    }

    private fun setTickOrCross(imageView: ImageView, isCorrect: Boolean) {
        imageView.visibility = View.VISIBLE
        if (isCorrect) {
            imageView.setImageResource(R.drawable.ic_baseline_check_32)
        } else {
            imageView.setImageResource(R.drawable.ic_baseline_clear_32)
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val pattern: Pattern
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$"
        pattern = Pattern.compile(passwordPattern)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    private fun modifyAlert(isPatternCorrect: Boolean) {
        if (isPatternCorrect){
            binding.tvAlerts.visibility = View.INVISIBLE
        }else{
            binding.tvAlerts.visibility = View.VISIBLE
        }
    }

    private fun runLogin(){
        binding.apply {
            if (etEmail.text!!.isNotEmpty() && etEmail.text!!.isNotBlank()
                && etPassword.text!!.isNotEmpty() && etPassword.text!!.isNotBlank()){
                startActivity(Intent(layoutInflater.context, MainActivity::class.java))
            }else{
                Toast.makeText(layoutInflater.context, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}