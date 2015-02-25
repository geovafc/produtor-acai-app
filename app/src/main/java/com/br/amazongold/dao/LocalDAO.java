package com.br.amazongold.dao;

import android.content.Context;

import com.br.amazongold.entidade.Local;
import com.j256.ormlite.stmt.QueryBuilder;

public class LocalDAO extends GenericDAO<Local>{
	

	public LocalDAO(Context context) {
		super(context, Local.class);
		// TODO Auto-generated constructor stub
	}
	
	public Local obterUltimo() {
		Local l = new Local();
		try {
			QueryBuilder<Local, Integer> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy("id", true);
			l = dao.queryForFirst(queryBuilder.prepare());
		} catch (Exception e) {
			e.getMessage();
		}
		return l;
	}

}
