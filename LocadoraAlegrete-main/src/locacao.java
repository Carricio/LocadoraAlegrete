// Classe Locacao com atributos para cliente, veículo, datas e valor
class Locacao {
    private Cliente cliente;
    private Veiculo veiculo;
    private String dataRetirada;
    private String dataDevolucao;
    private double valor;

    public Locacao(Cliente cliente, Veiculo veiculo, String dataRetirada, String dataDevolucao, double valor) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDataRetirada() {
        return dataRetirada;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "cliente=" + cliente +
                ", veiculo=" + veiculo +
                ", dataRetirada='" + dataRetirada + '\'' +
                ", dataDevolucao='" + dataDevolucao + '\'' +
                ", valor=" + valor +
                '}';
    }
}

// Classe para gerenciar uma lista de locações
class ListaLocacoes {
    private Locacao[] locacoes;
    private int count;

    public ListaLocacoes(int tamanho) {
        this.locacoes = new Locacao[tamanho];
        this.count = 0;
    }

    public int count() {
        return count;
    }

    public Locacao get(int index) {
        if (index >= 0 && index < count) {
            return locacoes[index];
        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    public void adicionarLocacao(Locacao locacao) {
        if (count < locacoes.length) {
            locacoes[count] = locacao;
            count++;
        } else {
            expandirArray();
            locacoes[count++] = locacao;
        }
    }

    private void expandirArray() {
        Locacao[] novoArray = new Locacao[locacoes.length * 2];
        System.arraycopy(locacoes, 0, novoArray, 0, locacoes.length);
        locacoes = novoArray;
    }

    public void excluirLocacao(String placaVeiculo) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (locacoes[i].getVeiculo().getPlaca().equals(placaVeiculo)) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            for (int i = index; i < count - 1; i++) {
                locacoes[i] = locacoes[i + 1];
            }
            locacoes[count - 1] = null;
            count--;
            System.out.println("Locação excluída com sucesso.");
        } else {
            System.out.println("Locação não encontrada.");
        }
    }

    public Locacao buscarLocacaoPorVeiculo(String placaVeiculo) {
        for (int i = 0; i < count; i++) {
            if (locacoes[i].getVeiculo().getPlaca().equals(placaVeiculo)) {
                return locacoes[i];
            }
        }
        return null;
    }

    public void listarLocacoes() {
        for (int i = 0; i < count; i++) {
            System.out.println(locacoes[i]);
        }
    }

    public void listarLocacoesReverso() {
        for (int i = count - 1; i >= 0; i--) {
            System.out.println(locacoes[i]);
        }
    }

    public void listarVeiculosDisponiveis(ListaVeiculos gerenciadorVeiculos) {
        System.out.println("Veículos disponíveis para locação:");
        // Loop em todos os veículos
        for (int i = 0; i < gerenciadorVeiculos.getCount(); i++) {
            boolean disponivel = true; // Assume que o veículo está disponível
            Veiculo veiculo = gerenciadorVeiculos.get(i); // Obter o veículo da lista

            // Verifica se o veículo está em alguma locação ativa
            for (int j = 0; j < this.count; j++) {
                if (this.locacoes[j].getVeiculo().getPlaca().equals(veiculo.getPlaca())) {
                    disponivel = false; // Se o veículo está em uma locação, ele não está disponível
                    break;
                }
            }

            // Se o veículo não está em nenhuma locação, ele é considerado disponível
            if (disponivel) {
                System.out.println(veiculo);
            }
        }
    }

    public void listarVeiculosDisponiveis() {

    }
}