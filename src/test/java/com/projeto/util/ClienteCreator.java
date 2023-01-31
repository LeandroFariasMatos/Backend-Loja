package com.projeto.util;

import com.projeto.domain.Cliente;
import com.projeto.domain.Role;

import java.util.UUID;

public class ClienteCreator {

    public static Cliente createClienteToBeSaved(){

        return Cliente.builder()
                .nomeCliente("Leandro")
                .email("leandromatos95@gmail.com")
                .cpf("19260356741")
                .senha("123456")
                .telefone("21999997777")
                .endereco("Rua Teste")
                .saldo(10000)
                .role(Role.USER)
                .build();
    }


    public static Cliente createValidCliente(){
        return Cliente.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .nomeCliente("Leandro")
                .email("leandromatos95@gmail.com")
                .cpf("19260356741")
                .senha("123456")
                .telefone("21999997777")
                .endereco("Rua Teste")
                .role(Role.USER)
                .saldo(10000)
                .build();
    }

    public static Cliente createValidUpdateCliente(){
        return Cliente.builder()
                .id(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"))
                .nomeCliente("Leandro2")
                .email("leandromatos95@gmail.com")
                .cpf("19260356741")
                .senha("123456")
                .telefone("21999997777")
                .endereco("Rua Teste")
                .role(Role.USER)
                .saldo(10000)
                .build();

    }
}
