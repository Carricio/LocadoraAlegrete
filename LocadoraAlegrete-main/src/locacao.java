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

    public void adicionarLocacao(Locacao locacao) {
        if (count < locacoes.length) {
            locacoes[count] = locacao;
            count++;
        } else {
            System.out.println("Lista de locações está cheia.");
        }
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
            System.out.println("Locação excluída.");
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

    public void listarVeiculosDisponiveis() {
        // Lista para armazenar placas dos veículos locados
        String[] placasLocadas = new String[count];
        int locadasCount = 0;

        // Coletar placas de veículos locados
        for (int i = 0; i < count; i++) {
            placasLocadas[locadasCount] = locacoes[i].getVeiculo().getPlaca();
            locadasCount++;
        }

        // Listar veículos disponíveis
        System.out.println("Veículos disponíveis para locação:");
        for (int i = 0; i < placasLocadas.length; i++) {
            boolean estaLocado = false;
            for (int j = 0; j < locadasCount; j++) {
                if (placasLocadas[i] != null && placasLocadas[i].equals(locacoes[j].getVeiculo().getPlaca())) {
                    estaLocado = true;
                    break;
                }
            }
            if (!estaLocado) {
                System.out.println("Veículo disponível: " + placasLocadas[i]);
            }
        }
    }
}