package com.devsDoAgi.SAFeR.service;

import java.util.List;

import com.devsDoAgi.SAFeR.exception.DeviceNotFound;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.devsDoAgi.SAFeR.repository.DispositivoRepository;
import com.devsDoAgi.SAFeR.dto.DispositivoRequestDTO;
import com.devsDoAgi.SAFeR.dto.DispositivoResponseDTO;
import com.devsDoAgi.SAFeR.enums.DispositivoStatus;
import com.devsDoAgi.SAFeR.mapper.DispositivoMapper;
import com.devsDoAgi.SAFeR.model.Dispositivo;

@Service
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;
    private final DispositivoMapper dispositivoMapper;

    public DispositivoService(DispositivoRepository dispositivoRepository, DispositivoMapper dispositivoMapper) {
        this.dispositivoRepository = dispositivoRepository;
        this.dispositivoMapper = dispositivoMapper;
    }

    @Transactional
    public DispositivoResponseDTO criarDispositivo(DispositivoRequestDTO dto) {
        Dispositivo dispositivo = dispositivoMapper.toEntity(dto);
        Dispositivo salva = dispositivoRepository.save(dispositivo);
        return dispositivoMapper.toResponseDTO(salva);
    }

    @Transactional
    public DispositivoResponseDTO buscarDispositivo(Long id) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFound("Dispositivo não encontrado"));
        return dispositivoMapper.toResponseDTO(dispositivo);
    }

    @Transactional
    public List<DispositivoResponseDTO> listarDispositivo() {
        List<Dispositivo> dispositivos = dispositivoRepository.findAll();
        return dispositivoMapper.toResponseDTOList(dispositivos);
    }

    @Transactional
    public DispositivoResponseDTO atualizarDispositivo(Long id, DispositivoRequestDTO dto) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFound("Dispositivo não encontrado"));
        dispositivo.setLocal(dto.getLocal());
        Dispositivo atualizado = dispositivoRepository.save(dispositivo);
        return dispositivoMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public DispositivoResponseDTO bloquearDispositivo(Long id) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFound("Dispositivo não encontrado"));
        dispositivo.setStatus(DispositivoStatus.NAO_AUTORIZADO);
        Dispositivo dispositivoBloqueado = dispositivoRepository.save(dispositivo);
        return dispositivoMapper.toResponseDTO(dispositivoBloqueado);
    }
}
