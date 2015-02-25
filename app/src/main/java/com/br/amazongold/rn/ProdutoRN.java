package com.br.amazongold.rn;

import java.util.List;

import android.content.Context;
import com.br.amazongold.dao.ProdutoDAO;
import com.br.amazongold.entidade.Produto;

public class ProdutoRN {
ProdutoDAO dao;
Context context;

public ProdutoRN(Context context) {
	this.context=context;
	dao = new ProdutoDAO(context);
	
}

public void salvar(Produto produto){		
	 dao.insert(produto);
}

public List<Produto> obterTodos(){
	return dao.obterTodos();
}

public void atualizar(Produto produto){
	dao.update(produto);
}

public Produto  obterID(int id){
	Produto produto=dao.obterId(id);
	return produto;
}

//public Produto obterUltimo(){
//	return dao.obterUltimo();
//}

public void excluir(Produto produto){
	dao.delete(produto);
}
}
