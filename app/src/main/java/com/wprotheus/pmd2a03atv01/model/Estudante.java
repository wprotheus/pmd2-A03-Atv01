package com.wprotheus.pmd2a03atv01.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wprotheus.pmd2a03atv01.R;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Estudante implements Serializable {
    private final static long serialVersionUID = -5418105005221494259L;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("ano_nascimento")
    @Expose
    private int anoNascimento;
    @SerializedName("nota")
    @Expose
    private Nota nota;
    private int idade;
    private int imagem;

    public Estudante(String nome, int anoNascimento, Nota nota) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public int getIdade() {
        return idade = (LocalDateTime.now().getYear()) - anoNascimento;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getMedia() {
        DecimalFormat df = new DecimalFormat("0.##");
        return Double.parseDouble(df.format((double) (nota.getN1() + nota.getN2() + nota.getN3()) / 3));
    }

    public void setMedia(double media) {
    }

    public boolean isSituacao() {
        return ((nota.getN1() + nota.getN2() + nota.getN3()) / 3) > 6;
    }

    public void setSituacao(boolean situacao) {
    }

    public String situacao() {
        return isSituacao() ? "Aprovado" : "Reprovado";
    }

    public int getImagem() {
        return isSituacao() ? R.raw.passed : R.raw.notpass;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        String resultado = isSituacao() ? "Aprovado" : "Reprovado";
        final StringBuilder sb = new StringBuilder("User { ");
        sb.append("Nome: ").append(getNome()).append('\n')
                .append("Idade: ").append(getIdade()).append('\n')
                .append("Média: ").append(getMedia()).append('\n')
                .append("Situação: ").append(resultado).append(" }\n");
        return sb.toString();
    }
}