package com.example.actfollowed.service;

import com.example.actfollowed.dto.ActFollowDTO;
import com.example.actfollowed.dto.ActFollowRequest;
import com.example.actfollowed.model.ActFollowedVO;

import java.util.List;

public interface IActFollowedService {

    List<ActFollowedVO> getActFollows(Integer memId);

    Byte getActFollows(Integer actId,  Integer memId);

    ActFollowedVO createActFollow(ActFollowRequest actFollowRequest);

    ActFollowedVO updateActFollow(ActFollowRequest actFollowRequest);
}
