package com.projeto.util;

import com.projeto.request.ProdutoPutRequestBody;

public class ProdutoPutRequestBodyCreator {

    public static ProdutoPutRequestBody createValidProdutoPutRequestBody(){
        return ProdutoPutRequestBody.builder()
                .id(ProdutoCreator.createUpdateProduto().getId())
                .nomeProduto(ProdutoCreator.createUpdateProduto().getNomeProduto())
                .tipoProduto(ProdutoCreator.createUpdateProduto().getTipoProduto())
                .preco(ProdutoCreator.createUpdateProduto().getPreco())
                .caminhoImage(ProdutoCreator.createUpdateProduto().getCaminhoImage())
                .quantidadeEmEstoque(ProdutoCreator.createUpdateProduto().getQuantidadeEmEstoque())
                .quantidadeVendida(ProdutoCreator.createUpdateProduto().getQuantidadeVendida())
                .build();
    }
}
