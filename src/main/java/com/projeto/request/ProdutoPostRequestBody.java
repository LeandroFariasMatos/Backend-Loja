package com.projeto.request;

import com.projeto.domain.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoPostRequestBody{
    private String nomeProduto;
    private TipoProduto tipoProduto;
    private double preco;
    private int quantidadeEmEstoque;
    private int quantidadeVendida;
    private String caminhoImage;


}
