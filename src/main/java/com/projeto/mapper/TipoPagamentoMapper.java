package com.projeto.mapper;

import com.projeto.domain.TipoPagamento;
import com.projeto.request.TipoPagamentoPostRequestBody;
import com.projeto.request.TipoPagamentoPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TipoPagamentoMapper {
    public static final TipoPagamentoMapper INSTANCE = Mappers.getMapper(TipoPagamentoMapper.class);

    public abstract TipoPagamento toTipoPagamento(TipoPagamentoPostRequestBody tipoPagamentoPostRequestBody);
    public abstract  TipoPagamento toTipoPagamento(TipoPagamentoPutRequestBody tipoPagamentoPutRequestBody);
}
