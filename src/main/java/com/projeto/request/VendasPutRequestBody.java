package com.projeto.request;

import com.projeto.domain.TipoPagamento;
import com.projeto.domain.Carrinho;
import com.projeto.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendasPutRequestBody {
    private UUID id;
    private Cliente cliente_id;
    private Carrinho carrinho;
    private TipoPagamento pagamento_id;
    private double valorDaVenda;
}
