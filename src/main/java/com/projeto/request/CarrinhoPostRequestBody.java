package com.projeto.request;

import com.projeto.domain.Cliente;
import com.projeto.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoPostRequestBody {
    private Produto produto;
    private Cliente cliente;
    private int quantidadeSelecionada;
    private int status=0;
}
