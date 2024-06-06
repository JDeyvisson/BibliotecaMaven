package com.biblioteca.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CPF;
    private String login;
    private String senha;
    private String sexo;
    private String nome;


    public Usuario() {
    }

    public Usuario(String nome, String cpf, String sexo, String login, String senha) {
        this.nome = nome;
        this.CPF = cpf;
        this.sexo = sexo;
        this.login = login;
        this.senha = senha;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public abstract void menu();

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}