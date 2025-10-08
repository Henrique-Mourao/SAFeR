package com.devsDoAgi.SAFeR.mapper;

import com.devsDoAgi.SAFeR.dto.TransacaoRequestDTO;
import com.devsDoAgi.SAFeR.dto.TransacaoResponseDTO;
import com.devsDoAgi.SAFeR.enums.DispositivoTipos;
import com.devsDoAgi.SAFeR.enums.TransacaoMeioPagamento;
import com.devsDoAgi.SAFeR.model.Transacao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-07T17:13:34-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class TransacaoMapperImpl implements TransacaoMapper {

    @Override
    public Transacao toEntity(TransacaoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Transacao transacao = new Transacao();

        transacao.setValor( dto.getValor() );
        transacao.setDataHoraOperacao( dto.getDataHoraOperacao() );
        if ( dto.getDispositivo() != null ) {
            transacao.setDispositivo( Enum.valueOf( DispositivoTipos.class, dto.getDispositivo() ) );
        }
        Double[] local = dto.getLocal();
        if ( local != null ) {
            transacao.setLocal( Arrays.copyOf( local, local.length ) );
        }
        if ( dto.getMeioPagamento() != null ) {
            transacao.setMeioPagamento( Enum.valueOf( TransacaoMeioPagamento.class, dto.getMeioPagamento() ) );
        }
        transacao.setNumContaOrigem( dto.getNumContaOrigem() );
        transacao.setNumAgenciaOrigem( dto.getNumAgenciaOrigem() );
        transacao.setIspbOrigem( dto.getIspbOrigem() );
        transacao.setCpfOrigem( dto.getCpfOrigem() );
        transacao.setNumContaDestino( dto.getNumContaDestino() );
        transacao.setNumAgenciaDestino( dto.getNumAgenciaDestino() );
        transacao.setIspbDestino( dto.getIspbDestino() );
        transacao.setCpfCnpjDestino( dto.getCpfCnpjDestino() );
        transacao.setScoreTransacao( dto.getScoreTransacao() );
        transacao.setTransacaoAnalisada( dto.isTransacaoAnalisada() );

        return transacao;
    }

    @Override
    public TransacaoResponseDTO toResponseDTO(Transacao transacao) {
        if ( transacao == null ) {
            return null;
        }

        TransacaoResponseDTO transacaoResponseDTO = new TransacaoResponseDTO();

        transacaoResponseDTO.setIdTransacao( transacao.getIdTransacao() );
        transacaoResponseDTO.setValor( transacao.getValor() );
        transacaoResponseDTO.setDataHoraOperacao( transacao.getDataHoraOperacao() );
        if ( transacao.getDispositivo() != null ) {
            transacaoResponseDTO.setDispositivo( transacao.getDispositivo().name() );
        }
        Double[] local = transacao.getLocal();
        if ( local != null ) {
            transacaoResponseDTO.setLocal( Arrays.copyOf( local, local.length ) );
        }
        if ( transacao.getMeioPagamento() != null ) {
            transacaoResponseDTO.setMeioPagamento( transacao.getMeioPagamento().name() );
        }
        transacaoResponseDTO.setNumContaOrigem( transacao.getNumContaOrigem() );
        transacaoResponseDTO.setNumAgenciaOrigem( transacao.getNumAgenciaOrigem() );
        transacaoResponseDTO.setIspbOrigem( transacao.getIspbOrigem() );
        transacaoResponseDTO.setCpfOrigem( transacao.getCpfOrigem() );
        transacaoResponseDTO.setNumContaDestino( transacao.getNumContaDestino() );
        transacaoResponseDTO.setNumAgenciaDestino( transacao.getNumAgenciaDestino() );
        transacaoResponseDTO.setIspbDestino( transacao.getIspbDestino() );
        transacaoResponseDTO.setCpfCnpjDestino( transacao.getCpfCnpjDestino() );
        transacaoResponseDTO.setScoreTransacao( transacao.getScoreTransacao() );
        transacaoResponseDTO.setTransacaoAnalisada( transacao.isTransacaoAnalisada() );

        return transacaoResponseDTO;
    }

    @Override
    public List<TransacaoResponseDTO> toResponseDTOList(List<Transacao> transacao) {
        if ( transacao == null ) {
            return null;
        }

        List<TransacaoResponseDTO> list = new ArrayList<TransacaoResponseDTO>( transacao.size() );
        for ( Transacao transacao1 : transacao ) {
            list.add( toResponseDTO( transacao1 ) );
        }

        return list;
    }
}
