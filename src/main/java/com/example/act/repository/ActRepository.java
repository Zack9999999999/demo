package com.example.act.repository;

import com.example.act.model.ActVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActRepository extends JpaRepository<ActVO, Integer> {
}
