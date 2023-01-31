package com.projeto.util;

import com.projeto.domain.Vendas;

import java.util.UUID;

public class VendasCreator {

    public static Vendas createValidVendas(){
        return Vendas.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .cliente(ClienteCreator.createValidCliente())
                .pagamento(TipoPagamentoCreator.createValidTipoPagamento())
                .carrinho(CarrinhoCreator.createValidCarrinho())
                .valorDaVenda(5000)
                .build();
    }


    public static Vendas createToBeSavedVendas(){
        return Vendas.builder()
                .cliente(ClienteCreator.createValidCliente())
                .pagamento(TipoPagamentoCreator.createValidTipoPagamento())
                .carrinho(CarrinhoCreator.createValidCarrinho())
                .valorDaVenda(5000)
                .build();
    }

    public static Vendas createUpdateVendas(){
        return Vendas.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .cliente(ClienteCreator.createValidCliente())
                .pagamento(TipoPagamentoCreator.createValidTipoPagamento())
                .carrinho(CarrinhoCreator.createValidCarrinho())
                .valorDaVenda(3000)
                .build();
    }
}
