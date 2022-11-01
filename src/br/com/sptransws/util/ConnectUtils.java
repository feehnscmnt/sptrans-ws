package br.com.sptransws.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import br.com.sptransws.domain.Bundle;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.Serializable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Classe responsável pela comunicação com a API!
 * 
 * @author Felipe Nascimento
 *
 */

public class ConnectUtils implements Serializable {
	private static final Logger LOG = LogManager.getLogger(ConnectUtils.class.getName());
	private static final long serialVersionUID = -361540884794143539L;
	private transient HttpURLConnection con;
	private transient InputStream is;
	
	public ConnectUtils(String urlApi) {
		try {
			System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
			URL url = new URL(urlApi);
			con = (HttpURLConnection) url.openConnection();
		} catch(IOException e) {
			LOG.error("Houve erro no construtor da classe ConnectUtils. Exception: {}", e.getMessage());
		}
	}
	
	public void addMethod(String method) {
		try {
			con.setRequestMethod(method);
		} catch(ProtocolException e) {
			LOG.error("Houve erro no método addMethod. Exception: {}", e.getMessage());
		}
	}
	
	public void addHeader(String key, String value) {
		con.setRequestProperty(key, value);
	}
	
	public void addCookie(String apiCredentials) {
		con.setRequestProperty(Bundle.getChaveProp("KEY_HEADER_COOKIE"), apiCredentials);
	}
	
	public HttpURLConnection getConnection() {
        return con;
    }
	
	public void execute() {
		try {
			con.connect();
			
			if (con.getResponseCode() != HttpURLConnection.HTTP_CREATED && con.getResponseCode() != HttpURLConnection.HTTP_OK) {
				LOG.info("Método da requisição: {}", con.getRequestMethod());				
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));				
				String linha;
				
				while ((linha = br.readLine()) != null) {
		        	LOG.info("Corpo da requisição: {}", linha);
		        }
			}
			
			is = con.getInputStream();
		} catch(IOException e) {
			LOG.error("Houve erro no método execute. Exception: {}", e.getMessage());
		}
	}
	
	public String getResultAsString() {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		try {
			String output = br.readLine();
			while (output != null) {
				sb.append(output);
				output = br.readLine();
			}
		} catch(IOException e) {
			LOG.error("Houve erro no método getResultAsString. Exception: {}", e.getMessage());
		}
		
		return sb.toString();
	}
	
	public void disconnect() {
		try {
			is.close();
			con.disconnect();
		} catch(IOException e) {
			LOG.error("Houve erro no método disconnect. Exception: {}", e.getMessage());
		}
	}
	
}