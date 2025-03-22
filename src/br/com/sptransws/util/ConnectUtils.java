package br.com.sptransws.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import br.com.sptransws.domain.Bundle;
import java.net.URISyntaxException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.Serializable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Classe responsável pela comunicação com a API.
 * 
 * @author Felipe Nascimento
 *
 */

public class ConnectUtils implements Serializable {
	private static final Logger LOG = LogManager.getLogger(ConnectUtils.class.getName());
	private static final long serialVersionUID = -361540884794143539L;
	private transient HttpURLConnection con;
	private transient InputStream is;
	
	/**
	 * Construtor da classe parametrizado.
	 * 
	 * @param urlApi - URL da API
	 * 
	 */
	public ConnectUtils(String urlApi) {
		
		try {
			
			System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
			con = (HttpURLConnection) new URI(urlApi).toURL().openConnection();
			
		} catch (URISyntaxException | IOException e) {
			
			LOG.error("Houve erro no construtor da classe ConnectUtils. Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, String.format("Houve erro no construtor da classe ConnectUtils. Exception: %s", e.getMessage()), "ERRO", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	/**
	 * Método responsável pela inclusão do método na requisição.
	 * 
	 * @param method - método que será passado na requisição
	 * 
	 */
	public void addMethod(String method) {
		
		try {
			
			con.setRequestMethod(method);
			
		} catch (ProtocolException e) {
			
			LOG.error("Houve erro no método addMethod. Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, String.format("Houve erro no método addMethod. Exception: %s", e.getMessage()), "ERRO", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	/**
	 * Método responsável pela inclusão do cabeçalho na requisição.
	 * 
	 * @param key - chave do cabeçalho
	 * @param value - valor da chave do cabeçalho
	 * 
	 */
	public void addHeader(String key, String value) {
		
		con.setRequestProperty(key, value);
		
	}
	
	/**
	 * Método responsável pela inclusão da autorização de acesso à API no cookie.
	 * 
	 * @param apiCredentials - autorização de acesso à API
	 * 
	 */
	public void addCookie(String apiCredentials) {
		
		con.setRequestProperty(Bundle.getChaveProp("KEY_HEADER_COOKIE"), apiCredentials);
		
	}
	
	/**
	 * Método responsável por obter o objeto da conexão com a API.
	 * 
	 * @return objeto da conexão
	 * 
	 */
	public HttpURLConnection getConnection() {
		
        return con;
        
    }
	
	/**
	 * Método responsável pela execução das requisições.
	 */
	public void execute() {
		
		try {
			
			con.connect();
			
			if (con.getResponseCode() != HttpURLConnection.HTTP_CREATED && con.getResponseCode() != HttpURLConnection.HTTP_OK) {
				
				LOG.info("Método da requisição: {}", con.getRequestMethod());				
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));				
				String linha;
				
				while ((linha = br.readLine()) != null) {
					
		        	LOG.info("Corpo de erro da requisição: {}", linha);
		        	
		        }
				
			}
			
			is = con.getInputStream();
			
		} catch (IOException e) {
			
			LOG.error("Houve erro no método execute. Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, String.format("Houve erro no método execute. Exception: %s", e.getMessage()), "ERRO", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	/**
	 * Método responsável por obter o resultado da execução das requisições.
	 * 
	 * @return resultado das requisições
	 * 
	 */
	public String getResultAsString() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		try {
			
			String output = br.readLine();
			
			while (output != null) {
				
				sb.append(output);
				output = br.readLine();
				
			}
			
		} catch (IOException e) {
			
			LOG.error("Houve erro no método getResultAsString. Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, String.format("Houve erro no método getResultAsString. Exception: %s", e.getMessage()), "ERRO", JOptionPane.ERROR_MESSAGE);
			
		}
		
		return String.valueOf(sb);
		
	}
	
	/**
	 * Método responsável pelo encerramento da execução das requisições.
	 */
	public void disconnect() {
		
		try {
			
			is.close();
			con.disconnect();
			
		} catch (IOException e) {
			
			LOG.error("Houve erro no método disconnect. Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, String.format("Houve erro no método disconnect. Exception: %s", e.getMessage()), "ERRO", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
}