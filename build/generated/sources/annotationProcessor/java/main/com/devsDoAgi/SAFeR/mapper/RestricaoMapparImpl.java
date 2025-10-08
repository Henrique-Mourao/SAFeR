package com.devsDoAgi.SAFeR.mapper;

import com.devsDoAgi.SAFeR.dto.RestricaoRequestDTO;
import com.devsDoAgi.SAFeR.dto.RestricaoResponseDTO;
import com.devsDoAgi.SAFeR.enums.RestricaoStatus;
import com.devsDoAgi.SAFeR.enums.RestricaoTipos;
import com.devsDoAgi.SAFeR.model.Restricao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-08T15:00:01-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class RestricaoMapparImpl implements RestricaoMappar {

    @Override
    public Restricao toEntity(RestricaoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Restricao restricao = new Restricao();

        restricao.setId( dto.getId() );
        if ( dto.getTipo() != null ) {
            restricao.setTipo( Enum.valueOf( RestricaoTipos.class, dto.getTipo() ) );
        }
        if ( dto.getStatus() != null ) {
            restricao.setStatus( Enum.valueOf( RestricaoStatus.class, dto.getStatus() ) );
        }

        return restricao;
    }

    @Override
    public RestricaoResponseDTO toResponseDTO(Restricao restricao) {
        if ( restricao == null ) {
            return null;
        }

        RestricaoResponseDTO restricaoResponseDTO = new RestricaoResponseDTO();

        restricaoResponseDTO.setId( restricao.getId() );
        if ( restricao.getTipo() != null ) {
            restricaoResponseDTO.setTipo( restricao.getTipo().name() );
        }
        if ( restricao.getStatus() != null ) {
            restricaoResponseDTO.setStatus( restricao.getStatus().name() );
        }

        return restricaoResponseDTO;
    }

    @Override
    public List<RestricaoResponseDTO> toResponseDTOList(List<Restricao> restricoes) {
        if ( restricoes == null ) {
            return null;
        }

        List<RestricaoResponseDTO> list = new ArrayList<RestricaoResponseDTO>( restricoes.size() );
        for ( Restricao restricao : restricoes ) {
            list.add( toResponseDTO( restricao ) );
        }

        return list;
    }
}
