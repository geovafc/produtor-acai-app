package com.br.amazongold.activity.colheita;

import java.math.BigDecimal;
import java.util.Calendar;

import com.br.amazongold.adapter.SpinnerAdapterLocal;
import com.br.amazongold.dao.ColheitaDAO;
import com.br.amazongold.entidade.Colheita;
import com.br.amazongold.entidade.Local;
import com.br.amazongold.entidade.Usuario;
import com.br.amazongold.rn.LocalRN;
import com.br.amazongold.rn.UsuarioRN;
import com.example.amazongold.R;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;

public class CadastroColheita extends ActionBarActivity {
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
	private UsuarioRN rnU;
	private int codigoProdutor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_colheita);
//		Intent it = getIntent();
//		codigoProdutor = it.getIntExtra("codigoP", 1);
//		System.out.println("codigo  inicio" + codigoProdutor);
		pesotxt = (EditText) findViewById(R.id.pesoColheita);
		observacaoColheitatxt = (EditText) findViewById(R.id.observacaoColheita);
		// Calend�rio
		Calendar calendar = Calendar.getInstance();
		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);

		dataColheitaB = (Button) findViewById(R.id.dataColheita);
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

		sp.setAdapter(new SpinnerAdapterLocal(getBaseContext(), rnLocal.obterTodos()));
		// Coloca o spinner dentro do lynearlayout especificado
		LinearLayout ln = (LinearLayout) findViewById(R.id.layoutColheit);
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

	// Falta o codigo do produtor
	public void cadastrarColheita(View v) {
		Colheita c = new Colheita();
		ColheitaDAO daoColheita = new ColheitaDAO(getBaseContext());
		rnU= new UsuarioRN(getBaseContext());
		codigoProdutor=rnU.obterCodUsuarioLogado();
		usuarioP=rnU.obterId(codigoProdutor);
		System.out.println("codigo  cadastro" + usuarioP.getId());

		BigDecimal peso = new BigDecimal(pesotxt.getText().toString());
		String observacao = observacaoColheitatxt.getText().toString();
		
		c.setData(dataColheita);
		// Seta o local clicado no spinner
		c.setLocal(local);
		c.setPeso(peso);
		c.setObservacao(observacao);
		c.setProdutor(usuarioP);
		
		
		System.out.println("produtor colheita set : "+usuarioP.getId());
		daoColheita.insert(c);

		if (c != null) {
			Toast.makeText(getBaseContext(), "Sucesso, colheita salva!",
					Toast.LENGTH_SHORT).show();
            finish();
		} else {
			Toast.makeText(getBaseContext(), "Erro ao salvar!!! ",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void selecionarDataC(View view) {
		showDialog(view.getId());
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (R.id.dataColheita == id) {
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.colheita, menu);
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
