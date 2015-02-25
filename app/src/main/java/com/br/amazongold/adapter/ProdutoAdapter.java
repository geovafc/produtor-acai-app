package com.br.amazongold.adapter;

import java.util.List;

import com.br.amazongold.entidade.Produto;
import com.br.amazongold.entidade.Rasa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProdutoAdapter extends BaseAdapter{
	private Context context;
	private List<Produto> lista;

	public ProdutoAdapter(Context context, List<Produto> lista) {
		this.context = context;
		this.lista = lista;
	}

	// Quantidade de elementos da lista
	@Override
	public int getCount() {

		return lista.size();
	}

	// Retorna um objeto que                                                                                                                                                                                            est� em uma determinada posi��o
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
	 * Esse m�todo retorna os elementos para uma view. Esses elementos s�o
	 * buscados em uma lista que s�o pegados pela posi��o indicada em getItem, o
	 * getCount indica a quantidade de elementos que podem ser buscados em uma
	 * lista
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Retorna valores para o elemento dessa determinada posicao
		Produto p = (Produto) getItem(position);
		LayoutInflater layout = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// Transforma o meu layout em um objeto do tipo View.
		View v = layout.inflate(
				com.example.amazongold.R.layout.activity_lista_produto, null);

		TextView txtcdgRasa = (TextView) v
				.findViewById(com.example.amazongold.R.id.produtosView);
		txtcdgRasa.setText(Integer.toString(p.getId()));
		return v;
	}

}


