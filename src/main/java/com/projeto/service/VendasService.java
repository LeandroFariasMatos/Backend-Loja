package com.projeto.service;

import com.projeto.domain.Produto;
import com.projeto.domain.Vendas;
import com.projeto.exceptions.BadRequestException;
import com.projeto.repository.VendasRepository;
import com.projeto.request.ProdutoPutRequestBody;
import com.projeto.request.VendasPutRequestBody;
import com.projeto.domain.Carrinho;
import com.projeto.domain.Cliente;
import com.projeto.mapper.VendasMapper;
import com.projeto.repository.CarrinhoRepository;
import com.projeto.request.ClientePutRequestBody;
import com.projeto.request.VendasPostRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VendasService {
    private final VendasRepository vendasRepository;

    private final CarrinhoRepository carrinhoRepository;

    private final ProdutoService produtoService;

    private final CarrinhoService carrinhoService;
    private final ClienteService clienteService;

    public Page<Vendas> findAll(Pageable pageable) {
        return vendasRepository.findAll(pageable);
    }

    public Vendas findByIdOrThrow(UUID id) {
        return vendasRepository.findById(id).orElseThrow(()-> new BadRequestException("ID de vendas não encontrado"));
    }


    @Transactional
    public Vendas save(VendasPostRequestBody vendasPostRequestBody) {
        Vendas vendas = VendasMapper.INSTANCE.toVendas(vendasPostRequestBody);

        Cliente cliente = clienteService.findByIdOrThrow(vendas.getCliente().getId());


        Optional<Carrinho> carrinho = carrinhoRepository.findById(vendasPostRequestBody.getCarrinho().getId());

        Produto produto = produtoService.findByIdOrThrow(carrinho.get().getProduto().getId());

        vendas.setValorDaVenda(produto.getPreco() * carrinho.get().getQuantidadeSelecionada());

        if(cliente.getSaldo() >= vendas.getValorDaVenda()){
            cliente.setSaldo(cliente.getSaldo() - vendas.getValorDaVenda());

            clienteService.replace(ClientePutRequestBody.builder()
                            .id(cliente.getId())
                            .nomeCliente(cliente.getNomeCliente())
                            .cpf(cliente.getCpf())
                            .senha(cliente.getSenha())
                            .email(cliente.getEmail())
                            .telefone(cliente.getTelefone())
                            .endereco(cliente.getEndereco())
                            .role(cliente.getRole())
                            .saldo(cliente.getSaldo())
                            .build());
        }else{
            throw new BadRequestException("Saldo menor que o valor de compra");
        }

        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - carrinho.get().getQuantidadeSelecionada());
        produto.setQuantidadeVendida(produto.getQuantidadeVendida()+ carrinho.get().getQuantidadeSelecionada());
        produtoService.replace(
                ProdutoPutRequestBody.builder()
                        .nomeProduto(produto.getNomeProduto())
                        .tipoProduto(produto.getTipoProduto())
                        .id(produto.getId())
                        .preco(produto.getPreco())
                        .caminhoImage(produto.getCaminhoImage())
                        .quantidadeVendida(produto.getQuantidadeVendida())
                        .quantidadeEmEstoque(produto.getQuantidadeEmEstoque())
                        .build());



        carrinhoService.finalizarCompra(carrinho.get().getCliente().getId());

        return vendasRepository.save(vendas);
    }

    public void replace(VendasPutRequestBody vendasPutRequestBody) {
        Vendas vendasSalvo = findByIdOrThrow(vendasPutRequestBody.getId());
        Vendas vendas = VendasMapper.INSTANCE.toVendas(vendasPutRequestBody);
        vendas.setId(vendasSalvo.getId());
        vendasRepository.save(vendas);
    }

    public void deletar(UUID id) {
        vendasRepository.deleteById(id);
    }
}
