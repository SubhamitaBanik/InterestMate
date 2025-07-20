package com.example.interestmate.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtils {

    fun currentUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }

    fun currentUserRef(): DatabaseReference? {
        return currentUserId()?.let {
            FirebaseDatabase.getInstance().getReference("users").child(it)
        }
    }

    fun groupRef(): DatabaseReference {
        return FirebaseDatabase.getInstance().getReference("groups")
    }

    fun messageRef(groupId: String): DatabaseReference {
        return FirebaseDatabase.getInstance()
            .getReference("messages")
            .child(groupId)
    }

    fun createGroup(groupName: String, description: String, creatorId: String): DatabaseReference {
        val groupId = groupRef().push().key ?: return groupRef()
        val newGroup = hashMapOf(
            "groupName" to groupName,
            "description" to description,
            "members" to mapOf(creatorId to true)
        )
        groupRef().child(groupId).setValue(newGroup)
        return groupRef().child(groupId)
    }

    fun joinGroup(groupId: String, userId: String) {
        groupRef().child(groupId).child("members").child(userId).setValue(true)
    }
}
