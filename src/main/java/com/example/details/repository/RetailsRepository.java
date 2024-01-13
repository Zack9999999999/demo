package com.example.details.repository;

import com.example.act.model.ActVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RetailsRepository extends JpaRepository<ActVO, Integer> {

//    SELECT m.mem_name
//    FROM activity a JOIN membership m ON a.mem_id = m.mem_id
//    WHERE act_id = 1

}
