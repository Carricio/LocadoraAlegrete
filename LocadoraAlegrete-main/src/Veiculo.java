import java.io.BufferedReader;
import java.io.FileReader;

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

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
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
    int count;

    public ListaVeiculos(int tamanho) {
        this.veiculos = new Veiculo[tamanho];
        this.count = 0;
    }

    public ListaVeiculos() {

    }

    public int getCount() {
        return count;
    }

    public Veiculo get(int index) {
        if (index >= 0 && index < count) {
            return veiculos[index];
        }
        return null;
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./LocadoraAlegrete-main/Veiculos.csv"))) {

            String formatLines;
            while((formatLines = bufferedReader.readLine()) != null) {
                String formatLines1 = formatLines.replaceAll(";", " | ");
                System.out.println(formatLines1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < count; i++) {
            Veiculo veiculo = veiculos[i];
            String concat = String.valueOf(veiculo.getPlaca()).concat(" | ").concat(veiculo.getModelo().concat(veiculo.getMarca()));
            System.out.println(concat);
            System.out.println(veiculos[i]);
        }
    }

    public void listarVeiculosReverso() {
        for (int i = count - 1; i >= 0; i--) {
            System.out.println(veiculos[i]);
        }
    }

    public int count() {
        return count;
    }
}