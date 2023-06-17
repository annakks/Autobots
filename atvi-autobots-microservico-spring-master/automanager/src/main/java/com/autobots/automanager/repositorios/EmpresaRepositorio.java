package com.autobots.automanager.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entidades.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
  public Optional<Empresa> findByRazaoSocial(String nome);
}