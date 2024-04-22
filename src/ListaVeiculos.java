class ListaVeiculos {
    private NodoVeiculo inicio;
    private NodoVeiculo fim;

    public ListaVeiculos() {
        this.inicio = null;
        this.fim = null;
    }

    // Incluir um veículo no final da lista
    public void incluirVeiculo(Veiculo veiculo) {
        NodoVeiculo novoNodo = new NodoVeiculo(veiculo);
        if (inicio == null) {
            inicio = fim = novoNodo;
        } else {
            fim.proximo = novoNodo;
            novoNodo.anterior = fim;
            fim = novoNodo;
        }
    }

    // Buscar um veículo por placa
    public Veiculo buscarVeiculo(String placa) {
        NodoVeiculo atual = inicio;
        while (atual != null) {
            if (atual.veiculo.getPlaca().equals(placa)) {
                return atual.veiculo;
            }
            atual = atual.proximo;
        }
        return null; // Veículo não encontrado
    }

    // Excluir um veículo por placa (com restrição de locação)
    public void excluirVeiculo(String placa, GerenciadorLocacoes gerenciadorLocacoes) {
        if (gerenciadorLocacoes.temLocacaoComVeiculo(placa)) {
            throw new IllegalArgumentException("Veículo está atrelado a uma locação. Não pode ser excluído.");
        }

        NodoVeiculo atual = inicio;
        while (atual != null) {
            if (atual.veiculo.getPlaca().equals(placa)) {
                if (atual.anterior != null) {
                    atual.anterior.proximo = atual.proximo;
                } else {
                    inicio = atual.proximo; // Excluindo do início
                }

                if (atual.proximo != null) {
                    atual.proximo.anterior = atual.anterior;
                } else {
                    fim = atual.anterior; // Excluindo do final
                }
                return;
            }
            atual = atual.proximo;
        }

        throw new IllegalArgumentException("Veículo não encontrado.");
    }

    // Editar informações de um veículo por placa
    public void editarVeiculo(String placa, String novoModelo, int novoAno, int novaPotencia, int novoNumLugares) {
        Veiculo veiculo = buscarVeiculo(placa);
        if (veiculo != null) {
            veiculo.setModelo(novoModelo);
            veiculo.setAno(novoAno);
            veiculo.setPotencia(novaPotencia);
            veiculo.setNumLugares(novoNumLugares);
        } else {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }
    }

    // Listar veículos do início ao fim
    public void listarVeiculosInicioAoFim() {
        NodoVeiculo atual = inicio;
        System.out.println("Lista de Veículos:");
        while (atual != null) {
            System.out.println(atual.veiculo);
            atual = atual.proximo;
        }
    }

    // Listar veículos do fim ao início
    public void listarVeiculosFimAo Inicio() {
        NodoVeiculo atual = fim;
        System.out.println("Lista de Veículos (Reversa):");
        while (atual != null) {
            System.out.println(atual.veiculo);
            atual = atual.anterior;
        }
    }
}
