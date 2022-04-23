package br.com.jconsult.activity;

import br.com.jconsult.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
/**
 * Classe responsável por incial a tela de splash da aplicação. (aguardando x segundo para carrega a tela pricipal).
 * @author luan
 */
public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(3000);//3s			
				} catch (InterruptedException e) {
					e.printStackTrace();
				
				}finally{
					Intent it = new Intent("br.com.jconsult.activity.MainActivity");
					startActivity(it);
					finish();
				}
			}
		};
		timer.start();	
		
	}
	
	

}
