import java.io.*;

class Categoria {
    private String nome;
    private int identificador;

    public Categoria() {
    }

    public Categoria(String nome, int identificador) {
        this.nome = nome;
        this.identificador = identificador;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", identificador=" + identificador +
                '}';
    }
    public BufferedReader mostraTabela() throws FileNotFoundException {
        return new BufferedReader(new FileReader("C:\\Users\\Gabriel\\Documents\\LocadoraAlegrete-main\\LocadoraAlegrete-main\\Categorias.csv"));
    }

    public void mostrarLinhas() throws IOException {
        BufferedReader fileInputStream = mostraTabela();
        String itens = "";
        while((itens = fileInputStream.readLine()) != null) {
            String itensLinhas = itens.replaceAll(";", " | ");
            System.out.println(itensLinhas);
        }
    }
}

// Classe para gerenciar uma lista de categorias
class ListaCategorias {
    private Categoria[] categorias;
    private int count;

    public ListaCategorias(int tamanho) {
        this.categorias = new Categoria[tamanho];
        this.count = 0;
    }

    public void adicionarCategoria(Categoria categoria) {
        if (count < categorias.length) {
            categorias[count] = categoria;
            count++;
        } else {
            System.out.println("Lista de categorias está cheia.");
        }
    }

    public void excluirCategoria(int identificador) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (categorias[i].getIdentificador() == identificador) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            for (int i = index; i < count - 1; i++) {
                categorias[i] = categorias[i + 1];
            }
            categorias[count - 1] = null;
            count--;
            System.out.println("Categoria excluída.");
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    public Categoria buscarCategoriaPorIdentificador(int identificador) {
        for (int i = 0; i < count; i++) {
            if (categorias[i].getIdentificador() == identificador) {
                return categorias[i];
            }
        }
        return null;
    }

    public void listarCategorias() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./LocadoraAlegrete-main/Categorias.csv"))) {

            String formatLines;
            while((formatLines = bufferedReader.readLine()) != null) {
                String formatLines1 = formatLines.replaceAll(";", " | ");
                System.out.println(formatLines1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < count; i++) {
            Categoria categoria = categorias[i];
            String concat = String.valueOf(categoria.getIdentificador()).concat(" | ").concat(categoria.getNome());
            System.out.println(concat);
        }
    }

    public void listarCategoriasReverso() {
        for (int i = count - 1; i >= 0; i--) {
            System.out.println(categorias[i]);
        }
    }
}