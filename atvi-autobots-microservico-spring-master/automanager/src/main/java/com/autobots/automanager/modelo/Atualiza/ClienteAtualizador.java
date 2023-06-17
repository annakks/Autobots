package com.autobots.automanager.modelo.atualiza;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelo.StringVerificadorNulo;

public class ClienteAtualizador {
	private StringVerificadorNulo verificador = new StringVerificadorNulo();
	private EnderecoAtualizador enderecoAtualizador = new EnderecoAtualizador();
	private TelefoneAtualizador telefoneAtualizador = new TelefoneAtualizador();

	private void atualizarDados(Cliente cliente, Cliente atualizacao) {
		if (!verificador.verificar(atualizacao.getNome())) {
			cliente.setNome(atualizacao.getNome());
		}
		if (!verificador.verificar(atualizacao.getNomeSocial())) {
			cliente.setNomeSocial(atualizacao.getNomeSocial());
		}
		if (!(atualizacao.getDataCadastro() == null)) {
			cliente.setDataCadastro(atualizacao.getDataCadastro());
		}
		if (!(atualizacao.getDataNascimento() == null)) {
			cliente.setDataNascimento(atualizacao.getDataNascimento());
		}
	}

	public void atualizar(Cliente cliente, Cliente atualizacao) {
		atualizarDados(cliente, atualizacao);
		enderecoAtualizador.atualizar(cliente.getEndereco(), atualizacao.getEndereco());
		telefoneAtualizador.atualizar(cliente.getTelefones(), atualizacao.getTelefones());
	}
}
