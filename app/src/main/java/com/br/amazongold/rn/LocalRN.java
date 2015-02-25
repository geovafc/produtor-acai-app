package com.br.amazongold.rn;
import java.util.List;

import com.br.amazongold.dao.LocalDAO;
import com.br.amazongold.entidade.Local;

import android.content.Context;

public class LocalRN {
	LocalDAO dao;
	Context context;
	

	public LocalRN(Context context) {
		this.context=context;
		dao = new LocalDAO(context);
		
	}

	public void salvar(Local local){		
		 dao.insert(local);
	}
	
	public List<Local> obterTodos(){
		return dao.obterTodos();
	}
	
	public void atualizar(Local local){
		dao.update(local);
	}
	
	public Local  obterID(int id){
		Local local=dao.obterId(id);
		return local;
	}
	
	public Local obterUltimo(){
		return dao.obterUltimo();
	}
	
	public void excluir(Local l){
		dao.delete(l);
	}
	

}
