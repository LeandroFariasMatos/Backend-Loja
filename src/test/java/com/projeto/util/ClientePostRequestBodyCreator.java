package com.projeto.util;

import com.projeto.request.ClientePostRequestBody;

public class ClientePostRequestBodyCreator {

    public static ClientePostRequestBody createValidClientePostRequestBody(){
        return ClientePostRequestBody
                .builder()
                .nomeCliente(ClienteCreator.createClienteToBeSaved().getNomeCliente())
                .email(ClienteCreator.createClienteToBeSaved().getEmail())
                .cpf(ClienteCreator.createClienteToBeSaved().getCpf())
                .senha(ClienteCreator.createClienteToBeSaved().getSenha())
                .endereco(ClienteCreator.createClienteToBeSaved().getEndereco())
                .saldo(ClienteCreator.createClienteToBeSaved().getSaldo())
                .telefone(ClienteCreator.createClienteToBeSaved().getTelefone())
                .build();
    }
}
