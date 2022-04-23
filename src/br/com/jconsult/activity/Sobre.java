package br.com.jconsult.activity;

import br.com.jconsult.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
/**
 * Classe responsável por iniciar a tela de informações sobre o sistema (Tela Sobre)
 * @author luan
 *
 */
public class Sobre extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sobre);
		
	}
	
	

}
