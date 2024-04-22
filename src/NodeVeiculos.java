class NodoVeiculo {
    Veiculo veiculo;
    NodoVeiculo proximo;
    NodoVeiculo anterior;

    public NodoVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.proximo = null;
        this.anterior = null;
    }
}
