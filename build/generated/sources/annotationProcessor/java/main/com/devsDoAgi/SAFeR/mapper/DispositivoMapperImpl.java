package com.devsDoAgi.SAFeR.mapper;

import com.devsDoAgi.SAFeR.dto.DispositivoRequestDTO;
import com.devsDoAgi.SAFeR.dto.DispositivoResponseDTO;
import com.devsDoAgi.SAFeR.enums.DispositivoStatus;
import com.devsDoAgi.SAFeR.enums.DispositivoTipos;
import com.devsDoAgi.SAFeR.model.Dispositivo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-08T08:34:51-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class DispositivoMapperImpl implements DispositivoMapper {

    @Override
    public Dispositivo toEntity(DispositivoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Dispositivo dispositivo = new Dispositivo();

        if ( dto.getTipo() != null ) {
            dispositivo.setTipo( Enum.valueOf( DispositivoTipos.class, dto.getTipo() ) );
        }
        if ( dto.getStatus() != null ) {
            dispositivo.setStatus( Enum.valueOf( DispositivoStatus.class, dto.getStatus() ) );
        }
        Double[] local = dto.getLocal();
        if ( local != null ) {
            dispositivo.setLocal( Arrays.copyOf( local, local.length ) );
        }

        return dispositivo;
    }

    @Override
    public DispositivoResponseDTO toResponseDTO(Dispositivo dispositivo) {
        if ( dispositivo == null ) {
            return null;
        }

        DispositivoResponseDTO dispositivoResponseDTO = new DispositivoResponseDTO();

        dispositivoResponseDTO.setIdDispositivo( dispositivo.getIdDispositivo() );
        if ( dispositivo.getTipo() != null ) {
            dispositivoResponseDTO.setTipo( dispositivo.getTipo().name() );
        }
        if ( dispositivo.getStatus() != null ) {
            dispositivoResponseDTO.setStatus( dispositivo.getStatus().name() );
        }
        Double[] local = dispositivo.getLocal();
        if ( local != null ) {
            dispositivoResponseDTO.setLocal( Arrays.copyOf( local, local.length ) );
        }

        return dispositivoResponseDTO;
    }

    @Override
    public List<DispositivoResponseDTO> toResponseDTOList(List<Dispositivo> dispositivo) {
        if ( dispositivo == null ) {
            return null;
        }

        List<DispositivoResponseDTO> list = new ArrayList<DispositivoResponseDTO>( dispositivo.size() );
        for ( Dispositivo dispositivo1 : dispositivo ) {
            list.add( toResponseDTO( dispositivo1 ) );
        }

        return list;
    }
}
