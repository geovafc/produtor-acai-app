package com.br.amazongold.entidade;

import java.io.Serializable;
import java.util.Collection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "usuario")
public class Usuario implements Serializable{
	// Pega o id do produtor enviado do webService
//	@DatabaseField(id=true)
//	private int id;
	@DatabaseField(id=true)
	private int id;
    @DatabaseField
    private String username;
    @DatabaseField
    private String perfil;

	@DatabaseField
	private String email;
	@DatabaseField
	private String senha;
	// Indica que uma cole��o de colheitas ser� carregada de forma sincrona
	// (eager=false)
	// toda vez que o objeto usuarioP for carregado.
	//@ForeignCollectionField(eager = false)
	//private static Collection<Colheita> colheitas;

	public Usuario() {

	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/*

    public Collection<Colheita> getColheitas() {
		return colheitas;
	}
	 */

    /*

	public void setColheitas(Collection<Colheita> colheitas) {
		this.colheitas = colheitas;
	}
     */


}
