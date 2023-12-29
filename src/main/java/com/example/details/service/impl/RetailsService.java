package com.example.details.service.impl;

import com.example.act.model.ActVO;
import com.example.details.repository.RetailsRepository;
import com.example.details.service.IRetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetailsService implements IRetailsService {
    @Autowired
    private RetailsRepository retailsRepository;

    @Override
    public ActVO getRetail(Integer actId) {
        ActVO act = retailsRepository.findById(actId).orElse(null);
        return act;
    }
}
