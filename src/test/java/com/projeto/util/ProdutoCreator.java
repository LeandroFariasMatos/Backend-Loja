package com.projeto.util;

import com.projeto.domain.Produto;

import java.util.UUID;

public class ProdutoCreator {
    public static Produto createValidProduto() {

        return Produto.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .nomeProduto("Geladeira")
                .tipoProduto(TipoProdutoCreator.createValidTipoProduto())
                .caminhoImage("teste")
                .preco(50)
                .quantidadeEmEstoque(5)
                .quantidadeVendida(5)
                .build();
    }

    public static Produto createToBeSavedProduto() {
        return Produto.builder()
                .nomeProduto("Geladeira")
                .tipoProduto(TipoProdutoCreator.createValidTipoProduto())
                .caminhoImage("teste")
                .preco(50)
                .quantidadeEmEstoque(5)
                .quantidadeVendida(5)
                .build();
    }

    public static Produto createUpdateProduto() {

        return Produto.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .nomeProduto("Fogão")
                .tipoProduto(TipoProdutoCreator.createValidTipoProduto())
                .caminhoImage("teste")
                .preco(50)
                .quantidadeEmEstoque(5)
                .quantidadeVendida(5)
                .build();
    }

}
