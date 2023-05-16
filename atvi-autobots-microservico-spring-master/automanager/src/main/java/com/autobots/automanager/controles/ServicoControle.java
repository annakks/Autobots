package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.modelo.Link.AdicionadorLinkServico;
import com.autobots.automanager.modelo.Seleciona.EmpresaSelecionador;
import com.autobots.automanager.modelo.Seleciona.ServicoSelecionador;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.ServicoRepositorio;
import com.autobots.automanager.servico.EmpresaServico;

@RestController
@RequestMapping("/servico")
@PreAuthorize("hasRole('FUNCIONARIO')")
public class ServicoControle {
	@Autowired
	private ServicoRepositorio repositorio;
	@Autowired
	private EmpresaRepositorio repositorioEmpresa;
	@Autowired
	private ServicoSelecionador selecionador;
	@Autowired
	private EmpresaSelecionador selecionadorEmpresa;
	@Autowired
	private AdicionadorLinkServico adicionadorLink;
	
	@GetMapping("/{id}")
	public ResponseEntity<Servico> obterServico(@PathVariable long id) {
		List<Servico> servicos = repositorio.findAll();
		Servico servico = selecionador.selecionar(servicos, id);
		
		if(servico == null) {
			ResponseEntity<Servico> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(servico);
			ResponseEntity<Servico> resposta = new ResponseEntity<Servico>(servico, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@GetMapping("/servicos")
	public ResponseEntity<List<Servico>> obterServicos() {
		List<Servico> servicos = repositorio.findAll();
		
		if(servicos.isEmpty()) {
			ResponseEntity<List<Servico>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adicionadorLink.adicionarLink(servicos);
			ResponseEntity<List<Servico>> resposta = new ResponseEntity<>(servicos, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@PostMapping("/servico/cadastro")
	public ResponseEntity<?> cadastrarServicos(@RequestBody EmpresaServico dadosServicoNovo) {
		HttpStatus status = HttpStatus.CONFLICT;
		List<Empresa> empresas = repositorioEmpresa.findAll();
		Empresa selecionada = selecionadorEmpresa.selecionar(empresas, dadosServicoNovo.getId());
		
		if (selecionada != null) {
			selecionada.getServicos().add(dadosServicoNovo.getServico());
			repositorioEmpresa.save(selecionada);
			status = HttpStatus.CREATED;
		}
		
		return new ResponseEntity<>(status);
	}
}
