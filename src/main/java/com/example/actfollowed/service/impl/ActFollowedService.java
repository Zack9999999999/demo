package com.example.actfollowed.service.impl;

import com.example.actfollowed.dto.ActFollowRequest;
import com.example.actfollowed.model.ActFollowedVO;
import com.example.actfollowed.repository.ActFollowedRepository;
import com.example.actfollowed.service.IActFollowedService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActFollowedService implements IActFollowedService {

    @Autowired
    private ActFollowedRepository actFollowedRepository;

    @Override
    public Byte getActFollows(Integer actId, Integer memId) {
        ActFollowedVO actFollowed = actFollowedRepository.findByActIdAndMemId(actId, memId).orElse(null);
        if (actFollowed != null) {
            return actFollowed.getFolStatus();
        } else {
            return null;
        }
    }

    @Override
    public ActFollowedVO createActFollow(ActFollowRequest actFollowRequest) {

        ActFollowedVO actFollowed = new ActFollowedVO();
        BeanUtils.copyProperties(actFollowRequest, actFollowed);

        return actFollowedRepository.save(actFollowed);
    }

    @Override
    public ActFollowedVO updateActFollow(ActFollowRequest actFollowRequest) {

        ActFollowedVO actFollowed = new ActFollowedVO();
        BeanUtils.copyProperties(actFollowRequest, actFollowed);

        return actFollowedRepository.save(actFollowed);
    }


}
