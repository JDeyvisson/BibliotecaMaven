package com.biblioteca.servico;



import com.biblioteca.entidade.Livro;

import com.biblioteca.repositorio.RepositorioLivro;

public class ServicoLivro {
    


    public void adicionarLivro(RepositorioLivro rLivro, String titulo, String autor) {
        Livro livro = new Livro(titulo, autor);
        rLivro.adicionarLivro(livro);
    }

     public void removerLivro(RepositorioLivro rLivro, String titulo) {
        rLivro.removerLivro(titulo);
    }

    public void consultarCatalogo(RepositorioLivro rLivro) {
        rLivro.listarLivros();
    }
    

}
