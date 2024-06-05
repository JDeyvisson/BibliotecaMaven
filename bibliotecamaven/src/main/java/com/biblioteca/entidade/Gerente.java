package com.biblioteca.entidade;



import javax.persistence.Entity;

@Entity
public class Gerente extends Usuario {

    public Gerente() {
    }

    public Gerente(String login, String senha) {
        super(login, senha);
    }

    
    @Override
    public void menu() {
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Remover Livro");
        System.out.println("3. Listar Empréstimos");
        System.out.println("4. Cancelar Empréstimo");
        System.out.println("5. Sair");
    }
   

    

    

    
   
}