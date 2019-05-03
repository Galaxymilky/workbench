package com.workbench.protocolSimulation.service;

import com.workbench.protocolSimulation.dto.ProtocolSimulationForm;
import com.workbench.protocolSimulation.dto.ProtocolSimulationJson;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProtocolSimulationService {

    void addProtocolSimulation(ProtocolSimulationForm form);

    List<ProtocolSimulationJson> listProtocolSimulation(String protocolType, String operDate);
}
