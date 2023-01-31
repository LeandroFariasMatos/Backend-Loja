package com.projeto.repository;

import com.projeto.domain.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, UUID> {
    TipoPagamento findByNomePagamento(String nome);
}
