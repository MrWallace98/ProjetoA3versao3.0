package model;

import java.util.ArrayList;
import java.util.List;

public class Conta {

    private int numeroConta;
    private double saldo;
    private String clienteCpf;
    private List<String> historicoTransacoes;

    public Conta(int numeroConta, double saldo, String clienteCpf) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.clienteCpf = clienteCpf;
        this.historicoTransacoes = new ArrayList<>();
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            historicoTransacoes.add("Saque: -" + valor);
            return true;
        }
        return false; // Saldo insuficiente
    }

    public void depositar(double valor) {
        saldo += valor;
        historicoTransacoes.add("Depósito: +" + valor);
    }

    public List<String> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    int getNumero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
