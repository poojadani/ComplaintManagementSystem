package com.example.ComplaintSystem.Entity;


import com.example.ComplaintSystem.Helper.ComplaintStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Complaint")
@Getter
@Setter
public class ComplaintEntity
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int complaintID;
    private String customerName;
    private String issueDescription;
    private LocalDateTime raisedOn=LocalDateTime.now();
    private LocalDateTime resolvedOn;
    private long resolutionTime;


    @Enumerated(EnumType.STRING)
    private ComplaintStatus status=ComplaintStatus.OPEN;





    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public LocalDateTime getRaisedOn() {
        return raisedOn;
    }

    public void setRaisedOn(LocalDateTime raisedOn) {
        this.raisedOn = raisedOn;
    }

    public LocalDateTime getResolvedOn() {
        return resolvedOn;
    }

    public void setResolvedOn(LocalDateTime resolvedOn) {
        this.resolvedOn = resolvedOn;
    }

    public long getResolutionTime() {
        return resolutionTime;
    }

    public void setResolutionTime(long resolutionTime) {
        this.resolutionTime = resolutionTime;
    }
}

