package com.autobots.automanager.modelo;

import java.util.Date;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.enumeracoes.TipoDocumento;

public class StringVerificadorNulo {

	public boolean verificar(String string) {
		boolean nulo = true;
		if (!(string == null)) {
			if (!string.isBlank()) {
				nulo = false;
			}
		}
		return nulo;
	}

	public boolean verificar(TipoDocumento tipo) {
		return false;
	}

	public boolean verificar(Endereco endereco) {
		return false;
	}

	public boolean verificar(Date cadastro) {
		return false;
	}
}