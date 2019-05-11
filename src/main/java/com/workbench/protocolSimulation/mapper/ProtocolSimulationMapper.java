package com.workbench.protocolSimulation.mapper;

import com.workbench.protocolSimulation.dto.ProtocolSimulationDto;
import com.workbench.protocolSimulation.dto.ProtocolSimulationJson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProtocolSimulationMapper {
    /**
     * 增加新对象
     *
     * @param dto
     * @return
     */
    int insertProtocolSimulation(ProtocolSimulationDto dto);

    List<ProtocolSimulationJson> listProtocolSimulation(@Param("protocolType") String protocolType, @Param("operDate") String operDate);

    Integer countProtocol(@Param("protocolType") String protocolType);
}
