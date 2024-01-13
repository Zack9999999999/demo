package com.example.actreg.service;

import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.dto.ActRegStatus;
import com.example.actreg.dto.MemNameAndPicDTO;
import com.example.actreg.model.ActRegVO;

import java.util.List;

public interface IActRegService {

    List<ActRegVO> getActRegs();

    ActRegVO getActReg(Integer actRegId);

    ActRegVO createActReg(ActRegRequest actRegRequest);

    ActRegVO updateActReg(Integer actRegId, ActRegStatus actRegStatus);

    List<MemNameAndPicDTO> findMemIdAndPic(Integer actId, Integer isActPart);
}
