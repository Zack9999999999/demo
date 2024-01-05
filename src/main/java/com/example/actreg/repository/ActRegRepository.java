package com.example.actreg.repository;

import com.example.actreg.model.ActRegVO;
import com.github.houbb.heaven.annotation.reflect.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActRegRepository extends JpaRepository<ActRegVO, Integer> {

    @Query("SELECT a FROM ActRegVO a JOIN FETCH a.act WHERE a.actRegId = :actRegId")
    Optional<ActRegVO> findByActRegIdAndFetchActEagerly(@Param("actRegId") Integer actRegId);
}
