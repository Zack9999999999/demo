package com.example.actreg.repository;

import com.example.actreg.model.ActRegVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActRegRepository extends JpaRepository<ActRegVO, Integer> {
}
