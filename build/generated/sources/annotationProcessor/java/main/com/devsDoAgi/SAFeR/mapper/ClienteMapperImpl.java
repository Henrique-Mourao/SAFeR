package com.devsDoAgi.SAFeR.mapper;

import com.devsDoAgi.SAFeR.dto.ClienteRequestDTO;
import com.devsDoAgi.SAFeR.dto.ClienteResponseDTO;
import com.devsDoAgi.SAFeR.model.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-09T17:10:39-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setCpf( dto.getCpf() );
        cliente.setNome( dto.getNome() );
        if ( dto.getDataNascimento() != null ) {
            cliente.setDataNascimento( LocalDate.parse( dto.getDataNascimento() ) );
        }
        cliente.setScore( dto.getScore() );
        cliente.setAtivo( dto.isAtivo() );

        return cliente;
    }

    @Override
    public ClienteResponseDTO toResponseDTO(Cliente conta) {
        if ( conta == null ) {
            return null;
        }

        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();

        clienteResponseDTO.setCpf( conta.getCpf() );
        clienteResponseDTO.setNome( conta.getNome() );
        if ( conta.getDataNascimento() != null ) {
            clienteResponseDTO.setDataNascimento( DateTimeFormatter.ISO_LOCAL_DATE.format( conta.getDataNascimento() ) );
        }
        clienteResponseDTO.setScore( conta.getScore() );
        clienteResponseDTO.setAtivo( conta.isAtivo() );

        return clienteResponseDTO;
    }

    @Override
    public List<ClienteResponseDTO> toResponseDTOList(List<Cliente> contas) {
        if ( contas == null ) {
            return null;
        }

        List<ClienteResponseDTO> list = new ArrayList<ClienteResponseDTO>( contas.size() );
        for ( Cliente cliente : contas ) {
            list.add( toResponseDTO( cliente ) );
        }

        return list;
    }
}
