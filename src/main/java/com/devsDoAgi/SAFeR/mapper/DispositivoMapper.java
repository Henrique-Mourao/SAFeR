package com.devsDoAgi.SAFeR.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devsDoAgi.SAFeR.dto.DispositivoRequestDTO;
import com.devsDoAgi.SAFeR.dto.DispositivoResponseDTO;
import com.devsDoAgi.SAFeR.model.Dispositivo;

@Mapper(componentModel = "spring")
public interface DispositivoMapper {

    // RequestDTO → Entidade
    Dispositivo toEntity(DispositivoRequestDTO dto);

    // Entidade → ResponseDTO
    DispositivoResponseDTO toResponseDTO(Dispositivo dispositivo);

    // Lista de entidades → Lista de ResponseDTO
    List<DispositivoResponseDTO> toResponseDTOList(List<Dispositivo> dispositivo);
}
