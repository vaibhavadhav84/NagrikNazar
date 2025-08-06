package com.project1_example.Model;

import java.util.Date;

import com.google.cloud.Timestamp;
import com.google.firebase.database.PropertyName;

public class Complaint {

    private String id; // Firestore ID
    private String category;
    private String type; // complainttype in Firebase
    private String description;
    private String address;
    private String landmark;
    private String filename;
    private String status;
    private String approval;
    private String attachmentUrl;
    private String documentId; // Firestore doc ID, set manually after fetch
    private Timestamp timestamp;
     private Timestamp statusUpdatedAt;

    // No-arg constructor for Firebase
    public Complaint() {
    }

    // Full constructor
 public Complaint(String id, String category, String type, String description,
                 String address, String landmark, String filename,
                 String status, String approval) {
    this.id = id;
    this.category = category;
    this.type = type;
    this.description = description;
    this.address = address;
    this.landmark = landmark;
    this.filename = filename;
    this.status = status;
    this.approval = approval;
}

    // Firebase aliases for `complainttype`
    @PropertyName("complainttype")
    public void setComplainttype(String complainttype) {
        this.type = complainttype;
    }

    @PropertyName("complainttype")
    public String getComplainttype() {
        return this.type;
    }

    // Optional alias for consistency (if stored as 'complaintId' in Firebase)
    @PropertyName("complaintId")
    public void setComplaintId(String complaintId) {
        this.id = complaintId;
    }

    @PropertyName("complaintId")
    public String getComplaintId() {
        return this.id;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Date getDate() {
        return timestamp != null ? timestamp.toDate() : null;
    }
       public Timestamp getStatusUpdatedAt() {
        return statusUpdatedAt;
    }

    public void setStatusUpdatedAt(Timestamp statusUpdatedAt) {
        this.statusUpdatedAt = statusUpdatedAt;
    }

    // Complaint.java
private String rejectionReason;          // NEW

public String getRejectionReason() {     // getters / setters
    return rejectionReason;
}
public void setRejectionReason(String rejectionReason) {
    this.rejectionReason = rejectionReason;
}

}
