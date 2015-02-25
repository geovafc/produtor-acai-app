package com.br.amazongold.rn;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.br.amazongold.dao.ColheitaDAO;
import com.br.amazongold.entidade.Colheita;

import android.content.Context;
import android.net.ParseException;
import android.text.format.DateFormat;


public class ColheitaRN {
	ColheitaDAO dao;
	Context context;

	public ColheitaRN(Context context) {
		this.context=context;
		this.dao = new ColheitaDAO(context);
		
		
	}
	
	public List<Colheita> obterTodos(){
		return dao.obterTodos();
	}
	
	public Date conveteStringDate(String data) throws java.text.ParseException{
//		Calendar c=Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date d = formatter.parse(data);
		System.out.println( d);
		return d;
	}
}
