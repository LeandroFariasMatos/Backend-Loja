package com.projeto.request;

import com.projeto.domain.TipoPagamento;
import com.projeto.domain.Carrinho;
import com.projeto.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VendasPostRequestBody {
    private Cliente cliente;
    private Carrinho carrinho;
    private TipoPagamento pagamento;




}
