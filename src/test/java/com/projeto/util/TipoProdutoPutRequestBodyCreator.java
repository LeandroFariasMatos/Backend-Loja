package com.projeto.util;

import com.projeto.request.TipoProdutoPutRequestBody;

public class TipoProdutoPutRequestBodyCreator {

    public static TipoProdutoPutRequestBody createValidTipoProdutoPutRequestBody(){
        return TipoProdutoPutRequestBody.builder()
                .id(TipoProdutoCreator.createValidUpdateTipoProduto().getId())
                .nomeTipoProduto(TipoProdutoCreator.createValidUpdateTipoProduto().getNomeTipoProduto())
                .build();
    }
}
