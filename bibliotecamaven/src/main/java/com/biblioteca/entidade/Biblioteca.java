package com.biblioteca.entidade;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Biblioteca {
    private EntityManagerFactory emf;
    private EntityManager em;

    public Biblioteca() {
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

    public void listarLivros() {
        List<Livro> livros = em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
        for (Livro livro : livros) {
            System.out.println(livro);
        }
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
        } else {
            System.out.println("Livro não disponível: " + titulo);
        }
    }

    public void reservarLivro(UsuarioComum usuario, String titulo) {
        Livro livro = buscarLivro(titulo);
        if (livro != null) {
            // Implementar lógica de reserva
            System.out.println("Reserva realizada: " + titulo);
        } else {
            System.out.println("Livro não encontrado: " + titulo);
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