package com.example.actreg.service.impl;

import com.example.act.model.ActVO;
import com.example.act.repository.ActRepository;
import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.model.ActRegVO;
import com.example.actreg.repository.ActRegRepository;
import com.example.actreg.service.IActRegService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ActRegService implements IActRegService {

    @Autowired
    private ActRegRepository actRegRepository;

    @Autowired
    private ActRepository actRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ActRegVO> getActRegs() {
        return actRegRepository.findAll();
    }

    @Override
    public ActRegVO getActReg(Integer actRegId) {
        return actRegRepository.findById(actRegId).orElse(null);
    }

    @Override
    @Transactional
    public ActRegVO createActReg(ActRegRequest actRegRequest) {

        ActRegVO actReg = new ActRegVO();
        BeanUtils.copyProperties(actRegRequest, actReg);
        actReg.setRegTime(new Date());

        if (actRegRequest.getActId() != null) {
            ActVO act = actRepository.findById(actRegRequest.getActId()).orElse(null);
            actReg.setAct(act);
        }
        //做判斷 如果報名人數超過 則不能報名
//        ActRegVO actReg = modelMapper.map(actRegRequest, ActRegVO.class); //PK有問題 會跟repId一樣

        return actRegRepository.save(actReg);

    }

    @Override
    public ActRegVO updateActReg(Integer actRegId, ActRegRequest actRegRequest) {

//        ActVO act = actRepository.findById(actRegRequest.getActId()).orElse(null);

        //ActRegVO內如果是@ManyToOne(fetch = FetchType.LAZY)延遲加載會錯誤
        ActRegVO actReg = actRegRepository.findById(actRegId).orElse(null);
//        actReg.setAct(act);

        actReg.setAct(actRepository.findById(actRegRequest.getActId()).orElse(null));

        modelMapper.map(actRegRequest, actReg);

//        if (actRegRequest.getRegStatus() != null) {
//            updateActReg.setRegStatus(actRegRequest.getRegStatus());
//        } else if (actRegRequest.getIsActPart() != null) {
//            updateActReg.setIsActPart(actRegRequest.getIsActPart());
//        } else
//            updateActReg.setActRating(actRegRequest.getActRating());

        return actRegRepository.save(actReg);

        //1.活動主審核狀態regStatus 2.參加者修改參加狀態isActPart 3.參加者活動評分actRating 4.參加者報名人數也要可以改regTotal
    }
}
