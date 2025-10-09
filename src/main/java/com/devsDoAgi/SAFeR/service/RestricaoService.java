package com.devsDoAgi.SAFeR.service;

import java.util.List;

import com.devsDoAgi.SAFeR.exception.RestrictionNotFound;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.devsDoAgi.SAFeR.repository.RestricaoRepository;
import com.devsDoAgi.SAFeR.dto.RestricaoRequestDTO;
import com.devsDoAgi.SAFeR.dto.RestricaoResponseDTO;
import com.devsDoAgi.SAFeR.enums.RestricaoStatus;
import com.devsDoAgi.SAFeR.mapper.RestricaoMappar;
import com.devsDoAgi.SAFeR.model.Restricao;

@Service
public class RestricaoService {

    private final RestricaoRepository restricaoRepository;
    private final RestricaoMappar restricaoMapper;

    public RestricaoService(RestricaoRepository restricaoRepository, RestricaoMappar restricaoMapper) {
        this.restricaoRepository = restricaoRepository;
        this.restricaoMapper = restricaoMapper;
    }

    @Transactional
    public RestricaoResponseDTO criarRestricao(RestricaoRequestDTO dto) {
        Restricao restricao = restricaoMapper.toEntity(dto);
        Restricao salva = restricaoRepository.save(restricao);
        return restricaoMapper.toResponseDTO(salva);
    }

    @Transactional
    public RestricaoResponseDTO buscarPorCPF(String id) {
        Restricao restricao = restricaoRepository.findById(id)
                .orElseThrow(() -> new RestrictionNotFound("CPF não encontrado"));
        return restricaoMapper.toResponseDTO(restricao);
    }

    @Transactional
    public List<RestricaoResponseDTO> listarRestricoes() {
        List<Restricao> restricoes = restricaoRepository.findAll();
        return restricaoMapper.toResponseDTOList(restricoes);
    }

    @Transactional
    public RestricaoResponseDTO atualizarRestricaoNormal(String id) {
        Restricao restricao = restricaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));
        restricao.setStatus(RestricaoStatus.SEM_RESTRICAO);
        Restricao atualizada = restricaoRepository.save(restricao);
        return restricaoMapper.toResponseDTO(atualizada);
    }

    @Transactional
    public RestricaoResponseDTO atualizarRestricaoRestrito(String id) {
        Restricao restricao = restricaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));
        restricao.setStatus(RestricaoStatus.COM_RESTRICAO);
        Restricao atualizada = restricaoRepository.save(restricao);
        return restricaoMapper.toResponseDTO(atualizada);
    }

    @Transactional
    public RestricaoResponseDTO atualizarRestricaoBloqueado(String id) {
        Restricao restricao = restricaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));
        restricao.setStatus(RestricaoStatus.BLOQUEADO);
        Restricao atualizada = restricaoRepository.save(restricao);
        return restricaoMapper.toResponseDTO(atualizada);
    }

    @Transactional
    public RestricaoResponseDTO atualizarRestricaoCancelado(String id) {
        Restricao restricao = restricaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));
        restricao.setStatus(RestricaoStatus.CANCELADO);
        Restricao atualizada = restricaoRepository.save(restricao);
        return restricaoMapper.toResponseDTO(atualizada);
    }
}
