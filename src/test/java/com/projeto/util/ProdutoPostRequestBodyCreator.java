package com.projeto.util;

import com.projeto.request.ProdutoPostRequestBody;

public class ProdutoPostRequestBodyCreator {

    public static ProdutoPostRequestBody createValidProdutoPostRequestBody(){
        return ProdutoPostRequestBody.builder()
                .nomeProduto(ProdutoCreator.createToBeSavedProduto().getNomeProduto())
                .tipoProduto(ProdutoCreator.createToBeSavedProduto().getTipoProduto())
                .preco(ProdutoCreator.createToBeSavedProduto().getPreco())
                .caminhoImage(ProdutoCreator.createToBeSavedProduto().getCaminhoImage())
                .quantidadeEmEstoque(ProdutoCreator.createToBeSavedProduto().getQuantidadeEmEstoque())
                .quantidadeVendida(ProdutoCreator.createToBeSavedProduto().getQuantidadeVendida())
                .build();

    }
}
