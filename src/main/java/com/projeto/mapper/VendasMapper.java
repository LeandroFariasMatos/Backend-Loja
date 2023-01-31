package com.projeto.mapper;

import com.projeto.domain.Vendas;
import com.projeto.request.VendasPutRequestBody;
import com.projeto.request.VendasPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class VendasMapper {
    public static final VendasMapper INSTANCE = Mappers.getMapper(VendasMapper.class);

    public abstract Vendas toVendas(VendasPostRequestBody vendasPostRequestBody);
    public abstract Vendas toVendas(VendasPutRequestBody vendasPutRequestBody);
}
