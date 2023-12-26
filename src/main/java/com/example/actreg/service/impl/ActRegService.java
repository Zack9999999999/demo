package com.example.actreg.service.impl;

import com.example.actreg.dto.ActRegRequest;
import com.example.actreg.model.ActRegVO;
import com.example.actreg.repository.ActRegRepository;
import com.example.actreg.service.IActRegService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        actReg.setMemId(actRegRequest.getMemId());
        actReg.setActId(actRegRequest.getActId());
        actReg.setRegTotal(actRegRequest.getRegTotal());
        actReg.setRegTime(new Date());

        //        ActRegVO actReg = modelMapper.map(actRegRequest, ActRegVO.class); //不會報錯也有回傳但是DB不會新增資料

        return actRegRepository.save(actReg);
    }

    public ActRegVO updateActReg(Integer actRegId, ActRegRequest actRegRequest) {

        Optional<ActRegVO> actReg = actRegRepository.findById(actRegId);

        if (!actReg.isPresent()) {
            return null;
        }
        ActRegVO updateActReg = actReg.get();

        modelMapper.map(actRegRequest, updateActReg);

        return actRegRepository.save(updateActReg);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String user = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .findFirst()
//                .orElse(null);
//
//        switch (user) { //操作者是誰
//            case "staff":
//
//                break;
//            case "User":
//
//                break;
    }
}
