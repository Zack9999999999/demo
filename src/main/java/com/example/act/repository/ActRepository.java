package com.example.act.repository;

import com.example.act.model.ActVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActRepository extends JpaRepository<ActVO, Integer> {

    Page<ActVO> findByMemId(Integer memId, Pageable pageable);

    Page<ActVO> findByMemIdAndActStatus(Integer memId,Byte actStatus, Pageable pageable);

}
