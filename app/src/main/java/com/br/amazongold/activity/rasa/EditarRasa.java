package com.br.amazongold.activity.rasa;

import java.math.BigDecimal;

import com.br.amazongold.activity.local.EditarLocal;
import com.br.amazongold.dao.RasaDAO;
import com.br.amazongold.entidade.Rasa;
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

public class EditarRasa extends ActionBarActivity {
	EditText pesoCapacidadetxt;
	EditText codigotxt;
	EditText complementotxt;
	private Rasa rasa;
	private RasaDAO dao;
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_rasa);
		Intent it= getIntent();
		id=it.getIntExtra("codigo",1);
		dao=new RasaDAO(getBaseContext());
		rasa=dao.obterId(id);
		pesoCapacidadetxt = (EditText) findViewById(R.id.editarPesoCapacidadeRasa);
		codigotxt = (EditText) findViewById(R.id.editarCodigoRasa);
		complementotxt = (EditText) findViewById(R.id.editarComplementoRasa);
		
		pesoCapacidadetxt.setText(rasa.getPesoCapacidade().toString());
		 codigotxt.setText(rasa.getCodigo().toString());
		 complementotxt.setText(rasa.getCodigo().toString());

	}
	public void excluirRasa(View v) {
		AlertDialog.Builder alerta= new AlertDialog.Builder(EditarRasa.this);
		alerta.setTitle("Confrimar");
		alerta.setMessage("Confirmar a exclus�o ?");
		//Metodo executado se escolher sim 
		alerta.setPositiveButton("Sim",new DialogInterface.OnClickListener() 
 {
			
			public void onClick(DialogInterface dialog, int which) {
				//Deleta a rasa que obteve pelo id
				dao.delete(rasa);
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
	public void cadastrarRasa(View v) {

		BigDecimal pesoCapacidadeRasa = new BigDecimal(pesoCapacidadetxt
				.getText().toString());
		String codigo = codigotxt.getText().toString();
		String complemento = complementotxt.getText().toString();

		rasa.setCodigo(codigo);
		rasa.setComplemento(complemento);
		rasa.setPesoCapacidade(pesoCapacidadeRasa);
		dao.update(rasa);
		if (rasa != null) {
			Toast.makeText(getBaseContext(), "Sucesso, rasa atualizada!",
					Toast.LENGTH_SHORT).show();
            finish();
		} else {
			Toast.makeText(getBaseContext(), "Erro ao atualizar!!! ",
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
