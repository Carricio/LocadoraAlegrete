class Categoria {
    private String nome;
    private int identificador;

    public Categoria(String nome, int identificador) {
        this.nome = nome;
        this.identificador = identificador;
    }

    public int getIdentificador() {
        return identificador;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", identificador=" + identificador +
                '}';
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
        for (int i = 0; i < count; i++) {
            System.out.println(categorias[i]);
        }
    }

    public void listarCategoriasReverso() {
        for (int i = count - 1; i >= 0; i--) {
            System.out.println(categorias[i]);
        }
    }
}
