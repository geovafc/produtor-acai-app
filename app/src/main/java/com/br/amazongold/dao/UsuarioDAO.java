package com.br.amazongold.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.br.amazongold.entidade.Usuario;
import com.j256.ormlite.stmt.QueryBuilder;

public class UsuarioDAO extends GenericDAO<Usuario> {
	List<Usuario> usuarios;

	public UsuarioDAO(Context context) {
		super(context, Usuario.class);

	}

	public List<Usuario> obterPorLoginEPorSenha(String email, String senha) {
		usuarios = new ArrayList<Usuario>();
		try {
			QueryBuilder<Usuario, Integer> queryBuilder = dao.queryBuilder();
			queryBuilder.where().eq("email", email).and().eq("senha", senha);
			usuarios = dao.query(queryBuilder.prepare());

		} catch (Exception e) {
			System.out.println("Erro:" + e.getMessage());
			e.printStackTrace();
		}
		return usuarios;
	}

}
