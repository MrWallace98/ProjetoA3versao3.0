package controller;

import model.Cliente;
import dao.ClienteDAO;
import dao.ContaDAO;
import model.Conta;

public class ClienteController {

    private ClienteDAO clienteDAO;
    private ContaDAO contaDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public Conta buscarContaDoCliente(Cliente cliente) {
        ContaDAO contaDAO = new ContaDAO();
        return contaDAO.buscarContaPorCliente(cliente.getCpf());
    }

    // Método para adicionar um novo cliente
    public boolean adicionarCliente(String nome, String cpf, String telefone, String senha) {
        Cliente novoCliente = new Cliente(nome, cpf, telefone, senha);
        return clienteDAO.adicionarCliente(novoCliente);
    }

    // Método para buscar um cliente por CPF
    public Cliente buscarClientePorCpf(String cpf) {
        return clienteDAO.buscarClientePorCpf(cpf);
    }

    // Método para atualizar um cliente
    public boolean atualizarCliente(String nome, String cpf, String telefone, String senha) {
        Cliente clienteAtualizado = new Cliente(nome, cpf, telefone, senha);
        return clienteDAO.atualizarCliente(clienteAtualizado);
    }

    // Método para deletar um cliente
    public boolean deletarCliente(String cpf) {
        return clienteDAO.deletarCliente(cpf);
    }

    // Método para autenticar um cliente
    public Cliente autenticarCliente(String cpf, String senha) {
        Cliente cliente = clienteDAO.buscarClientePorCpf(cpf);
        if (cliente != null && cliente.autenticar(senha)) {
            return cliente; // Retorna o cliente autenticado
        }
        return null; // Retorna null se a autenticação falhar ou se o cliente não for encontrado
    }
}
