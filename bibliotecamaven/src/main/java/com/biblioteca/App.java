package com.biblioteca;






import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

import com.biblioteca.entidade.Biblioteca;
import com.biblioteca.entidade.Gerente;
import com.biblioteca.entidade.Livro;
import com.biblioteca.entidade.Usuario;
import com.biblioteca.entidade.UsuarioComum;
import com.biblioteca.repositorio.RepositorioBiblioteca;
import com.biblioteca.repositorio.RepositorioEmprestimo;
import com.biblioteca.repositorio.RepositorioLivro;
import com.biblioteca.repositorio.RepositorioReserva;
import com.biblioteca.repositorio.RepositorioUsuario;
import com.biblioteca.servico.ServicoEmprestimo;
import com.biblioteca.servico.ServicoLivro;



public class App {

    private Usuario usuarioLogado;
    private RepositorioLivro rLivro;
    private ServicoLivro sLivro;
    private ServicoEmprestimo sEmprestimo;
    private RepositorioEmprestimo rEmprestimo;
    private RepositorioUsuario rUsuario;
    private RepositorioReserva rReserva;
    private RepositorioBiblioteca rBiblioteca;

    public App(){
        rBiblioteca = new RepositorioBiblioteca();
        rLivro = new RepositorioLivro();
        sLivro = new ServicoLivro();
        sEmprestimo = new ServicoEmprestimo();
        rEmprestimo = new RepositorioEmprestimo();
        rUsuario = new RepositorioUsuario();
        rReserva = new RepositorioReserva();

    }
    
    
    private void menuPrincipal(Scanner scanner) {
        while (true) {
            usuarioLogado.menu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (usuarioLogado instanceof Gerente) {
                Gerente gerente = (Gerente) usuarioLogado;
                switch (opcao) {
                    case 1:
                        List<Biblioteca> biblioteca = rBiblioteca.listarBibliotecas();
                        if (biblioteca != null) {
                            System.out.println("Nome da Biblioteca em que o Livro será criado: ");
                            String biblioteca2 = scanner.nextLine();
                            Biblioteca biblioteca3 = rBiblioteca.buscarBiblioteca(biblioteca2);
                            System.out.println("Título do Livro: ");
                            String titulo = scanner.nextLine();
                            System.out.println("Autor do Livro: ");
                            String autor = scanner.nextLine();
                            sLivro.adicionarLivro(rLivro, titulo, autor, biblioteca3);

                            break;                                           
                        }else{
                            System.out.println("É preciso ter Bibliotecas antes de adicionar Livros!");
                            break;
                        }
                        
                    case 2:
                        System.out.println("Título do Livro a remover: ");
                        String titulo = scanner.nextLine();
                        sLivro.removerLivro(rLivro, titulo);
                        break;
                    case 3:
                        sEmprestimo.listarEmprestimos(rEmprestimo);
                        break;
                    case 4:
                        System.out.println("Título do Livro para cancelar empréstimo: ");
                        titulo = scanner.nextLine();
                        sEmprestimo.cancelarEmprestimo(rEmprestimo, titulo);
                        break;
                    case 5:
                        sLivro.consultarCatalogo(rLivro);
                        System.out.println("Digite um Id de livro para adicionar etiqueta: ");
                        Long id = scanner.nextLong();
                        Livro livro1 = rLivro.buscarLivro(id);
                        scanner.nextLine();
                        if (livro1 != null) {
                            System.out.println("Digite a etiqueta: ");
                            String etiqueta = scanner.nextLine();
                            rLivro.adicionarEtiqueta(etiqueta, id);
                            break;
                        }else{
                            System.out.println("Id invalido");
                            break;
                        }
                    case 6:
                        sLivro.consultarCatalogo(rLivro);
                        System.out.println("Digite um Id de livro para remover etiqueta");
                        Long id1 = scanner.nextLong();
                        Livro livro2 = rLivro.buscarLivro(id1);
                        if (livro2 != null) {
                            rLivro.removerEtiqueta(id1);
                            break;
                        }else{
                            System.out.println("Id invalido");
                            break;
                        }
                    case 7:
                        System.out.println("Nome da biblioteca: ");
                        String nome = scanner.nextLine();
                        rBiblioteca.adicionarBiblioteca(nome);
                        break;
                    case 8:
                        System.out.println("Nome da biblioteca que deseja remover: ");
                        String nome1 = scanner.nextLine();
                        rBiblioteca.removerBiblioteca(nome1);
                        break;
                    case 9:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else if (usuarioLogado instanceof UsuarioComum) {
                UsuarioComum usuarioComum = (UsuarioComum) usuarioLogado;
                switch (opcao) {
                    case 1:
                        sLivro.consultarCatalogo(rLivro);
                        break;
                    case 2:
                        System.out.println("Título do Livro para empréstimo: ");
                        String titulo = scanner.nextLine();
                        rEmprestimo.emprestarLivro(usuarioComum, titulo);
                        break;
                    case 3:
                        System.out.println("Título do Livro para reserva: ");
                        titulo = scanner.nextLine();
                        rReserva.reservarLivro(usuarioComum, titulo);
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }

    private void cadastrarUsuario(Scanner scanner) {
        System.out.println("Escolha o tipo de usuário:");
        System.out.println("1. Gerente");
        System.out.println("2. Usuário Comum");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("CPF: ");
        String CPF = scanner.nextLine();
        System.out.println("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        Usuario novoUsuario;
        if (escolha == 1) {
            novoUsuario = new Gerente(nome, CPF, sexo, login, senha);
        } else if (escolha == 2) {
            novoUsuario = new UsuarioComum(nome, CPF, sexo, login, senha);
        } else {
            System.out.println("Opção inválida. Voltando ao menu principal.");
            return;
        }

        rUsuario.adicionarUsuario(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private boolean autenticarUsuario(Scanner scanner, int tipoUsuario) {
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : rUsuario.getUsuarios()) {
            if (usuario.getLogin().equals(login) && usuario.autenticar(senha) &&
                ((tipoUsuario == 1 && usuario instanceof Gerente) || (tipoUsuario == 2 && usuario instanceof UsuarioComum))) {
                usuarioLogado = usuario;
                return true;
            }
        }

        System.out.println("Login ou senha incorretos.");
        return false;
    }


     public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bem-vindo à Biblioteca!");
            System.out.println("1. Login");
            System.out.println("2. Cadastro de Usuário");
            System.out.println("3. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (escolha == 1) {
                System.out.println("Escolha o tipo de usuário:");
                System.out.println("1. Gerente");
                System.out.println("2. Usuário Comum");
                int tipoUsuario = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                if (autenticarUsuario(scanner, tipoUsuario)) {
                    menuPrincipal(scanner);
                }
            } else if (escolha == 2) {
                cadastrarUsuario(scanner);
            } else if (escolha == 3) {
                System.out.println("Saindo...");
                return;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
    public void menu() {
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Remover Livro");
        System.out.println("3. Listar Empréstimos");
        System.out.println("4. Cancelar Empréstimo");
        System.out.println("5. Sair");
    }
    public static void main(String[] args) {
        App main = new App();
        main.iniciar();
    }
}


