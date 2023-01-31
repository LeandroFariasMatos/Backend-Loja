package com.projeto.service;

import com.projeto.exceptions.BadRequestException;
import com.projeto.domain.Carrinho;
import com.projeto.domain.Produto;
import com.projeto.mapper.CarrinhoMapper;
import com.projeto.repository.CarrinhoRepository;
import com.projeto.request.CarrinhoPostRequestBody;
import com.projeto.request.CarrinhoPutRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoService produtoService;

    public Page<Carrinho> findAll(Pageable pageable){
        return carrinhoRepository.findAll(pageable);
    }

    public Carrinho findByIdOrThrow(UUID id) {
        return carrinhoRepository.findById(id).orElseThrow(()-> new BadRequestException("ID Carrinho Não encontrado"));
    }

    public Page<Carrinho> findByIdCliente(Pageable pageable,UUID id){
        return carrinhoRepository.findByCliente(pageable,id);
    }

    @Transactional
    public Carrinho save(CarrinhoPostRequestBody carrinhoPostRequestBody) {
        Carrinho carrinho = CarrinhoMapper.INSTANCE.toCarrinho(carrinhoPostRequestBody);
        Produto produto = produtoService.findByIdOrThrow(carrinho.getProduto().getId());
        carrinho.setPreco(carrinho.getQuantidadeSelecionada() *produto.getPreco());
        return carrinhoRepository.save(carrinho);

    }


    @Transactional
    public void finalizarCompra(UUID id) {
        List<Carrinho> carrinhos = carrinhoRepository.findByFinalizarCompra(id);
        for (Carrinho car : carrinhos) {
            UUID idSafe = car.getId();
            car.setStatus(1);
            car.setId(idSafe);
            carrinhoRepository.save(car);
        }

    }


    @Transactional
    public void deletar(UUID id) {
         carrinhoRepository.deleteById(id);
    }

    @Transactional
    public void deletarIdCliente(UUID id) {
        carrinhoRepository.deletarIdCliente(id);
    }

    public void updateQntSelecionada(CarrinhoPutRequestBody carrinhoPutRequestBody) {
        Carrinho carrinhoSalvo = findByIdOrThrow(carrinhoPutRequestBody.getId());
        Produto produto = produtoService.findByIdOrThrow(carrinhoSalvo.getProduto().getId());
        carrinhoSalvo.setId(carrinhoSalvo.getId());
        carrinhoSalvo.setQuantidadeSelecionada(carrinhoPutRequestBody.getQuantidadeSelecionada());
        if(carrinhoSalvo.getQuantidadeSelecionada() <= produto.getQuantidadeEmEstoque()){
            carrinhoSalvo.setPreco(carrinhoSalvo.getQuantidadeSelecionada() * produto.getPreco());
            carrinhoRepository.save(carrinhoSalvo);
        }else{
            throw new BadRequestException("Quantidade selecionada maior que a do estoque");
        }

    }
}
