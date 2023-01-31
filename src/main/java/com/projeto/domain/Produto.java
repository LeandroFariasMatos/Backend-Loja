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
@Table(name = "produto")
@Builder
public class Produto {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;
    @Column(name="nome_produto",nullable = false,unique = true)
    private String nomeProduto;
    @ManyToOne
    @JoinColumn(name="tipo_produto_id",nullable = false)
    private TipoProduto tipoProduto;
    @Column(name="preco",nullable = false,scale=2)
    private double preco;
    @Column(name="quantidade_estoque")
    private int quantidadeEmEstoque;
    @Column(name="quantidade_vendida")
    private int quantidadeVendida;
    @Column(name="caminho_Image")
    private String caminhoImage;





}
