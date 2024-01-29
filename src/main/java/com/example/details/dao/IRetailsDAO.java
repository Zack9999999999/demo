package com.example.details.dao;

import com.example.act.model.ActVO;
import com.example.details.dto.ActDTO;
import com.example.details.dto.ActRandomDTO;

import java.util.List;

public interface IRetailsDAO {

    ActDTO getDetail(Integer actId);

    List<ActRandomDTO> randomFourAct();
}
