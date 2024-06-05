package com.biblioteca.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.biblioteca.entidade.Livro;
import com.biblioteca.entidade.Reserva;
import com.biblioteca.entidade.UsuarioComum;

public class RepositorioReserva {
    private EntityManagerFactory emf;
    private EntityManager em;

    public RepositorioReserva(){

       emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }

    public void reservarLivro(UsuarioComum usuario, String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            Reserva reserva = new Reserva(usuario, livro);
            em.getTransaction().begin();
            em.persist(reserva);
            //em.merge(livro); // Atualizando o estado do livro
            em.getTransaction().commit();
            System.out.println("Reserva realizada: " + titulo);
        } else {
            System.out.println("Livro não encontrado: " + titulo);
        }
    }

     public Livro buscarLivro(String titulo) {
        List<Livro> livros = em.createQuery("SELECT l FROM Livro l WHERE l.titulo = :titulo", Livro.class)
                               .setParameter("titulo", titulo)
                               .getResultList();
        return livros.isEmpty() ? null : livros.get(0);
    }
}
