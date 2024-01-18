package com.example.details.service.impl;

import com.example.act.model.ActVO;
import com.example.details.dao.IRetailsDAO;
import com.example.details.dto.ActDTO;
import com.example.details.service.IRetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetailsService implements IRetailsService {
//    @Autowired
//    private RetailsRepository retailsRepository;

    @Autowired
    private IRetailsDAO retailsDAO;

    @Override
    public ActDTO getDetail(Integer actId) {
        return retailsDAO.getDetail(actId);
    }

}
