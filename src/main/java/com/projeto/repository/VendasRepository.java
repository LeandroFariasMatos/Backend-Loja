package com.projeto.repository;

import com.projeto.domain.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, UUID> {
//    List<Vendas> findByCliente_id (long id);
}
