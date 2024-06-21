package br.edu.gov.fatec.saudebem.dtos;

import br.edu.gov.fatec.saudebem.models.MedicoModel;
import br.edu.gov.fatec.saudebem.models.PacienteModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsultaRecordDto(@NotBlank String dataHora, @NotNull Integer pacienteId, @NotNull Integer medicoId) {
    public Integer getPacienteId() {
        return pacienteId;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public String getDataHora() {

        return dataHora;
    }
}
