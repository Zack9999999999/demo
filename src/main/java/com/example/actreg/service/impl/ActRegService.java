package com.example.actreg.service.impl;

import com.example.act.model.ActVO;
import com.example.act.repository.ActRepository;
import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.dto.MemIdAndPicDTO;
import com.example.actreg.model.ActRegVO;
import com.example.actreg.repository.ActRegRepository;
import com.example.actreg.service.IActRegService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;

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

        //如果報名人數超過 則不能報名
        if ((actRepository.findById(actRegRequest.getActId()).orElse(null).getActUpper()) - actRegRequest.getRegTotal() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return actRegRepository.save(actReg);
    }

    @Override
    @Transactional
    public ActRegVO updateActReg(Integer actRegId, ActRegRequest actRegRequest) {

        ActVO act = actRepository.findById(actRegRequest.getActId()).orElse(null);

        ActRegVO actReg = actRegRepository.findById(actRegId).orElse(null);

        modelMapper.map(actRegRequest, actReg);

//        if (actRegRequest.getRegStatus() != null) { //未通過的話要將活動的總人數扣掉報名人數
//            actReg.setRegStatus(actRegRequest.getRegStatus());
//            if (actRegRequest.getRegStatus() == 1) {
//                ActVO act = actRepository.findById(actRegRequest.getActId()).orElse(null);
//                act.setActCount(act.getActCount() - actReg.getRegTotal());
//
//            } else if (actRegRequest.getRegStatus() == 3) {
//
//            }
//        } else if (actRegRequest.getIsActPart() != null) { //取消參加要將活動的總人數扣掉報名人數
//            actReg.setIsActPart(actRegRequest.getIsActPart());
//            if (actRegRequest.getRegStatus() == 1) {
//                ActVO act = actRepository.findById(actRegRequest.getActId()).orElse(null);
//                act.setActCount(act.getActCount() - actReg.getRegTotal());
//
//            } else if (actRegRequest.getRegStatus() == 2) {
//
//            }
//        } else
//            actReg.setActRating(actRegRequest.getActRating());

        return actRegRepository.save(actReg);

        //1.活動主審核狀態regStatus 2.參加者修改參加狀態isActPart 3.參加者活動評分actRating 4.參加者報名人數也要可以改regTotal
    }

    @Override
    public List<MemIdAndPicDTO> findMemIdAndPic(Integer actId, Integer isActPart) {

        List<Object[]> membersAndPicByPart = actRegRepository.findMembersAndPicByPart(actId, isActPart);

        List<MemIdAndPicDTO> dtos = new ArrayList<>();

        for (Object[] obj : membersAndPicByPart) {
            MemIdAndPicDTO memIdAndPicDTO = new MemIdAndPicDTO();
            memIdAndPicDTO.setMemName((String) obj[0]);
            memIdAndPicDTO.setMemPic((byte[]) obj[1]);
            dtos.add(memIdAndPicDTO);
        }

        return dtos;
    }
}
