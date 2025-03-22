package br.com.sptransws.domain;

import org.apache.logging.log4j.LogManager;
import java.util.MissingResourceException;
import org.apache.logging.log4j.Logger;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import java.text.MessageFormat;
import java.io.Serializable;
import java.util.Locale;

/**
 * Classe responsável pela leitura do properties contendo as chaves (keys) com propriedades para consumo da API.
 * 
 * @author Felipe Nascimento
 * 
 */

public class Bundle implements Serializable {
	private static final Logger LOG = LogManager.getLogger(Bundle.class.getName());
	private static final long serialVersionUID = -7114329575128588313L;
	private static ResourceBundle resourceBundle;
	
	static {
		
		resourceBundle = ResourceBundle.getBundle("br/com/sptransws/bundle/props", Locale.of("pt", "BR"));
		
	}
	
	/**
	 * Método responsável pela obtenção da chave no .properties através da variável resourceBundle.
	 * 
	 * @param chave - chave a ser obtida para consumo da API
	 * 
	 * @return valor da chave informada
	 * 
	 */
	public static String getChaveProp(String chave) {
		
		try {
			
			return resourceBundle.getString(chave);
			
		} catch (MissingResourceException e) {
			
			LOG.error("Houve um erro inesperado. Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, String.format("Houve um erro inesperado. Exception: %s", e.getMessage()), "ERRO", JOptionPane.ERROR_MESSAGE);
			return String.format("Chave: %s", chave);
			
		} catch (NullPointerException e) {
			
			LOG.error("Houve um erro inesperado. Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, String.format("Houve um erro inesperado. Exception: %s", e.getMessage()), "ERRO", JOptionPane.ERROR_MESSAGE);
			return "";
			
		}
		
	}
	
	/**
	 * Método responsável pela obtenção da chave no .properties através da variável resourceBundle.
	 * 
	 * @param chave - chave a ser obtida para consumo da API
	 * @param params - parâmetro que será incluído no valor da chave
	 * 
	 * @return valor da chave informada formatado com o parâmetro informado
	 * 
	 */
	public static String getChavePropComParametro(String chave, Object ... params) {
		
		return new MessageFormat(resourceBundle.getString(chave)).format(params);
		
	}
	
}