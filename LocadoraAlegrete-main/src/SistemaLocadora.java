import java.util.Scanner;

public class SistemaLocadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Gerenciadores para clientes, veículos, categorias e locações
        ListaClientes gerenciadorClientes = new ListaClientes(100);
        ListaVeiculos gerenciadorVeiculos = new ListaVeiculos(100);
        ListaCategorias gerenciadorCategorias = new ListaCategorias(100);
        ListaLocacoes gerenciadorLocacoes = new ListaLocacoes(100);

        // Loop do menu
        while (true) {
            System.out.println("---- Sistema de Locadora ----");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Veículos");
            System.out.println("3. Gerenciar Categorias");
            System.out.println("4. Gerenciar Locações");
            System.out.println("5. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerenciarClientes(scanner, gerenciadorClientes, gerenciadorLocacoes);
                    break;
                case 2:
                    gerenciarVeiculos(scanner, gerenciadorVeiculos, gerenciadorLocacoes, gerenciadorCategorias);
                    break;
                case 3:
                    gerenciarCategorias(scanner, gerenciadorCategorias, gerenciadorVeiculos);
                    break;
                case 4:
                    gerenciarLocacoes(scanner, gerenciadorLocacoes, gerenciadorClientes, gerenciadorVeiculos);
                    break;
                case 5:
                    System.out.println("Saindo do sistema.");
                    return; // Sai do sistema
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Método para gerenciar clientes
    private static void gerenciarClientes(Scanner scanner, ListaClientes gerenciadorClientes, ListaLocacoes gerenciadorLocacoes) {
        System.out.println("---- Gerenciamento de Clientes ----");
        System.out.println("1. Incluir Cliente");
        System.out.println("2. Excluir Cliente");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Listar Clientes");
        System.out.println("5. Voltar ao Menu Principal");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                incluirCliente(scanner, gerenciadorClientes);
                break;
            case 2:
                excluirCliente(scanner, gerenciadorClientes, gerenciadorLocacoes);
                break;
            case 3:
                editarCliente(scanner, gerenciadorClientes);
                break;
            case 4:
                listarClientes(scanner, gerenciadorClientes);
                break;
            case 5:
                System.out.println("Voltando ao Menu Principal.");
                return; // Volta ao menu principal
            default:
                System.out.println("Opção inválida.");
        }
    }

    // Método para incluir um cliente
    private static void incluirCliente(Scanner scanner, ListaClientes gerenciadorClientes) {
        System.out.println("---- Incluir Cliente ----");
        System.out.print("Nome: ");
        scanner.nextLine(); // Limpar buffer
        String nome = scanner.nextLine();

        System.out.print("CNH: ");
        String cnh = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cnh, telefone, cpf);
        gerenciadorClientes.adicionarCliente(cliente);

        System.out.println("Cliente adicionado com sucesso!");
    }

    // Método para excluir um cliente
    private static void excluirCliente(Scanner scanner, ListaClientes gerenciadorClientes, ListaLocacoes gerenciadorLocacoes) {
        System.out.println("---- Excluir Cliente ----");
        System.out.print("CPF: ");
        scanner.nextLine(); // Limpar buffer
        String cpf = scanner.nextLine();

        Cliente cliente = gerenciadorClientes.buscarClientePorCPF(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Verificar se o cliente está associado a alguma locação
        boolean clienteAtreladoLocacao = false;
        for (int i = 0; i < gerenciadorLocacoes.count(); i++) {
            if (gerenciadorLocacoes.get(i).getCliente().getCpf().equals(cpf)) {
                clienteAtreladoLocacao = true;
                break;
            }
        }

        if (clienteAtreladoLocacao) {
            System.out.println("Não é possível excluir um cliente atrelado a uma locação.");
        } else {
            gerenciadorClientes.excluirCliente(cpf);
        }
    }

    // Método para editar um cliente
    private static void editarCliente(Scanner scanner, ListaClientes gerenciadorClientes) {
        System.out.println("---- Editar Cliente ----");
        System.out.print("CPF do Cliente a ser Editado: ");
        scanner.nextLine(); // Limpar buffer
        String cpf = scanner.nextLine();

        Cliente cliente = gerenciadorClientes.buscarClientePorCPF(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine();
        System.out.print("Nova CNH: ");
        String novaCnh = scanner.nextLine();
        System.out.print("Novo Telefone: ");
        String novoTelefone = scanner.nextLine();

        gerenciadorClientes.editarCliente(cpf, novoNome, novaCnh, novoTelefone);
    }

    // Método para listar clientes
    private static void listarClientes(Scanner scanner, ListaClientes gerenciadorClientes) {
        System.out.println("---- Listar Clientes ----");
        System.out.println("1. Listar Clientes do Início ao Fim");
        System.out.println("2. Listar Clientes do Fim ao Início");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        if (opcao == 1) {
            gerenciadorClientes.listarClientes();
        } else if (opcao == 2) {
            gerenciadorClientes.listarClientesReverso();
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Método para gerenciar veículos
    private static void gerenciarVeiculos(Scanner scanner, ListaVeiculos gerenciadorVeiculos, ListaLocacoes gerenciadorLocacoes, ListaCategorias gerenciadorCategorias) {
        System.out.println("---- Gerenciamento de Veículos ----");
        System.out.println("1. Incluir Veículo");
        System.out.println("2. Excluir Veículo");
        System.out.println("3. Editar Veículo");
        System.out.println("4. Listar Veículos");
        System.out.println("5. Voltar ao Menu Principal");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                incluirVeiculo(scanner, gerenciadorVeiculos, gerenciadorCategorias);
                break;
            case 2:
                excluirVeiculo(scanner, gerenciadorVeiculos, gerenciadorLocacoes);
                break;
            case 3:
                editarVeiculo(scanner, gerenciadorVeiculos, gerenciadorCategorias);
                break;
            case 4:
                listarVeiculosDisponiveis(scanner, gerenciadorLocacoes, gerenciadorVeiculos);
                break;
            case 5:
                System.out.println("Voltando ao Menu Principal.");
                return; // Volta ao menu principal
            default:
                System.out.println("Opção inválida.");
        }
    }

    // Método para incluir um veículo
    private static void incluirVeiculo(Scanner scanner, ListaVeiculos gerenciadorVeiculos, ListaCategorias gerenciadorCategorias) {
        System.out.println("---- Incluir Veículo ----");
        System.out.print("Placa: ");
        scanner.nextLine(); // Limpar buffer
        String placa = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Ano: ");
        int ano = scanner.nextInt();

        System.out.print("Potência: ");
        int potencia = scanner.nextInt();

        System.out.print("Número de Lugares: ");
        int lugares = scanner.nextInt();

        System.out.print("ID da Categoria: ");
        int idCategoria = scanner.nextInt();

        Categoria categoria = gerenciadorCategorias.buscarCategoriaPorIdentificador(idCategoria);

        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        Veiculo veiculo = new Veiculo(placa, modelo, marca, ano, potencia, lugares, categoria);
        gerenciadorVeiculos.adicionarVeiculo(veiculo);

        System.out.println("Veículo adicionado com sucesso!");
    }

    // Método para excluir um veículo
    private static void excluirVeiculo(Scanner scanner, ListaVeiculos gerenciadorVeiculos, ListaLocacoes gerenciadorLocacoes) {
        System.out.println("---- Excluir Veículo ----");
        System.out.print("Placa: ");
        scanner.nextLine(); // Limpar buffer
        String placa = scanner.nextLine();

        Veiculo veiculo = gerenciadorVeiculos.buscarVeiculoPorPlaca(placa);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        // Verificar se o veículo está associado a alguma locação
        boolean veiculoAtreladoLocacao = false;
        for (int i = 0; i < gerenciadorLocacoes.count(); i++) {
            if (gerenciadorLocacoes.get(i).getVeiculo().getPlaca().equals(placa)) {
                veiculoAtreladoLocacao = true;
                break;
            }
        }

        if (veiculoAtreladoLocacao) {
            System.out.println("Não é possível excluir um veículo atrelado a uma locação.");
        } else {
            gerenciadorVeiculos.excluirVeiculo(placa);
        }
    }

    // Método para editar um veículo
    private static void editarVeiculo(Scanner scanner, ListaVeiculos gerenciadorVeiculos, ListaCategorias gerenciadorCategorias) {
        System.out.println("---- Editar Veículo ----");
        System.out.print("Placa do Veículo a ser Editado: ");
        scanner.nextLine(); // Limpar buffer
        String placa = scanner.nextLine();

        Veiculo veiculo = gerenciadorVeiculos.buscarVeiculoPorPlaca(placa);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Novo Modelo: ");
        String novoModelo = scanner.nextLine();

        System.out.print("Nova Marca: ");
        String novaMarca = scanner.nextLine();

        System.out.print("Novo Ano: ");
        int novoAno = scanner.nextInt();

        System.out.print("Nova Potência: ");
        int novaPotencia = scanner.nextInt();

        System.out.print("Novo Número de Lugares: ");
        int novoNumeroDeLugares = scanner.nextInt();

        System.out.print("Novo ID da Categoria: ");
        int idCategoria = scanner.nextInt();

        Categoria novaCategoria = gerenciadorCategorias.buscarCategoriaPorIdentificador(idCategoria);

        if (novaCategoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        veiculo = new Veiculo(placa, novoModelo, novaMarca, novoAno, novaPotencia, novoNumeroDeLugares, novaCategoria);

        System.out.println("Veículo atualizado com sucesso!");
    }

    // Método para listar veículos
    private static void listarVeiculosDisponiveis(Scanner scanner, ListaLocacoes gerenciadorLocacoes, ListaVeiculos gerenciadorVeiculos) {
        System.out.println("---- Veículos Disponíveis para Locação ----");
        gerenciadorLocacoes.listarVeiculosDisponiveis(gerenciadorVeiculos);

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        if (opcao == 1) {
            gerenciadorVeiculos.listarVeiculos();
        } else if (opcao == 2) {
            gerenciadorVeiculos.listarVeiculosReverso();
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Método para gerenciar categorias
    private static void gerenciarCategorias(Scanner scanner, ListaCategorias gerenciadorCategorias, ListaVeiculos gerenciadorVeiculos) {
        System.out.println("---- Gerenciamento de Categorias ----");
        System.out.println("1. Incluir Categoria");
        System.out.println("2. Excluir Categoria");
        System.out.println("3. Listar Categorias");
        System.out.println("4. Voltar ao Menu Principal");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                incluirCategoria(scanner, gerenciadorCategorias);
                break;
            case 2:
                excluirCategoria(scanner, gerenciadorCategorias, gerenciadorVeiculos);
                break;
            case 3:
                listarCategorias(scanner, gerenciadorCategorias);
                break;
            case 4:
                System.out.println("Voltando ao Menu Principal.");
                return; // Volta ao menu principal
            default:
                System.out.println("Opção inválida.");
        }
    }

    // Método para incluir uma categoria
    private static void incluirCategoria(Scanner scanner, ListaCategorias gerenciadorCategorias) {
        System.out.println("---- Incluir Categoria ----");
        System.out.print("Nome: ");
        scanner.nextLine(); // Limpar buffer
        String nome = scanner.nextLine();

        System.out.print("Identificador: ");
        int identificador = scanner.nextInt();

        Categoria categoria = new Categoria(nome, identificador);

        gerenciadorCategorias.adicionarCategoria(categoria);

        System.out.println("Categoria adicionada com sucesso!");
    }

    // Método para excluir uma categoria
    private static void excluirCategoria(Scanner scanner, ListaCategorias gerenciadorCategorias, ListaVeiculos gerenciadorVeiculos) {
        System.out.println("---- Excluir Categoria ----");
        System.out.print("Identificador: ");
        scanner.nextLine(); // Limpar buffer
        int identificador = scanner.nextInt();

        Categoria categoria = gerenciadorCategorias.buscarCategoriaPorIdentificador(identificador);

        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        // Verificar se a categoria está associada a algum veículo
        boolean categoriaAtreladaVeiculo = false;
        for (int i = 0; i < gerenciadorVeiculos.count(); i++) {
            if (gerenciadorVeiculos.get(i).getCategoria().getIdentificador() == identificador) {
                categoriaAtreladaVeiculo = true;
                break;
            }
        }

        if (categoriaAtreladaVeiculo) {
            System.out.println("Não é possível excluir uma categoria atrelada a um veículo.");
        } else {
            gerenciadorCategorias.excluirCategoria(identificador);
        }
    }

    // Método para listar categorias
    private static void listarCategorias(Scanner scanner, ListaCategorias gerenciadorCategorias) {
        System.out.println("---- Listar Categorias ----");
        System.out.println("1. Listar Categorias do Início ao Fim");
        System.out.println("2. Listar Categorias do Fim ao Início");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        if (opcao == 1) {
            gerenciadorCategorias.listarCategorias();
        } else if (opcao == 2) {
            gerenciadorCategorias.listarCategoriasReverso();
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Método para gerenciar locações
    private static void gerenciarLocacoes(Scanner scanner, ListaLocacoes gerenciadorLocacoes, ListaClientes gerenciadorClientes, ListaVeiculos gerenciadorVeiculos) {
        System.out.println("---- Gerenciamento de Locações ----");
        System.out.println("1. Incluir Locação");
        System.out.println("2. Excluir Locação");
        System.out.println("3. Listar Locações");
        System.out.println("4. Verificar Veículos Disponíveis");
        System.out.println("5. Voltar ao Menu Principal");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                incluirLocacao(scanner, gerenciadorLocacoes, gerenciadorClientes, gerenciadorVeiculos);
                break;
            case 2:
                excluirLocacao(scanner, gerenciadorLocacoes);
                break;
            case 3:
                listarLocacoes(scanner, gerenciadorLocacoes);
                break;
            case 4:
                listarVeiculosDisponiveis(scanner, gerenciadorLocacoes, gerenciadorVeiculos);
                break;
            case 5:
                System.out.println("Voltando ao Menu Principal.");
                return; // Volta ao menu principal
            default:
                System.out.println("Opção inválida.");
        }
    }

    // Método para incluir uma locação
    private static void incluirLocacao(Scanner scanner, ListaLocacoes gerenciadorLocacoes, ListaClientes gerenciadorClientes, ListaVeiculos gerenciadorVeiculos) {
        System.out.println("---- Incluir Locação ----");
        System.out.print("CPF do Cliente: ");
        scanner.nextLine(); // Limpar buffer
        String cpf = scanner.nextLine();

        Cliente cliente = gerenciadorClientes.buscarClientePorCPF(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Placa do Veículo: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = gerenciadorVeiculos.buscarVeiculoPorPlaca(placa);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Data de Retirada: ");
        String dataRetirada = scanner.nextLine();

        System.out.print("Data de Devolução: ");
        String dataDevolucao = scanner.nextLine();

        System.out.print("Valor da Locação: ");
        double valor = scanner.nextDouble();

        Locacao locacao = new Locacao(cliente, veiculo, dataRetirada, dataDevolucao, valor);
        gerenciadorLocacoes.adicionarLocacao(locacao);

        System.out.println("Locação incluída com sucesso!");
    }

    // Método para excluir uma locação
    private static void excluirLocacao(Scanner scanner, ListaLocacoes gerenciadorLocacoes) {
        System.out.println("---- Excluir Locação ----");
        System.out.print("Placa do Veículo: ");
        scanner.nextLine(); // Limpar buffer
        String placa = scanner.nextLine();

        Locacao locacao = gerenciadorLocacoes.buscarLocacaoPorVeiculo(placa);

        if (locacao == null) {
            System.out.println("Locação não encontrada.");
        } else {
            gerenciadorLocacoes.excluirLocacao(placa);
            System.out.println("Locação excluída com sucesso!");
        }
    }

    // Método para listar locações
    private static void listarLocacoes(Scanner scanner, ListaLocacoes gerenciadorLocacoes) {
        System.out.println("---- Listar Locações ----");
        System.out.println("1. Listar Locações do Início ao Fim");
        System.out.println("2. Listar Locações do Fim ao Início");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        if (opcao == 1) {
            gerenciadorLocacoes.listarLocacoes();
        } else if (opcao == 2) {
            gerenciadorLocacoes.listarLocacoesReverso();
        } else {
            System.out.println("Opção inválida.");
        }
    }

    ListaVeiculos gerenciadorVeiculos = new ListaVeiculos(); // Inicializa a lista de veículos
    // Adicione veículos à lista conforme necessário

    // Método para verificar veículos disponíveis para locação
    private static void listarVeiculosDisponiveis(Scanner scanner, ListaLocacoes gerenciadorLocacoes) {
        System.out.println("---- Veículos Disponíveis para Locação ----");
        gerenciadorLocacoes.listarVeiculosDisponiveis();
    }
}