package com.devsDoAgi.SAFeR.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devsDoAgi.SAFeR.dto.ContaRequestDTO;
import com.devsDoAgi.SAFeR.dto.ContaResponseDTO;
import com.devsDoAgi.SAFeR.model.Conta;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    // RequestDTO → Entidade
    Conta toEntity(ContaRequestDTO dto);

    // Entidade → ResponseDTO
    ContaResponseDTO toResponseDTO(Conta conta);

    // Lista de entidades → Lista de ResponseDTO
    List<ContaResponseDTO> toResponseDTOList(List<Conta> contas);
}
