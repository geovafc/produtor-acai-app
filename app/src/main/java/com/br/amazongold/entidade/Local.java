package com.br.amazongold.entidade;

import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "local")
public class Local {

	@DatabaseField(generatedId = true)
	private int id;
	// @DatabaseField
	// private String nome;
	@DatabaseField
	private String nome;
	@DatabaseField
	private String complemento;
	// @DatabaseField
	// private String estado;
	@DatabaseField
	private double latitude;
	@DatabaseField
	private double longitude;
	@ForeignCollectionField(eager = false)
	private Collection<Colheita> colheitas;

	public Local() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	// public String getEstado() {
	// return estado;
	// }
	//
	// public void setEstado(String estado) {
	// this.estado = estado;
	// }

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Collection<Colheita> getColheitas() {
		return colheitas;
	}

	public void setColheitas(Collection<Colheita> colheitas) {
		this.colheitas = colheitas;
	}

}
