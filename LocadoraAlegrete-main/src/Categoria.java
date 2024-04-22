class Categoria {
    private String nome;
    private int identificador;

    public Categoria(String nome, int identificador) {
        this.nome = nome;
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
// o ema