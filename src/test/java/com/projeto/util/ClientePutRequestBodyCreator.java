package com.projeto.util;

import com.projeto.request.ClientePutRequestBody;

public class ClientePutRequestBodyCreator {

    public static ClientePutRequestBody createValidClientePutRequestBody(){
        return ClientePutRequestBody
                .builder()
                .id(ClienteCreator.createValidUpdateCliente().getId())
                .email(ClienteCreator.createValidUpdateCliente().getEmail())
                .nomeCliente(ClienteCreator.createValidUpdateCliente().getNomeCliente())
                .endereco(ClienteCreator.createValidUpdateCliente().getEndereco())
                .telefone(ClienteCreator.createValidUpdateCliente().getTelefone())
                .senha(ClienteCreator.createValidUpdateCliente().getSenha())
                .cpf(ClienteCreator.createValidUpdateCliente().getCpf())
                .saldo(ClienteCreator.createValidUpdateCliente().getSaldo())
                .role(ClienteCreator.createValidUpdateCliente().getRole())
                .build();
    }
}
