package com.example.details.service.impl;

import com.example.act.model.ActVO;
import com.example.details.dao.IRetailsDAO;
import com.example.details.dto.ActDTO;
import com.example.details.dto.ActRandomDTO;
import com.example.details.service.IRetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetailsService implements IRetailsService {

    @Autowired
    private IRetailsDAO retailsDAO;

    @Override
    public ActDTO getDetail(Integer actId) {
        return retailsDAO.getDetail(actId);
    }

    @Override
    public List<ActRandomDTO> randomFourAct() {

        //寫radom亂數抓4個活動出來

        return retailsDAO.randomFourAct();
    }
}
