package com.example.details.dao;

import com.example.act.model.ActVO;
import com.example.details.dto.ActDTO;

public interface IRetailsDAO {

    ActDTO getDetail(Integer actId);
}
