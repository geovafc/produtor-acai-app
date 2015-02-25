package com.br.amazongold.activity.produto;

import com.br.amazongold.adapter.ColheitaAdapter;
import com.br.amazongold.adapter.ProdutoAdapter;
import com.br.amazongold.rn.ProdutoRN;
import com.example.amazongold.R;
import com.example.amazongold.R.id;
import com.example.amazongold.R.layout;
import com.example.amazongold.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListaProduto extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_produto);
		ProdutoRN rn = new ProdutoRN(getBaseContext());
		setListAdapter(new ProdutoAdapter(getBaseContext(), rn.obterTodos()));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_produto, menu);
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
