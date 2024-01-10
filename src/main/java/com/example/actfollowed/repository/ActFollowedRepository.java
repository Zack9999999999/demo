package com.example.actfollowed.repository;

import com.example.actfollowed.model.ActFollowed;
import com.example.actfollowed.model.ActFollowedVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActFollowedRepository extends JpaRepository<ActFollowedVO, ActFollowed> {
}
