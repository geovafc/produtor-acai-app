package com.br.amazongold.rn;

import java.io.Serializable;

import android.content.Context;

import com.br.amazongold.dao.UsuarioDAO;
import com.br.amazongold.entidade.Usuario;

public class UsuarioRN implements Serializable {
	Context context;
	UsuarioDAO dao ;
	public UsuarioRN(Context context) {
		this.context = context;
		dao = new UsuarioDAO(context);
	}
	
	public Usuario obterId(int id) {
		try {
			Usuario usuario = dao.obterId(id);
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao obter:" + e.getMessage());
			return null;
		}
	}
	
	
	public int obterCodUsuarioLogado(){
		int codigo = 0;
//Pecorre todos devido ter apenas um usu�rio no banco e retorna o id do mesmo
		for (Usuario u : dao.obterTodos()) {
			codigo= u.getId();
		}
		return codigo;
	}
	

	// Ao realizar o login, ser� recuperado os dados do usuario que vem do
	// webservice e
	// armazenado no bd do celular para realizar login posteriormente.
	// Criar uma consulta para buscar os dados no banco em caso de posteriores
	// logins
	public Usuario obterPorLoginEPorSenha(Usuario usuario) {
		// primeiro verificar se loga depois criar uma logica pra so salvar quem
		// nao tiver no bd
		return (Usuario) dao.obterPorLoginEPorSenha(usuario.getEmail(),
				usuario.getSenha());
	}
	

	// colocar a logica para salvar, pois ta so testando
	public void salvar(Usuario usuario) {
		 dao.insert(usuario);
	}
}
