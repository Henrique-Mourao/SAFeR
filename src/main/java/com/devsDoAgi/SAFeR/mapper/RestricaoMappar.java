package com.devsDoAgi.SAFeR.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devsDoAgi.SAFeR.dto.RestricaoRequestDTO;
import com.devsDoAgi.SAFeR.dto.RestricaoResponseDTO;
import com.devsDoAgi.SAFeR.model.Restricao;

@Mapper(componentModel = "spring")
public interface RestricaoMappar {

    // RequestDTO → Entidade
    Restricao toEntity(RestricaoRequestDTO dto);

    // Entidade → ResponseDTO
    RestricaoResponseDTO toResponseDTO(Restricao restricao);

    // Lista de entidades → Lista de ResponseDTO
    List<RestricaoResponseDTO> toResponseDTOList(List<Restricao> restricoes);
    
}
