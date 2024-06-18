package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private String cpf;
    private String telefone;
    private String senha;
    private List<Conta> contas;

    public Cliente(String nome, String cpf, String telefone, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
        this.contas = new ArrayList<>();
    }
    
    public int getNumeroConta() {
        if (contas != null && !contas.isEmpty()) {
            return contas.get(0).getNumero(); // Supondo que a classe Conta tenha um método getNumero()
        } else {
            return -1; // Valor especial indicando que não há contas
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Métodos específicos para contas
    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public void adicionarConta(Conta novaConta) {
        this.contas.add(novaConta);
    }

    public Conta buscarConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s\nCPF: %s\nTelefone: %s", nome, cpf, telefone);
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}
