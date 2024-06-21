package br.edu.gov.fatec.saudebem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicoRecordDto(@NotBlank String nome, @NotBlank String cpf, @NotBlank String endereco, @NotNull Integer idade, @NotBlank String telefone, @NotBlank String crm, @NotBlank String especialidade) {
}
