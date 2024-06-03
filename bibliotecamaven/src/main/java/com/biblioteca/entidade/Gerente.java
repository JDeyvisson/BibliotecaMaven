package com.biblioteca.entidade;



import javax.persistence.Entity;

@Entity
public class Gerente extends Usuario {

    public Gerente() {
    }

    public Gerente(String login, String senha) {
        super(login, senha);
    }

    public void adicionarLivro(Biblioteca biblioteca, String titulo, String autor) {
        Livro livro = new Livro(titulo, autor);
        biblioteca.adicionarLivro(livro);
    }

    public void removerLivro(Biblioteca biblioteca, String titulo) {
        biblioteca.removerLivro(titulo);
    }

    public void listarEmprestimos(Biblioteca biblioteca) {
        biblioteca.listarEmprestimos();
    }

    public void cancelarEmprestimo(Biblioteca biblioteca, String titulo) {
        biblioteca.cancelarEmprestimo(titulo);
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