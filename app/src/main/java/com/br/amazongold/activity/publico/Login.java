package com.br.amazongold.activity.publico;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.br.amazongold.activity.produtor.InicioActivity;
import com.br.amazongold.entidade.Usuario;
import com.br.amazongold.rn.UsuarioRN;
import com.example.amazongold.R;
import com.google.gson.Gson;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends ActionBarActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    private static final String PREFERENCE_NAME="LoginActivityPreferences";
	private EditText login;
	private EditText senha;
	// C�digo vem do webservice, � o id da tabela usuario
	private int codigoProdutor ;
    String url="http://187.24.7.165:8080/WSAcai/resources/wsusuario/logar";
    RequestQueue requestQueue;
    private static Usuario usuarioSolicitador = new Usuario();
    Gson gson = new Gson();
	UsuarioRN rn;
    private ProgressDialog progressD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
/*
sharedP representa o arquivo de preferencia que será criado

PREFERENCE_NAME: nome do arquivo de preferencia
MODE_PRIVATE: só deixa ser acessado o shared preferences desta aplicação. Para uma aplicação poder ter acesso ao shared preferences de outra
aplicação, é necessário ambas terem o mesmo userId (criado pelo linux).
 */
        SharedPreferences sharedP=getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
//Variável responsável por armazenar os dados relacionados a chave de login.
/*
O segundo parâmetro é um valor default que é retornado caso não haja nem um valor no loginPreferences para aquela chave.
Caso exista algum valor, o mesmo será retornado em vez do "".
 */
        String loginPreferences=sharedP.getString("login","");
//Variável responsável por armazenar os dados relacionados a chave senha.
        String senhaPreferences=sharedP.getString("senha","");
////Variável responsável por armazenar os dados relacionados a chave codigoProdutor.
        int codigoProdutorPreferences=sharedP.getInt("codigoProdutor",0);

        login = (EditText) findViewById(R.id.login);
		senha = (EditText) findViewById(R.id.senha);


// utilizo o getApplicationContext() para o requestQueue durar toda a vida útil da minha aplicação e não precisar ser recriado
//Quando for contruida uma nova activity, no caso quando o usuario gira o dispositivo ou chamar outra activity.
         requestQueue= Volley.newRequestQueue(this.getApplicationContext());

        if (!loginPreferences.equals("") && !senhaPreferences.equals("")){
            startActivity(new Intent(this,InicioActivity.class));
        }
	}

	// Dados vindo inicialmente do webservice, depois ficam armazenados no cel.
	// O c�digo do produtor fica armazenados para poder ser utilizado
	// pela entidade colheita. Ao logar, os dados s�o salvos.
	public void entrarOnClick(View v) throws JSONException {
        progressD=ProgressDialog.show(this,"Aguarde","...");


		String emailInformado = login.getText().toString();
		String senhaInformada = senha.getText().toString();

        usuarioSolicitador.setEmail(emailInformado);
        usuarioSolicitador.setSenha(senhaInformada);




        String convertidoJson=gson.toJson(usuarioSolicitador);
     JSONObject jsonConversor= new JSONObject(convertidoJson);



        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST,url,jsonConversor,this,this);
        requestQueue.add(jsonObjectRequest);
//Se n�o tiver nem um valor salva no banco para poder realizar o acesso
//isEmpty verifica se aquele objeto n�o contem nada
	//	if (usuarios.isEmpty()){
	//		dao.insert(u);
	//		System.out.println("inserido");
	//	}
//Valor que recuperou do BD	pra poder validar  o dado digitado
		//u = dao.obterId(codigoProdutor);
		// System.out.println(u.getEmail());
		// AsyncTaskLogin async= new AsyncTaskLogin(getBaseContext());
		// async.autenticarAsync(emailInformado, senhaInformada);
		//
		// u=async.autenticarAsync(emailInformado, senhaInformada);
/*
		if (u.getEmail().equals(emailInformado) && u.getSenha().equals(senhaInformada)) {

			String mensagemSucesso = "Sucesso, seja bem vindo "+usuarioLogado.getEmail();
			Toast notificacaoErro = Toast.makeText(this, mensagemSucesso,
					Toast.LENGTH_SHORT);
			notificacaoErro.show();
			*/
//Chama a outra activity e manda o id do objeto que foi setado para outra tela, para em seguida ser usado por outras activity

        /*
		} else {
			// Em caso de erro, mostra uma mensagem
			String mensagemErro = "Login ou Senha inv�lidos";
			Toast notificacaoErro = Toast.makeText(this, mensagemErro,
					Toast.LENGTH_SHORT);
			notificacaoErro.show();
		}
*/
	}
    @Override
    public void onErrorResponse(VolleyError error) {
            System.out.println("Erro  " + error.getMessage());
            String mensagemErro=error.getMessage().toString();
            progressD.dismiss();
        Toast.makeText(getBaseContext(),mensagemErro,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onResponse(JSONObject response) {
            progressD.dismiss();
        try{
            System.out.println("recebido: " + response.toString());
            System.out.println("solc"+usuarioSolicitador.getEmail());
            System.out.println("solc"+usuarioSolicitador.getSenha());

            Usuario usuarioRetornado=gson.fromJson(response.toString(),Usuario.class);
            System.out.println("usuario"+usuarioRetornado.getEmail());
            System.out.println("senha"+usuarioRetornado.getSenha());

            if  (usuarioRetornado!=null && usuarioSolicitador.getEmail().equals(usuarioRetornado.getEmail())
                    && usuarioSolicitador.getSenha().equals(usuarioRetornado.getSenha())){
//Acessa o arquivo de preferencia
                SharedPreferences sharedP=getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
//Cria uma variável para editar  o arquivo representado por sharedP.
                SharedPreferences.Editor editor = sharedP.edit();
//Adiciona os valores que serão salvos no arquivo de preferencia.
                editor.putString("login",usuarioRetornado.getEmail());
                editor.putString("senha",usuarioRetornado.getSenha());
                editor.putInt("codigoProdutor",usuarioRetornado.getId());
//Passa os valores informados para as suas respectivas chaves. Se o arquivo ainda não existe ele é criado junto com as suas variáveis.
                editor.commit();

                startActivity(new Intent(getBaseContext(), InicioActivity.class));
                Toast.makeText(getBaseContext(),"Sucesso, seja bem vindo!",Toast.LENGTH_SHORT).show();
            }else {
                System.out.println("Login ou Senha Incorretos!");
                Toast.makeText(getBaseContext(),"Login ou Senha Incorreto!",Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            System.out.println("erro na resposta: "+e.getMessage());
        }


    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
