package com.devsDoAgi.SAFeR.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsDoAgi.SAFeR.model.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    Optional<Dispositivo> findByContaNumConta(String numContaOrigem);
}
