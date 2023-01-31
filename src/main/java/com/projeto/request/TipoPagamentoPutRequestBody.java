package com.projeto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoPagamentoPutRequestBody {
    private UUID id;
    private String nomePagamento;
}
