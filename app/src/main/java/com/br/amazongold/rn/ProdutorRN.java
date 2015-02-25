package com.br.amazongold.rn;

import com.br.amazongold.dao.ProdutorDAO;
import com.br.amazongold.entidade.Produtor;

import android.content.Context;

public class ProdutorRN {
	ProdutorDAO dao;
	public ProdutorRN(Context context) {
		dao = new ProdutorDAO(context);
	}
	//Dados vindo do webService
	public void salvar (Produtor p){
		dao.insert(p);
	}
	
	//Dados vindo do webService
	public void atualizar(Produtor p){
		dao.update(p);
	}
}
