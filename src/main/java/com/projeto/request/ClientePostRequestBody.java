package com.projeto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePostRequestBody {
    private String nomeCliente;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private String endereco;
    private double saldo;

}
