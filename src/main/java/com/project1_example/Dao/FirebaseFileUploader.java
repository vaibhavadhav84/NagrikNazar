package com.project1_example.Dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.FirestoreClient;
import com.project1_example.Model.Complaint;
import com.google.cloud.firestore.WriteResult;
import java.util.concurrent.ExecutionException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FirebaseFileUploader {
    private static final String BUCKET_NAME = "nagriknazar-37560.firebasestorage.app";
    private static final String PROJECT_ID = "nagriknazar-37560";
    private static final String API_KEY = "AIzaSyCrnywAXM6F9NTBlISp2x7zsn75eJxeRCo";

 public static String uploadFileToFirebase(File file, String category, String type, String description, String address,
                                          String landmark, File selectedFile) throws IOException {

    if (file == null || !file.exists()) {
        throw new IllegalArgumentException("File is null or does not exist.");
    }

    // 🔽 Step 1: Upload the file to Firebase Storage
    String fileName = "complaints/" + System.currentTimeMillis() + "_" + file.getName();
    BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
            .setContentType("image")
            .build();

    byte[] fileBytes = Files.readAllBytes(file.toPath());
    FirebaseInitializer.storage.create(blobInfo, fileBytes);

    String fileUrl = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/" +
            fileName.replace("/", "%2F") + "?alt=media";

    System.out.println("✅ Uploaded image URL: " + fileUrl);

    // 🔽 Step 2: Create complaint data to store in Firestore
    Map<String, Object> issueData = new HashMap<>();
    issueData.put("category", category);
    issueData.put("complainttype", type);
    issueData.put("description", description);
    issueData.put("address", address);
    issueData.put("landmark", landmark);
    issueData.put("timestamp", new Date());
    issueData.put("attachmentUrl", fileUrl);  // ✅ Store URL instead of file object

    // 🔽 Step 3: Store complaint in Firestore
    Firestore db = FirestoreClient.getFirestore();
    db.collection("Complaints").add(issueData);

    return fileUrl;
}

   public static void uploadComplaintToFirebase(File file, Complaint complaint) throws IOException {
    // Upload image first
    String fileName = "complaints/" + System.currentTimeMillis() + "_" + file.getName();
    BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();

    byte[] fileBytes = Files.readAllBytes(file.toPath());
    FirebaseInitializer.storage.create(blobInfo, fileBytes);

    // Create public image URL
    String imageUrl = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/" +
                      fileName.replace("/", "%2F") + "?alt=media";

    // Set image URL in complaint object
    complaint.setFilename(imageUrl);

    // Default values
    complaint.setStatus("Pending");
    complaint.setApproval("Pending");

    // Push to Firestore
    Firestore db = FirestoreClient.getFirestore();
    ApiFuture<DocumentReference> future = db.collection("Complaints").add(complaint);

    // Store generated ID in complaint document
    future.addListener(() -> {
        try {
            String id = future.get().getId();
            db.collection("Complaints").document(id).update("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }, Runnable::run);
}



public void updateComplaintStatus(Complaint complaint, String newStatus, String newApproval) {
    Firestore db = FirestoreClient.getFirestore();

    if (complaint.getId() == null || complaint.getId().isEmpty()) {
        System.out.println("❌ Complaint ID is missing. Cannot update.");
        return;
    }

    Map<String, Object> updates = new HashMap<>();
    updates.put("status", newStatus);
    updates.put("approval", newApproval);

    ApiFuture<WriteResult> future = db.collection("complaints")
                                      .document(complaint.getId())
                                      .update(updates);

    new Thread(() -> {
        try {
            WriteResult result = future.get(); // This blocks until the update completes
            System.out.println("✅ Complaint updated at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("❌ Update failed: " + e.getMessage());
        }
    }).start();
}


    // Update user
    static String updateUserToFirestore(String docId, String name, String rank) {
        if (docId.isEmpty() || name.isEmpty() || rank.isEmpty()) {
            return "Enter docID,name and rank to update.";
        }
        String endpoint = String.format(
                "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/users/%s?key=%s",
                PROJECT_ID, docId, API_KEY);

        String payload = String.format(
                "{\"fields\":{\"name\":{\"stringValue\":\"%s\"},\"rank\":{\"integerValue\":\"%s\"}}}", name, rank);

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
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
            return "Updated! Firestore says:\n" + response;
        } catch (Exception e) {
            return "Error:" + e.getMessage();
        }
    }

    // Delete User
    static String deleteUserToFirestore(String docId) {
        if (docId.isEmpty()) {
            return "Enter doc ID to delete.";
        }
        String endpoint = String.format(
                "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/users/%s?key=%s",
                PROJECT_ID, docId, API_KEY);

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            conn.disconnect();
            if (responseCode == 200) {
                return "Deleted successfully! :\n" + responseCode;
            } else {
                return "Added! Firestore says:\n" + responseCode;

            }

        } catch (Exception e) {
            return "Error:" + e.getMessage();
        }
    }

  public static Firestore getFirestoreObject() {
    return FirestoreClient.getFirestore();
}




}
