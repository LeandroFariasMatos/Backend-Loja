package com.projeto.util;

import com.projeto.domain.Carrinho;

import java.util.UUID;

public class CarrinhoCreator {
    public static Carrinho createValidCarrinho() {

        return Carrinho.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .cliente(ClienteCreator.createValidCliente())
                .produto(ProdutoCreator.createValidProduto())
                .quantidadeSelecionada(5)
                .preco(ProdutoCreator.createValidProduto().getPreco() *5)
                .status(0)
                .build();
    }

    public static Carrinho createToBeSavedCarrinho() {
        return Carrinho.builder()
                .cliente(ClienteCreator.createValidCliente())
                .produto(ProdutoCreator.createValidProduto())
                .quantidadeSelecionada(5)
                .preco(ProdutoCreator.createValidProduto().getPreco() *5)
                .status(0)
                .build();
    }

    public static Carrinho createToBeCheckoutCarrinho() {
        return Carrinho.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .cliente(ClienteCreator.createValidCliente())
                .produto(ProdutoCreator.createValidProduto())
                .quantidadeSelecionada(5)
                .preco(ProdutoCreator.createValidProduto().getPreco() *5)
                .status(1)
                .build();
    }


}
