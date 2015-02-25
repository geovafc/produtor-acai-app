package com.br.amazongold.activity.rasa;

import java.math.BigDecimal;

import com.br.amazongold.dao.RasaDAO;
import com.br.amazongold.entidade.Local;
import com.br.amazongold.entidade.Rasa;
import com.br.amazongold.rn.LocalRN;
import com.example.amazongold.R;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroRasa extends ActionBarActivity {
EditText pesoCapacidadetxt;
EditText codigotxt;
EditText complementotxt;
private Rasa rasa;
private RasaDAO dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_rasa);
		pesoCapacidadetxt=(EditText)findViewById(R.id.pesoCapacidadeRasa);
		codigotxt=(EditText)findViewById(R.id.codigoRasa);
		complementotxt=(EditText)findViewById(R.id.complementoRasa);
		
		
	}
	public void cadastrarRasa(View v) {
		rasa= new Rasa();
		dao = new RasaDAO(getBaseContext());
		BigDecimal pesoCapacidadeRasa= new BigDecimal(pesoCapacidadetxt.getText().toString());
		String codigo = codigotxt.getText().toString();
		String complemento = complementotxt.getText().toString();
	

	rasa.setCodigo(codigo);
	rasa.setComplemento(complemento);
	rasa.setPesoCapacidade(pesoCapacidadeRasa);
	dao.insert(rasa);
		if (rasa != null) {
			Toast.makeText(getBaseContext(),
					"Sucesso, rasa salva",
					Toast.LENGTH_SHORT).show();
			finish();
		}else {
			Toast.makeText(getBaseContext(),
					"Erro ao salvar!!! ",
					Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_rasa, menu);
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
