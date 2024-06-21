package br.edu.gov.fatec.saudebem.models;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_PACIENTES")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("P")
public class PacienteModel extends PessoaModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String convenio;

    public PacienteModel() {
    }

    public PacienteModel(Integer id, String nome, String cpf, String endereco, Integer idade, String telefone, String convenio) {
        super(id, nome, cpf, endereco, idade, telefone);
        this.convenio = convenio;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    @Override
    public String toString() {
        return "PacienteModel{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", idade=" + getIdade() +
                ", telefone='" + getTelefone() + '\'' +
                ", convenio='" + convenio + '\'' +
                '}';
    }
}
