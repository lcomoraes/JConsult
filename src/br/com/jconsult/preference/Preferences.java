package br.com.jconsult.preference;

import br.com.jconsult.R;
import br.com.jconsult.activity.MainActivity;
import br.com.jconsult.model.ProcessoJudicial;
import br.com.jconsult.webservice.ProcessoJudicialREST;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.Preference.OnPreferenceClickListener;
import android.widget.Toast;

/**
 * Classe responsável por iniciar a tela de preferências do aplicativo.
 * @author luan
 */
public class Preferences extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferencias);
		
		Preference customPref = (Preference) findPreference("botao_teste");
        customPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {

	        public boolean onPreferenceClick(Preference preference) {
	        	
	            Toast.makeText(getBaseContext(), "Teste de Conexão!", Toast.LENGTH_LONG).show();
	            SharedPreferences customSharedPreference = getSharedPreferences("myCustomSharedPrefs", Activity.MODE_PRIVATE);
	            SharedPreferences.Editor editor = customSharedPreference.edit();
	            editor.putString("myCustomPref", "The preference has been clicked");
	            return editor.commit();
	        }

        });
	}





}