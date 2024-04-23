class Cliente {
    private String nome;
    private String cnh;
    private String telefone;
    private String cpf;

    public Cliente(String nome, String cnh, String telefone, String cpf) {
        this.nome = nome;
        this.cnh = cnh;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cnh='" + cnh + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}

// Classe para gerenciar uma lista de clientes
class ListaClientes {
    private Cliente[] clientes;
    private int count;

    public ListaClientes(int tamanho) {
        this.clientes = new Cliente[tamanho];
        this.count = 0;
    }

    public void adicionarCliente(Cliente cliente) {
        if (count < clientes.length) {
            clientes[count] = cliente;
            count++;
        } else {
            System.out.println("Lista de clientes está cheia.");
        }
    }

    public void excluirCliente(String cpf) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (clientes[i].getCpf().equals(cpf)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            // Move os elementos para frente para preencher o espaço do item excluído
            for (int i = index; i < count - 1; i++) {
                clientes[i] = clientes[i + 1];
            }
            clientes[count - 1] = null; // Última posição agora é null
            count--;
            System.out.println("Cliente excluído.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void editarCliente(String cpf, String novoNome, String novaCnh, String novoTelefone) {
        for (int i = 0; i < count; i++) {
            if (clientes[i].getCpf().equals(cpf)) {
                clientes[i].setNome(novoNome);
                clientes[i].setCnh(novaCnh);
                clientes[i].setTelefone(novoTelefone);
                System.out.println("Cliente atualizado.");
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    public Cliente buscarClientePorCPF(String cpf) {
        for (int i = 0; i < count; i++) {
            if (clientes[i].getCpf().equals(cpf)) {
                return clientes[i];
            }
        }
        return null;
    }

    public void listarClientes() {
        for (int i = 0; i < count; i++) {
            System.out.println(clientes[i]);
        }
    }

    public void listarClientesReverso() {
        for (int i = count - 1; i >= 0; i--) {
            System.out.println(clientes[i]);
        }
    }
}

// aaaaaa