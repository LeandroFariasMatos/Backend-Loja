package com.projeto.controller;

import com.projeto.domain.TipoProduto;
import com.projeto.repository.TipoProdutoRepository;
import com.projeto.request.TipoProdutoPutRequestBody;
import com.projeto.service.TipoProdutoService;
import com.projeto.request.TipoProdutoPostRequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "loja")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class TipoProdutoController {
    private final TipoProdutoService tipoProdutoService;
    private final TipoProdutoRepository tipoProdutoRepository;

    @GetMapping("/tipoProduto/todos")
    public ResponseEntity<Page<TipoProduto>> listAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TipoProduto> pageTipoProduto = tipoProdutoService.findAll(pageable);
        return new ResponseEntity<>(pageTipoProduto,HttpStatus.OK);
    }


    @GetMapping("/tipoProduto/{id}")
    public ResponseEntity<TipoProduto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(tipoProdutoService.findByIdOrThrow(id));
    }

    @GetMapping("/tipoProduto/nome")
    public ResponseEntity<Page<TipoProduto>> findByNomeTipoProduto(@RequestParam String nome,@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TipoProduto> tipoProdutoPage = tipoProdutoService.findByNomeTipoProduto(nome,pageable);
        return new ResponseEntity<>(tipoProdutoPage,HttpStatus.OK);
    }


    @PostMapping( "/tipoProduto/salvar")
    public ResponseEntity<TipoProduto> save(@RequestBody @Valid TipoProdutoPostRequestBody tipoProdutoPostRequestBody){
        return new ResponseEntity<>(tipoProdutoService.save(tipoProdutoPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/tipoProduto/update")
    public ResponseEntity<Void> replace(@RequestBody TipoProdutoPutRequestBody tipoProdutoPutRequestBody){
        tipoProdutoService.replace(tipoProdutoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tipoProduto/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        tipoProdutoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}


