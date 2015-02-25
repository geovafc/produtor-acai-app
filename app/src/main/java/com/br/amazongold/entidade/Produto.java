package com.br.amazongold.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "produto")
public class Produto {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(foreign = true)
	private Rasa rasa;
	@DatabaseField(foreign = true)
	private Colheita colheita;
	
	public Produto(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rasa getRasa() {
		return rasa;
	}

	public void setRasa(Rasa rasa) {
		this.rasa = rasa;
	}

	public Colheita getColheita() {
		return colheita;
	}

	public void setColheita(Colheita colheita) {
		this.colheita = colheita;
	}
	
}
