package com.br.amazongold.activity.colheita;

import com.br.amazongold.adapter.ColheitaAdapter;
import com.br.amazongold.adapter.LocalAdapter;
import com.br.amazongold.dao.ColheitaDAO;
import com.br.amazongold.entidade.Colheita;
import com.br.amazongold.entidade.Local;
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

public class ListaColheita extends ListActivity {
	private Colheita colheita;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ColheitaDAO dao = new ColheitaDAO(getBaseContext());
		setListAdapter(new ColheitaAdapter(getBaseContext(), dao.obterTodos()));
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		// Recebe como valor do item clicado da lista naquela posição
		colheita = (Colheita) l.getAdapter().getItem(position);
		// Chama a Activity editar e envia o id desse objeto para ela.
		startActivity(new Intent(getBaseContext(), EditarColheita.class)
				.putExtra("codigo", colheita.getId()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listar_colheita, menu);
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
