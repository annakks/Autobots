package com.autobots.automanager.servico;

import com.autobots.automanager.entidades.Usuario;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaUsuario {
	private long id;
	private Usuario usuario;
}
