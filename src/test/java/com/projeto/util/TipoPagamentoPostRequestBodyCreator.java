package com.projeto.util;

import com.projeto.request.TipoPagamentoPostRequestBody;

public class TipoPagamentoPostRequestBodyCreator {

    public static TipoPagamentoPostRequestBody createTipoPagamentoPostRequestBody(){
        return TipoPagamentoPostRequestBody.builder()
                .nomePagamento(TipoPagamentoCreator.createToBeSavedTipoPagamento().getNomePagamento())
                .build();
    }
}
