package com.biblioteca.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.biblioteca.entidade.Usuario;

public class RepositorioUsuario {


    
    private EntityManagerFactory emf;
    private EntityManager em;

    public RepositorioUsuario() {
        emf = Persistence.createEntityManagerFactory("biblioteca");
        em = emf.createEntityManager();
    }

     public void adicionarUsuario(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        System.out.println("Usuário adicionado: " + usuario.getLogin());
    }

    public List<Usuario> getUsuarios() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }


}
