package com.projeto.controller;

import com.projeto.domain.Cliente;
import com.projeto.domain.Role;
import com.projeto.service.ClienteService;
import com.projeto.util.ClienteCreator;
import lombok.extern.log4j.Log4j2;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Log4j2
public class ClienteControllerTest {


    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Cliente> clientePage = new PageImpl<>(List.of(ClienteCreator.createValidCliente()));
        BDDMockito.when(clienteServiceMock.findAll(ArgumentMatchers.any())).thenReturn(clientePage);

        BDDMockito.when(clienteServiceMock.findByIdOrThrow(ArgumentMatchers.any())).thenReturn(ClienteCreator.createValidCliente());

        BDDMockito.when(clienteServiceMock.findByNomeCliente(ArgumentMatchers.anyString())).thenReturn(List.of(ClienteCreator.createValidCliente()));

        BDDMockito.when(clienteServiceMock.findByEmail(ArgumentMatchers.anyString())).thenReturn(ClienteCreator.createValidCliente());


        BDDMockito.doNothing().when(clienteServiceMock).delete(ClienteCreator.createValidCliente().getEmail());


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

        Page<Cliente> clientePage = clienteController.listAll(null).getBody();

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

        Cliente cliente = clienteController.findById(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")).getBody();
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

        List<Cliente> cliente = clienteController.findByNomeCliente("Leandro").getBody();
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
    void findByEmailCliente_ReturnsCliente_WhenSuccessful(){

        UUID idEsperado = ClienteCreator.createValidCliente().getId();
        String nomeEsperado = ClienteCreator.createValidCliente().getNomeCliente();
        String emailEsperado = ClienteCreator.createValidCliente().getEmail();
        String telefoneEsperado = ClienteCreator.createValidCliente().getTelefone();
        String cpfEsperado = ClienteCreator.createValidCliente().getCpf();
        String enderecoEsperado = ClienteCreator.createValidCliente().getEndereco();
        String senhaEsperado = ClienteCreator.createValidCliente().getSenha();
        double saldoEsperado = ClienteCreator.createValidCliente().getSaldo();
        Role roleEsperado = ClienteCreator.createValidCliente().getRole();

        Cliente cliente = clienteController.findByEmailCliente("Leandro").getBody();
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
    @DisplayName("delete remove um cliente que é sucesso")
    void delete_Cliente_WhenSuccessful(){
        Assertions.assertThatCode(()->clienteController.delete("leandromatos95@gmail.com"))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = clienteController.delete("leandromatos95@gmail.com");

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }






}
