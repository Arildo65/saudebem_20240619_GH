package br.edu.gov.fatec.saudebem.models;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.DiscriminatorValue;

@Entity
@Table(name = "TB_MEDICOS")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("M")
public class MedicoModel extends PessoaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String crm;
    private String especialidade;

    public MedicoModel() {
    }

    public MedicoModel(Integer id, String nome, String cpf, String endereco, Integer idade, String telefone, String crm, String especialidade) {
        super(id, nome, cpf, endereco, idade, telefone);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "MedicoModel{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", idade=" + getIdade() +
                ", telefone='" + getTelefone() + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
