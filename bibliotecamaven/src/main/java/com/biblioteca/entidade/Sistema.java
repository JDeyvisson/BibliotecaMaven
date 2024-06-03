package com.biblioteca.entidade;

import java.util.Scanner;

public class Sistema {
    private Biblioteca biblioteca;
    private Usuario usuarioLogado;

    public Sistema() {
        biblioteca = new Biblioteca();
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

    private boolean autenticarUsuario(Scanner scanner, int tipoUsuario) {
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : biblioteca.getUsuarios()) {
            if (usuario.getLogin().equals(login) && usuario.autenticar(senha) &&
                ((tipoUsuario == 1 && usuario instanceof Gerente) || (tipoUsuario == 2 && usuario instanceof UsuarioComum))) {
                usuarioLogado = usuario;
                return true;
            }
        }

        System.out.println("Login ou senha incorretos.");
        return false;
    }

    private void cadastrarUsuario(Scanner scanner) {
        System.out.println("Escolha o tipo de usuário:");
        System.out.println("1. Gerente");
        System.out.println("2. Usuário Comum");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        Usuario novoUsuario;
        if (escolha == 1) {
            novoUsuario = new Gerente(login, senha);
        } else if (escolha == 2) {
            novoUsuario = new UsuarioComum(login, senha);
        } else {
            System.out.println("Opção inválida. Voltando ao menu principal.");
            return;
        }

        biblioteca.adicionarUsuario(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
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
                        System.out.println("Título do Livro: ");
                        String titulo = scanner.nextLine();
                        System.out.println("Autor do Livro: ");
                        String autor = scanner.nextLine();
                        gerente.adicionarLivro(biblioteca, titulo, autor);
                        break;
                    case 2:
                        System.out.println("Título do Livro a remover: ");
                        titulo = scanner.nextLine();
                        gerente.removerLivro(biblioteca, titulo);
                        break;
                    case 3:
                        gerente.listarEmprestimos(biblioteca);
                        break;
                    case 4:
                        System.out.println("Título do Livro para cancelar empréstimo: ");
                        titulo = scanner.nextLine();
                        gerente.cancelarEmprestimo(biblioteca, titulo);
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else if (usuarioLogado instanceof UsuarioComum) {
                UsuarioComum usuarioComum = (UsuarioComum) usuarioLogado;
                switch (opcao) {
                    case 1:
                        usuarioComum.consultarCatalogo(biblioteca);
                        break;
                    case 2:
                        System.out.println("Título do Livro para empréstimo: ");
                        String titulo = scanner.nextLine();
                        usuarioComum.fazerEmprestimo(biblioteca, titulo);
                        break;
                    case 3:
                        System.out.println("Título do Livro para reserva: ");
                        titulo = scanner.nextLine();
                        usuarioComum.fazerReserva(biblioteca, titulo);
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
}