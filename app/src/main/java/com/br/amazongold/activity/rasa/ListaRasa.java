package com.br.amazongold.activity.rasa;

import com.br.amazongold.adapter.RasaAdapter;
import com.br.amazongold.dao.RasaDAO;
import com.br.amazongold.entidade.Local;
import com.br.amazongold.entidade.Rasa;
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

public class ListaRasa extends ListActivity {
private Rasa rasa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RasaDAO dao = new RasaDAO(getBaseContext());
		setListAdapter(new RasaAdapter(getBaseContext(), dao.obterTodos()));
	}
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// Recebe como valor do item clicado da lista naquela posição
		rasa=(Rasa) l.getAdapter().getItem(position);
		// Chama a Activity editar e envia o id desse objeto para ela.
		startActivity(new Intent(getBaseContext(),EditarRasa.class).putExtra("codigo",
				rasa.getId()));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_rasa, menu);
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
