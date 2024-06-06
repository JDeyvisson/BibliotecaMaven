package com.biblioteca.entidade;



import javax.persistence.Entity;

@Entity
public class Gerente extends Usuario {

    public Gerente() {
    }

    public Gerente(String nome, String cpf, String sexo, String login, String senha) {
        super(nome, cpf, sexo, login, senha);
    }
    
    @Override
    public void menu() {
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Remover Livro");
        System.out.println("3. Listar Empréstimos");
        System.out.println("4. Cancelar Empréstimo");
        System.out.println("5. Adicionar Etiqueta");
        System.out.println("6. Remover Etiqueta");
        System.out.println("7. Adicionar Biblioteca");
        System.out.println("8. Remover Biblioteca");
        System.out.println("9. Sair");
    }
   
   /* 
   @Override
   public void menu() {
       System.out.println("1. Menu Biblioteca");
       System.out.println("2. Menu Livro");
       System.out.println("3. Menu Empréstimo");
       System.out.println("4. Menu Etiqueta");
       System.out.println("5. Sair");
   }

    */

    
   
}