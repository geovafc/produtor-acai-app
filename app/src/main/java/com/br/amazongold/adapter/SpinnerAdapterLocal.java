package com.br.amazongold.adapter;

import java.util.List;

import com.br.amazongold.entidade.Local;
import com.example.amazongold.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerAdapterLocal extends BaseAdapter{
	private Context context;
	private List<Local> lista;
	
	public SpinnerAdapterLocal(Context context, List<Local> lista){
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
			Local l= (Local)getItem(position);
			//atributo que ir� mostrar cada item do spinner
			TextView tv= new TextView(context);
			//Seta o nome da localidade para mostrar no spinner
			tv.setText(l.getNome());
			//Retorna o TextView para mostrar os dados na tela
			return tv;
//			LayoutInflater layout=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			//Transforma o meu layout em um objeto do tipo View.	
//			View v=layout.inflate(android.R.layout.simple_spinner_dropdown_item, null);
//			//atributo que ir� mostrar cada item do spinner no layout especificado
//			TextView txtLocalidade=new TextView(context);
//			txtLocalidade.setText(l.getLocalidade());
//			return v;
		}

}
