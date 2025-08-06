package com.project1_example.Controller;


import com.project1_example.Dao.FirebaseInitializer;
import com.project1_example.Model.Complaint;

import javafx.scene.control.Alert;
import javafx.application.Platform;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;


import java.util.concurrent.ExecutionException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FirebaseAuthService {

    private static final String API_KEY = "AIzaSyCrnywAXM6F9NTBlISp2x7zsn75eJxeRCo";
    // private static final String BUCKET_NAME = "nagriknazar-37560.firebasestorage.app";


    private static final String PROJECT_ID = "nagriknazar-37560";

    public static boolean authenticateUser(String email, String password) {
        // You cannot verify passwords directly using Admin SDK.
        // Instead, use REST API for password sign-in (Firebase Auth REST API)

        try {
            // Normally you'd make a REST request here
            // For now, simulate a successful login
            UserRecord user = FirebaseAuth.getInstance().getUserByEmail(email);
            return user != null;
        } catch (FirebaseAuthException e) {
            System.err.println("❌ Auth error: " + e.getMessage());
            return false;
        }
    }

    public static boolean loginUser(String email, String password) {
        try {
            URL url = new URL("hhttps://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            String payload = String.format("{\"email\":\"%s\", \"password\":\"%s\", \"returnSecureToken\":true}", email,
                    password);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                // Login successful
                return true;
            } else {
                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
                    String error = scanner.useDelimiter("\"").next();
                    System.err.println("❌ Login failed: " + error);
                }
                return false;
            }

        } catch (Exception e) {
            System.err.println("❌ Error logging in: " + e.getMessage());
            return false;
        }
    }

    public static boolean signUpUser(String email, String password) {
        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            String payload = String.format("{\"email\":\"%s\", \"password\":\"%s\", \"returnSecureToken\":true}", email,
                    password);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return true;
            } else {
                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
                    String error = scanner.useDelimiter("\"").next();
                    System.err.println("❌ Signup failed: " + error);
                }
                return false;
            }

        } catch (Exception e) {
            System.err.println("❌ Error signing up: " + e.getMessage());
            return false;
        }
    }

    public static boolean sendPasswordResetEmail(String email) {
        try {
            String firebaseResetUrl = "https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=" + API_KEY;

            String payload = String.format("{\"requestType\":\"PASSWORD_RESET\",\"email\":\"%s\"}", email);

            URL url = new URL(firebaseResetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and print success response (optional)
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    while (scanner.hasNext()) {
                        System.out.println(scanner.nextLine());
                    }
                }
                return true;
            } else {
                // Read error response (optional)
                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
                    while (scanner.hasNext()) {
                        System.err.println(scanner.nextLine());
                    }
                }
                return false;
            }

        } catch (Exception e) {
            System.err.println("Exception while sending reset email: " + e.getMessage());
            return false;
        }
    }

  


   
    public static boolean signInWithEmailAndPassword(String email, String password) {
        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email,
                    password);
            OutputStream os = null;
            os = conn.getOutputStream();
            os.write(payload.getBytes());
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return true;
            } else {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }

                }
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }
    }

    static String adduserToFirestore(String fname, String lname, String phone, String address, String city,
            String pincode) {
        if (fname.isEmpty() || lname.isEmpty() || phone.isEmpty() || address.isEmpty() || city.isEmpty()
                || pincode.isEmpty()) {
            return "Form Error! Please fill all the fields.";
        }

        String endpoint1 = String.format(
                "https://firestore.googleapis.com/v1/projects/%s/databases/(default)/documents/users?key=%s",
                PROJECT_ID, API_KEY);
        String payload = String.format("{\"fields\":{" + "\"fname1\":{\"stringValue\":\"%s\"},"
                + "\"lname1\":{\"stringValue\":\"%s\"}," + "\"phone1\":{\"integerValue\":\"%s\"}"
                + "\"address1\":{\"stringValue\":\"%s\"}," + "\"pincode1\":{\"integerValue\":\"%s\"}" + "}}", fname,
                lname, phone, address, city, pincode);

        try {
            URL url = new URL(endpoint1);
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

        } catch (Exception ex) {
            return "Error:" + ex.getMessage();
        }

    }

    public boolean submitIssue(String category, String type, String description, String address,
            String landmark, File selectedFile) {
        try {
            FirebaseInitializer.initialize();
            System.out.println("*******************in submit issue*************");
            String fileUrl = null;
            if (selectedFile != null) {
                fileUrl = uploadFileToStorage(selectedFile);
                System.out.println(fileUrl);
            }

            Map<String, Object> issueData = new HashMap<>();
            issueData.put("category", category);
            issueData.put("type", type);
            issueData.put("description", description);
            issueData.put("address", address);
            issueData.put("landmark", landmark);
            issueData.put("timestamp", new Date());
            if (fileUrl != null) {
                issueData.put("attachmentUrl", fileUrl);
            }
            System.out.println("*******************in  issue*************");

            Firestore db = FirestoreClient.getFirestore();
            db.collection("Complaints").add(issueData);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String uploadFileToStorage(File file) throws Exception {
        Storage storage = StorageOptions.getDefaultInstance().getService();

        String fileName = file.getName();

        BlobId blobId = BlobId.of("nagriknazar-37560.firebasestorage.app.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("media")
                .build();

        storage.create(blobInfo, new FileInputStream(file));

        return "https://firebasestorage.googleapis.com/v0/b/nagriknazar-37560.firebasestorage.app.com/o/"
                + URLEncoder.encode(fileName, "UTF-8") + "?alt=media";
    }




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

    public static FirebaseDatabase getInstance() {
        return null;
    }



public static Firestore getFirestoreObject() {
    return FirestoreClient.getFirestore();
}


public static void updateStatusInFirestore(Complaint complaint, String newStatus) {
    Firestore db = FirebaseInitializer.getFirestore();

       if (complaint.getDocumentId() == null || complaint.getDocumentId().isEmpty()) {
        Platform.runLater(() -> showAlert("Error", "Complaint Document ID is missing. Cannot update Firestore."));
        return;
    }

    Map<String, Object> updates = new HashMap<>();
    updates.put("status", newStatus);

    ApiFuture<WriteResult> future = db.collection("Complaints") // ✅ Use correct case
            .document(complaint.getDocumentId())
            .update(updates);

    try {
        WriteResult result = future.get();
        System.out.println("✅ Status updated at: " + result.getUpdateTime());
    } catch (Exception e) {
        System.out.println("❌ Error updating status: " + e.getMessage());
        e.printStackTrace();
    }
}
private static void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
}


public void updateComplaintStatus(Complaint complaint, String newStatus, String newApproval) {
    Firestore db = FirestoreClient.getFirestore();

    if (complaint.getId() == null || complaint.getId().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Complaint ID missing", "Cannot update complaint without an ID.");
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
            WriteResult result = future.get();
            Platform.runLater(() -> showAlert(Alert.AlertType.INFORMATION,
                    "Success", "Complaint updated at: " + result.getUpdateTime()));
        } catch (InterruptedException | ExecutionException e) {
            Platform.runLater(() -> showAlert(Alert.AlertType.ERROR,
                    "Update Failed", e.getMessage()));
        }
    }).start();
}
public void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


}
