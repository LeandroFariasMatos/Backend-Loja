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
@Table(name="vendas")
@Builder
public class Vendas {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name="cliente_id",nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="carrinho_id",nullable = false)
    private Carrinho carrinho;
    @ManyToOne
    @JoinColumn(name="pagamento_id",nullable = false)
    private TipoPagamento pagamento;
    @Column(name="valor_da_venda",scale=2)
    private double valorDaVenda;

}
