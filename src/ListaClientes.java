class ListaClientes {
    private NodoCliente inicio;
    private NodoCliente fim;

    public ListaClientes() {
        this.inicio = null;
        this.fim = null;
    }

    // Incluir um cliente no final da lista
    public void incluirCliente(Cliente cliente) {
        NodoCliente novoNodo = new NodoCliente(cliente);
        if (inicio == null) {
            inicio = fim = novoNodo;
        } else {
            fim.proximo = novoNodo;
            novoNodo.anterior = fim;
            fim = novoNodo;
        }
    }

    // Buscar um cliente por CPF
    public Cliente buscarCliente(String cpf) {
        NodoCliente atual = inicio;
        while (atual != null) {
            if (atual.cliente.getCpf().equals(cpf)) {
                return atual.cliente;
            }
            atual = atual.proximo;
        }
        return null;
    }

    // Excluir um cliente por CPF (com restrição de locação)
    public void excluirCliente(String cpf, GerenciadorLocacoes locacoes) {
        if (locacoes.temLocacaoComCliente(cpf)) {
            throw new IllegalArgumentException("Cliente associado a locações não pode ser excluído.");
        }

        NodoCliente atual = inicio;
        while (atual != null) {
            if (atual.cliente.getCpf().equals(cpf)) {
                if (atual.anterior != null) {
                    atual.anterior.proximo = atual.proximo;
                } else {
                    inicio = atual.proximo; // Excluindo do início da lista
                }

                if (atual.proximo != null) {
                    atual.proximo.anterior = atual.anterior;
                } else {
                    fim = atual.anterior; // Excluindo do final da lista
                }
                return; // Cliente excluído com sucesso
            }
            atual = atual.proximo;
        }

        throw new IllegalArgumentException("Cliente não encontrado.");
    }

    // Editar informações de um cliente por CPF
    public void editarCliente(String cpf, String novoNome, String novoTelefone) {
        Cliente cliente = buscarCliente(cpf);
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setTelefone(novoTelefone);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    // Listar clientes do início ao fim
    public void listarClientesInicioAoFim() {
        NodoCliente atual = inicio;
        System.out.println("Lista de Clientes:");
        while (atual != null) {
            System.out.println(atual.cliente);
            atual = atual.proximo;
        }
    }

    // Listar clientes do fim ao início
    public void listarClientesFimAoInicio() {
        NodoCliente atual = fim;
        System.out.println("Lista de Clientes (Reversa):");
        while (atual != null) {
            System.out.println(atual.cliente);
            atual = atual.anterior;
        }
    }
}
