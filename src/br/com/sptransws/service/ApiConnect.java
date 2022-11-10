package br.com.sptransws.service;

import org.apache.logging.log4j.LogManager;
import br.com.sptransws.util.ConnectUtils;
import org.apache.logging.log4j.Logger;
import br.com.sptransws.domain.Bundle;

/**
 * Classe responsável pela chamada dos métodos de consumo da API!
 * 
 * @author Felipe Nascimento
 *
 */

public class ApiConnect {
	private static final Logger LOG = LogManager.getLogger(ApiConnect.class.getName());
	private String apiCredentials;
	
	public void autenticar() {
		LOG.info("Realizando autenticação...");
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_AUTH", Bundle.getChaveProp("URL_API"), Bundle.getChaveProp("TOKEN_APP")));
		con.addHeader(Bundle.getChaveProp("KEY_HEADER_CONTENT_LENGTH"), Bundle.getChaveProp("VALUE_HEADER_CONTENT_LENGTH"));
		con.addMethod(Bundle.getChaveProp("METHOD_POST"));
		con.execute();
		LOG.info("Response Autenticação >> {}", con.getResultAsString());
		con.getConnection().getHeaderFields().get("Set-Cookie").forEach(cookie -> LOG.info("Cookie >> {}", cookie));
		con.getConnection().getHeaderFields().get("Set-Cookie").forEach(cookie -> apiCredentials = cookie);
		LOG.info("Autenticação realizada com sucesso!");
	}
	
	public String buscarLinha(String termo) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_LINHA", Bundle.getChaveProp("URL_API"), termo));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String buscarLinhaSentido(String termo, String sentido) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_LINHA_SENTIDO", Bundle.getChaveProp("URL_API"), termo, sentido));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String buscarParada(String termo) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PARADA", Bundle.getChaveProp("URL_API"), termo));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String buscarParadaPorLinha(String codigoLinha) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PARADA_POR_LINHA", Bundle.getChaveProp("URL_API"), codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String corredor() {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_CORREDOR", Bundle.getChaveProp("URL_API")));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String buscarParadasPorCorredor(String codigoCorredor) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PARADAS_POR_CORREDOR", Bundle.getChaveProp("URL_API"), codigoCorredor));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String empresa() {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_EMPRESA", Bundle.getChaveProp("URL_API")));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String posicao() {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_POSICAO", Bundle.getChaveProp("URL_API")));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String posicaoPorLinha(String codigoLinha) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_POSICAO_POR_LINHA", Bundle.getChaveProp("URL_API"), codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String posicaoGaragem(String codigoEmpresa, String codigoLinha) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_POSICAO_GARAGEM", Bundle.getChaveProp("URL_API"), codigoEmpresa, codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String previsaoChegada(String codigoParada, String codigoLinha) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PREVISAO", Bundle.getChaveProp("URL_API"), codigoParada, codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String previsaoChegadaPorLinha(String codigoLinha) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PREVISAO_POR_LINHA", Bundle.getChaveProp("URL_API"), codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
	public String previsaoChegadaPorParada(String codigoParada) {
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PREVISAO_POR_PARADA", Bundle.getChaveProp("URL_API"), codigoParada));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		return con.getResultAsString();
	}
	
}