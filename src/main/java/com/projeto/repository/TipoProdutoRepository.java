package com.projeto.repository;

import com.projeto.domain.TipoProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, UUID> {
    @Query("SELECT tp FROM TipoProduto tp WHERE tp.nomeTipoProduto = :nome")
    Page<TipoProduto> findByNomeTipoProduto(String nome , Pageable pageable);
}
