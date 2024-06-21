package br.edu.gov.fatec.saudebem.dtos;

import jakarta.validation.constraints.NotBlank;

public record PessoaRecordDto(@NotBlank String nome, @NotBlank String cpf, @NotBlank String endereco, @NotBlank Integer idade, @NotBlank String telefone) {
}
