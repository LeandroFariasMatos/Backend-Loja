package com.projeto.service;

import com.projeto.domain.TipoProduto;
import com.projeto.exceptions.BadRequestException;
import com.projeto.repository.TipoProdutoRepository;
import com.projeto.request.TipoProdutoPutRequestBody;
import com.projeto.mapper.TipoProdutoMapper;
import com.projeto.request.TipoProdutoPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TipoProdutoService {
    private final TipoProdutoRepository tipoProdutoRepository;


    public Page<TipoProduto> findAll(Pageable pageable) {
        return tipoProdutoRepository.findAll(pageable);
    }

    public TipoProduto findByIdOrThrow(UUID id) {
        return tipoProdutoRepository.findById(id).orElseThrow(()-> new BadRequestException("id Tipo Produto não encontrado"));
    }

    public Page<TipoProduto> findByNomeTipoProduto(String nome ,Pageable pageable) {
        return tipoProdutoRepository.findByNomeTipoProduto(nome,pageable);
    }

    @Transient
    public TipoProduto save(TipoProdutoPostRequestBody tipoProdutoPostRequestBody) {
        return tipoProdutoRepository.save(TipoProdutoMapper.INSTANCE.toTipoProduto(tipoProdutoPostRequestBody));
    }

    public void replace(TipoProdutoPutRequestBody tipoProdutoPutRequestBody) {
        TipoProduto tipoProdutoSalvo = findByIdOrThrow(tipoProdutoPutRequestBody.getId());
        TipoProduto tipoProduto = TipoProdutoMapper.INSTANCE.toTipoProduto(tipoProdutoPutRequestBody);
        tipoProduto.setId(tipoProdutoSalvo.getId());
        tipoProdutoRepository.save(tipoProduto);
    }

    public void deletar(UUID id) {
        tipoProdutoRepository.deleteById(id);
    }
}
