package com.projeto.util;

import com.projeto.request.VendasPostRequestBody;

public class VendasPostRequestBodyCreator {

    public static VendasPostRequestBody createValidVendasPostRequestBody(){
        return VendasPostRequestBody
                .builder()
                .cliente(VendasCreator.createToBeSavedVendas().getCliente())
                .pagamento(VendasCreator.createToBeSavedVendas().getPagamento())
                .carrinho(VendasCreator.createToBeSavedVendas().getCarrinho())
                .build();
    }
}
