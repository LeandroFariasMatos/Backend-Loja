package com.projeto.controller;

import com.projeto.domain.Carrinho;
import com.projeto.domain.Cliente;
import com.projeto.domain.TipoPagamento;
import com.projeto.domain.Vendas;
import com.projeto.site.domain.*;
import com.projeto.service.VendasService;
import com.projeto.site.util.*;
import com.projeto.util.VendasCreator;
import com.projeto.util.VendasPostRequestBodyCreator;
import com.projeto.util.VendasPutRequestBodyCreator;
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
public class VendasControllerTest {

    @InjectMocks
    private VendasController vendasController;

    @Mock
    private VendasService vendasServiceMock;

    @BeforeEach()
    void setUp(){
        PageImpl<Vendas> vendasPage = new PageImpl<>(List.of(VendasCreator.createValidVendas()));

        BDDMockito.when(vendasServiceMock.findAll(ArgumentMatchers.any())).thenReturn(vendasPage);
        BDDMockito.when(vendasServiceMock.findByIdOrThrow(ArgumentMatchers.any())).thenReturn(VendasCreator.createValidVendas());
        BDDMockito.when(vendasServiceMock.save(ArgumentMatchers.any())).thenReturn(VendasCreator.createValidVendas());
        BDDMockito.doNothing().when(vendasServiceMock).replace(ArgumentMatchers.any());
        BDDMockito.doNothing().when(vendasServiceMock).deletar(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Retorna uma lista de Vendas dentro de uma page quando é sucesso")
    void listAll_ReturnsListOfVendasInsidePageObject_WhenSuccessful() {
        UUID idEsperado = VendasCreator.createValidVendas().getId();
        Cliente clienteEsperado = VendasCreator.createValidVendas().getCliente();
        TipoPagamento tipoPagamentoEsperado = VendasCreator.createValidVendas().getPagamento();
        double valorDaVendaEsperado = VendasCreator.createValidVendas().getValorDaVenda();
        Carrinho carrinhoEsperado = VendasCreator.createValidVendas().getCarrinho();

        Page<Vendas> vendasPage = vendasController.listAll(null).getBody();

        Assertions.assertThat(vendasPage).isNotNull();
        Assertions.assertThat(vendasPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(vendasPage.toList().get(0).getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(vendasPage.toList().get(0).getId()).isEqualTo(idEsperado);
        Assertions.assertThat(vendasPage.toList().get(0).getPagamento()).isEqualTo(tipoPagamentoEsperado);
        Assertions.assertThat(vendasPage.toList().get(0).getValorDaVenda()).isEqualTo(valorDaVendaEsperado);
        Assertions.assertThat(vendasPage.toList().get(0).getCarrinho()).isEqualTo(carrinhoEsperado);

    }

    @Test
    @DisplayName("Retorna uma Venda quando é sucesso")
    void findById_ReturnsVendas_WhenSuccessful() {
        UUID idEsperado = VendasCreator.createValidVendas().getId();
        Cliente clienteEsperado = VendasCreator.createValidVendas().getCliente();
        TipoPagamento tipoPagamentoEsperado = VendasCreator.createValidVendas().getPagamento();
        double valorDaVendaEsperado = VendasCreator.createValidVendas().getValorDaVenda();
        Carrinho carrinhoEsperado = VendasCreator.createValidVendas().getCarrinho();

        Vendas vendas = vendasController.findById(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")).getBody();

        Assertions.assertThat(vendas).isNotNull();
        Assertions.assertThat(vendas.getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(vendas.getId()).isEqualTo(idEsperado);
        Assertions.assertThat(vendas.getPagamento()).isEqualTo(tipoPagamentoEsperado);
        Assertions.assertThat(vendas.getValorDaVenda()).isEqualTo(valorDaVendaEsperado);
        Assertions.assertThat(vendas.getCarrinho()).isEqualTo(carrinhoEsperado);

    }

    @Test
    @DisplayName("Retorna uma Venda quando é salvo com sucesso")
    void save_ReturnsVendas_WhenSuccessful() {
        Cliente clienteEsperado = VendasCreator.createToBeSavedVendas().getCliente();
        TipoPagamento tipoPagamentoEsperado = VendasCreator.createToBeSavedVendas().getPagamento();
        Carrinho carrinhoEsperado = VendasCreator.createToBeSavedVendas().getCarrinho();

        Vendas vendas = vendasController.save(VendasPostRequestBodyCreator.createValidVendasPostRequestBody()).getBody();

        Assertions.assertThat(vendas).isNotNull();
        Assertions.assertThat(vendas.getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(vendas.getPagamento()).isEqualTo(tipoPagamentoEsperado);
        Assertions.assertThat(vendas.getCarrinho()).isEqualTo(carrinhoEsperado);
    }

    @Test
    @DisplayName("Altera uma Venda quando é sucesso")
    void update_Vendas_WhenSuccessful() {

        Assertions.assertThatCode(() -> vendasController.update(VendasPutRequestBodyCreator.createValidVendasPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = vendasController.update(VendasPutRequestBodyCreator.createValidVendasPutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("Delete uma Venda quando é sucesso")
    void delete_Vendas_WhenSuccessful() {

        Assertions.assertThatCode(() -> vendasController.delete(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = vendasController.delete(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}
