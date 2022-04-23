package br.com.jconsult.model;

import java.io.Serializable;

/**
 * Classe entidade (Bean) processo judicial, mesma usada no Web Service
 * @author luan
 */
public class ProcessoJudicial implements Serializable{
	
	private String numeroUnico;
    private String numeroProcesso;
    private String dataAbertura;
    private String natureza;
    private String classe;
    private String parte;
    private String dt_entrada;
    private String volume;
    private String juiz;
    private String comarca;
    private String acao;
    private String processoOrginario;
    
	public String getNumeroUnico() {
		return numeroUnico;
	}
	public void setNumeroUnico(String numeroUnico) {
		this.numeroUnico = numeroUnico;
	}
	public String getNumeroProcesso() {
		return numeroProcesso;
	}
	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}
	public String getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public String getNatureza() {
		return natureza;
	}
	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	@Override
	public String toString() {
		return "Número Único: " + this.numeroUnico +
				"\nNúmero do Processo:" + this.numeroProcesso +
				"\nData de Abertura: " + this.dataAbertura +
				"\nNatureza: " + this.natureza +
				"\nClasse: " + this.classe;
	}
	

}
