package br.com.sptransws.domain;

import org.apache.logging.log4j.LogManager;
import java.util.MissingResourceException;
import org.apache.logging.log4j.Logger;
import java.util.ResourceBundle;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * Classe responsável pela leitura do properties contendo as chaves (keys) com propriedades para consumo da API!
 * 
 * @author Felipe Nascimento
 */

public class Bundle implements Serializable {
	private static final Logger LOG = LogManager.getLogger(Bundle.class.getName());
	private static final long serialVersionUID = -7114329575128588313L;
	private static ResourceBundle resourceBundle;
	
	static {
		resourceBundle = ResourceBundle.getBundle("br/com/sptransws/bundle/props", new Locale("pt", "BR"));
	}
	
	public static String getChaveProp(String chave) {
		try {
			return resourceBundle.getString(chave);
		} catch(MissingResourceException e) {
			LOG.error("Houve um erro inesperado. Exception: {}", e.getMessage());
			return "Chave: ".concat(chave);
		} catch(NullPointerException e) {
			LOG.error("Houve um erro inesperado. Exception: {}", e.getMessage());
			return "";
		}
	}
	
	public static String getChavePropComParametro(String chave, Object ... params) {
		String str = resourceBundle.getString(chave);
		MessageFormat messageFormat = new MessageFormat(str);
		return messageFormat.format(params);
	}
	
}