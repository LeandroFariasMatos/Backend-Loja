package com.projeto.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="carrinho")
@Builder
public class Carrinho {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name="produto_id",nullable = false)
    private Produto produto;
    @ManyToOne
    @JoinColumn(name="cliente_id",nullable = false)
    private Cliente cliente;
    @Column(name="quantidade_selecionada")
    private int quantidadeSelecionada;
    @Column(name="preco",nullable = false,scale=2)
    private double preco;
    @Column(name="status",nullable=false)
    private int status;

}
