package com.projeto.request;

import com.projeto.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePutRequestBody {
    private UUID id;
    private String nomeCliente;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private String endereco;
    private Role role = Role.USER;

    private double saldo;
}
