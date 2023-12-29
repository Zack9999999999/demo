package com.example.actreg.service.impl;

import com.example.act.model.ActVO;
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

        //做判斷 如果報名人數超過 則不能報名
//        ActRegVO actReg = modelMapper.map(actRegRequest, ActRegVO.class); //PK有問題 會跟repId一樣

//        if (actReg.getAct().getActUpper() - actReg.getRegTotal() > 0) {
            return actRegRepository.save(actReg);
//        }
//        return null;
    }

    @Override
    public ActRegVO updateActReg(Integer actRegId, ActRegRequest actRegRequest) {

        Optional<ActRegVO> actReg = actRegRepository.findById(actRegId);

        //1.審核狀態 2.參加狀態 3.活動評分

        if (!actReg.isPresent()) {
            return null;
        }
        ActRegVO updateActReg = actReg.get();

        modelMapper.map(actRegRequest, updateActReg);

        return actRegRepository.save(updateActReg);
    }
}
