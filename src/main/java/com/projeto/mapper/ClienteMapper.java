package com.projeto.mapper;

import com.projeto.request.ClientePostRequestBody;
import com.projeto.domain.Cliente;
import com.projeto.request.ClienteLoginRequestBody;
import com.projeto.request.ClientePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {
    public static final ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    public abstract Cliente toCliente(ClientePostRequestBody clientePostRequestBody);
    public abstract Cliente toCliente(ClientePutRequestBody clientePutRequestBody);

    public abstract Cliente toCliente(ClienteLoginRequestBody clienteLoginRequestBody);
}
