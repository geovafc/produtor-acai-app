package com.br.amazongold.entidade;

import java.math.BigDecimal;
import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "colheita")
public class Colheita {
	// utilizar integer pq vai se trabalhar com objeto
	@DatabaseField(generatedId = true)
	private int id;
	//O produtor � um objeto do tipo usuario produtor, pois os dados vem do webservice e 
	// s�o armazenados em usuarioP
	@DatabaseField(foreign = true)
	private Usuario produtor;
	@DatabaseField
	private String data_colheita;
	@DatabaseField
	private BigDecimal peso;
	@DatabaseField
	private String observacao;
	@DatabaseField(foreign = true)
	private Local local;
	@ForeignCollectionField(eager = false)
	private Collection<Produto> produtos;
	public Colheita() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getProdutor() {
		return produtor;
	}

	public void setProdutor(Usuario produtor) {
		this.produtor = produtor;
	}

	public String getData() {
		return data_colheita;
	}

	public void setData(String data) {
		this.data_colheita = data;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}
	
	

}
