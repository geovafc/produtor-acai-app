package com.br.amazongold.activity.produto;

import java.util.ArrayList;
import java.util.List;

import com.br.amazongold.adapter.SpinnerAdapterColheita;
import com.br.amazongold.adapter.SpinnerAdapterRasa;
import com.br.amazongold.dao.RasaDAO;
import com.br.amazongold.entidade.Colheita;
import com.br.amazongold.entidade.Local;
import com.br.amazongold.entidade.Produto;
import com.br.amazongold.entidade.Rasa;
import com.br.amazongold.rn.ColheitaRN;
import com.br.amazongold.rn.ProdutoRN;
import com.example.amazongold.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class CadastroProduto extends ActionBarActivity {
	private Spinner sp;
	private Rasa rasa;
	private Produto p;
	private RasaDAO daoR;
	private ProdutoRN rnP;
	private ColheitaRN rnC;
	private Colheita colheita;
	private int idRasa;
	private int cont=0;
	private EditText rasastxt;
	private Button b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_produto);
		b=(Button)findViewById(R.id.rasasSelecionadas);
		
//Spinner Rasa
		daoR = new RasaDAO(getBaseContext());
		sp = new Spinner(getBaseContext());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		sp.setLayoutParams(lp);
		sp.setAdapter(new SpinnerAdapterRasa(getBaseContext(), daoR
				.obterTodos()));
		LinearLayout ln = (LinearLayout) findViewById(R.id.layoutProduto);
		ln.addView(sp, 1);
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				rasa = (Rasa) parent.getAdapter().getItem(position);
				idRasa = rasa.getId();
				
				cont++;
				b.setText(Integer.toString(cont));
				System.out.println("cont Rasa: " +cont );
				

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		
		
//Spinner Colheita
		rnC= new ColheitaRN(getBaseContext());
		sp = new Spinner(getBaseContext());
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		sp.setLayoutParams(lp2);
		sp.setAdapter(new SpinnerAdapterColheita(getBaseContext(), rnC.obterTodos()));
		ln.addView(sp, 5);
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				colheita = (Colheita) parent.getAdapter().getItem(position);			

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}
	
	public void salvarRasasPreenchidas(View v){
		List<Produto> lista= new ArrayList<Produto>();
		p= new Produto();
		rnP= new ProdutoRN(getBaseContext());
		p.setColheita(colheita);
		p.setRasa(rasa);
		rnP.salvar(p);
		lista=rnP.obterTodos();
		for (Produto produto : lista) {
			System.out.println("Rasas preenchidas:"+produto.getId());
		}
		if (p != null) {
			Toast.makeText(getBaseContext(), "Sucesso, Rasas  Preenchidas!",
					Toast.LENGTH_SHORT).show();
            finish();
		} else {
			Toast.makeText(getBaseContext(), "Erro ao Preencher!!! ",
					Toast.LENGTH_SHORT).show();
		}
	}
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		// Pega o menu xml e passa para o objeto menu
		inflater.inflate(R.menu.produtor, menu);
		return super.onCreateOptionsMenu(menu);

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
