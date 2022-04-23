package br.com.jconsult.activity;

import java.io.InputStream;

import org.apache.http.HttpEntity;

import br.com.jconsult.R;
import br.com.jconsult.model.ProcessoJudicial;
import br.com.jconsult.preference.Preferences;
import br.com.jconsult.webservice.HttpClientSingleton;
import br.com.jconsult.webservice.ProcessoJudicialREST;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity principal do aplicativo, responsável por criar os objetos da tela principal.
 * @author luan
 *
 */
public class MainActivity extends Activity {

	private TextView textNumeroProcesso;
    private TextView textNumeroUnico;
    private TextView textPesquisa;
    private EditText edtNumeroProcesso;
    private EditText edtNumeroUnico;
    private ImageButton btPesquisar;
    private ImageButton btLimpar;
    private Intent intentListaProcesso;
    private ProgressDialog dialogi;
    private Handler handler = new Handler();
	//private static final String PREFERENCIA = "preferenciaJConsult";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);       
        inicializa();
        eventos();
        lerPreferencias();
      
    }
    
    
    /**
     * Adiciona o menu a Tela da aplicação
     */
    @Override
   	public boolean onCreateOptionsMenu(Menu menu) {
   		
    	MenuInflater menuInflater = getMenuInflater();
    	menuInflater.inflate(R.menu.lista_menu, menu);
    	return true;  	
   	}
    
    /**
     * Ação dos menus da aplicação
     */
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
    	switch (item.getItemId()){
    		case R.id.menu_configuracao:
    			Intent itentPreferencias = new Intent(MainActivity.this, Preferences.class);
    			startActivity(itentPreferencias);
    			return true;
    		case R.id.menu_sobre:
    			Intent itentSobre = new Intent(MainActivity.this, Sobre.class);
    			startActivity(itentSobre);
    			return true;
    		default:
    			break;
    	
    	}
    	return super.onOptionsItemSelected(item);
	}
    
    private void inicializa() {
        // Intancia os Intent
        intentListaProcesso = new Intent(this, ListaProcesso.class);
        
        // Instancia os textView
        textNumeroProcesso = (TextView) findViewById(R.id.label_numero_processo);
        textNumeroUnico = (TextView) findViewById(R.id.label_numero_unico);
        textPesquisa = (TextView) findViewById(R.id.texto_pesquisa);
        
        //Instancia os EditText
        edtNumeroProcesso = (EditText) findViewById(R.id.edt_numero_processo);
        edtNumeroUnico = (EditText) findViewById(R.id.edt_numero_unico);
        
        // Instancia os Button
        btPesquisar = (ImageButton) findViewById(R.id.btn_pesquisar);
        btLimpar = (ImageButton) findViewById(R.id.btn_limpar);

    }
    
   
	

	/**
     * Método utilizado para inicializar os eventos dos botões exitentes na camada de visão do arquivo main.xml
     */
        private void eventos() {
        	
        	btPesquisar.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                	/*
                	 * Nenhum dado para pesquisa foi informado (quando não há preenchimento)
                	 */
                	
                	if ((edtNumeroUnico.getText().toString().length()>0)) {
                		///Só pra não gerar o erro(Função ainda não implementada)
        				showDialong("Número de Processo Inválido");
        				limparCampos();
                		edtNumeroProcesso.requestFocus();
                	}else
                	/*testa se os campos (edtNumeroProcesso e edtNumeroUnico) foram ambos preenchidos*/
                	if ((edtNumeroProcesso.getText().toString().length()>0) && (edtNumeroUnico.getText().toString().length()>0)) {
        				showDialong("Por favor, preencha somente um dos campos");
        				limparCampos();
                		edtNumeroProcesso.requestFocus();
                	}else
                	/*testa se os campos (edtNumeroProcesso e edtNumeroUnico) estão vazios*/
                	if ((edtNumeroProcesso.getText().toString().length()<=0) && (edtNumeroUnico.getText().toString().length()<=0)) {
                		//edtNumeroProcesso.setError("Preencha o campo Número de Processo.");
                		//edtNumeroUnico.setError("Ou, preencha o campo Número Único.");
                		showDialong("Nenhum dado para pesquisa foi informado!");
                		edtNumeroProcesso.requestFocus();
        			
                	}else{
	                	try {
	                		String numeroProcesso = edtNumeroProcesso.getText().toString();               	
	                    	ProcessoJudicialREST ProcessoREST = new ProcessoJudicialREST();
	                    	String url = lerPreferencias();	
	                    	
	                    	/*Cria uma mensagem de aguarde na tela*/
	                    	dialogi = ProgressDialog.show(MainActivity.this, "Aguarde..", "Buscando Processo Judicial...", true, true);
	                		ProcessoJudicial processoJudicial = ProcessoREST.getProcesso(url, numeroProcesso);
	    					
	                		/*Envia Parâmetro de resultado da consulta para Activity ListaProcesso*/
	    					String resultadoConsulta = (processoJudicial.toString());  					
	    					Intent it = new Intent(getBaseContext(), ListaProcesso.class);
	    					it.putExtra("resultadoConsulta", resultadoConsulta);
	                    	
	                    	/*Envia Parâmetro do número inserido nos EditText de pesquisa de Processo*/
	    					it.putExtra("numeroProcesso",edtNumeroProcesso.getText().toString());
	    					it.putExtra("numeroUnicoProcesso", edtNumeroUnico.getText().toString());
	    					
	    					/*starta a Activity*/
	    					startActivity(it);
	    					
	    				} catch (NumberFormatException e) {
	    					e.printStackTrace();
	    					fimProgressDialog();
	    					showDialong("Digite um Número de Processo válido!");
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    					gerarToast(e.getMessage());
	    				}finally{
	    					fimProgressDialog();
	    				}
        			
        			}
                	
                }
            });
        	
            btLimpar.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                	limparCampos();
                	edtNumeroProcesso.requestFocus();
                }
            });
        
        }
        /**
         * Mostra uma caixa de dialogo para ao usuário
         * @param mensagem
         */
        private void showDialong(CharSequence mensagem) {
            Builder builder = new AlertDialog.Builder(MainActivity.this); 
            builder.setTitle("JConsult");     
            builder.setMessage(mensagem);                  
            builder.setNeutralButton("OK", null);
            builder.show(); 
        }
        

        /**
         * Método que vai limpar os campos da tela
         */
        public void limparCampos() {
        	edtNumeroProcesso.setText("");
            edtNumeroUnico.setText("");
        }
        
        /**
         * Gera um Tost: uma mensagem breve na tela com uma informação ao usuário.
         * @param message
         * @return messagemToast
         */
		private void gerarToast(CharSequence mensagem) {
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(getApplicationContext(), mensagem, duration);
			toast.show();
		}
		
		/**
		 * Encerra a mensagem ProgressDialog
		 */
		private void fimProgressDialog(){    
			handler.post(new Runnable() {
			 @Override
			 public void run() {
				dialogi.dismiss();    
			 } 
	        });
	    }
		/**
		 * Realiza a leitura das preferencias de conexão do aplicativo
		 * @return URL
		 */
		public String lerPreferencias(){
			
			String URL = null;
			//SharedPreferences prefernciasConexao = getSharedPreferences(, mode)
			SharedPreferences preferenciasConexao = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			try {
			String urlProtocolo = preferenciasConexao.getString("protoloco", "");
			String urlIp = preferenciasConexao.getString("ip", "");
			String urlPorta = preferenciasConexao.getString("porta", "");
			String urlNomeServico = preferenciasConexao.getString("nomeServico", "");
			
			
			URL = (urlProtocolo + urlIp + ":"+ urlPorta +"/"+ urlNomeServico+"/processo/");
			Log.i("URL-MONTADA", URL);
				
			} catch (Exception e) {
				Log.e("URL-MONTADA", URL);
			}
			return URL;
			
		}

		
		
		
		
}
        
        