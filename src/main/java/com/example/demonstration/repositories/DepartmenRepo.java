package com.example.demonstration.repositories;


import com.example.demonstration.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmenRepo extends JpaRepository<Department, UUID> {
}
