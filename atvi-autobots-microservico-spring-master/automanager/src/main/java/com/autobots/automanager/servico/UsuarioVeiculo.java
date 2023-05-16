package com.autobots.automanager.servico;

import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioVeiculo {
	private long id;
	private Usuario usuario;
	private Veiculo veiculo;
}
