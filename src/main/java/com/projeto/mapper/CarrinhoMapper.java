package com.projeto.mapper;

import com.projeto.domain.Carrinho;
import com.projeto.request.CarrinhoPostRequestBody;
import com.projeto.request.CarrinhoPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CarrinhoMapper {
    public static final CarrinhoMapper INSTANCE = Mappers.getMapper(CarrinhoMapper.class);

    public abstract Carrinho toCarrinho(CarrinhoPostRequestBody carrinhoPostRequestBody);
    public abstract Carrinho toCarrinho(CarrinhoPutRequestBody carrinhoPutRequestBody);
}
