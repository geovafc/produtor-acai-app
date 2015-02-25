package com.br.amazongold.activity.local;

import com.br.amazongold.activity.produtor.InicioActivity;
import com.br.amazongold.dao.LocalDAO;
import com.br.amazongold.entidade.Local;
import com.br.amazongold.rn.LocalRN;
import com.example.amazongold.R;
import com.example.amazongold.R.id;
import com.example.amazongold.R.layout;
import com.example.amazongold.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarLocal extends ActionBarActivity {
	EditText localidadetxt;
	EditText complementotxt;
	EditText latitudetxt;
	EditText longitutetxt;
	Local local;
	LocalDAO dao;
	int id = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_local);
		// Retorna a intent que chamou essa Activity AQUI (Editar.class)
		Intent it = getIntent();
		// Obtem o id do objeto que foi clicado. Recupera o id daquele objeto
		// atrav�s da chave codigo
		id = it.getIntExtra("codigo", 1);
		dao = new LocalDAO(getBaseContext());
		// Obtenho pelo id pra poder trabalhar com o elemento que veio da outra
		// Activity
		local = dao.obterId(id);
		System.out.println("por id: " + local.getLatitude());
		localidadetxt = (EditText) findViewById(R.id.editarLocalidade);
		complementotxt = (EditText) findViewById(R.id.editarComplemento);
		latitudetxt = (EditText) findViewById(R.id.editarLatitude);
		longitutetxt = (EditText) findViewById(R.id.editarLongitude);

		localidadetxt.setText(local.getNome());
		complementotxt.setText(local.getComplemento());
		latitudetxt.setText(local.getLatitude().toString());
		longitutetxt.setText(local.getLongitude().toString());
	}

	public void excluirLocal(View v) {
		AlertDialog.Builder alerta= new AlertDialog.Builder(EditarLocal.this);
		alerta.setTitle("Confrimar");
		alerta.setMessage("Confirmar a exclus�o ?");
		//Metodo executado se escolher sim 
		alerta.setPositiveButton("Sim",new DialogInterface.OnClickListener() 
 {
			
			public void onClick(DialogInterface dialog, int which) {
				local = dao.obterId(id);
				dao.delete(local);
				Toast.makeText(getBaseContext(), "Excus�o realizada com suceso!",Toast.LENGTH_SHORT).show();
                finish();
				
				
			}
		});
		//M�todo executado se escolher n�o
		alerta.setNegativeButton("N�o", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "N�o exclu�do!",Toast.LENGTH_SHORT).show();
				
			}
		});
		alerta.show();
		
	}

	public void cadastrarLocal(View v) {
		String localidade = localidadetxt.getText().toString();
		String complemento = complementotxt.getText().toString();
		double latitude = Double.parseDouble(latitudetxt.getText().toString());
		double longitude = Double
				.parseDouble(longitutetxt.getText().toString());

		local.setNome(localidade);
		local.setComplemento(complemento);
		local.setLatitude(latitude);
		local.setLongitude(longitude);
		dao.update(local);
		local = dao.obterUltimo();
		if (local != null) {
			Toast.makeText(getBaseContext(),
					"Sucesso, �rea atualizada",
					Toast.LENGTH_SHORT).show();
			//startActivity(new Intent("Inicio"));
            finish();
		} else {
			Toast.makeText(getBaseContext(), "Erro ao atualizar!!! ",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_local, menu);
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
