package com.projeto.repository;

import com.projeto.domain.Produto;
import com.projeto.domain.TipoProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    @Query("SELECT p FROM Produto p WHERE p.nomeProduto LIKE CONCAT('%',:nomeProduto,'%')")
    Page<Produto> findByNomeProduto(Pageable pageable , String nomeProduto);

    @Query("SELECT p FROM Produto p WHERE p.tipoProduto = :tipoProduto")
    Page<Produto> findByTipoProduto(Pageable pageable, TipoProduto tipoProduto);
}
