package com.devsDoAgi.SAFeR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsDoAgi.SAFeR.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
