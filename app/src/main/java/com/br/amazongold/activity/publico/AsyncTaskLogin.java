package com.br.amazongold.activity.publico;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.br.amazongold.entidade.Usuario;
import com.br.amazongold.rn.UsuarioRN;

public class AsyncTaskLogin {
	Context context;
	UsuarioRN rn;
	Usuario u;

	public AsyncTaskLogin(Context contex) {
		this.context = contex;

	}

	public Usuario autenticarAsync(final String email, final String senha) {
		rn = new UsuarioRN(context);
		u = new Usuario();
		// Cria uma AsyncTask aqui. O objetivo � criar uma nova tarefa que ser�
		// executada em
		// Background, aguardando a resposta do banco.
		AsyncTask<Void, Void, Usuario> tarefa = new AsyncTask<Void, Void, Usuario>() {

			// Esse m�todo realiza todo todo o processamento pesado.
			@Override
			protected Usuario doInBackground(Void... params) {

				Log.i("Enviando", "Enviando dados para o banco..."
						+ "\nEmail: " + email + "\nSenha: " + senha);
//				u=rn.obterPorLoginEPorSenha(email, senha);
				Log.i("Login", "Login efetuado");
				return u;

			}

		};
		tarefa.execute();
		return u;
	}
}
