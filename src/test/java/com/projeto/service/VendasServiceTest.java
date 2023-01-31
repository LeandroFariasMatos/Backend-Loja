package com.projeto.service;

import com.projeto.repository.VendasRepository;
import com.projeto.domain.Carrinho;
import com.projeto.domain.Cliente;
import com.projeto.domain.TipoPagamento;
import com.projeto.domain.Vendas;
import com.projeto.repository.CarrinhoRepository;
import com.projeto.site.util.*;
import com.projeto.util.*;
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
public class VendasServiceTest {

    @InjectMocks
    private VendasService vendasService;

    @Mock
    private VendasRepository vendasRepositoryMock;

    @Mock
    private ClienteService clienteServiceMock;

    @Mock
    private CarrinhoService carrinhoServiceMock;

    @Mock
    private CarrinhoRepository carrinhoRepositoryMock;


    @Mock
    private ProdutoService produtoServiceMock;

    @BeforeEach()
    void setUp(){
        PageImpl<Vendas> vendasPage = new PageImpl<>(List.of(VendasCreator.createValidVendas()));

        BDDMockito.when(vendasRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(vendasPage);
        BDDMockito.when(vendasRepositoryMock.findById(ArgumentMatchers.any())).thenReturn(Optional.of(VendasCreator.createValidVendas()));
        BDDMockito.when(vendasRepositoryMock.save(ArgumentMatchers.any())).thenReturn(VendasCreator.createValidVendas());
        BDDMockito.doNothing().when(vendasRepositoryMock).deleteById(ArgumentMatchers.any());
        BDDMockito.when(clienteServiceMock.findByIdOrThrow(ArgumentMatchers.any())).thenReturn(ClienteCreator.createValidCliente());
        BDDMockito.doNothing().when(clienteServiceMock).replace(ArgumentMatchers.any());
        BDDMockito.doNothing().when(carrinhoServiceMock).finalizarCompra(ArgumentMatchers.any());
        BDDMockito.doNothing().when(produtoServiceMock).replace(ArgumentMatchers.any());
        BDDMockito.when(carrinhoRepositoryMock.findById(ArgumentMatchers.any())).thenReturn(Optional.of(CarrinhoCreator.createValidCarrinho()));
        BDDMockito.when(produtoServiceMock.findByIdOrThrow(ArgumentMatchers.any())).thenReturn(ProdutoCreator.createValidProduto());
    }

    @Test
    @DisplayName("Retorna uma lista de Vendas dentro de uma page quando é sucesso")
    void listAll_ReturnsListOfVendasInsidePageObject_WhenSuccessful() {
        UUID idEsperado = VendasCreator.createValidVendas().getId();
        Cliente clienteEsperado = VendasCreator.createValidVendas().getCliente();
        TipoPagamento tipoPagamentoEsperado = VendasCreator.createValidVendas().getPagamento();
        double valorDaVendaEsperado = VendasCreator.createValidVendas().getValorDaVenda();
        Carrinho carrinhoEsperado = VendasCreator.createValidVendas().getCarrinho();

        Page<Vendas> vendasPage = vendasService.findAll(PageRequest.of(1,1));

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

        Vendas vendas = vendasService.findByIdOrThrow(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));

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

        Vendas vendas = vendasService.save(VendasPostRequestBodyCreator.createValidVendasPostRequestBody());

        Assertions.assertThat(vendas).isNotNull();
        Assertions.assertThat(vendas.getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(vendas.getPagamento()).isEqualTo(tipoPagamentoEsperado);
        Assertions.assertThat(vendas.getCarrinho()).isEqualTo(carrinhoEsperado);
    }

    @Test
    @DisplayName("Altera uma Venda quando é sucesso")
    void update_Vendas_WhenSuccessful() {

        Assertions.assertThatCode(() -> vendasService.replace(VendasPutRequestBodyCreator.createValidVendasPutRequestBody()))
                .doesNotThrowAnyException();



    }

    @Test
    @DisplayName("Delete uma Venda quando é sucesso")
    void delete_Vendas_WhenSuccessful() {

        Assertions.assertThatCode(() -> vendasService.deletar(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();


    }
}
