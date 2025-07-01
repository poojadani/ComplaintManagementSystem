package com.example.ComplaintSystem.DTO;

import com.example.ComplaintSystem.Helper.ComplaintStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

public class ComplaintDTO
{
    //public Object ComplaintStatus;

    private String customerName;
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status=ComplaintStatus.OPEN;

    private LocalDate raisedOn=LocalDate.now();
    private LocalDate resolvedOn;
    private long resolutionTime;

    public long getResolutionTime() {
        return resolutionTime;
    }

    public void setResolutionTime(long resolutionTime) {
        this.resolutionTime = resolutionTime;
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

    public LocalDate getRaisedOn() {
        return raisedOn;
    }

    public void setRaisedOn(LocalDate raisedOn) {
        this.raisedOn = raisedOn;
    }

    public LocalDate getResolvedOn() {
        return resolvedOn;
    }

    public void setResolvedOn(LocalDate resolvedOn) {
        this.resolvedOn = resolvedOn;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

}
