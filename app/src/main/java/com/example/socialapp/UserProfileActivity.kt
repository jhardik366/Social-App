package com.example.socialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        display(currentUser)
    }

    private fun display(firebaseUser: FirebaseUser?) {
        if (firebaseUser != null) {
            val name = firebaseUser.displayName
            val email = firebaseUser.email
            val imageUrl = firebaseUser.photoUrl
            Glide.with(this).load(imageUrl).circleCrop().into(profilePicture)
            userName.text = name
            userEmail.text = email
        }
    }

}