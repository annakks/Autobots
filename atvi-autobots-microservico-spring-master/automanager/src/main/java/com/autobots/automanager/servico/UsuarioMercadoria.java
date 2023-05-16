package com.autobots.automanager.servico;

import com.autobots.automanager.entidades.Mercadoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioMercadoria {
	private long id;
	private Mercadoria mercadoria;
}
