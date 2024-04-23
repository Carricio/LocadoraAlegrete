class Veiculo {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private int potencia;
    private int lugares;
    private Categoria categoria;

    public Veiculo(String placa, String modelo, String marca, int ano, int potencia, int lugares, Categoria categoria) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.potencia = potencia;
        this.lugares = lugares;
        this.categoria = categoria;
    }

    public String getPlaca() {
        return placa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", ano=" + ano +
                ", potencia=" + potencia +
                ", lugares=" + lugares +
                ", categoria=" + categoria +
                '}';
    }
}

// Classe para gerenciar uma lista de veículos
class ListaVeiculos {
    private Veiculo[] veiculos;
    private int count;

    public ListaVeiculos(int tamanho) {
        this.veiculos = new Veiculo[tamanho];
        this.count = 0;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        if (count < veiculos.length) {
            veiculos[count] = veiculo;
            count++;
        } else {
            System.out.println("Lista de veículos está cheia.");
        }
    }

    public void excluirVeiculo(String placa) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (veiculos[i].getPlaca().equals(placa)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            for (int i = index; i < count - 1; i++) {
                veiculos[i] = veiculos[i + 1];
            }
            veiculos[count - 1] = null;
            count--;
            System.out.println("Veículo excluído.");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (int i = 0; i < count; i++) {
            if (veiculos[i].getPlaca().equals(placa)) {
                return veiculos[i];
            }
        }
        return null;
    }

    public void listarVeiculos() {
        for (int i = 0; i < count; i++) {
            System.out.println(veiculos[i]);
        }
    }

    public void listarVeiculosReverso() {
        for (int i = count - 1; i >= 0; i--) {
            System.out.println(veiculos[i]);
        }
    }
}