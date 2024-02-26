package com.example.details.service;

import com.example.act.model.ActVO;
import com.example.details.dto.ActDTO;
import com.example.details.dto.ActRandomDTO;

import java.util.List;

public interface IRetailsService {
    ActDTO getDetail(Integer actId);

    List<ActRandomDTO> randomFourAct(Integer actTypeId, Integer actId);

}
