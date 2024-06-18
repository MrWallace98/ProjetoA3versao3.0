package dao;

import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Configurações do banco de dados (substitua com suas próprias configurações)
    private static final String URL = "jdbc:mysql://localhost:3306/banco_clientes";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    // SQL statements
    private static final String INSERT_CLIENTE = "INSERT INTO clientes (nome, cpf, telefone, senha) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CLIENTE_BY_CPF = "SELECT * FROM clientes WHERE cpf = ?";
    private static final String UPDATE_CLIENTE = "UPDATE clientes SET nome = ?, telefone = ?, senha = ? WHERE cpf = ?";
    private static final String DELETE_CLIENTE = "DELETE FROM clientes WHERE cpf = ?";
    private static final String SELECT_ALL_CLIENTES = "SELECT * FROM clientes";

    // Método para adicionar um novo cliente no banco de dados
    public boolean adicionarCliente(Cliente cliente) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                PreparedStatement stmt = conn.prepareStatement(INSERT_CLIENTE)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getSenha());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar um cliente por CPF no banco de dados
    public Cliente buscarClientePorCpf(String cpf) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                PreparedStatement stmt = conn.prepareStatement(SELECT_CLIENTE_BY_CPF)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String senha = rs.getString("senha");

                return new Cliente(nome, cpf, telefone, senha);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para atualizar um cliente no banco de dados
    public boolean atualizarCliente(Cliente clienteAtualizado) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                PreparedStatement stmt = conn.prepareStatement(UPDATE_CLIENTE)) {

            stmt.setString(1, clienteAtualizado.getNome());
            stmt.setString(2, clienteAtualizado.getTelefone());
            stmt.setString(3, clienteAtualizado.getSenha());
            stmt.setString(4, clienteAtualizado.getCpf());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para deletar um cliente do banco de dados
    public boolean deletarCliente(String cpf) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                PreparedStatement stmt = conn.prepareStatement(DELETE_CLIENTE)) {

            stmt.setString(1, cpf);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos os clientes do banco de dados
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_ALL_CLIENTES)) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                String senha = rs.getString("senha");

                Cliente cliente = new Cliente(nome, cpf, telefone, senha);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}
