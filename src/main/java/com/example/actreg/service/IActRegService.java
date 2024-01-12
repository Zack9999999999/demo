package com.example.actreg.service;

import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.dto.MemIdAndPicDTO;
import com.example.actreg.model.ActRegVO;

import java.util.List;

public interface IActRegService {

    List<ActRegVO> getActRegs();

    ActRegVO getActReg(Integer actRegId);

    ActRegVO createActReg(ActRegRequest actRegRequest);

    ActRegVO updateActReg(Integer actRegId, ActRegRequest actRegRequest);

    List<MemIdAndPicDTO> findMemIdAndPic(Integer actId, Integer isActPart);
}
