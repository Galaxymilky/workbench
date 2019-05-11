package com.workbench.protocolSimulation.service.impl;

import com.workbench.protocolSimulation.dto.ProtocolSimulationDto;
import com.workbench.protocolSimulation.dto.ProtocolSimulationForm;
import com.workbench.protocolSimulation.dto.ProtocolSimulationJson;
import com.workbench.protocolSimulation.mapper.ProtocolSimulationMapper;
import com.workbench.protocolSimulation.service.ProtocolSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("protocolSimulationService")
public class ProtocolSimulationServiceImpl implements ProtocolSimulationService {

    @Autowired
    private ProtocolSimulationMapper mapper;

    @Override
    public void addProtocolSimulation(ProtocolSimulationForm form) {
        ProtocolSimulationDto dto = new ProtocolSimulationDto();

        dto.setSnCode(form.getSnCode());
        dto.setKPre(form.getKPre());
        dto.setDkPre(form.getDkPre());
        dto.setLeakage(form.getLeakage());
        dto.setResult(form.getResult());
        dto.setOperTime(form.getOperTime());
        dto.setProtocolType(form.getProtocolType());

        mapper.insertProtocolSimulation(dto);
    }

    @Override
    public List<ProtocolSimulationJson> listProtocolSimulation(String protocolType, String operDate) {
        return mapper.listProtocolSimulation(protocolType, operDate);
    }

    @Override
    public Integer countProtocol(String protocolType) {
        return mapper.countProtocol(protocolType);
    }

    @Override
    public Integer countTodayProtocol() {
        return mapper.countTodayProtocol();
    }
}
