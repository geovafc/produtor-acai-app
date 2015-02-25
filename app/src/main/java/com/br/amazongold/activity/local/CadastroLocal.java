package com.br.amazongold.activity.local;

import java.io.IOException;
import com.br.amazongold.entidade.Local;
import com.br.amazongold.rn.LocalRN;
import com.example.amazongold.R;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroLocal extends ActionBarActivity implements
		LocationListener {
	EditText localidadetxt;
	EditText complementotxt;
	EditText latitudetxt;
	EditText longitutetxt;
	LocationManager lm = null;
	ProgressDialog pgd = null;
	double latitude;
	double longitude;
	Local l;
	LocalRN rn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_local);
		localidadetxt = (EditText) findViewById(R.id.localidade);
		complementotxt = (EditText) findViewById(R.id.complemento);
		latitudetxt = (EditText) findViewById(R.id.latitude);
		longitutetxt = (EditText) findViewById(R.id.longitude);
		l = new Local();
		rn = new LocalRN(getBaseContext());
	}

	public void marcarCoordenada(View v) {
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Indica os crit�rios a sele��o de um provedor de localiza��o.
		Criteria criteria = new Criteria();
		// Procura o melhor provedor para pegar as coordenadas
		String provider = lm.getBestProvider(criteria, false);
		// Seta o provedor utilizado para obter a localiza��o, o tempo minimo
		// para obter a localiza��o
		// de quanto em quanto tempo eu quero pegar a localiza��o (chama o
		// locationChanged),
		// a distancia minima para obter a localiza��o (de quantos em quantos
		// metros eu quero que
		// pegue a minha localiza��o, se setar 0 , a cada mudan�a ele pega a
		// locALIZA��O)
		// por ultimo especifica quem est� utilizando o LocationListener
		lm.requestLocationUpdates(provider, 4000, 0, this);
		pgd = ProgressDialog.show(CadastroLocal.this, "Aguarde...",
				"Buscando Localiza��o!", true, false, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
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

	// � disparado quando o posicionamento altera
	@Override
	public void onLocationChanged(Location location) {
		pgd.dismiss();

		// EditText edtxtLatitude = (EditText) findViewById(R.id.edtxtLatitude);
		// EditText edtxtLongitude = (EditText)
		// findViewById(R.id.edtxtLongitude);
		//
		// edtxtLatitude.setText(String.valueOf(location.getLatitude()));
		// edtxtLongitude.setText(String.valueOf(location.getLongitude()));

		latitude = location.getLatitude();
		longitude = location.getLongitude();
		latitudetxt.setText(String.valueOf(latitude));
		longitutetxt.setText(String.valueOf(longitude));

		// Remove todas as atualiza��es de localiza��o pendente para essa
		// activity
		lm.removeUpdates(this);

	}

	public void cadastrarLocal(View v) {

		String localidade = localidadetxt.getText().toString();
		String complemento = complementotxt.getText().toString();
		// double latitude =
		// Double.parseDouble(latitudetxt.getText().toString());
		// double longitude = Double
		// .parseDouble(longitutetxt.getText().toString());

		l.setNome(localidade);
		l.setComplemento(complemento);
		l.setLatitude(latitude);
		l.setLongitude(longitude);
		rn.salvar(l);
		if (l != null) {
			Toast.makeText(getBaseContext(), "Sucesso, �rea  salva!",
					Toast.LENGTH_SHORT).show();
			//startActivity(new Intent("Inicio"));
            finish();
		} else {
			Toast.makeText(getBaseContext(), "Erro ao salvar!!! ",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}
}
