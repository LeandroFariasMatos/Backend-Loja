package com.projeto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoPutRequestBody {
    private UUID id;
    private int quantidadeSelecionada;
}
