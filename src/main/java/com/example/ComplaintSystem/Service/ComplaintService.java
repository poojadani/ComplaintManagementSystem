package com.example.ComplaintSystem.Service;

import com.example.ComplaintSystem.DTO.ComplaintDTO;
import com.example.ComplaintSystem.Entity.ComplaintEntity;
import com.example.ComplaintSystem.Helper.ComplaintStatus;
import com.example.ComplaintSystem.Repository.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComplaintService
{
    @Autowired
    private ComplaintRepo complaintRepo;
    public ComplaintEntity raiseComplaint(ComplaintDTO complaintDTO) {
        ComplaintEntity complaintEntity=new ComplaintEntity();
        complaintEntity.setCustomerName(complaintDTO.getCustomerName());
        complaintEntity.setStatus(ComplaintStatus.OPEN);
        complaintEntity.setRaisedOn(LocalDateTime.now());
      //  complaintEntity.setResolvedOn(null);
        return complaintRepo.save(complaintEntity);

    }

    public List<ComplaintEntity> getAllComplaintsList() {
        return complaintRepo.findAll();
    }

    public ComplaintEntity updateStatus(Integer id, ComplaintStatus newstatus) throws IllegalAccessException {
        ComplaintEntity complaintEntity = complaintRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        ComplaintStatus currentStatus = complaintEntity.getStatus();

        //For resolved Complaints
        if (currentStatus == ComplaintStatus.Resolved) {
            throw new IllegalAccessException("Sorry!! Cannot change status for already resolved complaints");
        }

        //For Open and In-Progress complaints
        if (currentStatus==ComplaintStatus.OPEN  || newstatus==ComplaintStatus.IN_Progress)
        {
            complaintEntity.setStatus(newstatus);
        }
        else if (currentStatus==ComplaintStatus.IN_Progress  || newstatus==ComplaintStatus.Resolved)
        {
            complaintEntity.setStatus(newstatus);
            complaintEntity.setResolvedOn(LocalDateTime.now());

        } else if (newstatus==ComplaintStatus.Resolved)
        {
            LocalDateTime resolvedDate= LocalDateTime.now();
            complaintEntity.setResolvedOn(resolvedDate);
            long durationvar= ChronoUnit.MINUTES.between(resolvedDate,complaintEntity.getRaisedOn());
            complaintEntity.setResolutionTime(durationvar);

        } else
        {
            throw new IllegalAccessException("Invalid status transition");
        }
        return complaintRepo.save(complaintEntity);
    }

    public Map<ComplaintStatus, Long> getStatusCount() {
        return complaintRepo.findAll().stream().collect(Collectors.groupingBy(ComplaintEntity::getStatus,Collectors.counting()));
    }

//    public void calculateDuration() {
//        if (raisedOn != null && resolvedOn != null) {
//            Duration duration = Duration.between(raisedOn, resolvedOn);
//            long hours = duration.toHours();
//            long minutes = duration.toMinutesPart();
//            long seconds = duration.toSecondsPart();
//            this.resolutionTime = hours + "h " + minutes + "m " + seconds + "s";
//        } else {
//            this.resolutionTime = "Not yet resolved";
//        }
//    }
}
