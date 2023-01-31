package com.projeto.util;

import com.projeto.request.CarrinhoPostRequestBody;

public class CarrinhoPostRequestBodyCreator {

    public static CarrinhoPostRequestBody createCarrinhoPostRequestBody(){
        return CarrinhoPostRequestBody.builder()
                .cliente(ClienteCreator.createValidCliente())
                .produto(ProdutoCreator.createValidProduto())
                .quantidadeSelecionada(5)
                .status(0)
                .build();

    }
}
