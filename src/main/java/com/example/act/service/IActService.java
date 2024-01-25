package com.example.act.service;

import com.example.act.dto.ActQueryParams;
import com.example.act.model.ActVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IActService {

    Page<ActVO> reviewActs(ActQueryParams actQueryParams, Pageable pageable);

    ActVO deleteActReg(Integer actId);
}
