package br.edu.gov.fatec.saudebem.repositories;

import br.edu.gov.fatec.saudebem.models.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaModel, Integer> {
}
