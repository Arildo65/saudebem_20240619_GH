package br.edu.gov.fatec.saudebem.repositories;

import br.edu.gov.fatec.saudebem.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {
}
