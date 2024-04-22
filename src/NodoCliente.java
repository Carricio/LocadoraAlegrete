class NodoCliente {
    Cliente cliente;
    NodoCliente proximo;
    NodoCliente anterior;

    public NodoCliente(Cliente cliente) {
        this.cliente = cliente;
        this.proximo = null;
        this.anterior = null;
    }
}
