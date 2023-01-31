package com.projeto.domain;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cliente")
@Builder
public class Cliente implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;
    @Column(name="nome_cliente",nullable = false)
    private String nomeCliente;

    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name="senha",nullable = false)
    private String senha;
    @Column(name="telefone",length = 50,nullable = false)
    private String telefone;
    @Column(name="cpf",length = 50,nullable = false,unique = true)
    private String cpf;
    @Column(name="endereco",nullable = false)
    private String endereco;
    @Column(name="saldo",scale=2)
    private double saldo;
    @Enumerated(EnumType.STRING)
    private Role role= Role.USER;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
