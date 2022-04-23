package br.com.jconsult.webservice;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.com.jconsult.activity.MainActivity;
import br.com.jconsult.model.ProcessoJudicial;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Classe responsável pelo mapeamento entre o Web Service cliente e a Activity.
 * 
 * @author luan
 * 
 */
public class ProcessoJudicialREST {

	/* IP do Emulador usado como localhost:10.0.2.2 */
	public ProcessoJudicial getProcesso(String URLWS, String numeroProcesso) throws Exception {
	
	String[] resposta = new WebServiceProcessoJudicial().get(URLWS + numeroProcesso);
		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ProcessoJudicial processo = gson.fromJson(resposta[1],
					ProcessoJudicial.class);
			//Log.i("URL-MONTADA", URLMONTADA);
			return processo;
		} else {
			throw new Exception(resposta[1]);
		}
	}

	/*public List<ProcessoJudicial> getListaCliente() throws Exception {

		//String[] resposta = new WebServiceProcessoJudicial().get(URL_WS + "buscarTodosGSON");
		String[] resposta = new WebServiceProcessoJudicial().get(URL_WS + "buscarTodosGSON");
		
		// String[] resposta = new WebServiceCliente().get(URL_WS +
		// "buscarTodos");

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<ProcessoJudicial> listaProcesso = new ArrayList<ProcessoJudicial>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {
				listaProcesso.add(gson.fromJson(array.get(i),
						ProcessoJudicial.class));
			}
			return listaProcesso;
		} else {
			throw new Exception(resposta[1]);
		}
	}*/
	//private static final String URL_WS = "http://192.168.0.103:8082/WebServiceJConsult/processo/";
	private static final String URL_WS = "http://10.0.2.2:8082/WebServiceJConsult/processo/testeConexao";
	
	public String getConexao() throws Exception {
		
		//String[] resposta = new WebServiceProcessoJudicial().get(URL_WS	+ numeroProcesso);
		String retornoTeste = null;
		String[] resposta = new WebServiceProcessoJudicial().get(URL_WS);
		
		
		if (resposta[0].equals("200")) {
			
			if (resposta == null){
				Log.e("NGVL", "Falha ao acessar WS");
				//return retornoTeste;	
			}
			return retornoTeste;
			
		}else {
			Log.e("NGVL", "Falha ao acessar WS");
			throw new Exception(resposta[1]);
		}
	}

}
