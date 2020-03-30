package com.example.up.loginSignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.up.R
import com.example.up.navigation.MenuNavActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        btn_log_in.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MenuNavActivity::class.java))
        }

        tv_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, Signup::class.java))
        }

       /* mAuth = FirebaseAuth.getInstance()

        button_sign_in.setOnClickListener{
            val email = et_email_login.text.toString().trim()
            val password = et_password_login.text.toString().trim()

            if(email.isEmpty()){
                et_email_login.error = "Email Required"
                et_email_login.requestFocus()
                return@setOnClickListener

            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email_login.error = "Valid Email Required"
                et_email_login.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty() || password.length < 6){
                et_password_login.error = "6 char password is required"
                et_password_login.requestFocus()
                return@setOnClickListener
            }
            loginUser(email, password)
        }

        text_view_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, Signup::class.java))
        }*/
    }

    /*private fun loginUser(email: String, password: String) {
        progressbar.visibility = View.VISIBLE
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                progressbar.visibility = View.GONE
                if(task.isSuccessful){
                    login()

                }else{
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
