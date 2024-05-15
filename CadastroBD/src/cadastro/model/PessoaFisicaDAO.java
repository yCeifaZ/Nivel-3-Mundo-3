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
import cadastrobd.model.PessoaFisica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    public PessoaFisica getPessoa(int id) {
        PessoaFisica pessoa = null;
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Pessoa p JOIN PessoaFisica pf ON p.id = pf.id WHERE p.id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                pessoa = new PessoaFisica(resultSet.getInt("id"),
                                          resultSet.getString("nome"),
                                          resultSet.getString("logradouro"),
                                          resultSet.getString("cidade"),
                                          resultSet.getString("estado"),
                                          resultSet.getString("telefone"),
                                          resultSet.getString("email"),
                                          resultSet.getString("cpf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Pessoa p JOIN PessoaFisica pf ON p.id = pf.id");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                PessoaFisica pessoa = new PessoaFisica(resultSet.getInt("id"),
                                                        resultSet.getString("nome"),
                                                        resultSet.getString("logradouro"),
                                                        resultSet.getString("cidade"),
                                                        resultSet.getString("estado"),
                                                        resultSet.getString("telefone"),
                                                        resultSet.getString("email"),
                                                        resultSet.getString("cpf"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoa) {
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement("INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)");
             PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement("INSERT INTO PessoaFisica (Pessoa_idPessoa, cpf) VALUES (?, ?)")) {

            preparedStatementPessoa.setString(2, pessoa.getNome());
            preparedStatementPessoa.setString(3, pessoa.getLogradouro());
            preparedStatementPessoa.setString(4, pessoa.getCidade());
            preparedStatementPessoa.setString(5, pessoa.getEstado());
            preparedStatementPessoa.setString(6, pessoa.getTelefone());
            preparedStatementPessoa.setString(7, pessoa.getEmail());
            preparedStatementPessoa.executeUpdate();


            preparedStatementPessoaFisica.setString(2, pessoa.getCpf());
            preparedStatementPessoaFisica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pessoa) {
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement("UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?");
             PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement("UPDATE PessoaFisica SET cpf = ? WHERE id = ?")) {
            preparedStatementPessoa.setString(1, pessoa.getNome());
            preparedStatementPessoa.setString(2, pessoa.getLogradouro());
            preparedStatementPessoa.setString(3, pessoa.getCidade());
            preparedStatementPessoa.setString(4, pessoa.getEstado());
            preparedStatementPessoa.setString(5, pessoa.getTelefone());
            preparedStatementPessoa.setString(6, pessoa.getEmail());
            preparedStatementPessoa.setInt(7, pessoa.getId());
            preparedStatementPessoa.executeUpdate();

            preparedStatementPessoaFisica.setString(1, pessoa.getCpf());
            preparedStatementPessoaFisica.setInt(2, pessoa.getId());
            preparedStatementPessoaFisica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement("DELETE FROM PessoaFisica WHERE id = ?");
             PreparedStatement preparedStatementPessoa = connection.prepareStatement("DELETE FROM Pessoa WHERE id = ?")) {
            preparedStatementPessoaFisica.setInt(1, id);
            preparedStatementPessoaFisica.executeUpdate();

            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
