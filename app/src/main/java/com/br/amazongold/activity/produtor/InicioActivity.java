package com.br.amazongold.activity.produtor;

import java.util.List;

import com.br.amazongold.activity.colheita.CadastroColheita;
import com.br.amazongold.activity.colheita.ListaColheita;
import com.br.amazongold.activity.local.CadastroLocal;
import com.br.amazongold.activity.local.ListaLocal;
import com.br.amazongold.activity.produto.CadastroProduto;
import com.br.amazongold.activity.produto.ListaProduto;
import com.br.amazongold.activity.rasa.CadastroRasa;
import com.br.amazongold.activity.rasa.ListaRasa;
import com.br.amazongold.entidade.Colheita;
import com.br.amazongold.rn.ColheitaRN;
import com.example.amazongold.R;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
public class InicioActivity extends ActionBarActivity {
    private static final String PREFERENCE_NAME="LoginActivityPreferences";
	private int codigoProdutor;
	private Button b;
	private List<Colheita> colheitas;
	private ColheitaRN rn;
	private int contColheitas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);

        SharedPreferences sharedP=getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        String login=sharedP.getString("login","");
        String senha=sharedP.getString("senha","");
        int produtor=sharedP.getInt("codigoProdutor",0);

        System.out.println("login "+login+"senha "+senha+"produtor "+produtor);


		b=(Button) findViewById(R.id.todasColheitas);
		rn = new ColheitaRN(getApplicationContext());
		colheitas=rn.obterTodos();		
		for (Colheita colheita : colheitas) {
			contColheitas++;
		}
		b.setText(Integer.toString(contColheitas));
		
		
		
		Intent it=getIntent();
//Um � o valor padr�o
		codigoProdutor=it.getIntExtra("codigoP", 1);
		System.out.println("codigo: p"+ codigoProdutor);
	}

    public void sair(){
        SharedPreferences sharedP=getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedP.edit();
//O clear irá limpar os dados daquele arquivo de preferencia e em seguida irá confirmar a operação com o commit.
        editor.clear().commit();

        String login=sharedP.getString("login","");
        String senha=sharedP.getString("senha","");
        int produtor=sharedP.getInt("codigoProdutor",0);

        System.out.println("login sair "+login+"senha "+senha+"produtor "+produtor);
        finish();
    }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicio, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.nova_area:
			startActivity(new Intent(this,CadastroLocal.class));
			break;
		case R.id.listar_area:
			startActivity(new Intent(this,ListaLocal.class));
			break;
		case R.id.nova_colheita:
			startActivity(new Intent(this, CadastroColheita.class));
			break;
		case R.id.listar_colheita:
			startActivity(new Intent(this, ListaColheita.class));
			break;
		case R.id.preencher_rasas:
			startActivity(new Intent(this,CadastroProduto.class));
			break;
		case R.id.listar_rasas_preenchidas:
			startActivity(new Intent(this,ListaProduto.class));

			break;
		case R.id.nova_rasa:
			startActivity(new Intent(this, CadastroRasa.class));
			break;
		case R.id.listar_rasa:
		startActivity(new Intent(this, ListaRasa.class));
			break;
        case R.id.sair:
            sair();
            break;
		//case R.id.enviar_dados:startActivity(new Intent(this, EnviarDados.class));

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
