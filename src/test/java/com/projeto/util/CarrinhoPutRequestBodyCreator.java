package com.projeto.util;

import com.projeto.request.CarrinhoPutRequestBody;

public class CarrinhoPutRequestBodyCreator {
    public static CarrinhoPutRequestBody createCarrinhoPutRequestBody(){
        return CarrinhoPutRequestBody.builder()
                .id(ClienteCreator.createValidCliente().getId())
                .quantidadeSelecionada(3)
                .build();

    }
}
