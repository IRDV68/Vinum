package com.m600.alumnos.cice.vinum

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth?.getCurrentUser()

        if (currentUser != null) {
            updateUI(currentUser)
        } else {
            mAuth?.signInAnonymously()?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth?.currentUser
                    user?.let { updateUI(it) }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    fun registrarUsuario(view: View) {

        val email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()
        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) {

                if (it.isSuccessful) {
                    Log.d("Tag", "createUserWithEmail:success")

                    val user = mAuth?.getCurrentUser()
                    updateUI(user)
                } else {
                    Log.w("Tag", "createUserWithEmail:failure", it.exception)
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show()
                    updateUI(null)
                }
            }
    }

    fun loginUsuario(view: View) {

        var email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()
        mAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Log.d("Tag", "createUserWithEmail:failure", it.exception)
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()

                }
            }
    }

    fun updateUI(usuario: FirebaseUser?) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
