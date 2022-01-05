package com.example.bv_connect

import android.app.TaskStackBuilder
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.*
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    lateinit var etName: EditText
    private lateinit var etPass: EditText
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )
    private val EMAIL_PATTERN: Pattern = Pattern.compile(
        "^" +
                "([@])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )
    private val ID_PATTERN: Pattern = Pattern.compile(
        "([a-zA-Z])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{3,}" +  // at least 4 characters
                "$"

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        name.visibility = View.INVISIBLE
        id.visibility = View.INVISIBLE
        signin.setOnClickListener {
            signin.setTextColor(Color.parseColor("#FFFFFF"))
            signin.setBackgroundColor(Color.parseColor("#FF2729C3"))
            signup.setTextColor(Color.parseColor("#FF2729C3"))
            signup.setBackgroundResource(R.drawable.bordershape)
            circleImageView.setImageResource(R.drawable.sigin_boy_img)
            signin_signup_txt.text = "Sign In"
            signin_signup_btn.text = "Sign In"
            name.visibility = View.INVISIBLE
            id.visibility = View.INVISIBLE
            forgot_password.visibility = View.VISIBLE
            signin_signup_btn.setOnClickListener{
                login()
            }
            forgot_password.setOnClickListener{
                password.visibility = View.INVISIBLE
                pd.visibility = View.INVISIBLE
                forgot_password.visibility = View.INVISIBLE
                signin_signup_txt.text="Forgot Password"
                signin_signup_btn.text="Reset Password"
                //resetpassword()
            }

        }
        signup.setOnClickListener {
            signup.setTextColor(Color.parseColor("#FFFFFF"))
            signup.setBackgroundColor(Color.parseColor("#FF2729C3"))
            signin.setTextColor(Color.parseColor("#FF2729C3"))
            signin.setBackgroundResource(R.drawable.bordershape)
            circleImageView.setImageResource(R.drawable.sigup_boy_img)
            signin_signup_txt.text = "Sign Up"
            signin_signup_btn.text = "Sign Up"
            name.visibility = View.VISIBLE
            id.visibility = View.VISIBLE
            forgot_password.visibility = View.INVISIBLE
            signin_signup_btn.setOnClickListener{
                signUpUser()
            }
        }
    }


    private fun resetpassword() {

        val email = etEmail.text.toString()
        if (email.isEmpty()) {
            etEmail.error = "This field is required"
            etEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Provide valid email address"
            etEmail.requestFocus()
            return
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this,
                    "Check your email address for password reset",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Something went wrong, Try again.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun login() {

        val email = etEmail.text.toString()
        val pass = etPass.text.toString()

        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Fields are mandatory", Toast.LENGTH_SHORT).show()
        }
        else
            if (!PASSWORD_PATTERN.matcher(pass).matches()) {
                Toast.makeText(this, "Password is too weak !", Toast.LENGTH_SHORT)
                    .show()

            }

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(Intent(this, LoginActivity::class.java))
                    .addNextIntent(Intent(this, MainActivity::class.java))
                    .startActivities()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val name = etName.text.toString()
        val id = etConfPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || id.isBlank()|| name.isBlank()) {
            Toast.makeText(this, "Fields are mandatory", Toast.LENGTH_SHORT).show()

        }
        else {
            if (!PASSWORD_PATTERN.matcher(pass).matches()) {
                Toast.makeText(this, "Password is too weak !", Toast.LENGTH_SHORT)
                    .show()

            }
            if (!EMAIL_PATTERN.matcher(pass).matches()) {
                Toast.makeText(this, "Wrong email address!", Toast.LENGTH_SHORT)
                    .show()

            }
            if (!ID_PATTERN.matcher(pass).matches()) {
                Toast.makeText(this, "Wrong student id!", Toast.LENGTH_SHORT)
                    .show()

            }
        }

        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(Intent(this, LoginActivity::class.java))
                    .addNextIntent(Intent(this, MainActivity::class.java))
                    .startActivities()

            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}