
package com.projeto.controller;

import com.projeto.config.AuthenticationResponse;
import com.projeto.config.AuthenticationService;
import com.projeto.request.ClientePostRequestBody;
import com.projeto.service.ClienteService;
import com.projeto.domain.Cliente;
import com.projeto.request.ClienteLoginRequestBody;
import com.projeto.request.ClientePutRequestBody;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("loja")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Log4j2
public class ClienteController {
    private final ClienteService clienteService;
    private final AuthenticationService authenticationService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cliente/todos")
    public ResponseEntity<Page<Cliente>> listAll (@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Cliente> pageCliente = clienteService.findAll(pageable);
        return new ResponseEntity<>(pageCliente,HttpStatus.OK);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable UUID id){
        return ResponseEntity.ok(clienteService.findByIdOrThrow(id));
    }

    @GetMapping("/cliente/nome")
    public ResponseEntity<List<Cliente>> findByNomeCliente(@RequestParam String nome){
        return ResponseEntity.ok(clienteService.findByNomeCliente(nome));
    }

    @GetMapping("/cliente/email")
    public ResponseEntity<Cliente> findByEmailCliente(@RequestParam String email){
        return ResponseEntity.ok(clienteService.findByEmail(email));
    }

    @PostMapping("/cliente/logar")
    public ResponseEntity<AuthenticationResponse> logarCliente(
            @RequestBody ClienteLoginRequestBody clienteLoginRequestBody){
        return ResponseEntity.ok(authenticationService.login(clienteLoginRequestBody));
    }


    @PostMapping("/cliente/registro")
    public ResponseEntity<AuthenticationResponse> save(@RequestBody ClientePostRequestBody clientePostRequestBody){
        return new ResponseEntity<>(authenticationService.registro(clientePostRequestBody),HttpStatus.CREATED);
    }

    @PutMapping( "/cliente/update")
    public ResponseEntity<AuthenticationResponse> replace(@RequestBody ClientePutRequestBody clientePutRequestBody){
        authenticationService.replace(clientePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cliente/delete")
    public ResponseEntity<Void> delete(@RequestParam String email){
        clienteService.delete(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
