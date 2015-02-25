package com.br.amazongold.adapter;

import java.util.List;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.amazongold.entidade.Local;

public class LocalAdapter extends BaseAdapter {
	private Context context;
	private List<Local> lista;

	public LocalAdapter(Context context, List<Local> lista) {
		this.context = context;
		this.lista = lista;
	}

	// Quantidade de elementos da lista
	@Override
	public int getCount() {

		return lista.size();
	}

	// Retorna um objeto que está em uma determinada posição
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

	/*
	 * Esse método retorna os elementos para uma view. Esses elementos são
	 * buscados em uma lista que são pegados pela posição indicada em getItem, o
	 * getCount indica a quantidade de elementos que podem ser buscados em uma
	 * lista
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Retorna valores para o elemento dessa determinada posicao
		Local l = (Local) getItem(position);
		LayoutInflater layout = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// Transforma o meu layout em um objeto do tipo View.
		View v = layout.inflate(
				com.example.amazongold.R.layout.activity_lista_local, null);

		TextView txtLocalidade = (TextView) v
				.findViewById(com.example.amazongold.R.id.viewLocalidade);
		txtLocalidade.setText(l.getNome());
		return v;
	}

}
