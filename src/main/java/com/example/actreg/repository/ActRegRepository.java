package com.example.actreg.repository;

import com.example.actreg.dto.ActRegDTO;
import com.example.actreg.model.ActRegVO;
import com.github.houbb.heaven.annotation.reflect.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActRegRepository extends JpaRepository<ActRegVO, Integer> {

    ActRegVO findByAct_ActIdAndMemId(Integer actId, Integer memId);

    @Query(value = "SELECT m.mem_name, m.mem_pic FROM activity_registration r" +
            " JOIN membership m ON r.mem_id = m.mem_id" +
            " WHERE r.act_id = :actId AND r.is_act_part = :isActPart", nativeQuery = true)
    List<Object[]> findMembersAndPicByPart(@Param("actId") Integer actId, @Param("isActPart") Integer isActPart);

    @Query("SELECT r FROM ActRegVO r JOIN r.act a WHERE r.memId = :memId")
    List<ActRegVO> findRegByMemId(@Param("memId") Integer memId);
}
