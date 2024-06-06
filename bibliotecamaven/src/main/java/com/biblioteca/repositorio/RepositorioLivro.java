package com.biblioteca.repositorio;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import com.biblioteca.entidade.Livro;


public class RepositorioLivro {
    
    private EntityManagerFactory emf;
    private EntityManager em;


    public RepositorioLivro() {
        emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }

    public void adicionarLivro(Livro livro) {
        em.getTransaction().begin();
        em.persist(livro);
        em.getTransaction().commit();
        System.out.println("Livro adicionado: " + livro.getTitulo());
    }


    public void removerLivro(String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            em.getTransaction().begin();
            em.remove(livro);
            em.getTransaction().commit();
            System.out.println("Livro removido: " + titulo);
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

    public Livro buscarLivro(Long id) {
        List<Livro> livros = em.createQuery("SELECT l FROM Livro l WHERE l.id = :id", Livro.class)
                               .setParameter("id", id)
                               .getResultList();
        return livros.isEmpty() ? null : livros.get(0);
    }


    public void listarLivros() {
        List<Livro> livros = em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
        if(livros.isEmpty()){

            System.out.println("Não existem livros");
        }else{
                for (Livro livro : livros) {
                    System.out.println(livro);
                }           
            }
        }
    
    public void adicionarEtiqueta(String etiqueta, Long id){
        Livro livro = buscarLivro(id);
        livro.setEtiqueta(etiqueta);
        em.getTransaction().begin();
        em.merge(livro); // Atualizando o estado do livro
        em.getTransaction().commit();
        System.out.println("Etiqueta adicionada");

    }

    public void removerEtiqueta(Long id){
        Livro livro = buscarLivro(id);
        if (livro.getEtiqueta() != null) {
            livro.setEtiqueta(null);
            em.getTransaction().begin();
            em.merge(livro); // Atualizando o estado do livro
            em.getTransaction().commit();
            System.out.println("Etiqueta removida");
        }else{

            System.out.println("Este livro já está sem etiqueta");
        }
        

    }
}
