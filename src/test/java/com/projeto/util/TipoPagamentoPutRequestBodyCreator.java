package com.projeto.util;

import com.projeto.request.TipoPagamentoPutRequestBody;

public class TipoPagamentoPutRequestBodyCreator {

    public static TipoPagamentoPutRequestBody createTipoPagamentoPutRequestBody(){
        return TipoPagamentoPutRequestBody.builder()
                .id(TipoPagamentoCreator.createValidUpdateTipoPagamento().getId())
                .nomePagamento(TipoPagamentoCreator.createValidUpdateTipoPagamento().getNomePagamento())
                .build();

    }
}
