package com.projeto.service;

import com.projeto.exceptions.BadRequestException;
import com.projeto.mapper.ClienteMapper;
import com.projeto.repository.ClienteRepository;
import com.projeto.request.ClientePostRequestBody;
import com.projeto.domain.Cliente;
import com.projeto.request.ClientePutRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    public Page<Cliente> findAll(Pageable pageable) {

        return clienteRepository.findAll(pageable);
    }

    public Cliente findByIdOrThrow(UUID id){
        return clienteRepository.findById(id).orElseThrow(()-> new BadRequestException("id Cliente não foi encontrado"));
    }


    public List<Cliente> findByNomeCliente(String nome) {
        return clienteRepository.findByNomeCliente(nome);
    }

    public Cliente findByEmail(String email){
        return clienteRepository.findByEmail(email);
    }


    @Transactional
    public Cliente save(ClientePostRequestBody clientePostRequestBody)  {

        return clienteRepository.save(ClienteMapper.INSTANCE.toCliente(clientePostRequestBody));
    }

    @Transactional
    public void replace(ClientePutRequestBody clientePutRequestBody) {
        Cliente clienteSalvo = findByIdOrThrow(clientePutRequestBody.getId());
        Cliente cliente = ClienteMapper.INSTANCE.toCliente(clientePutRequestBody);
        cliente.setId(clienteSalvo.getId());
        clienteRepository.save(cliente);


    }




    @Transactional
    public void delete(String email){
        Cliente cliente = findByEmail(email);
        if(cliente == null){
            throw new BadRequestException("Email não está registrado");
        }
        clienteRepository.delete(cliente);
    }

}
