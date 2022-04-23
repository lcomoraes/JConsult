package br.com.jconsult.activity;

import br.com.jconsult.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.widget.TextView;

/**
 * Classe responsável por iniciar a activity (Tela) que listará os processos consultados.
 * @author luan
 *
 */
public class ListaProcesso extends Activity {

	private TextView mostraNumeroPesquisado;
    private TextView mostraPesquidaProcesso;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.listar_processo);
        
        inicializa();
        getResultadoConsulta();
      
    }
    /**
     * Instância os componentes da Activity que serão manipulados pela Classe 
     */
    private void inicializa() {
    	
        // Instancia os textView
        mostraNumeroPesquisado = (TextView) findViewById(R.id.textMostraNumeroPesquisado);
        mostraPesquidaProcesso = (TextView) findViewById(R.id.textMostraPesquisa);       
        
        // Instancia os Button
        //btPesquisar = (ImageButton) findViewById(R.id.btn_pesquisar);

    }
    /**
     * Obtém os parâmetros que foram enviados pela MainActivity e seta os valores no campos TextViews.
     */
    private void getResultadoConsulta() {
    	
    	//Obtém o resultado da pesquisa do processo consultado e seta no TextView: textMostraProcessoPesquisado
    	Intent it = getIntent();
         if (it != null){
         	String resultadoConsulta = it.getStringExtra("resultadoConsulta");
         	if(resultadoConsulta != null){
         		mostraPesquidaProcesso.setText(resultadoConsulta);
         	}         	
         	
         }
         //Obtém o Número de processo pesquisado inserido no EditText (edtNumeroProcesso/edtNumeroUnico) para setar no TextView: textMostraNumeroPesquisado 
         String numeroPesquisadoProcesso = it.getStringExtra("numeroProcesso");
         if((numeroPesquisadoProcesso != null) || !(numeroPesquisadoProcesso.trim().equals("")) ) {
        	 mostraNumeroPesquisado.setText(numeroPesquisadoProcesso);
      	 }
         String numeroUnicoPesquisadoProcesso = it.getStringExtra("numeroUnicoProcesso");
         if ((numeroUnicoPesquisadoProcesso != null) || !(numeroUnicoPesquisadoProcesso.trim().equals("")) ){
        	mostraNumeroPesquisado.setText(numeroPesquisadoProcesso);
      	 }
      
         
    }
    
       
        
           
        
        
        
    
    
    
}
        
        