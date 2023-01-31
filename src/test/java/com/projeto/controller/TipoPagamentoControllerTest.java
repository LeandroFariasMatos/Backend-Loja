package com.projeto.controller;

import com.projeto.domain.TipoPagamento;
import com.projeto.service.TipoPagamentoService;
import com.projeto.site.util.*;
import com.projeto.util.TipoPagamentoCreator;
import com.projeto.util.TipoPagamentoPostRequestBodyCreator;
import com.projeto.util.TipoPagamentoPutRequestBodyCreator;
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
public class TipoPagamentoControllerTest {
    @InjectMocks
    private TipoPagamentoController tipoPagamentoController;

    @Mock
    private TipoPagamentoService tipoPagamentoServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<TipoPagamento> tipoPage = new PageImpl<>(List.of(TipoPagamentoCreator.createValidTipoPagamento()));

        BDDMockito.when(tipoPagamentoServiceMock.findAll(ArgumentMatchers.any())).thenReturn(tipoPage);
        BDDMockito.when(tipoPagamentoServiceMock.findByIdOrThrow(ArgumentMatchers.any())).thenReturn(TipoPagamentoCreator.createValidTipoPagamento());
        BDDMockito.when(tipoPagamentoServiceMock.findByNomePagamento(ArgumentMatchers.anyString())).thenReturn(TipoPagamentoCreator.createValidTipoPagamento());
        BDDMockito.when(tipoPagamentoServiceMock.save(ArgumentMatchers.any())).thenReturn(TipoPagamentoCreator.createToBeSavedTipoPagamento());
        BDDMockito.doNothing().when(tipoPagamentoServiceMock).replace(ArgumentMatchers.any());
        BDDMockito.doNothing().when(tipoPagamentoServiceMock).deletar(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Retorna lista de Tipo Pagamento dentro de uma page quando é sucesso")
    void listAll_ReturnsListOfTipoPagamentoInsidePageObject_WhenSuccessful(){
        UUID idEsperado = TipoPagamentoCreator.createValidTipoPagamento().getId();

        String nomePagamentoEsperado = TipoPagamentoCreator.createValidTipoPagamento().getNomePagamento();

        Page<TipoPagamento> tipoPagamentoPage = tipoPagamentoController.listAll(null).getBody();
        Assertions.assertThat(tipoPagamentoPage).isNotNull();
        Assertions.assertThat(tipoPagamentoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(tipoPagamentoPage.toList().get(0).getNomePagamento()).isEqualTo(nomePagamentoEsperado);
        Assertions.assertThat(tipoPagamentoPage.toList().get(0).getId()).isEqualTo(idEsperado);


    }

    @Test
    @DisplayName("Retorna um Tipo Pagamento quando é sucesso")
    void findById_ReturnsTipoPagamento_WhenSuccessful(){

        UUID idEsperado = TipoPagamentoCreator.createValidTipoPagamento().getId();
        String nomePagamentoEsperado = TipoPagamentoCreator.createValidTipoPagamento().getNomePagamento();

        TipoPagamento tipoPagamento= tipoPagamentoController.findById(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")).getBody();
        Assertions.assertThat(tipoPagamento).isNotNull();
        Assertions.assertThat(tipoPagamento.getNomePagamento()).isEqualTo(nomePagamentoEsperado);
        Assertions.assertThat(tipoPagamento.getId()).isEqualTo(idEsperado);


    }

    @Test
    @DisplayName("Retorna um Tipo Pagamento quando é sucesso")
    void findByNomePagamento_ReturnsTipoPagamento_WhenSuccessful(){

        UUID idEsperado = TipoPagamentoCreator.createValidTipoPagamento().getId();
        String nomePagamentoEsperado = TipoPagamentoCreator.createValidTipoPagamento().getNomePagamento();

        TipoPagamento tipoPagamento= tipoPagamentoController.findByNomePagamento("Pix").getBody();
        Assertions.assertThat(tipoPagamento).isNotNull();
        Assertions.assertThat(tipoPagamento.getNomePagamento()).isEqualTo(nomePagamentoEsperado);
        Assertions.assertThat(tipoPagamento.getId()).isEqualTo(idEsperado);


    }

    @Test
    @DisplayName("Retorna um Tipo Pagamento quando é salvo sucesso")
    void save_ReturnsTipoPagamento_WhenSuccessful(){

        String nomePagamentoEsperado = TipoPagamentoCreator.createToBeSavedTipoPagamento().getNomePagamento();

        TipoPagamento tipoPagamento= tipoPagamentoController.save(TipoPagamentoPostRequestBodyCreator.createTipoPagamentoPostRequestBody()).getBody();
        Assertions.assertThat(tipoPagamento).isNotNull();
        Assertions.assertThat(tipoPagamento.getNomePagamento()).isEqualTo(nomePagamentoEsperado);


    }

    @Test
    @DisplayName("Muda o nome do Tipo Pagamento quando é sucesso")
    void replace_TipoPagamento_WhenSuccessful(){

        Assertions.assertThatCode(()->tipoPagamentoController.replace(TipoPagamentoPutRequestBodyCreator.createTipoPagamentoPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = tipoPagamentoController.replace(TipoPagamentoPutRequestBodyCreator.createTipoPagamentoPutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("Deleta o Tipo Pagamento quando é sucesso")
    void delete_TipoPagamento_WhenSuccessful(){

        Assertions.assertThatCode(()->tipoPagamentoController.delete(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = tipoPagamentoController.delete(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}
