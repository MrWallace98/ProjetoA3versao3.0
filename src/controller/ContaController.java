package controller;

import dao.ContaDAO;
import model.Conta;

import java.util.List;

public class ContaController {

    private ContaDAO contaDAO;
    
    public boolean atualizarConta(Conta conta) {
        return contaDAO.atualizarConta(conta);
    }

    public ContaController() {
        this.contaDAO = new ContaDAO();
    }

    // Método para adicionar uma nova conta
    public boolean adicionarConta(int numeroConta, double saldo, String clienteCpf) {
        return contaDAO.adicionarConta(new Conta(numeroConta, saldo, clienteCpf));
    }

    // Método para buscar uma conta por número
    public Conta buscarContaPorNumero(int numeroConta) {
        return contaDAO.buscarContaPorNumero(numeroConta);
    }

    // Método para atualizar o saldo de uma conta
    public boolean atualizarSaldoConta(int numeroConta, double novoSaldo) {
        Conta conta = contaDAO.buscarContaPorNumero(numeroConta);
        if (conta != null) {
            conta.setSaldo(novoSaldo);
            return contaDAO.atualizarConta(conta);
        }
        return false;
    }

    // Método para realizar um saque em uma conta
    public boolean sacarConta(int numeroConta, double valorSaque) {
        Conta conta = contaDAO.buscarContaPorNumero(numeroConta);
        if (conta != null && conta.sacar(valorSaque)) {
            return contaDAO.atualizarConta(conta);
        }
        return false;
    }

    // Método para obter o saldo de uma conta
    public double obterSaldoConta(int numeroConta) {
        Conta conta = contaDAO.buscarContaPorNumero(numeroConta);
        if (conta != null) {
            return conta.getSaldo();
        }
        return -1; // Retorna -1 se a conta não for encontrada
    }

    // Método para deletar uma conta
    public boolean deletarConta(int numeroConta) {
        return contaDAO.deletarConta(numeroConta);
    }

    // Método para listar todas as contas
    public List<Conta> listarContas() {
        return contaDAO.listarContas();
    }

   
    public boolean sacar(Conta conta, double valorSaque) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
