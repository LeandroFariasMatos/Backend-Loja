package com.projeto.service;

import com.projeto.exceptions.BadRequestException;
import com.projeto.domain.Produto;
import com.projeto.domain.TipoProduto;
import com.projeto.mapper.ProdutoMapper;
import com.projeto.repository.ProdutoRepository;
import com.projeto.request.ProdutoPostRequestBody;
import com.projeto.request.ProdutoPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Page<Produto> findAll(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }

    public Produto findByIdOrThrow(UUID id) {
        return produtoRepository.findById(id).orElseThrow(()-> new BadRequestException("id produto não foi encontrado"));
    }

    public Page<Produto> findByName(Pageable pageable,String nome) {
        return produtoRepository.findByNomeProduto(pageable,nome);
    }

    public Page<Produto> findByTipoProduto(Pageable pageable,UUID id){
        return produtoRepository.findByTipoProduto(pageable, TipoProduto.builder().id(id).build());
    }

    @Transient
    public Produto save(ProdutoPostRequestBody produtoPostRequestBody) {
        return produtoRepository.save(ProdutoMapper.INSTANCE.toProduto(produtoPostRequestBody));
    }

    @Transient
    public void replace(ProdutoPutRequestBody produtoPutRequestBody) {
        Produto produtoSalvo = findByIdOrThrow(produtoPutRequestBody.getId());
        Produto produto = ProdutoMapper.INSTANCE.toProduto(produtoPutRequestBody);
        produto.setId(produtoSalvo.getId());
        produtoRepository.save(produto);
    }


    public void deletar(UUID id) {
        produtoRepository.deleteById(id);
    }
}
