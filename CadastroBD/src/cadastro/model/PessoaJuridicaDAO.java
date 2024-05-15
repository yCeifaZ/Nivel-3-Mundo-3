/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model;

/**
 *
 * @author yCeifazZ
 */
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import cadastrobd.model.PessoaJuridica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    public PessoaJuridica getPessoa(int id) {
        PessoaJuridica pessoa = null;
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Pessoa p JOIN PessoaJuridica pj ON p.id = pj.id WHERE p.id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                pessoa = new PessoaJuridica(resultSet.getInt("id"),
                                             resultSet.getString("nome"),
                                             resultSet.getString("logradouro"),
                                             resultSet.getString("cidade"),
                                             resultSet.getString("estado"),
                                             resultSet.getString("telefone"),
                                             resultSet.getString("email"),
                                             resultSet.getString("cnpj"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Pessoa p JOIN PessoaJuridica pj ON p.id = pj.id");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                PessoaJuridica pessoa = new PessoaJuridica(resultSet.getInt("id"),
                                                           resultSet.getString("nome"),
                                                           resultSet.getString("logradouro"),
                                                           resultSet.getString("cidade"),
                                                           resultSet.getString("estado"),
                                                           resultSet.getString("telefone"),
                                                           resultSet.getString("email"),
                                                           resultSet.getString("cnpj"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pessoa) {
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement("INSERT INTO Pessoa (id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)");
             PreparedStatement preparedStatementPessoaJuridica = connection.prepareStatement("INSERT INTO PessoaJuridica (id, cnpj) VALUES (?, ?)")) {
            int id = SequenceManager.getValue("nome_da_sua_sequence"); // Obter o próximo ID
            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoa.setString(2, pessoa.getNome());
            preparedStatementPessoa.setString(3, pessoa.getLogradouro());
            preparedStatementPessoa.setString(4, pessoa.getCidade());
            preparedStatementPessoa.setString(5, pessoa.getEstado());
            preparedStatementPessoa.setString(6, pessoa.getTelefone());
            preparedStatementPessoa.setString(7, pessoa.getEmail());
            preparedStatementPessoa.executeUpdate();

            preparedStatementPessoaJuridica.setInt(1, id);
            preparedStatementPessoaJuridica.setString(2, pessoa.getCnpj());
            preparedStatementPessoaJuridica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pessoa) {
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement("UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?");
             PreparedStatement preparedStatementPessoaJuridica = connection.prepareStatement("UPDATE PessoaJuridica SET cnpj = ? WHERE id = ?")) {
            preparedStatementPessoa.setString(1, pessoa.getNome());
            preparedStatementPessoa.setString(2, pessoa.getLogradouro());
            preparedStatementPessoa.setString(3, pessoa.getCidade());
            preparedStatementPessoa.setString(4, pessoa.getEstado());
            preparedStatementPessoa.setString(5, pessoa.getTelefone());
            preparedStatementPessoa.setString(6, pessoa.getEmail());
            preparedStatementPessoa.setInt(7, pessoa.getId());
            preparedStatementPessoa.executeUpdate();

            preparedStatementPessoaJuridica.setString(1, pessoa.getCnpj());
            preparedStatementPessoaJuridica.setInt(2, pessoa.getId());
            preparedStatementPessoaJuridica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoaJuridica = connection.prepareStatement("DELETE FROM PessoaJuridica WHERE id = ?");
             PreparedStatement preparedStatementPessoa = connection.prepareStatement("DELETE FROM Pessoa WHERE id = ?")) {
            preparedStatementPessoaJuridica.setInt(1, id);
            preparedStatementPessoaJuridica.executeUpdate();

            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
