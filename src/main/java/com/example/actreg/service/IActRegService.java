package com.example.actreg.service;

import com.example.actreg.dto.ActRegQueryParams;
import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.dto.ActRegStatus;
import com.example.actreg.dto.MemNameAndPicDTO;
import com.example.actreg.model.ActRegVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IActRegService {

    Page<ActRegVO> getActRegs(Integer memId, ActRegQueryParams actRegQueryParams, Pageable pageable);

    ActRegVO getActReg(Integer actId, Integer memId);

    ActRegVO createActReg(ActRegRequest actRegRequest);

    ActRegVO updateActReg(ActRegStatus actRegStatus);

    List<MemNameAndPicDTO> findMemNameAndPic(Integer actId, Integer isActPart);
}
