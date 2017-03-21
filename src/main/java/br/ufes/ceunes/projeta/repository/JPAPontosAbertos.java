package br.ufes.ceunes.projeta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufes.ceunes.projeta.model.PontoAberto;

@Repository
public interface JPAPontosAbertos extends JpaRepository<PontoAberto, Long> {

}
