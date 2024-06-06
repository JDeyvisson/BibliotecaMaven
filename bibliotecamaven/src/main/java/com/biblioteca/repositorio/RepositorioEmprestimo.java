package com.biblioteca.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.biblioteca.entidade.Emprestimo;
import com.biblioteca.entidade.Livro;
import com.biblioteca.entidade.UsuarioComum;


public class RepositorioEmprestimo {
    
    RepositorioLivro rLivro;
    private EntityManagerFactory emf;
    private EntityManager em;

    public RepositorioEmprestimo() {
        emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }

    public void emprestarLivro(UsuarioComum usuario, String titulo) {
    
        Livro livro = buscarLivro(titulo);
        if (livro != null && livro.isDisponivel()) {
            livro.setDisponivel(false);
            Emprestimo emprestimo = new Emprestimo(usuario, livro);
            em.getTransaction().begin();
            em.persist(emprestimo);
            em.merge(livro); // Atualizando o estado do livro
            em.getTransaction().commit();
            System.out.println("Empréstimo realizado: " + titulo);
            System.out.println("Em caso de não entrega até " + emprestimo.getDataDevolucao());
            System.out.println("Multa por atraso de R$30");
        } else {
            System.out.println("Livro não disponível: " + titulo);
        }
    }

    public void listarEmprestimos() {
        List<Emprestimo> emprestimos = em.createQuery("SELECT e FROM Emprestimo e", Emprestimo.class).getResultList();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
        } else {
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }

    public void cancelarEmprestimo(String titulo) {
        List<Emprestimo> emprestimos = em.createQuery("SELECT e FROM Emprestimo e WHERE e.livro.titulo = :titulo", Emprestimo.class)
                                         .setParameter("titulo", titulo)
                                         .getResultList();
        if (!emprestimos.isEmpty()) {
            Emprestimo emprestimo = emprestimos.get(0);
            em.getTransaction().begin();
            em.remove(emprestimo);
            emprestimo.getLivro().setDisponivel(true);
            em.merge(emprestimo.getLivro());
            em.getTransaction().commit();
            System.out.println("Empréstimo cancelado: " + titulo);
        } else {
            System.out.println("Empréstimo não encontrado para o livro: " + titulo);
        }
    }

    public Livro buscarLivro(String titulo) {
        List<Livro> livros = em.createQuery("SELECT l FROM Livro l WHERE l.titulo = :titulo", Livro.class)
                               .setParameter("titulo", titulo)
                               .getResultList();
        return livros.isEmpty() ? null : livros.get(0);
    }
}
