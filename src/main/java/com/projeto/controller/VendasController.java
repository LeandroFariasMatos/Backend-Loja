package com.projeto.controller;

import com.projeto.domain.Vendas;
import com.projeto.request.VendasPutRequestBody;
import com.projeto.service.VendasService;
import com.projeto.request.VendasPostRequestBody;
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
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@Log4j2
public class VendasController {
    private final VendasService vendasService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/vendas/todos")
    public ResponseEntity<Page<Vendas>> listAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Vendas> pageVendas = vendasService.findAll(pageable);
        return new ResponseEntity<>(pageVendas,HttpStatus.OK);
    }

    @GetMapping("/vendas/{id}")
    public ResponseEntity<Vendas> findById(@PathVariable UUID id){
        return ResponseEntity.ok(vendasService.findByIdOrThrow(id));
    }



    @PostMapping("/vendas/salvar")
    public ResponseEntity<Vendas> save(@RequestBody VendasPostRequestBody vendasPostRequestBody){
        log.info(vendasPostRequestBody);
        return new ResponseEntity<>(vendasService.save(vendasPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/vendas/update")
    public ResponseEntity<Void> update(@RequestBody VendasPutRequestBody vendasPutRequestBody){
        vendasService.replace(vendasPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/vendas/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        vendasService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
