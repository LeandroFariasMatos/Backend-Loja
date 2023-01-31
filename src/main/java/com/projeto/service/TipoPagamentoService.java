package com.projeto.service;

import com.projeto.domain.TipoPagamento;
import com.projeto.exceptions.BadRequestException;
import com.projeto.repository.TipoPagamentoRepository;
import com.projeto.mapper.TipoPagamentoMapper;
import com.projeto.request.TipoPagamentoPostRequestBody;
import com.projeto.request.TipoPagamentoPutRequestBody;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TipoPagamentoService {
    private final TipoPagamentoRepository tipoPagamentoRepository;

    public Page<TipoPagamento> findAll(Pageable pageable) {
        return tipoPagamentoRepository.findAll(pageable);
    }

    public TipoPagamento findByIdOrThrow(UUID id) {
        return tipoPagamentoRepository.findById(id).orElseThrow(()-> new BadRequestException("id tipoPagamento não encontrado"));
    }

    public TipoPagamento findByNomePagamento(String nome) {
        return tipoPagamentoRepository.findByNomePagamento(nome);
    }
    @Transient
    public TipoPagamento save(TipoPagamentoPostRequestBody tipoPagamentoPostRequestBody) {
        return tipoPagamentoRepository.save(TipoPagamentoMapper.INSTANCE.toTipoPagamento(tipoPagamentoPostRequestBody));
    }

    public void replace(TipoPagamentoPutRequestBody tipoPagamentoPutRequestBody) {
        TipoPagamento tipoPagamentoSalvo = findByIdOrThrow(tipoPagamentoPutRequestBody.getId());
        TipoPagamento tipoPagamento = TipoPagamentoMapper.INSTANCE.toTipoPagamento(tipoPagamentoPutRequestBody);
        tipoPagamento.setId(tipoPagamentoSalvo.getId());
        tipoPagamentoRepository.save(tipoPagamento);
    }

    public void deletar(UUID id) {
        tipoPagamentoRepository.deleteById(id);
    }
}
