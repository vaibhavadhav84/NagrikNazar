package com.project1_example.Dao;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.Map;

public class FirestoreService {

    public static void saveUserData(String uid, String name, String email) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(uid);

        Map<String, Object> data = new HashMap<>();
        data.put("uid", uid);
        data.put("name", name);
        data.put("email", email);

        docRef.set(data).addListener(() -> System.out.println("✅ User data saved to Firestore."),
                command -> System.err.println("❌ Failed to save user data."));
    }
}

