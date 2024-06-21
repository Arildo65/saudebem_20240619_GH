package br.edu.gov.fatec.saudebem.repositories;

import br.edu.gov.fatec.saudebem.models.ConsultaModel;
import br.edu.gov.fatec.saudebem.models.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Integer> {
}