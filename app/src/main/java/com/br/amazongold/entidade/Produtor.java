package com.br.amazongold.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "produtor")
public class Produtor {
	//Pega o id que vem do webService
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField
	private String nome;
	@DatabaseField
	private String sobreNome;
	@DatabaseField(foreign=true)
	private Usuario usuario;
	
	public Produtor(){
		
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

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
