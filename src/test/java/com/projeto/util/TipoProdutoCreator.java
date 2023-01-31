package com.projeto.util;

import com.projeto.domain.TipoProduto;

import java.util.UUID;

public class TipoProdutoCreator {
    public static TipoProduto createValidTipoProduto() {
        return TipoProduto.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .nomeTipoProduto("Eletrodomestico")
                .build();
    }

    public static TipoProduto createToBeSavedTipoProduto() {
        return TipoProduto.builder()
                .nomeTipoProduto("Eletrodomestico")
                .build();
    }

    public static TipoProduto createValidUpdateTipoProduto() {
        return TipoProduto.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .nomeTipoProduto("Lazer")
                .build();
    }


}
