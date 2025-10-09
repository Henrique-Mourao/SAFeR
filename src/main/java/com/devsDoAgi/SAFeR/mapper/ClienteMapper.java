package com.devsDoAgi.SAFeR.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.devsDoAgi.SAFeR.dto.ClienteRequestDTO;
import com.devsDoAgi.SAFeR.dto.ClienteResponseDTO;
import com.devsDoAgi.SAFeR.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // RequestDTO → Entidade
    Cliente toEntity(ClienteRequestDTO dto);

    // Entidade → ResponseDTO
    ClienteResponseDTO toResponseDTO(Cliente conta);

    // Lista de entidades → Lista de ResponseDTO
    List<ClienteResponseDTO> toResponseDTOList(List<Cliente> contas);
}
