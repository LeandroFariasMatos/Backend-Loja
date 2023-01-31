package com.projeto.controller;

import com.projeto.domain.Produto;
import com.projeto.service.CarrinhoService;
import com.projeto.service.ProdutoService;
import com.projeto.domain.Carrinho;
import com.projeto.domain.Cliente;
import com.projeto.site.util.*;
import com.projeto.util.CarrinhoCreator;
import com.projeto.util.CarrinhoPostRequestBodyCreator;
import com.projeto.util.CarrinhoPutRequestBodyCreator;
import com.projeto.util.ProdutoCreator;
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
public class CarrinhoControllerTest {

    @InjectMocks
    private CarrinhoController carrinhoController;

    @Mock
    private CarrinhoService carrinhoServiceMock;

    @Mock
    private ProdutoService produtoServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Carrinho> carrinhoPage =  new PageImpl<>(List.of(CarrinhoCreator.createValidCarrinho()));
        BDDMockito.when(carrinhoServiceMock.findAll(ArgumentMatchers.any())).thenReturn(carrinhoPage);

        BDDMockito.when(carrinhoServiceMock.findByIdOrThrow(ArgumentMatchers.any())).thenReturn(CarrinhoCreator.createValidCarrinho());

        BDDMockito.when(carrinhoServiceMock.findByIdCliente(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(carrinhoPage);

        BDDMockito.when(produtoServiceMock.findByIdOrThrow(ArgumentMatchers.any())).thenReturn(ProdutoCreator.createValidProduto());

        BDDMockito.when(carrinhoServiceMock.save(ArgumentMatchers.any())).thenReturn(CarrinhoCreator.createToBeSavedCarrinho());

        BDDMockito.doNothing().when(carrinhoServiceMock).updateQntSelecionada(ArgumentMatchers.any());

        BDDMockito.doNothing().when(carrinhoServiceMock).deletar(ArgumentMatchers.any());
        BDDMockito.doNothing().when(carrinhoServiceMock).deletarIdCliente(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Retorna lista de Carrinhos dentro de uma page quando é sucesso")
    void listAll_ReturnsListOfCarrinhosInsidePageObject_WhenSuccessful(){

        Cliente clienteEsperado =CarrinhoCreator.createValidCarrinho().getCliente();
        Produto produtoEsperado =CarrinhoCreator.createValidCarrinho().getProduto();
        double precoEsperado =CarrinhoCreator.createValidCarrinho().getPreco();
        int quantidadeSelecionadaEsperada =CarrinhoCreator.createValidCarrinho().getQuantidadeSelecionada();
        int statusEsperado = CarrinhoCreator.createValidCarrinho().getStatus();



        Page<Carrinho> carrinhoPage = carrinhoController.listAll(null).getBody();
        Assertions.assertThat(carrinhoPage).isNotNull();
        Assertions.assertThat(carrinhoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(carrinhoPage.toList().get(0).getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getProduto()).isEqualTo(produtoEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getQuantidadeSelecionada()).isEqualTo(quantidadeSelecionadaEsperada);
        Assertions.assertThat(carrinhoPage.toList().get(0).getStatus()).isEqualTo(statusEsperado);

    }

    @Test
    @DisplayName("Retorna um carrinho  quando é sucesso")
    void findById_ReturnsCarrinho_WhenSuccessful(){

        Cliente clienteEsperado =CarrinhoCreator.createValidCarrinho().getCliente();
        Produto produtoEsperado =CarrinhoCreator.createValidCarrinho().getProduto();
        double precoEsperado =CarrinhoCreator.createValidCarrinho().getPreco();
        int quantidadeSelecionadaEsperada =CarrinhoCreator.createValidCarrinho().getQuantidadeSelecionada();
        int statusEsperado = CarrinhoCreator.createValidCarrinho().getStatus();



        Carrinho carrinho = carrinhoController.findById(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")).getBody();
        Assertions.assertThat(carrinho).isNotNull();
        Assertions.assertThat(carrinho.getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(carrinho.getProduto()).isEqualTo(produtoEsperado);
        Assertions.assertThat(carrinho.getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(carrinho.getQuantidadeSelecionada()).isEqualTo(quantidadeSelecionadaEsperada);
        Assertions.assertThat(carrinho.getStatus()).isEqualTo(statusEsperado);

    }

    @Test
    @DisplayName("Retorna uma lista de Carrinhos dentro de uma page quando é sucesso")
    void findByIdCliente_ReturnsListOfCarrinhosInsidePageObject_WhenSuccessful(){

        Cliente clienteEsperado =CarrinhoCreator.createValidCarrinho().getCliente();
        Produto produtoEsperado =CarrinhoCreator.createValidCarrinho().getProduto();
        double precoEsperado =CarrinhoCreator.createValidCarrinho().getPreco();
        int quantidadeSelecionadaEsperada =CarrinhoCreator.createValidCarrinho().getQuantidadeSelecionada();
        int statusEsperado = CarrinhoCreator.createValidCarrinho().getStatus();



        Page<Carrinho> carrinhoPage = carrinhoController.findByIdCliente(null,UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")).getBody();
        Assertions.assertThat(carrinhoPage).isNotNull();
        Assertions.assertThat(carrinhoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(carrinhoPage.toList().get(0).getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getProduto()).isEqualTo(produtoEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getQuantidadeSelecionada()).isEqualTo(quantidadeSelecionadaEsperada);
        Assertions.assertThat(carrinhoPage.toList().get(0).getStatus()).isEqualTo(statusEsperado);

    }

    @Test
    @DisplayName("Retorna um carrinho  quando é salvo com sucesso")
    void save_ReturnsCarrinho_WhenSuccessful(){

        UUID idEsperado = CarrinhoCreator.createToBeSavedCarrinho().getId();;
        Cliente clienteEsperado =CarrinhoCreator.createToBeSavedCarrinho().getCliente();
        Produto produtoEsperado =CarrinhoCreator.createToBeSavedCarrinho().getProduto();
        double precoEsperado =CarrinhoCreator.createToBeSavedCarrinho().getPreco();
        int quantidadeSelecionadaEsperada =CarrinhoCreator.createToBeSavedCarrinho().getQuantidadeSelecionada();
        int statusEsperado = CarrinhoCreator.createToBeSavedCarrinho().getStatus();



        Carrinho carrinho = carrinhoController.save(CarrinhoPostRequestBodyCreator.createCarrinhoPostRequestBody()).getBody();
        Assertions.assertThat(carrinho).isNotNull();
        Assertions.assertThat(carrinho.getId()).isEqualTo(idEsperado);
        Assertions.assertThat(carrinho.getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(carrinho.getProduto()).isEqualTo(produtoEsperado);
        Assertions.assertThat(carrinho.getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(carrinho.getQuantidadeSelecionada()).isEqualTo(quantidadeSelecionadaEsperada);
        Assertions.assertThat(carrinho.getStatus()).isEqualTo(statusEsperado);

    }

    @Test
    @DisplayName("Altera a quantidade Selecionada do carrinho quando é sucesso")
    void update_Carrinho_WhenSuccessful(){

        Assertions.assertThatCode(()->carrinhoController.replace(CarrinhoPutRequestBodyCreator.createCarrinhoPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = carrinhoController.replace(CarrinhoPutRequestBodyCreator.createCarrinhoPutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("deleta pelo ID o carrinho com sucesso")
    void delete_Carrinho_WhenSuccessful(){

        Assertions.assertThatCode(()->carrinhoController.delete(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = carrinhoController.delete(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("deleta pelo IDcliente o carrinho com sucesso")
    void deleteIdCliente_Carrinho_WhenSuccessful(){

        Assertions.assertThatCode(()->carrinhoController.deleteIdCliente(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = carrinhoController.deleteIdCliente(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}
