package com.biblioteca.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UsuarioComum usuario;

    @ManyToOne
    private Livro livro;

    private LocalDate dataReserva;

    public Reserva(UsuarioComum usuario, Livro livro) {
        this.dataReserva = LocalDate.now();
        this.usuario = usuario;
        this.livro = livro;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioComum getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioComum usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", livro=" + livro +
                ", dataReserva=" + dataReserva +
                '}';
    }
}