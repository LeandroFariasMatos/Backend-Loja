package com.projeto.repository;

import com.projeto.domain.Carrinho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {

    @Query("SELECT c FROM Carrinho c WHERE c.cliente.id = (:id) AND c.status = 0")
    Page<Carrinho> findByCliente(Pageable pageable, UUID id);

    @Query("SELECT c FROM Carrinho c WHERE c.cliente.id = (:id) AND c.status = 0" )
    List<Carrinho> findByFinalizarCompra(@Param("id") UUID id);


    @Modifying
    @Query("DELETE FROM Carrinho c WHERE c.cliente.id = (:id)")
    void deletarIdCliente(UUID id);
}
