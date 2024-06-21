package br.edu.gov.fatec.saudebem.repositories;

import br.edu.gov.fatec.saudebem.models.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Integer> {
}
