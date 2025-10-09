package com.devsDoAgi.SAFeR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsDoAgi.SAFeR.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
