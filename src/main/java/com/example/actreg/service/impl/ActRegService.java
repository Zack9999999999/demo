package com.example.actreg.service.impl;

import com.example.act.model.ActVO;
import com.example.act.repository.ActRepository;
import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.dto.ActRegStatus;
import com.example.actreg.dto.MemNameAndPicDTO;
import com.example.actreg.model.ActRegVO;
import com.example.actreg.repository.ActRegRepository;
import com.example.actreg.service.IActRegService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ActRegService implements IActRegService {

    @Autowired
    private ActRegRepository actRegRepository;

    @Autowired
    private ActRepository actRepository;

    @Autowired
    private ModelMapper modelMapper;

    //log紀錄
    private final static Logger log = LoggerFactory.getLogger(ActRegService.class);

    @Override
    public List<ActRegVO> getActRegs() {
        actRepository.findAll();
        return actRegRepository.findAll();
    }

    @Override
    @Transactional
    public ActRegVO getActReg(Integer actRegId) {
        return actRegRepository.findByActRegIdAndFetchActEagerly(actRegId).orElse(null);
    }

    @Override
    @Transactional
    public ActRegVO createActReg(ActRegRequest actRegRequest) {
        ActRegVO actReg = new ActRegVO();

        BeanUtils.copyProperties(actRegRequest, actReg);
        actReg.setRegTime(new Date());
        actReg.setAct(actRepository.findById(actRegRequest.getActId()).orElse(null));

        //如果報名人數超過活動人數上限 則不能報名
        if ((actRepository.findById(actRegRequest.getActId()).orElse(null).getActUpper()) - actRegRequest.getRegTotal() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return actRegRepository.save(actReg);
    }

    @Override
    @Transactional
    public ActRegVO updateActReg(Integer actRegId, ActRegStatus actRegStatus) {

        ActVO act = actRepository.findById(actRegStatus.getActId()).orElse(null);

        ActRegVO actReg = actRegRepository.findById(actRegId).orElse(null);

        modelMapper.map(actRegStatus, actReg);

        //3 = 報名成功才將活動人數加上報名人數
        if (actReg.getRegStatus() == 3) {
            act.setActCount(act.getActCount() + actReg.getRegTotal());
        }

        return actRegRepository.save(actReg);

    }

    @Override
    public List<MemNameAndPicDTO> findMemIdAndPic(Integer actId, Integer isActPart) {

        List<Object[]> membersAndPicByPart = actRegRepository.findMembersAndPicByPart(actId, isActPart);

        List<MemNameAndPicDTO> dtos = new ArrayList<>();

        for (Object[] obj : membersAndPicByPart) {
            MemNameAndPicDTO memIdAndPicDTO = new MemNameAndPicDTO();
            memIdAndPicDTO.setMemName((String) obj[0]);
            memIdAndPicDTO.setMemPic((byte[]) obj[1]);
            dtos.add(memIdAndPicDTO);
        }

        return dtos;
    }
}
