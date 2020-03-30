package com.example.up.loginSignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.example.up.R
import com.example.up.navigation.MenuNavActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.et_password_signUp
import kotlinx.android.synthetic.main.activity_sign_up.progressbar
import kotlinx.android.synthetic.main.login.*

class Signup : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        /*mAuth = FirebaseAuth.getInstance()

        button_register.setOnClickListener{
            val email = et_email_signUp.text.toString().trim()
            val password = et_password_signUp.text.toString().trim()

            if(email.isEmpty()){
                et_email_signUp.error = "Email Required"
                et_email_signUp.requestFocus()
                return@setOnClickListener

            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email_signUp.error = "Valid Email Required"
                et_email_signUp.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty() || password.length < 6){
                et_password_signUp.error = "6 char password is required"
                et_password_signUp.requestFocus()
                return@setOnClickListener
            }

            registerUser(email, password)
        }*/

        btn_register_signUp.setOnClickListener {
            startActivity(Intent(this@Signup, MenuNavActivity::class.java))
        }

        tv_login.setOnClickListener {
            startActivity(Intent(this@Signup, LoginActivity::class.java))
        }
    }

    /*private fun registerUser(email: String, password: String) {
        progressbar.visibility = View.VISIBLE
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                progressbar.visibility = View.GONE
                if(task.isSuccessful){
                    //Registration success
                    login()
                }
                else{
                    task.exception?.message?.let{
                        toast(it)
                    }
                }
            }
    }

    override fun onStart(){
        super.onStart()

        mAuth.currentUser?.let{
            login()
        }
    }*/
}
