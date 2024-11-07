package br.com.topsaudehub.desafio.senior.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "contas_medicas")
public class ContasMedicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int matricula;
    private String tipo;
    private String especialidade;
    private BigDecimal valor;
    private int idade;
    private String sexo;

    public ContasMedicas(int id, int matricula, String tipo, String especialidade, BigDecimal valor, int idade, String sexo) {
        this.id = id;
        this.matricula = matricula;
        this.tipo = tipo;
        this.especialidade = especialidade;
        this.valor = valor;
        this.idade = idade;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
