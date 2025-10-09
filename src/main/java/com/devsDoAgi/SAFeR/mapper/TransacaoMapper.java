package com.devsDoAgi.SAFeR.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devsDoAgi.SAFeR.dto.TransacaoRequestDTO;
import com.devsDoAgi.SAFeR.dto.TransacaoResponseDTO;
import com.devsDoAgi.SAFeR.model.Transacao;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    // RequestDTO → Entidade
    Transacao toEntity(TransacaoRequestDTO dto);

    // Entidade → ResponseDTO
    TransacaoResponseDTO toResponseDTO(Transacao transacao);

    // Lista de entidades → Lista de ResponseDTO
    List<TransacaoResponseDTO> toResponseDTOList(List<Transacao> transacao);
}
