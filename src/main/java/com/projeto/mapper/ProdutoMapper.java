package com.projeto.mapper;

import com.projeto.request.ProdutoPostRequestBody;
import com.projeto.request.ProdutoPutRequestBody;
import com.projeto.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {
    public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    public abstract Produto toProduto(ProdutoPostRequestBody produtoPostRequestBody);


    public abstract Produto toProduto(ProdutoPutRequestBody ProdutoPutRequestBody);
}
