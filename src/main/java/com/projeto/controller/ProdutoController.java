package com.projeto.controller;

import com.projeto.domain.Produto;
import com.projeto.request.ProdutoPostRequestBody;
import com.projeto.request.ProdutoPutRequestBody;
import com.projeto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("loja")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping("/produto/todos")
    public ResponseEntity<Page<Produto>> listAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Produto> pageProduto = produtoService.findAll(pageable);
        return new ResponseEntity<>(pageProduto, HttpStatus.OK);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> findById(@PathVariable UUID id){
        return ResponseEntity.ok(produtoService.findByIdOrThrow(id));
    }

    @GetMapping("/produto/nome")
    public ResponseEntity<Page<Produto>> findByName(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,@RequestParam String nome){
        Page<Produto> produtoPage = produtoService.findByName(pageable,nome);
        return new ResponseEntity<>(produtoPage,HttpStatus.OK);
    }

    @GetMapping("/produto/tipo")
    public ResponseEntity<Page<Produto>> findByTipoProduto(@PageableDefault(page = 0, size = 10, sort="id", direction = Sort.Direction.ASC) Pageable pageable,@RequestParam UUID id){
        Page<Produto> produtoTipoPage = produtoService.findByTipoProduto(pageable,id);
        return new ResponseEntity<>(produtoTipoPage,HttpStatus.OK);
    }

    @PostMapping("/produto/salvar")
    public ResponseEntity<Produto> save(@RequestBody ProdutoPostRequestBody produtoPostRequestBody){
        return new ResponseEntity<>(produtoService.save(produtoPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/produto/update")
    public ResponseEntity<Void> replace(@RequestBody ProdutoPutRequestBody produtoPutRequestBody){
        produtoService.replace(produtoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/produto/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
