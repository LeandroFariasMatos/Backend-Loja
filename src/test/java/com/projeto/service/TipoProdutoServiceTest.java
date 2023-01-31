package com.projeto.service;

import com.projeto.repository.TipoProdutoRepository;
import com.projeto.domain.TipoProduto;
import com.projeto.util.TipoProdutoCreator;
import com.projeto.util.TipoProdutoPostRequestBodyCreator;
import com.projeto.util.TipoProdutoPutRequestBodyCreator;
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
public class TipoProdutoServiceTest {

    @InjectMocks
    private TipoProdutoService tipoProdutoService;

    @Mock
    private TipoProdutoRepository tipoProdutoRepositoryMock;

    @BeforeEach
    void setUp() {
        PageImpl<TipoProduto> tipoProdutoPage = new PageImpl<>(List.of(TipoProdutoCreator.createValidTipoProduto()));

        BDDMockito.when(tipoProdutoRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(tipoProdutoPage);
        BDDMockito.when(tipoProdutoRepositoryMock.findById(ArgumentMatchers.any())).thenReturn(Optional.of(TipoProdutoCreator.createValidTipoProduto()));
        BDDMockito.when(tipoProdutoRepositoryMock.findByNomeTipoProduto(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(tipoProdutoPage);
        BDDMockito.when(tipoProdutoRepositoryMock.save(ArgumentMatchers.any())).thenReturn(TipoProdutoCreator.createValidTipoProduto());
        BDDMockito.doNothing().when(tipoProdutoRepositoryMock).deleteById(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Retorna uma lista de Tipo Produto dentro de uma page quando é sucesso")
    void listAll_ReturnsListOfTipoProdutoInsidePageObject_WhenSuccessful() {
        UUID idEsperado = TipoProdutoCreator.createValidTipoProduto().getId();

        String nomeTipoProdutoEsperado = TipoProdutoCreator.createValidTipoProduto().getNomeTipoProduto();

        Page<TipoProduto> tipoProdutoPage = tipoProdutoService.findAll(PageRequest.of(1,1));
        Assertions.assertThat(tipoProdutoPage).isNotNull();
        Assertions.assertThat(tipoProdutoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(tipoProdutoPage.toList().get(0).getNomeTipoProduto()).isEqualTo(nomeTipoProdutoEsperado);
        Assertions.assertThat(tipoProdutoPage.toList().get(0).getId()).isEqualTo(idEsperado);

    }

    @Test
    @DisplayName("Retorna um Tipo Produto quando é sucesso")
    void findById_ReturnsTipoProduto_WhenSuccessful() {
        UUID idEsperado = TipoProdutoCreator.createValidTipoProduto().getId();

        String nomeTipoProdutoEsperado = TipoProdutoCreator.createValidTipoProduto().getNomeTipoProduto();

        TipoProduto tipoProduto = tipoProdutoService.findByIdOrThrow(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));
        Assertions.assertThat(tipoProduto).isNotNull();
        Assertions.assertThat(tipoProduto.getNomeTipoProduto()).isEqualTo(nomeTipoProdutoEsperado);
        Assertions.assertThat(tipoProduto.getId()).isEqualTo(idEsperado);

    }

    @Test
    @DisplayName("Retorna uma lista de Tipo Produto dentro de uma page quando é sucesso")
    void findByNomeTipoProduto_ReturnsListOfTipoProdutoInsidePageObject_WhenSuccessful() {
        UUID idEsperado = TipoProdutoCreator.createValidTipoProduto().getId();

        String nomeTipoProdutoEsperado = TipoProdutoCreator.createValidTipoProduto().getNomeTipoProduto();

        Page<TipoProduto> tipoProdutoPage = tipoProdutoService.findByNomeTipoProduto("Eletrodomestico", PageRequest.of(1,1));
        Assertions.assertThat(tipoProdutoPage).isNotNull();
        Assertions.assertThat(tipoProdutoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(tipoProdutoPage.toList().get(0).getNomeTipoProduto()).isEqualTo(nomeTipoProdutoEsperado);
        Assertions.assertThat(tipoProdutoPage.toList().get(0).getId()).isEqualTo(idEsperado);

    }


    @Test
    @DisplayName("Retorna um Tipo Produto quando é salvo com sucesso")
    void save_ReturnsTipoProduto_WhenSuccessful() {

        String nomeTipoProdutoEsperado = TipoProdutoCreator.createToBeSavedTipoProduto().getNomeTipoProduto();

        TipoProduto tipoProduto = tipoProdutoService
                .save(TipoProdutoPostRequestBodyCreator.createValidTipoProdutoPostRequestBody());

        Assertions.assertThat(tipoProduto).isNotNull();
        Assertions.assertThat(tipoProduto.getNomeTipoProduto()).isEqualTo(nomeTipoProdutoEsperado);

    }

    @Test
    @DisplayName("Altera o nome do Tipo Produto quando é sucesso")
    void update_TipoProduto_WhenSuccessful() {

        Assertions.assertThatCode(() -> tipoProdutoService.replace(TipoProdutoPutRequestBodyCreator.createValidTipoProdutoPutRequestBody()))
                .doesNotThrowAnyException();


    }

    @Test
    @DisplayName("Deleta o Tipo produto quando é sucesso")
    void delete_TipoPagamento_WhenSuccessful(){

        Assertions.assertThatCode(()->tipoProdutoService.deletar(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();

    }
}
