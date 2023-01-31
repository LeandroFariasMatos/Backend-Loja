package com.projeto.controller;

import com.projeto.domain.Produto;
import com.projeto.exceptions.BadRequestException;
import com.projeto.request.CarrinhoPostRequestBody;
import com.projeto.request.CarrinhoPutRequestBody;
import com.projeto.service.CarrinhoService;
import com.projeto.service.ProdutoService;
import com.projeto.domain.Carrinho;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("loja")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Log4j2
public class CarrinhoController {
    private final ProdutoService produtoService;

    private final CarrinhoService carrinhoService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/carrinho/todos")
    public ResponseEntity<Page<Carrinho>> listAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Carrinho> pageCarrinho = carrinhoService.findAll(pageable);
        return new ResponseEntity<>(pageCarrinho,HttpStatus.OK);
    }

    @GetMapping("/carrinho/{id}")
    public ResponseEntity<Carrinho> findById(@PathVariable UUID id){
        return ResponseEntity.ok(carrinhoService.findByIdOrThrow(id));
    }


    @GetMapping("/carrinho/idCliente")
    public ResponseEntity<Page<Carrinho>> findByIdCliente(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam UUID idCliente){
        Page<Carrinho> pageCarrinho = carrinhoService.findByIdCliente(pageable,idCliente);
        return new ResponseEntity<>(pageCarrinho,HttpStatus.OK);
    }

    @PostMapping("/carrinho/salvar")
    public ResponseEntity<Carrinho> save(@RequestBody CarrinhoPostRequestBody carrinhoPostRequestBody){
        Produto produto = produtoService.findByIdOrThrow(carrinhoPostRequestBody.getProduto().getId());
        if(carrinhoPostRequestBody.getQuantidadeSelecionada() <= produto.getQuantidadeEmEstoque()) {
            return new ResponseEntity<>(carrinhoService.save(carrinhoPostRequestBody), HttpStatus.CREATED);
        }else{
            throw new BadRequestException("Quantidade selecionada maior que a quantidade em estoque");
        }

    }

    @PutMapping("/carrinho/update")
    public ResponseEntity<Void> replace(@RequestBody CarrinhoPutRequestBody carrinhoPutRequestBody ){
        carrinhoService.updateQntSelecionada(carrinhoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/carrinho/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        carrinhoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/carrinho/delete/idCliente/{id}")
    public ResponseEntity<Void> deleteIdCliente(@PathVariable UUID id){
        carrinhoService.deletarIdCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
