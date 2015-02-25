package com.br.amazongold.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.amazongold.entidade.Colheita;

public class ColheitaAdapter extends BaseAdapter {
	private Context context;
	private List<Colheita> lista;

	public ColheitaAdapter(Context context, List<Colheita> lista) {
		this.context = context;
		this.lista = lista;
	}

	@Override
	public int getCount() {

		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Retorna valores para o elemento dessa determinada posicao
		Colheita c = (Colheita) getItem(position);
		LayoutInflater layout = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// Transforma o meu layout em um objeto do tipo View.
		View v = layout.inflate(
				com.example.amazongold.R.layout.activity_listar_colheita, null);

		TextView txtColheita = (TextView) v
				.findViewById(com.example.amazongold.R.id.viewColheita);
		txtColheita.setText(c.getData().toString());
		return v;
	}

}
