package com.example.socialapp.Dao

import com.example.socialapp.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollections = db.collection("users")

    fun addUser(user: User?) {
        user?.let {
            // working on background thread, with the help of globalscope (coroutines)
            GlobalScope.launch(Dispatchers.IO) {
                usersCollections.document(user.uid).set(it)
            }
        }
    }

    fun getUserById(uid: String) : Task<DocumentSnapshot> {
        return usersCollections.document(uid).get()
    }
}