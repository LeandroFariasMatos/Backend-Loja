package com.projeto.repository;

import com.projeto.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    List<Cliente> findByNomeCliente(String nome);

    Cliente findByEmail(String email);

    void deleteByEmail(String email);
}
