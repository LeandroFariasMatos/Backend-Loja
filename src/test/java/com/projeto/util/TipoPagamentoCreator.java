package com.projeto.util;

import com.projeto.domain.TipoPagamento;

import java.util.UUID;

public class TipoPagamentoCreator {
    public static TipoPagamento createValidTipoPagamento(){
        return TipoPagamento.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .nomePagamento("Pix")
                .build();
    }

    public static TipoPagamento createToBeSavedTipoPagamento(){
        return TipoPagamento.builder()
                .nomePagamento("Pix")
                .build();
    }

    public static TipoPagamento createValidUpdateTipoPagamento(){
        return TipoPagamento.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .nomePagamento("Cartão de Credito")
                .build();
    }
}
