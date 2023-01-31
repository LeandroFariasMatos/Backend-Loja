package com.projeto.service;

import com.projeto.domain.TipoPagamento;
import com.projeto.repository.TipoPagamentoRepository;
import com.projeto.util.TipoPagamentoCreator;
import com.projeto.util.TipoPagamentoPostRequestBodyCreator;
import com.projeto.util.TipoPagamentoPutRequestBodyCreator;
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
public class TipoPagamentoServiceTest {

    @InjectMocks
    private TipoPagamentoService tipoPagamentoService;

    @Mock
    private TipoPagamentoRepository tipoPagamentoRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<TipoPagamento> tipoPage = new PageImpl<>(List.of(TipoPagamentoCreator.createValidTipoPagamento()));

        BDDMockito.when(tipoPagamentoRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(tipoPage);
        BDDMockito.when(tipoPagamentoRepositoryMock.findById(ArgumentMatchers.any())).thenReturn(Optional.of(TipoPagamentoCreator.createValidTipoPagamento()));
        BDDMockito.when(tipoPagamentoRepositoryMock.findByNomePagamento(ArgumentMatchers.anyString())).thenReturn(TipoPagamentoCreator.createValidTipoPagamento());
        BDDMockito.when(tipoPagamentoRepositoryMock.save(ArgumentMatchers.any())).thenReturn(TipoPagamentoCreator.createToBeSavedTipoPagamento());
        BDDMockito.doNothing().when(tipoPagamentoRepositoryMock).deleteById(ArgumentMatchers.any());
    }


    @Test
    @DisplayName("Retorna lista de Tipo Pagamento dentro de uma page quando é sucesso")
    void listAll_ReturnsListOfTipoPagamentoInsidePageObject_WhenSuccessful(){
        UUID idEsperado = TipoPagamentoCreator.createValidTipoPagamento().getId();
        String nomePagamentoEsperado = TipoPagamentoCreator.createValidTipoPagamento().getNomePagamento();

        Page<TipoPagamento> tipoPagamentoPage = tipoPagamentoService.findAll(PageRequest.of(1,1));
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

        TipoPagamento tipoPagamento= tipoPagamentoService.findByIdOrThrow(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729"));
        Assertions.assertThat(tipoPagamento).isNotNull();
        Assertions.assertThat(tipoPagamento.getNomePagamento()).isEqualTo(nomePagamentoEsperado);
        Assertions.assertThat(tipoPagamento.getId()).isEqualTo(idEsperado);


    }

    @Test
    @DisplayName("Retorna um Tipo Pagamento quando é sucesso")
    void findByNomePagamento_ReturnsTipoPagamento_WhenSuccessful(){

        UUID idEsperado = TipoPagamentoCreator.createValidTipoPagamento().getId();
        String nomePagamentoEsperado = TipoPagamentoCreator.createValidTipoPagamento().getNomePagamento();

        TipoPagamento tipoPagamento= tipoPagamentoService.findByNomePagamento("Pix");
        Assertions.assertThat(tipoPagamento).isNotNull();
        Assertions.assertThat(tipoPagamento.getNomePagamento()).isEqualTo(nomePagamentoEsperado);
        Assertions.assertThat(tipoPagamento.getId()).isEqualTo(idEsperado);


    }

    @Test
    @DisplayName("Retorna um Tipo Pagamento quando é salvo sucesso")
    void save_ReturnsTipoPagamento_WhenSuccessful(){

        String nomePagamentoEsperado = TipoPagamentoCreator.createToBeSavedTipoPagamento().getNomePagamento();

        TipoPagamento tipoPagamento= tipoPagamentoService.save(TipoPagamentoPostRequestBodyCreator.createTipoPagamentoPostRequestBody());
        Assertions.assertThat(tipoPagamento).isNotNull();
        Assertions.assertThat(tipoPagamento.getNomePagamento()).isEqualTo(nomePagamentoEsperado);


    }

    @Test
    @DisplayName("Muda o nome do Tipo Pagamento quando é sucesso")
    void replace_TipoPagamento_WhenSuccessful(){

        Assertions.assertThatCode(()->tipoPagamentoService.replace(TipoPagamentoPutRequestBodyCreator.createTipoPagamentoPutRequestBody()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("Deleta o Tipo Pagamento quando é sucesso")
    void delete_TipoPagamento_WhenSuccessful(){

        Assertions.assertThatCode(()->tipoPagamentoService.deletar(UUID.fromString("0e5b51f6-96ed-4c79-8a57-b074ea874729")))
                .doesNotThrowAnyException();



    }
}
