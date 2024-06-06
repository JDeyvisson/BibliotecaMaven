package com.biblioteca.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.biblioteca.entidade.Biblioteca;


public class RepositorioBiblioteca {

    private EntityManagerFactory emf;
    private EntityManager em;

     public RepositorioBiblioteca() {
        emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }
    
    public void adicionarBiblioteca(String nome){
        Biblioteca biblioteca = new Biblioteca(nome);
        em.getTransaction().begin();
        em.persist(biblioteca);
        em.getTransaction().commit();
        System.out.println("Biblioteca adicionada: " + biblioteca.getNome());
    }
    
    public void removerBiblioteca(String nome){
        Biblioteca biblioteca = buscarBiblioteca(nome);
        if(biblioteca != null){
            em.getTransaction().begin();
            em.remove(biblioteca);
            em.getTransaction().commit();
            System.out.println("Biblioteca Removida: " + nome);
          }else{
            System.out.println("Biblioteca não encontrada: " + nome);
          }
        
    }

    public List<Biblioteca> listarBibliotecas() {
        List<Biblioteca> bibliotecas = em.createQuery("SELECT b FROM Biblioteca b", Biblioteca.class).getResultList();
         for (Biblioteca biblioteca : bibliotecas) {
            System.out.println(biblioteca);
        }
        return bibliotecas;
    }

    public boolean existemBibliotecas() {
        List<Biblioteca> bibliotecas = listarBibliotecas();
        return !bibliotecas.isEmpty();
    }
   
    /* 
    public void removerBiblioteca(String nome){

        List<Biblioteca> bibliotecas = em.createQuery("SELECT e FROM Biblioteca e WHERE e.nome = :nome", Biblioteca.class)
                                        .setParameter("nome", nome)
                                        .getResultList();
        if(!bibliotecas.isEmpty()) {
            Biblioteca biblioteca = bibliotecas.get(0);
            em.getTransaction().begin();
            em.remove(biblioteca);
            em.getTransaction().commit();
            System.out.println("Biblioteca Removida: " + nome);

        }else {

            System.out.println("Biblioteca não encontrada: " + nome);
        }


    }
*/

    public Biblioteca buscarBiblioteca(String nome){
        List<Biblioteca> bibliotecas = em.createQuery("SELECT b FROM Biblioteca b WHERE b.nome = :nome", Biblioteca.class)
                                        .setParameter("nome", nome)
                                        .getResultList();
        return bibliotecas.isEmpty() ? null : bibliotecas.get(0);
    }

}
