package com.projeto.mapper;

import com.projeto.domain.TipoProduto;
import com.projeto.request.TipoProdutoPostRequestBody;
import com.projeto.request.TipoProdutoPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TipoProdutoMapper {
    public static final TipoProdutoMapper INSTANCE = Mappers.getMapper(TipoProdutoMapper.class);

    public abstract TipoProduto toTipoProduto(TipoProdutoPostRequestBody tipoProdutoPostRequestBody);
    public abstract TipoProduto toTipoProduto(TipoProdutoPutRequestBody tipoProdutoPutRequestBody);
}
