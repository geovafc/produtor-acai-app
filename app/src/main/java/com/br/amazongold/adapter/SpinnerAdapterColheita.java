package com.br.amazongold.adapter;

import java.util.List;

import com.br.amazongold.entidade.Colheita;
import com.br.amazongold.entidade.Local;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerAdapterColheita extends BaseAdapter{
	private Context context;
	private List<Colheita> lista;
	
	public SpinnerAdapterColheita(Context context, List<Colheita> lista){
		this.context=context;
		this.lista=lista;
	}
	//Quantidade de elementos da lista
		@Override
		public int getCount() {
			
			return lista.size();
		}
		//Retorna um objeto que est� em uma determinada posi��o
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return lista.get(position);
		}

		@Override
		public long getItemId(int position) {
			
			return position;
		}
		/*Esse m�todo retorna os elementos para uma view. Esses elementos s�o buscados em uma lista
		 * que s�o pegados pela posi��o indicada em getItem, o getCount indica a quantidade de elementos
		 * que podem ser buscados em uma lista
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//Retorna o local daquela posi��o
			Colheita c= (Colheita)getItem(position);
			//atributo que ir� mostrar cada item do spinner
			TextView tv= new TextView(context);
			//Seta o nome da localidade para mostrar no spinner
			tv.setText(c.getData());
			//Retorna o TextView para mostrar os dados na tela
			return tv;
		}

}
