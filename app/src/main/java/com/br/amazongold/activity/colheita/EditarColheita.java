package com.br.amazongold.activity.colheita;

import java.math.BigDecimal;
import java.util.Calendar;

import com.br.amazongold.adapter.SpinnerAdapterLocal;
import com.br.amazongold.dao.ColheitaDAO;
import com.br.amazongold.dao.UsuarioDAO;
import com.br.amazongold.entidade.Colheita;
import com.br.amazongold.entidade.Local;
import com.br.amazongold.entidade.Usuario;
import com.br.amazongold.rn.LocalRN;
import com.example.amazongold.R;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class EditarColheita extends ActionBarActivity {
	private int ano, mes, dia;
	private Button dataColheitaB;
	private String dataColheita;
	private Spinner sp;
	private LocalRN rnLocal;
	private Local local;
	private int idLocal;
	EditText pesotxt;
	EditText observacaoColheitatxt;
	private Usuario usuarioP;
	private UsuarioDAO daoUsuario;
	private int id;
	private ColheitaDAO dao;
	private Colheita colheita;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_colheita);
		Intent it = getIntent();

		id = it.getIntExtra("codigo", 1);
		dao = new ColheitaDAO(getBaseContext());
		colheita = dao.obterId(id);
		System.out.println(colheita.getId());
		
		pesotxt = (EditText) findViewById(R.id.editarPesoColheita);
		observacaoColheitatxt = (EditText) findViewById(R.id.editarObservacaoColheita);
//Dados mostrados nos campos
		pesotxt.setText(colheita.getPeso().toString());
		observacaoColheitatxt.setText(colheita.getObservacao());
		// Calend�rio
		Calendar calendar = Calendar.getInstance();
		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);

		dataColheitaB = (Button) findViewById(R.id.editarDataColheita);
		// Formato que eu quero que apare�a
		dataColheitaB.setText(dia + "/" + (mes + 1) + "/" + ano);
		// Formato que eu quero enviar para o banco
		dataColheita = ano + "-" + (mes + 1) + "-" + dia;

		// Spinner
		rnLocal = new LocalRN(getBaseContext());
		sp = new Spinner(getBaseContext());
		// Utiliza esse elemento devido o spinner est� dentro desse layout
		// Especifica o tamnho que ser� pego do layout, lagura e altura
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		// Seta dentro de spinner para espcificar que o spinner esta naquele
		// layout
		sp.setLayoutParams(lp);

		sp.setAdapter(new SpinnerAdapterLocal(getBaseContext(), rnLocal
				.obterTodos()));
		// Coloca o spinner dentro do lynearlayout especificado
		LinearLayout ln = (LinearLayout) findViewById(R.id.editarLayoutColheita);
		ln.addView(sp, 3);
		// sp=(Spinner)findViewById(R.id.spinnerLocal);
		// sp.setAdapter(new SpinnerAdapterLocal(getBaseContext(),
		// rn.obterTodos()));
		// Pega o item que foi clicado e chama o metodo onItemSelected para
		// realizar uma a��o
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				local = (Local) parent.getAdapter().getItem(position);
				idLocal = local.getId();
				System.out.println("id clicado: " + idLocal);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		
		
	}

	public void selecionarDataC(View view) {
		showDialog(view.getId());
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (R.id.editarDataColheita == id) {
			return new DatePickerDialog(this, listener, ano, mes, dia);
		}
		return null;
	}

	private OnDateSetListener listener = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			ano = year;
			mes = monthOfYear;
			dia = dayOfMonth;
			dataColheitaB.setText(dia + "/" + (mes + 1) + "/" + ano);
		}
	};
	
	public void excluirColheita(View v) {
		AlertDialog.Builder alerta= new AlertDialog.Builder(EditarColheita.this);
		alerta.setTitle("Confrimar");
		alerta.setMessage("Confirmar a exclus�o ?");
		//Metodo executado se escolher sim 
		alerta.setPositiveButton("Sim",new DialogInterface.OnClickListener() 
 {
			
			public void onClick(DialogInterface dialog, int which) {
				colheita = dao.obterId(id);
				dao.delete(colheita);
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
		
		// l = rn.obterUltimo();
		// if (l != null) {
		// Toast.makeText(getBaseContext(),
		// "Sucesso, �rea " + l.getLocalidade() + " atualizada",
		// Toast.LENGTH_SHORT).show();
		// finish();
		// }else {
		// Toast.makeText(getBaseContext(),
		// "Erro ao atualizar!!! ",
		// Toast.LENGTH_SHORT).show();
		// }
	}
		public void cadastrarColheita(View v) {
//			Colheita c = new Colheita();
			ColheitaDAO daoColheita = new ColheitaDAO(getBaseContext());

			daoUsuario = new UsuarioDAO(getBaseContext());
		usuarioP=daoUsuario.obterId(colheita.getId());

			BigDecimal peso = new BigDecimal(pesotxt.getText().toString());
			String observacao = observacaoColheitatxt.getText().toString();
			
			colheita.setData(dataColheita);
			// Seta o local clicado no spinner
			colheita.setLocal(local);
			
			colheita.setPeso(peso);
			colheita.setObservacao(observacao);
			//Produtor � o mesmo n�o muda
			colheita.setProdutor(usuarioP);
			daoColheita.update(colheita);
			System.out.println(colheita.getLocal());
			if (colheita != null) {
				Toast.makeText(getBaseContext(), "Sucesso, colheita salva!",
						Toast.LENGTH_SHORT).show();
                finish();
			} else {
				Toast.makeText(getBaseContext(), "Erro ao salvar!!! ",
						Toast.LENGTH_SHORT).show();
			}
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_colheita, menu);
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
