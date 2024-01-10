package com.example.actfollowed.service;

import com.example.actfollowed.dto.ActFollowRequest;
import com.example.actfollowed.model.ActFollowedVO;

public interface IActFollowedService {

    ActFollowedVO createActFollow(ActFollowRequest actFollowRequest);

    ActFollowedVO updateActFollow(ActFollowRequest actFollowRequest);
}
