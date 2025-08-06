package com.project1_example.Dao;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseUtil {
       private static final String PROJECT_ID = "nagriknazar-37560";
    private static final String API_KEY = "AIzaSyCrnywAXM6F9NTBlISp2x7zsn75eJxeRCo";
  public static String addUserToFirestoreViewReport(String id, String category, String complaintType, String description,
                     String address, String landmark, String filename,
                     String status, String approval){
      
        if (category.isEmpty() || complaintType.isEmpty()||description.isEmpty()||address.isEmpty()||landmark.isEmpty()||filename.isEmpty()||status.isEmpty()||approval.isEmpty()) {
            return "Please enter data";
        }
        String endpoint = String.format(
                "https://firestore.googleapis.com/v1/projects/c2w-demo-cb830/databases/(default)/documents/users?key=%s",
                PROJECT_ID, API_KEY);

       String payload = String.format("""
            {
              "fields": {
                "id": { "stringValue": "%s" },
                "category": { "stringValue": "%s" },
                "complaintType": { "stringValue": "%s" },
                "description": { "stringValue": "%s" },
                "address": { "stringValue": "%s" },
                "landmark": { "stringValue": "%s" },
                "filename": { "stringValue": "%s" },
                "status": { "stringValue": "%s" },
                "approval": { "stringValue": "%s" }
              }
            }
            """, id, category, complaintType, description, address, landmark, filename, status, approval);
        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }
            InputStream is = conn.getInputStream();
            byte[] responseBytes = is.readAllBytes();
            String response = new String(responseBytes, StandardCharsets.UTF_8);
            is.close();
            conn.disconnect();
            return "Added! Firestore says:\n" + response;
        } catch (Exception e) {
            return "Error:" + e.getMessage();
        }
    }

    public static Firestore getFirestoreObject(){
        return FirestoreClient.getFirestore();
    }
}
