/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;

/**
 *
 * @author yCeifazZ
 */

import cadastro.model.PessoaFisicaDAO;
import cadastro.model.PessoaJuridicaDAO;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;

public class CadastroBDTeste {
    public static void main(String[] args) {
        // Instanciar uma pessoa física
        PessoaFisica pessoaFisica = new PessoaFisica(1, "Fulano", "Rua A", "Cidade A", "Estado A", "123456789", "fulano@example.com", "123.456.789-10");

        // Persistir a pessoa física no banco de dados
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.incluir(pessoaFisica);

        // Alterar os dados da pessoa física no banco
        pessoaFisica.setNome("Fulano Silva");
        pessoaFisicaDAO.alterar(pessoaFisica);

        // Consultar todas as pessoas físicas do banco de dados e listar no console
        System.out.println("Pessoas fisicas cadastradas:");
        for (PessoaFisica pessoa : pessoaFisicaDAO.getPessoas()) {
            System.out.println(pessoa);
        }

        // Excluir a pessoa física criada anteriormente no banco
        pessoaFisicaDAO.excluir(pessoaFisica.getId());

        // Instanciar uma pessoa jurídica
        PessoaJuridica pessoaJuridica = new PessoaJuridica(2, "Empresa XYZ", "Av. B", "Cidade B", "Estado B", "987654321", "empresa@example.com", "12.345.678/0001-90");

        // Persistir a pessoa jurídica no banco de dados
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.incluir(pessoaJuridica);

        // Alterar os dados da pessoa jurídica no banco
        pessoaJuridica.setNome("Nova Empresa XYZ");
        pessoaJuridicaDAO.alterar(pessoaJuridica);

        // Consultar todas as pessoas jurídicas do banco de dados e listar no console
        System.out.println("Pessoas juridicas cadastradas:");
        for (PessoaJuridica pessoa : pessoaJuridicaDAO.getPessoas()) {
            System.out.println(pessoa);
        }

        // Excluir a pessoa jurídica criada anteriormente no banco
        pessoaJuridicaDAO.excluir(pessoaJuridica.getId());
    }
}
