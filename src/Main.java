public class Main {
    public static void main(String[] args) {
        ListaClientes listaClientes = new ListaClientes();
        GerenciadorLocacoes gerenciadorLocacoes = new GerenciadorLocacoes(); // Usado para verificar locações associadas a clientes

        // Criar alguns clientes
        Cliente cliente1 = new Cliente("João Silva", "12345678901", "11987654321", "12345678901");
        Cliente cliente2 = new Cliente("Maria Oliveira", "10987654321", "11987654322", "10987654321");

        // Incluir clientes na lista
        listaClientes.incluirCliente(cliente1);
        listaClientes.incluirCliente(cliente2);

        // Listar clientes do início ao fim
        listaClientes.listarClientesInicioAoFim();

        // Buscar um cliente por CPF
        Cliente encontrado = listaClientes.buscarCliente("12345678901");
        System.out.println("Cliente encontrado: " + encontrado);

        // Editar informações do cliente
        listaClientes.editarCliente("12345678901", "João de Silva", "11987654323");

        // Listar clientes do fim ao início
        listaClientes.listarClientesFimAoInicio();

        // Tentar excluir cliente 1 (sem locações)
        try {
            listaClientes.excluirCliente("12345678901", gerenciadorLocacoes);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }

        // Excluir cliente 2 (sem locações)
        listaClientes.excluirCliente("10987654321", gerenciadorLocacoes);

        // Listar clientes após exclusão
        listaClientes.listarClientesInicioAoFim();
    }
}
