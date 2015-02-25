package com.br.amazongold.activity.local;

import com.br.amazongold.adapter.LocalAdapter;
import com.br.amazongold.entidade.Local;
import com.br.amazongold.rn.LocalRN;
import com.example.amazongold.R;
import com.example.amazongold.R.id;
import com.example.amazongold.R.layout;
import com.example.amazongold.R.menu;
import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ListaLocal extends ListActivity {
	Local local;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LocalRN rn = new LocalRN(getBaseContext());
		// Não pode vim com esse elemento, pois o layout já foi implementado em
		// AlunoAdpter
		// setContentView(R.layout.activity_listar);
		// Seta o adapter de um objeto. Através desse elemento, será retornado
		// uma lista de objetos na visao
		setListAdapter(new LocalAdapter(getBaseContext(), rn.obterTodos()));
	}

	// Esse método realiza uma ação para cada objeto
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// Recebe como valor do item clicado da lista naquela posição
		local = (Local) l.getAdapter().getItem(position);
		// Chama a Activity editar e envia o id desse objeto para ela.
		startActivity(new Intent("EdicaoLocal").putExtra("codigo",
				local.getId()));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listar_local, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
