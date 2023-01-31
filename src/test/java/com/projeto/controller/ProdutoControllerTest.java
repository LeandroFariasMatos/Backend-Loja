package com.projeto.controller;

import com.projeto.domain.Produto;
import com.projeto.domain.TipoProduto;
import com.projeto.service.ProdutoService;
import com.projeto.site.util.*;
import com.projeto.util.ProdutoCreator;
import com.projeto.util.ProdutoPostRequestBodyCreator;
import com.projeto.util.ProdutoPutRequestBodyCreator;
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
public class ProdutoControllerTest {
    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Produto> produtoPage = new PageImpl<>(List.of(ProdutoCreator.createValidProduto()));

        BDDMockito.when(produtoServiceMock.findAll(ArgumentMatchers.any())).thenReturn(produtoPage);
        BDDMockito.when(produtoServiceMock.findByIdOrThrow(ArgumentMatchers.any())).thenReturn(ProdutoCreator.createValidProduto());
        BDDMockito.when(produtoServiceMock.findByName(ArgumentMatchers.any(),ArgumentMatchers.anyString())).thenReturn(produtoPage);
        BDDMockito.when(produtoServiceMock.findByTipoProduto(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(produtoPage);
        BDDMockito.when(produtoServiceMock.save(ArgumentMatchers.any())).thenReturn(ProdutoCreator.createValidProduto());
        BDDMockito.doNothing().when(produtoServiceMock).replace(ArgumentMatchers.any());
        BDDMockito.doNothing().when(produtoServiceMock).deletar(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Retorna uma lista de Tipo Produto dentro de uma page quando é sucesso")
    void listAll_ReturnsListOfProdutoInsidePageObject_WhenSuccessful() {
        UUID idEsperado = ProdutoCreator.createValidProduto().getId();
        String nomeProdutoEsperado = ProdutoCreator.createValidProduto().getNomeProduto();
        TipoProduto tipoProdutoEsperado = ProdutoCreator.createValidProduto().getTipoProduto();
        double precoEsperado = ProdutoCreator.createValidProduto().getPreco();
        int qntVendidaEsperado = ProdutoCreator.createValidProduto().getQuantidadeVendida();
        int qntEmEstoqueEsperado = ProdutoCreator.createValidProduto().getQuantidadeEmEstoque();
        String caminhoImageEsperado = ProdutoCreator.createValidProduto().getCaminhoImage();



        Page<Produto> produtoPage = produtoController.listAll(null).getBody();
        Assertions.assertThat(produtoPage).isNotNull();
        Assertions.assertThat(produtoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(produtoPage.toList().get(0).getId()).isEqualTo(idEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getNomeProduto()).isEqualTo(nomeProdutoEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getTipoProduto()).isEqualTo(tipoProdutoEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getQuantidadeVendida()).isEqualTo(qntVendidaEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getQuantidadeEmEstoque()).isEqualTo(qntEmEstoqueEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getCaminhoImage()).isEqualTo(caminhoImageEsperado);


    }

    @Test
    @DisplayName("Retorna um Produto quando é sucesso")
    void findById_ReturnsProduto_WhenSuccessful() {
        UUID idEsperado = ProdutoCreator.createValidProduto().getId();
        String nomeProdutoEsperado = ProdutoCreator.createValidProduto().getNomeProduto();
        TipoProduto tipoProdutoEsperado = ProdutoCreator.createValidProduto().getTipoProduto();
        double precoEsperado = ProdutoCreator.createValidProduto().getPreco();
        int qntVendidaEsperado = ProdutoCreator.createValidProduto().getQuantidadeVendida();
        int qntEmEstoqueEsperado = ProdutoCreator.createValidProduto().getQuantidadeEmEstoque();
        String caminhoImageEsperado = ProdutoCreator.createValidProduto().getCaminhoImage();



        Produto produto = produtoController.findById(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")).getBody();
        Assertions.assertThat(produto).isNotNull();
        Assertions.assertThat(produto.getId()).isEqualTo(idEsperado);
        Assertions.assertThat(produto.getNomeProduto()).isEqualTo(nomeProdutoEsperado);
        Assertions.assertThat(produto.getTipoProduto()).isEqualTo(tipoProdutoEsperado);
        Assertions.assertThat(produto.getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(produto.getQuantidadeVendida()).isEqualTo(qntVendidaEsperado);
        Assertions.assertThat(produto.getQuantidadeEmEstoque()).isEqualTo(qntEmEstoqueEsperado);
        Assertions.assertThat(produto.getCaminhoImage()).isEqualTo(caminhoImageEsperado);


    }

    @Test
    @DisplayName("Retorna Retorna uma lista de Tipo Produto dentro de uma page quando é sucesso")
    void findByName_ReturnsListOfProdutoInsidePageObject_WhenSuccessful() {
        UUID idEsperado = ProdutoCreator.createValidProduto().getId();
        String nomeProdutoEsperado = ProdutoCreator.createValidProduto().getNomeProduto();
        TipoProduto tipoProdutoEsperado = ProdutoCreator.createValidProduto().getTipoProduto();
        double precoEsperado = ProdutoCreator.createValidProduto().getPreco();
        int qntVendidaEsperado = ProdutoCreator.createValidProduto().getQuantidadeVendida();
        int qntEmEstoqueEsperado = ProdutoCreator.createValidProduto().getQuantidadeEmEstoque();
        String caminhoImageEsperado = ProdutoCreator.createValidProduto().getCaminhoImage();

        Page<Produto> produtoPage = produtoController.findByName(null,"Geladeira").getBody();
        Assertions.assertThat(produtoPage).isNotNull();
        Assertions.assertThat(produtoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(produtoPage.toList().get(0).getId()).isEqualTo(idEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getNomeProduto()).isEqualTo(nomeProdutoEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getTipoProduto()).isEqualTo(tipoProdutoEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getQuantidadeVendida()).isEqualTo(qntVendidaEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getQuantidadeEmEstoque()).isEqualTo(qntEmEstoqueEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getCaminhoImage()).isEqualTo(caminhoImageEsperado);

    }

    @Test
    @DisplayName("Retorna Retorna uma lista de Tipo Produto dentro de uma page quando é sucesso")
    void findByTipoProduto_ReturnsListOfProdutoInsidePageObject_WhenSuccessful() {
        UUID idEsperado = ProdutoCreator.createValidProduto().getId();
        String nomeProdutoEsperado = ProdutoCreator.createValidProduto().getNomeProduto();
        TipoProduto tipoProdutoEsperado = ProdutoCreator.createValidProduto().getTipoProduto();
        double precoEsperado = ProdutoCreator.createValidProduto().getPreco();
        int qntVendidaEsperado = ProdutoCreator.createValidProduto().getQuantidadeVendida();
        int qntEmEstoqueEsperado = ProdutoCreator.createValidProduto().getQuantidadeEmEstoque();
        String caminhoImageEsperado = ProdutoCreator.createValidProduto().getCaminhoImage();

        Page<Produto> produtoPage = produtoController.findByTipoProduto(null,UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")).getBody();
        Assertions.assertThat(produtoPage).isNotNull();
        Assertions.assertThat(produtoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(produtoPage.toList().get(0).getId()).isEqualTo(idEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getNomeProduto()).isEqualTo(nomeProdutoEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getTipoProduto()).isEqualTo(tipoProdutoEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getQuantidadeVendida()).isEqualTo(qntVendidaEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getQuantidadeEmEstoque()).isEqualTo(qntEmEstoqueEsperado);
        Assertions.assertThat(produtoPage.toList().get(0).getCaminhoImage()).isEqualTo(caminhoImageEsperado);

    }

    @Test
    @DisplayName("Retorna um Produto quando é sucesso")
    void save_ReturnsProduto_WhenSuccessful() {
        String nomeProdutoEsperado = ProdutoCreator.createToBeSavedProduto().getNomeProduto();
        TipoProduto tipoProdutoEsperado = ProdutoCreator.createToBeSavedProduto().getTipoProduto();
        double precoEsperado = ProdutoCreator.createToBeSavedProduto().getPreco();
        int qntVendidaEsperado = ProdutoCreator.createToBeSavedProduto().getQuantidadeVendida();
        int qntEmEstoqueEsperado = ProdutoCreator.createToBeSavedProduto().getQuantidadeEmEstoque();
        String caminhoImageEsperado = ProdutoCreator.createToBeSavedProduto().getCaminhoImage();



        Produto produto = produtoController.save(ProdutoPostRequestBodyCreator.createValidProdutoPostRequestBody()).getBody();
        Assertions.assertThat(produto).isNotNull();
        Assertions.assertThat(produto.getNomeProduto()).isEqualTo(nomeProdutoEsperado);
        Assertions.assertThat(produto.getTipoProduto()).isEqualTo(tipoProdutoEsperado);
        Assertions.assertThat(produto.getPreco()).isEqualTo(precoEsperado);
        Assertions.assertThat(produto.getQuantidadeVendida()).isEqualTo(qntVendidaEsperado);
        Assertions.assertThat(produto.getQuantidadeEmEstoque()).isEqualTo(qntEmEstoqueEsperado);
        Assertions.assertThat(produto.getCaminhoImage()).isEqualTo(caminhoImageEsperado);

    }

    @Test
    @DisplayName("Altera o Produto quando é sucesso")
    void update_Produto_WhenSuccessful(){

        Assertions.assertThatCode(()->produtoController.replace(ProdutoPutRequestBodyCreator.createValidProdutoPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = produtoController.replace(ProdutoPutRequestBodyCreator.createValidProdutoPutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("Deleta o Produto quando é sucesso")
    void delete_Produto_WhenSuccessful(){

        Assertions.assertThatCode(()->produtoController.delete(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = produtoController.delete(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }


}
