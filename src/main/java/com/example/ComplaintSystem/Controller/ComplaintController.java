package com.example.ComplaintSystem.Controller;

import com.example.ComplaintSystem.DTO.ComplaintDTO;
import com.example.ComplaintSystem.Entity.ComplaintEntity;
import com.example.ComplaintSystem.Helper.ComplaintStatus;
import com.example.ComplaintSystem.Service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/complaints")
public class ComplaintController
{
    @Autowired
    ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/raiseCom")
    public ResponseEntity<ComplaintEntity> raiseComplaint(@RequestBody ComplaintDTO complaintDTO) {
        //return ResponseEntity.ok(complaintService.raiseComplaint(complaintDTO));
        return new ResponseEntity<>(complaintService.raiseComplaint(complaintDTO) , CREATED);

    }

    @GetMapping("/getCom")
    public ResponseEntity<List<ComplaintEntity>> getAllComplaintList()
    {
        return new ResponseEntity<>(complaintService.getAllComplaintsList(), HttpStatus.FOUND);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ComplaintEntity> updateStatus(@PathVariable Integer id, @RequestParam ComplaintStatus status) throws IllegalAccessException {
        return ResponseEntity.ok(complaintService.updateStatus(id, status));
    }

    @GetMapping("/status-count")
    public ResponseEntity<Map<ComplaintStatus,Long>> getStatusCount()
    {
        return ResponseEntity.ok(complaintService.getStatusCount());
    }
}
