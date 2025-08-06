package com.project1_example.Dao;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseInitializer {
    private static GoogleCredentials credentials;
    private static final String BUCKET_NAME = "nagriknazar-37560.appspot.com";
    public static Storage storage;

    public static void initialize() {
        try {
            // ✅ Use the input stream only once
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase.json");
            credentials = GoogleCredentials.fromStream(serviceAccount);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setDatabaseUrl("https://nagriknazar-37560-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .setStorageBucket(BUCKET_NAME)
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // ✅ Use the same credentials object
            storage = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();

            System.out.println("✅ Firebase initialized");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Failed to initialize Firebase");
        }

    }

    public static FirebaseDatabase getDatabase() {
        return FirebaseDatabase.getInstance();
    }

    public static GoogleCredentials getCredentials() {
        return credentials;
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

}
