package com.projeto.config;

import com.projeto.domain.Cliente;
import com.projeto.domain.Role;
import com.projeto.exceptions.BadRequestException;
import com.projeto.repository.ClienteRepository;
import com.projeto.request.ClienteLoginRequestBody;
import com.projeto.request.ClientePostRequestBody;
import com.projeto.request.ClientePutRequestBody;
import com.projeto.service.ClienteService;
import com.projeto.util.ValidacaoCPF;
import com.projeto.util.ValidacaoEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse registro(ClientePostRequestBody clientePostRequestBody)  {
        if(!ValidacaoEmail.emailValidator(clientePostRequestBody.getEmail())){
            throw new BadRequestException("EMAIL INVALIDO");
        }else if(!ValidacaoCPF.isCPF(clientePostRequestBody.getCpf())){
            throw new BadRequestException("CPF INVALIDO");
        }
        var cliente = Cliente.builder()
                .nomeCliente(clientePostRequestBody.getNomeCliente())
                .email(clientePostRequestBody.getEmail())
                .cpf(clientePostRequestBody.getCpf())
                .endereco(clientePostRequestBody.getEndereco())
                .telefone(clientePostRequestBody.getTelefone())
                .senha(passwordEncoder.encode(clientePostRequestBody.getSenha()))
                .saldo(clientePostRequestBody.getSaldo())
                .role(Role.USER)
                .build();
        clienteRepository.save(cliente);
        var jwtToken = jwtService.generateToken(cliente);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public AuthenticationResponse login(ClienteLoginRequestBody clienteLoginRequestBody){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        clienteLoginRequestBody.getEmail(),
                        clienteLoginRequestBody.getSenha()
                )
        );
        var cliente = clienteRepository.findByEmail(clienteLoginRequestBody.getEmail());
        if(cliente.getNomeCliente().isEmpty()){
            throw new BadRequestException("Email Invalido");
        }
        var jwtToken = jwtService.generateToken(cliente);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse replace(ClientePutRequestBody clientePutRequestBody){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        clientePutRequestBody.getEmail(),
                        clientePutRequestBody.getSenha())
        );

        clientePutRequestBody.setSenha(passwordEncoder.encode(clientePutRequestBody.getSenha()));
        clienteService.replace(clientePutRequestBody);
        Cliente cliente = clienteRepository.findByEmail(clientePutRequestBody.getEmail());

        var jwtToken = jwtService.generateToken(cliente);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}

