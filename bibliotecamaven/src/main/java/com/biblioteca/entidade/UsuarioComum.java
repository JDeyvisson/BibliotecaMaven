package com.biblioteca.entidade;

import javax.persistence.Entity;


@Entity
public class UsuarioComum extends Usuario {



    public UsuarioComum() {
    }

    public UsuarioComum(String login, String senha) {
        super(login, senha);
    }

   

    @Override
    public void menu() {
        System.out.println("1. Consultar Catálogo");
        System.out.println("2. Fazer Empréstimo");
        System.out.println("3. Fazer Reserva");
        System.out.println("4. Sair");
    }
}