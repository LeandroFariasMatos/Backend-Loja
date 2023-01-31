package com.projeto.util;

import com.projeto.request.VendasPutRequestBody;

public class VendasPutRequestBodyCreator {

    public static VendasPutRequestBody createValidVendasPutRequestBody(){
        return VendasPutRequestBody
                .builder()
                .id(VendasCreator.createUpdateVendas().getId())
                .cliente_id(VendasCreator.createUpdateVendas().getCliente())
                .carrinho(VendasCreator.createUpdateVendas().getCarrinho())
                .pagamento_id(VendasCreator.createUpdateVendas().getPagamento())
                .valorDaVenda(VendasCreator.createUpdateVendas().getValorDaVenda())
                .build();
    }
}
