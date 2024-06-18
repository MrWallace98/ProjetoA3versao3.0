package dao;

import model.Conta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    // Configurações do banco de dados (substitua com suas próprias configurações)
    private static final String URL = "jdbc:mysql://localhost:3306/banco_clientes";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    // SQL statements
    private static final String INSERT_CONTA = "INSERT INTO contas (numero_conta, saldo, cliente_cpf) VALUES (?, ?, ?)";
    private static final String SELECT_CONTA_BY_NUMERO = "SELECT * FROM contas WHERE numero_conta = ?";
    private static final String UPDATE_CONTA = "UPDATE contas SET saldo = ? WHERE numero_conta = ?";
    private static final String DELETE_CONTA = "DELETE FROM contas WHERE numero_conta = ?";
    private static final String SELECT_ALL_CONTAS = "SELECT * FROM contas WHERE cliente_cpf = ?";

    // Método para adicionar uma nova conta no banco de dados
    public boolean adicionarConta(Conta conta) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                PreparedStatement stmt = conn.prepareStatement(INSERT_CONTA)) {

            stmt.setInt(1, conta.getNumeroConta());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setString(3, conta.getClienteCpf());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar uma conta por número no banco de dados
    public Conta buscarContaPorNumero(int numeroConta) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                PreparedStatement stmt = conn.prepareStatement(SELECT_CONTA_BY_NUMERO)) {

            stmt.setInt(1, numeroConta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double saldo = rs.getDouble("saldo");
                String clienteCpf = rs.getString("cliente_cpf");
                return new Conta(numeroConta, saldo, clienteCpf);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para atualizar uma conta no banco de dados
    public boolean atualizarConta(Conta conta) {
        // Código para atualizar a conta no banco de dados
        // Este é apenas um exemplo, ajuste conforme a implementação do seu banco de dados
        String sql = "UPDATE contas SET saldo = ? WHERE numeroConta = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, conta.getSaldo());
            pstmt.setInt(2, conta.getNumeroConta());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método para deletar uma conta do banco de dados
    public boolean deletarConta(int numeroConta) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                PreparedStatement stmt = conn.prepareStatement(DELETE_CONTA)) {

            stmt.setInt(1, numeroConta);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar uma conta pelo CPF do cliente no banco de dados
    public Conta buscarContaPorCliente(String clienteCpf) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CONTAS)) {

            stmt.setString(1, clienteCpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int numeroConta = rs.getInt("numero_conta");
                double saldo = rs.getDouble("saldo");
                return new Conta(numeroConta, saldo, clienteCpf);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para listar todas as contas do banco de dados
    public List<Conta> listarContas() {
        List<Conta> contas = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CONTAS)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int numeroConta = rs.getInt("numero_conta");
                double saldo = rs.getDouble("saldo");
                String clienteCpf = rs.getString("cliente_cpf");

                Conta conta = new Conta(numeroConta, saldo, clienteCpf);
                contas.add(conta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contas;
    }

    private Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
