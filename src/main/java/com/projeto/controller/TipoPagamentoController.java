package com.projeto.controller;

import com.projeto.domain.TipoPagamento;
import com.projeto.request.TipoPagamentoPostRequestBody;
import com.projeto.request.TipoPagamentoPutRequestBody;
import com.projeto.service.TipoPagamentoService;
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
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class TipoPagamentoController {
    private final TipoPagamentoService tipoPagamentoService;

    @GetMapping("/tipoPagamento/todos")
    public ResponseEntity<Page<TipoPagamento>> listAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<TipoPagamento> pageTipoPagamento = tipoPagamentoService.findAll(pageable);
        return new ResponseEntity<>(pageTipoPagamento,HttpStatus.OK);
    }

    @GetMapping("/tipoPagamento/{id}")
    public ResponseEntity<TipoPagamento> findById(@PathVariable UUID id){
        return ResponseEntity.ok(tipoPagamentoService.findByIdOrThrow(id));
    }

    @GetMapping("/tipoPagamento/nome")
    public ResponseEntity<TipoPagamento> findByNomePagamento(@RequestParam String nome){
        return ResponseEntity.ok(tipoPagamentoService.findByNomePagamento(nome));
    }

    @PostMapping("/tipoPagamento/salvar")
    public ResponseEntity<TipoPagamento> save(@RequestBody @Valid TipoPagamentoPostRequestBody tipoPagamentoPostRequestBody){
        return new ResponseEntity<>(tipoPagamentoService.save(tipoPagamentoPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/tipoPagamento/update")
    public ResponseEntity<Void> replace(@RequestBody TipoPagamentoPutRequestBody tipoPagamentoPutRequestBody){
        tipoPagamentoService.replace(tipoPagamentoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tipoPagamento/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        tipoPagamentoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
