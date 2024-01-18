package com.example.details.service;

import com.example.act.model.ActVO;
import com.example.details.dto.ActDTO;

public interface IRetailsService {
    ActDTO getDetail(Integer actId);

}
