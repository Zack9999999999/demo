package com.example.details.repository;

import com.example.act.model.ActVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailsRepository extends JpaRepository<ActVO, Integer> {
}
