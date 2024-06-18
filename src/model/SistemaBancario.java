package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaBancario {

    private Map<String, Cliente> clientes;

    public SistemaBancario() {
        this.clientes = new HashMap<>();
    }

    // Registrar cliente
    public boolean registrarCliente(String nome, String cpf, String telefone, String senha) {
        if (!clientes.containsKey(cpf)) {
            Cliente novoCliente = new Cliente(nome, cpf, telefone, senha);
            clientes.put(cpf, novoCliente);
            return true; // Cliente registrado com sucesso
        }
        return false; // Cliente já está registrado
    }

    // Buscar cliente por CPF
    public Cliente buscarCliente(String cpf) {
        return clientes.get(cpf); // Retorna o cliente pelo CPF ou null se não encontrado
    }

    // Atualizar cliente
    public boolean atualizarCliente(String cpf, String novoNome, String novoTelefone, String novaSenha) {
        Cliente cliente = clientes.get(cpf);
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setTelefone(novoTelefone);
            cliente.setSenha(novaSenha);
            return true; // Cliente atualizado com sucesso
        }
        return false; // Cliente não encontrado
    }

    // Deletar cliente por CPF
    public boolean deletarCliente(String cpf) {
        Cliente clienteRemovido = clientes.remove(cpf);
        return clienteRemovido != null; // Retorna true se o cliente foi removido com sucesso
    }

    // Autenticar cliente
    public Cliente autenticarCliente(String cpf, String senha) {
        Cliente cliente = clientes.get(cpf);
        if (cliente != null && cliente.autenticar(senha)) {
            return cliente; // Retorna o cliente autenticado
        }
        return null; // Autenticação falhou
    }

    // Criar conta bancária para um cliente
    public boolean criarContaBancaria(String cpfCliente, int numeroConta) {
        Cliente cliente = clientes.get(cpfCliente);
        if (cliente != null) {
            Conta novaConta = new Conta(numeroConta, 0.0, cpfCliente); // Saldo inicial zero
            cliente.adicionarConta(novaConta);
            return true;
        }
        return false; // Cliente não encontrado
    }

    // Transferir entre contas do mesmo cliente
    public boolean transferirEntreContas(String cpfCliente, int numeroContaOrigem, int numeroContaDestino, double valor) {
        Cliente cliente = clientes.get(cpfCliente);
        if (cliente != null) {
            Conta contaOrigem = cliente.buscarConta(numeroContaOrigem);
            Conta contaDestino = cliente.buscarConta(numeroContaDestino);
            if (contaOrigem != null && contaDestino != null && contaOrigem.sacar(valor)) {
                contaDestino.depositar(valor);
                return true;
            }
        }
        return false; // Cliente, conta de origem ou conta de destino não encontrados, ou saldo insuficiente
    }

    // Consultar histórico de transações de uma conta
    public List<String> consultarHistoricoTransacoes(String cpfCliente, int numeroConta) {
        Cliente cliente = clientes.get(cpfCliente);
        if (cliente != null) {
            Conta conta = cliente.buscarConta(numeroConta);
            if (conta != null) {
                return conta.getHistoricoTransacoes();
            }
        }
        return null; // Cliente ou conta não encontrados
    }

    // Exemplo de autenticação com token (gerar e validar token)
    public boolean autenticarComToken(String cpfCliente, String senha, String token) {
        Cliente cliente = clientes.get(cpfCliente);
        if (cliente != null && cliente.autenticar(senha)) {
            // Lógica para gerar e validar token de autenticação
            // Aqui você pode implementar a geração e validação de token
            return true; // Simulação de autenticação bem-sucedida
        }
        return false; // Autenticação falhou
    }

    // Gerar relatório de saldo médio de todas as contas de um cliente
    public double calcularSaldoMedio(String cpfCliente) {
        Cliente cliente = clientes.get(cpfCliente);
        if (cliente != null) {
            List<Conta> contasCliente = cliente.getContas();
            double totalSaldo = 0;
            for (Conta conta : contasCliente) {
                totalSaldo += conta.getSaldo();
            }
            return totalSaldo / contasCliente.size();
        }
        return -1; // Cliente não encontrado
    }
}
