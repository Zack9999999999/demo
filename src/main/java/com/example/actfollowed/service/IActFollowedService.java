package com.example.actfollowed.service;

import com.example.actfollowed.dto.ActFollowRequest;
import com.example.actfollowed.model.ActFollowedVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IActFollowedService {

    Page<ActFollowedVO> getActFollows(Integer memId,  Pageable pageable);

    Byte getActFollows(Integer actId,  Integer memId);

    ActFollowedVO createActFollow(ActFollowRequest actFollowRequest);

    ActFollowedVO updateActFollow(ActFollowRequest actFollowRequest);
}
