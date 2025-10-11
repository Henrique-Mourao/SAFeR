package com.devsDoAgi.SAFeR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsDoAgi.SAFeR.model.Transacao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query(value = "select * from transacoes order by data_hora_operacao desc limit 1",nativeQuery = true)
    public Optional<Transacao> getLastTransaction();
    public List<Transacao> findBynumContaOrigem(String numContaOrigem);
}
