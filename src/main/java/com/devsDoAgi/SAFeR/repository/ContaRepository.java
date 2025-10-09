package com.devsDoAgi.SAFeR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsDoAgi.SAFeR.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, String> {
}
