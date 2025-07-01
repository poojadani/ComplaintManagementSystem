package com.example.ComplaintSystem.Repository;

import com.example.ComplaintSystem.Entity.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepo extends JpaRepository<ComplaintEntity, Integer> {
}
