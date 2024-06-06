package com.biblioteca.entidade;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private boolean disponivel;
    private String etiqueta;
    @ManyToOne
    @JoinColumn(name = "biblioteca_id")
    private Biblioteca biblioteca;

    public Livro() {
    }

    public Livro(String titulo, String autor, Biblioteca biblioteca) {
        this.titulo = titulo;
        this.autor = autor;
        this.biblioteca = biblioteca;
        this.disponivel = true;
    
    }

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }


    public String getEtiqueta() {
        return etiqueta;
    }


    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }


    public Biblioteca getBiblioteca() {
        return biblioteca;
    }


    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}