package com.biblioteca.servico;


import com.biblioteca.repositorio.RepositorioEmprestimo;

public class ServicoEmprestimo {

  
    
    public void listarEmprestimos(RepositorioEmprestimo rEmprestimo) {
        rEmprestimo.listarEmprestimos();
    }


     public void cancelarEmprestimo(RepositorioEmprestimo rEmprestimo, String titulo) {
        rEmprestimo.cancelarEmprestimo(titulo);
    }
    /*
    public void fazerEmprestimo(UsuarioComum usuario, String titulo) {
       
        rEmprestimo.emprestarLivro(usuario, titulo);
    }
     */
}
