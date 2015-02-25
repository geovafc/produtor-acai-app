package com.br.amazongold.dao;

import java.util.List;

import android.content.Context;

import com.br.amazongold.conexao.DataBaseHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

public class GenericDAO<E> extends DataBaseHelper<E> {
	protected Dao<E, Integer> dao;
	private Class<E> type;

	public GenericDAO(Context context, Class<E> type) {
		// Super permite acessar o contrutor da classe pai(DataBaseHelper) para
		// passar os
		// parametros
		super(context);
		this.type = type;
		setDao();
	}

	protected void setDao() {
		try {
			dao = DaoManager.createDao(getConnectionSource(), type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<E> obterTodos() {
		try {
			List<E> result = dao.queryForAll();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public E obterId(int id) {
		try {
			E obj = dao.queryForId(id);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao obter:" + e.getMessage());
			return null;
		}
	}

	public void insert(E obj) {
		try {
			dao.create(obj);
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir:" + e.getMessage());
			
		}
		
	}

	public void delete(E obj) {
		try {
			dao.delete(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(E obj) {
		try {
			dao.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}