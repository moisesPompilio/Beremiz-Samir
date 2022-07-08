/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samirAtivacao.modelo;

import java.util.List;

/**
 *
 * @author AGU
 */
public class InfomacoesDosPrev {
	private int id;
    private String numeroDoProcesso;
	private String nome;
	private String dataAjuizamento;
	private String cpf;
	private String dibInicial;
	private String dibFinal;
	private String rmi;
	private String beneficio;
	private String nb;
	private String aps;
	private String dip;
	private String citacao;
	private String dibAnterior;
	private  String cessado;
	
	private String urlProcesso;
	
	public String getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}
	public String getNb() {
		return nb;
	}
	public void setNb(String nb) {
		this.nb = nb;
	}
	public String getAps() {
		return aps;
	}
	public void setAps(String aps) {
		this.aps = aps;
	}
	public String getDip() {
		return dip;
	}
	public void setDip(String dip) {
		this.dip = dip;
	}
	public String getNumeroDoProcesso() {
		return numeroDoProcesso;
	}
	public void setNumeroDoProcesso(String numeroDoProcesso) {
		this.numeroDoProcesso = numeroDoProcesso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataAjuizamento() {
		return dataAjuizamento;
	}
	public void setDataAjuizamento(String dataAjuizamento) {
		this.dataAjuizamento = dataAjuizamento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDibInicial() {
		return dibInicial;
	}
	public void setDibInicial(String dibInicial) {
		this.dibInicial = dibInicial;
	}
	public String getDibFinal() {
		return dibFinal;
	}
	public void setDibFinal(String dibFinal) {
		this.dibFinal = dibFinal;
	}
	public String getRmi() {
		return rmi;
	}
	public void setRmi(String rmi) {
		this.rmi = rmi;
	}
	public void setInformacoes(InfomacoesDosPrev inf) {
		cpf = inf.getCpf();
		rmi = inf.getRmi();
		dibInicial = inf.dibInicial;
		dibInicial = inf.getDibFinal();
		nome =inf.getNome();
		numeroDoProcesso = inf.getNumeroDoProcesso();
		dataAjuizamento = inf.getDataAjuizamento();

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCitacao() {
		return citacao;
	}
	public void setCitacao(String citacao) {
		this.citacao = citacao;
	}

	public String getCessado() {
		return cessado;
	}

	public String getDibAnterior() {
		return dibAnterior;
	}

	public void setDibAnterior(String dibAnterior) {
		this.dibAnterior = dibAnterior;
	}

	public void setCessado(String cessado) {
		this.cessado = cessado;
	}
	public String getUrlProcesso() {
		return urlProcesso;
	}
	public void setUrlProcesso(String urlProcesso) {
		this.urlProcesso = urlProcesso;
	}
}
