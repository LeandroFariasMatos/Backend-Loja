package com.projeto.util;

import com.projeto.request.TipoProdutoPostRequestBody;

public class TipoProdutoPostRequestBodyCreator {

    public static TipoProdutoPostRequestBody createValidTipoProdutoPostRequestBody(){
        return TipoProdutoPostRequestBody.builder()
                .nomeTipoProduto(TipoProdutoCreator.createToBeSavedTipoProduto().getNomeTipoProduto())
                .build();
    }
}
