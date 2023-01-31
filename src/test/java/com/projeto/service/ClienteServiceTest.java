package com.projeto.service;

import com.projeto.domain.Cliente;
import com.projeto.domain.Role;
import com.projeto.repository.ClienteRepository;
import com.projeto.util.ClienteCreator;
import com.projeto.util.ClientePostRequestBodyCreator;
import com.projeto.util.ClientePutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Cliente> clientePage = new PageImpl<>(List.of(ClienteCreator.createValidCliente()));

        BDDMockito.when(clienteRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(clientePage);
        BDDMockito.when(clienteRepositoryMock.findById(ArgumentMatchers.any())).thenReturn(Optional.of(ClienteCreator.createValidCliente()));
        BDDMockito.when(clienteRepositoryMock.findByNomeCliente(ArgumentMatchers.any())).thenReturn(List.of(ClienteCreator.createValidCliente()));
        BDDMockito.when(clienteRepositoryMock.findByEmail(ArgumentMatchers.any())).thenReturn(ClienteCreator.createValidCliente());
        BDDMockito.when(clienteRepositoryMock.save(ArgumentMatchers.any())).thenReturn(ClienteCreator.createValidCliente());
    }

    @Test
    @DisplayName("Retorna lista de clientes dentro de uma page quando é sucesso")
    void findAll_ReturnsListOfClientesInsidePageObject_WhenSuccessful(){

        UUID idEsperado = ClienteCreator.createValidCliente().getId();
        String nomeEsperado = ClienteCreator.createValidCliente().getNomeCliente();
        String emailEsperado = ClienteCreator.createValidCliente().getEmail();
        String telefoneEsperado = ClienteCreator.createValidCliente().getTelefone();
        String cpfEsperado = ClienteCreator.createValidCliente().getCpf();
        String enderecoEsperado = ClienteCreator.createValidCliente().getEndereco();
        String senhaEsperado = ClienteCreator.createValidCliente().getSenha();
        double saldoEsperado = ClienteCreator.createValidCliente().getSaldo();
        Role roleEsperado = ClienteCreator.createValidCliente().getRole();

        Page<Cliente> clientePage = clienteService.findAll(PageRequest.of(1,1));

        Assertions.assertThat(clientePage).isNotNull();
        Assertions.assertThat(clientePage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(clientePage.toList().get(0).getId()).isEqualTo(idEsperado);
        Assertions.assertThat(clientePage.toList().get(0).getNomeCliente()).isEqualTo(nomeEsperado);
        Assertions.assertThat(clientePage.toList().get(0).getEmail()).isEqualTo(emailEsperado);
        Assertions.assertThat(clientePage.toList().get(0).getTelefone()).isEqualTo(telefoneEsperado);
        Assertions.assertThat(clientePage.toList().get(0).getCpf()).isEqualTo(cpfEsperado);
        Assertions.assertThat(clientePage.toList().get(0).getEndereco()).isEqualTo(enderecoEsperado);
        Assertions.assertThat(clientePage.toList().get(0).getSenha()).isEqualTo(senhaEsperado);
        Assertions.assertThat(clientePage.toList().get(0).getSaldo()).isEqualByComparingTo(saldoEsperado);
        Assertions.assertThat(clientePage.toList().get(0).getRole()).isEqualTo(roleEsperado);

    }

    @Test
    @DisplayName("Retorna um Cliente quando é sucesso")
    void findById_ReturnsCliente_WhenSuccessful(){

        UUID idEsperado = ClienteCreator.createValidCliente().getId();
        String nomeEsperado = ClienteCreator.createValidCliente().getNomeCliente();
        String emailEsperado = ClienteCreator.createValidCliente().getEmail();
        String telefoneEsperado = ClienteCreator.createValidCliente().getTelefone();
        String cpfEsperado = ClienteCreator.createValidCliente().getCpf();
        String enderecoEsperado = ClienteCreator.createValidCliente().getEndereco();
        String senhaEsperado = ClienteCreator.createValidCliente().getSenha();
        double saldoEsperado = ClienteCreator.createValidCliente().getSaldo();
        Role roleEsperado = ClienteCreator.createValidCliente().getRole();

        Cliente cliente = clienteService.findByIdOrThrow(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));
        Assertions.assertThat(cliente).isNotNull();
        Assertions.assertThat(cliente.getId()).isEqualTo(idEsperado);
        Assertions.assertThat(cliente.getNomeCliente()).isEqualTo(nomeEsperado);
        Assertions.assertThat(cliente.getEmail()).isEqualTo(emailEsperado);
        Assertions.assertThat(cliente.getTelefone()).isEqualTo(telefoneEsperado);
        Assertions.assertThat(cliente.getCpf()).isEqualTo(cpfEsperado);
        Assertions.assertThat(cliente.getEndereco()).isEqualTo(enderecoEsperado);
        Assertions.assertThat(cliente.getSenha()).isEqualTo(senhaEsperado);
        Assertions.assertThat(cliente.getSaldo()).isEqualByComparingTo(saldoEsperado);
        Assertions.assertThat(cliente.getRole()).isEqualTo(roleEsperado);
    }

    @Test
    @DisplayName("Retorna uma Lista de Clientes quando é sucesso")
    void findByNomeCliente_ReturnsListOfClientes_WhenSuccessful(){

        UUID idEsperado = ClienteCreator.createValidCliente().getId();
        String nomeEsperado = ClienteCreator.createValidCliente().getNomeCliente();
        String emailEsperado = ClienteCreator.createValidCliente().getEmail();
        String telefoneEsperado = ClienteCreator.createValidCliente().getTelefone();
        String cpfEsperado = ClienteCreator.createValidCliente().getCpf();
        String enderecoEsperado = ClienteCreator.createValidCliente().getEndereco();
        String senhaEsperado = ClienteCreator.createValidCliente().getSenha();
        double saldoEsperado = ClienteCreator.createValidCliente().getSaldo();
        Role roleEsperado = ClienteCreator.createValidCliente().getRole();

        List<Cliente> cliente = clienteService.findByNomeCliente("Leandro");
        Assertions.assertThat(cliente).isNotNull();
        Assertions.assertThat(cliente).isNotEmpty();
        Assertions.assertThat(cliente.get(0).getId()).isEqualTo(idEsperado);
        Assertions.assertThat(cliente.get(0).getNomeCliente()).isEqualTo(nomeEsperado);
        Assertions.assertThat(cliente.get(0).getEmail()).isEqualTo(emailEsperado);
        Assertions.assertThat(cliente.get(0).getTelefone()).isEqualTo(telefoneEsperado);
        Assertions.assertThat(cliente.get(0).getCpf()).isEqualTo(cpfEsperado);
        Assertions.assertThat(cliente.get(0).getEndereco()).isEqualTo(enderecoEsperado);
        Assertions.assertThat(cliente.get(0).getSenha()).isEqualTo(senhaEsperado);
        Assertions.assertThat(cliente.get(0).getSaldo()).isEqualByComparingTo(saldoEsperado);
        Assertions.assertThat(cliente.get(0).getRole()).isEqualTo(roleEsperado);
    }

    @Test
    @DisplayName("Retorna um Cliente quando é sucesso")
    void findByEmail_ReturnsCliente_WhenSuccessful(){

        UUID idEsperado = ClienteCreator.createValidCliente().getId();
        String nomeEsperado = ClienteCreator.createValidCliente().getNomeCliente();
        String emailEsperado = ClienteCreator.createValidCliente().getEmail();
        String telefoneEsperado = ClienteCreator.createValidCliente().getTelefone();
        String cpfEsperado = ClienteCreator.createValidCliente().getCpf();
        String enderecoEsperado = ClienteCreator.createValidCliente().getEndereco();
        String senhaEsperado = ClienteCreator.createValidCliente().getSenha();
        double saldoEsperado = ClienteCreator.createValidCliente().getSaldo();
        Role roleEsperado = ClienteCreator.createValidCliente().getRole();

        Cliente cliente = clienteService.findByEmail("leandromatos95@gmail.com");
        Assertions.assertThat(cliente).isNotNull();
        Assertions.assertThat(cliente.getId()).isEqualTo(idEsperado);
        Assertions.assertThat(cliente.getNomeCliente()).isEqualTo(nomeEsperado);
        Assertions.assertThat(cliente.getEmail()).isEqualTo(emailEsperado);
        Assertions.assertThat(cliente.getTelefone()).isEqualTo(telefoneEsperado);
        Assertions.assertThat(cliente.getCpf()).isEqualTo(cpfEsperado);
        Assertions.assertThat(cliente.getEndereco()).isEqualTo(enderecoEsperado);
        Assertions.assertThat(cliente.getSenha()).isEqualTo(senhaEsperado);
        Assertions.assertThat(cliente.getSaldo()).isEqualByComparingTo(saldoEsperado);
        Assertions.assertThat(cliente.getRole()).isEqualTo(roleEsperado);
    }

    @Test
    @DisplayName("Retorna um Cliente quando é salvo com sucesso")
    void save_ReturnsCliente_WhenSuccessful(){

        UUID idEsperado = ClienteCreator.createValidCliente().getId();
        String nomeEsperado = ClienteCreator.createValidCliente().getNomeCliente();
        String emailEsperado = ClienteCreator.createValidCliente().getEmail();
        String telefoneEsperado = ClienteCreator.createValidCliente().getTelefone();
        String cpfEsperado = ClienteCreator.createValidCliente().getCpf();
        String enderecoEsperado = ClienteCreator.createValidCliente().getEndereco();
        String senhaEsperado = ClienteCreator.createValidCliente().getSenha();
        double saldoEsperado = ClienteCreator.createValidCliente().getSaldo();
        Role roleEsperado = ClienteCreator.createValidCliente().getRole();

        Cliente cliente = clienteService.save(ClientePostRequestBodyCreator.createValidClientePostRequestBody());
        Assertions.assertThat(cliente).isNotNull();
        Assertions.assertThat(cliente.getId()).isEqualTo(idEsperado);
        Assertions.assertThat(cliente.getNomeCliente()).isEqualTo(nomeEsperado);
        Assertions.assertThat(cliente.getEmail()).isEqualTo(emailEsperado);
        Assertions.assertThat(cliente.getTelefone()).isEqualTo(telefoneEsperado);
        Assertions.assertThat(cliente.getCpf()).isEqualTo(cpfEsperado);
        Assertions.assertThat(cliente.getEndereco()).isEqualTo(enderecoEsperado);
        Assertions.assertThat(cliente.getSenha()).isEqualTo(senhaEsperado);
        Assertions.assertThat(cliente.getSaldo()).isEqualByComparingTo(saldoEsperado);
        Assertions.assertThat(cliente.getRole()).isEqualTo(roleEsperado);
    }

    @Test
    @DisplayName("atualiza um cliente quando é sucesso")
    void replace_Cliente_WhenSuccessful(){
        Assertions.assertThatCode(()->clienteService.replace(ClientePutRequestBodyCreator.createValidClientePutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete remove um cliente que é sucesso")
    void delete_Cliente_WhenSuccessful(){
        Assertions.assertThatCode(()->clienteService.delete("leandromatos95@gmail.com"))
                .doesNotThrowAnyException();


    }



}
