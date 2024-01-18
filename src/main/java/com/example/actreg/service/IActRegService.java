package com.example.actreg.service;

import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.dto.ActRegStatus;
import com.example.actreg.dto.MemNameAndPicDTO;
import com.example.actreg.model.ActRegVO;

import java.util.List;

public interface IActRegService {

    List<ActRegVO> getActRegs(Integer memId);

    ActRegVO getActReg(Integer actId, Integer memId);

    ActRegVO createActReg(ActRegRequest actRegRequest);

    ActRegVO updateActReg(ActRegStatus actRegStatus);

    List<MemNameAndPicDTO> findMemNameAndPic(Integer actId, Integer isActPart);
}
