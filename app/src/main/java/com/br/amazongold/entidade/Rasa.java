package com.br.amazongold.entidade;

import java.math.BigDecimal;
import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName="rasa")
public class Rasa {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField
	private BigDecimal peso;
	@DatabaseField
	private String codigo;
	@DatabaseField
	private String complemento;
	@ForeignCollectionField(eager = false)
	private Collection <Produto> produtos;
	
	public Rasa(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPesoCapacidade() {
		return peso;
	}

	public void setPesoCapacidade(BigDecimal pesoCapacidade) {
		this.peso = pesoCapacidade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
