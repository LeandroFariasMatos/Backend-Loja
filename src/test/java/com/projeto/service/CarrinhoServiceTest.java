package com.projeto.service;

import com.projeto.domain.Carrinho;
import com.projeto.domain.Cliente;
import com.projeto.domain.Produto;
import com.projeto.repository.CarrinhoRepository;
import com.projeto.util.CarrinhoCreator;
import com.projeto.util.CarrinhoPostRequestBodyCreator;
import com.projeto.util.ProdutoCreator;
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
public class CarrinhoServiceTest {

    @InjectMocks
    private CarrinhoService carrinhoService;

    @Mock
    private CarrinhoRepository carrinhoRepositoryMock;

    @Mock
    private ProdutoService produtoServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Carrinho> carrinhoPage = new PageImpl<>(List.of(CarrinhoCreator.createValidCarrinho()));

        BDDMockito.when(carrinhoRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(carrinhoPage);
        BDDMockito.when(carrinhoRepositoryMock.findById(ArgumentMatchers.any())).thenReturn(Optional.of(CarrinhoCreator.createValidCarrinho()));
        BDDMockito.when(carrinhoRepositoryMock.findByCliente(ArgumentMatchers.any(PageRequest.class),ArgumentMatchers.any())).thenReturn(carrinhoPage);
        BDDMockito.when(carrinhoRepositoryMock.save(ArgumentMatchers.any())).thenReturn(CarrinhoCreator.createValidCarrinho());

        BDDMockito.when(produtoServiceMock.findByIdOrThrow(ArgumentMatchers.any())).thenReturn(ProdutoCreator.createValidProduto());
        BDDMockito.doNothing().when(carrinhoRepositoryMock).deleteById(ArgumentMatchers.any());
        BDDMockito.doNothing().when(carrinhoRepositoryMock).deletarIdCliente(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Retorna lista de Carrinhos dentro de uma page quando é sucesso")
    void listAll_ReturnsListOfCarrinhosInsidePageObject_WhenSuccessful(){
        UUID idEsperado = CarrinhoCreator.createValidCarrinho().getId();
        Cliente clienteEsperado = CarrinhoCreator.createValidCarrinho().getCliente();
        Produto produtoEsperado =CarrinhoCreator.createValidCarrinho().getProduto();
        double precoEsperado =CarrinhoCreator.createValidCarrinho().getPreco();
        int quantidadeSelecionadaEsperada =CarrinhoCreator.createValidCarrinho().getQuantidadeSelecionada();
        int statusEsperado = CarrinhoCreator.createValidCarrinho().getStatus();



        Page<Carrinho> carrinhoPage = carrinhoService.findAll(PageRequest.of(1,1));
        Assertions.assertThat(carrinhoPage).isNotNull();
        Assertions.assertThat(carrinhoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(carrinhoPage.toList().get(0).getId()).isEqualTo(idEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getProduto()).isEqualTo(produtoEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getQuantidadeSelecionada()).isEqualTo(quantidadeSelecionadaEsperada);
        Assertions.assertThat(carrinhoPage.toList().get(0).getStatus()).isEqualTo(statusEsperado);

    }

    @Test
    @DisplayName("Retorna um Carrinho quando é sucesso")
    void findById_ReturnsCarrinho_WhenSuccessful(){
        UUID idEsperado = CarrinhoCreator.createValidCarrinho().getId();
        Cliente clienteEsperado = CarrinhoCreator.createValidCarrinho().getCliente();
        Produto produtoEsperado =CarrinhoCreator.createValidCarrinho().getProduto();
        double precoEsperado =CarrinhoCreator.createValidCarrinho().getPreco();
        int quantidadeSelecionadaEsperada =CarrinhoCreator.createValidCarrinho().getQuantidadeSelecionada();
        int statusEsperado = CarrinhoCreator.createValidCarrinho().getStatus();



        Carrinho carrinho = carrinhoService.findByIdOrThrow(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));
        Assertions.assertThat(carrinho).isNotNull();
        Assertions.assertThat(carrinho.getId()).isEqualTo(idEsperado);
        Assertions.assertThat(carrinho.getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(carrinho.getProduto()).isEqualTo(produtoEsperado);
        Assertions.assertThat(carrinho.getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(carrinho.getQuantidadeSelecionada()).isEqualTo(quantidadeSelecionadaEsperada);
        Assertions.assertThat(carrinho.getStatus()).isEqualTo(statusEsperado);

    }

    @Test
    @DisplayName("Retorna lista de Carrinhos dentro de uma page quando é sucesso")
    void findByIdCliente_ReturnsListOfCarrinhosInsidePageObject_WhenSuccessful(){
        UUID idEsperado = CarrinhoCreator.createValidCarrinho().getId();
        Cliente clienteEsperado = CarrinhoCreator.createValidCarrinho().getCliente();
        Produto produtoEsperado =CarrinhoCreator.createValidCarrinho().getProduto();
        double precoEsperado =CarrinhoCreator.createValidCarrinho().getPreco();
        int quantidadeSelecionadaEsperada =CarrinhoCreator.createValidCarrinho().getQuantidadeSelecionada();
        int statusEsperado = CarrinhoCreator.createValidCarrinho().getStatus();



        Page<Carrinho> carrinhoPage = carrinhoService.findByIdCliente(PageRequest.of(1,1),UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));
        Assertions.assertThat(carrinhoPage).isNotNull();
        Assertions.assertThat(carrinhoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(carrinhoPage.toList().get(0).getId()).isEqualTo(idEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getProduto()).isEqualTo(produtoEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(carrinhoPage.toList().get(0).getQuantidadeSelecionada()).isEqualTo(quantidadeSelecionadaEsperada);
        Assertions.assertThat(carrinhoPage.toList().get(0).getStatus()).isEqualTo(statusEsperado);
    }

    @Test
    @DisplayName("Retorna um Carrinho quando é salvo com sucesso")
    void save_ReturnsCarrinho_WhenSuccessful(){
        Cliente clienteEsperado = CarrinhoCreator.createToBeSavedCarrinho().getCliente();
        Produto produtoEsperado =CarrinhoCreator.createToBeSavedCarrinho().getProduto();
        double precoEsperado =CarrinhoCreator.createToBeSavedCarrinho().getPreco();
        int quantidadeSelecionadaEsperada =CarrinhoCreator.createToBeSavedCarrinho().getQuantidadeSelecionada();
        int statusEsperado = CarrinhoCreator.createToBeSavedCarrinho().getStatus();



        Carrinho carrinho = carrinhoService.save(CarrinhoPostRequestBodyCreator.createCarrinhoPostRequestBody());
        Assertions.assertThat(carrinho).isNotNull();
        Assertions.assertThat(carrinho.getCliente()).isEqualTo(clienteEsperado);
        Assertions.assertThat(carrinho.getProduto()).isEqualTo(produtoEsperado);
        Assertions.assertThat(carrinho.getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(carrinho.getQuantidadeSelecionada()).isEqualTo(quantidadeSelecionadaEsperada);
        Assertions.assertThat(carrinho.getStatus()).isEqualTo(statusEsperado);

    }

    @Test
    @DisplayName("deleta pelo ID o carrinho com sucesso")
    void delete_Carrinho_WhenSuccessful(){

        Assertions.assertThatCode(()->carrinhoService.deletar(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();


    }

    @Test
    @DisplayName("deleta pelo IDcliente o carrinho com sucesso")
    void deleteIdCliente_Carrinho_WhenSuccessful(){

        Assertions.assertThatCode(()->carrinhoService.deletarIdCliente(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();


    }
}
