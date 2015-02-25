package com.br.amazongold.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.br.amazongold.entidade.Colheita;
import com.br.amazongold.entidade.Local;
import com.br.amazongold.entidade.Produto;
import com.br.amazongold.entidade.Produtor;
import com.br.amazongold.entidade.Rasa;
import com.br.amazongold.entidade.Usuario;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataBaseHelper<E> extends OrmLiteSqliteOpenHelper {
	// nome do banco de dados(o ponto bd nao e obrigatorio)
	// Esses dois atributos devem ter sempre esses mesmo nome, pois
	// estao sendo passados como parametros no construtor da classe
	private static final String databaseName = "produtor.bd";
	// Versao desse BD
	private static final int databaseVersion = 2;

	// Conecta com o BD, esse construtor verifica se o bd existe, se n�o cria o
	// BD e se existir deleta e cria novamente se for necess�rio.
	public DataBaseHelper(Context context) {
		super(context, databaseName, null, databaseVersion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource src) {

		try {
			TableUtils.createTable(src, Usuario.class);
			TableUtils.createTable(src, Produtor.class);
			TableUtils.createTable(src, Colheita.class);
			TableUtils.createTable(src, Local.class);
			TableUtils.createTable(src, Produto.class);
			TableUtils.createTable(src, Rasa.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource src,
			int ioldVersion, int newVersion) {
		try {
			TableUtils.dropTable(src, Colheita.class, true);
			TableUtils.dropTable(src, Local.class, true);
			TableUtils.dropTable(src, Produto.class, true);
			TableUtils.dropTable(src, Produtor.class, true);
			TableUtils.dropTable(src, Rasa.class, true);
			TableUtils.dropTable(src, Usuario.class, true);
			onCreate(db, src);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void close() {
		super.close();
	}
}
