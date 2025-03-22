package br.com.sptransws.service;

import org.apache.logging.log4j.LogManager;
import br.com.sptransws.util.ConnectUtils;
import org.apache.logging.log4j.Logger;
import br.com.sptransws.domain.Bundle;
import java.io.Serializable;

/**
 * Classe responsável pela chamada dos métodos de consumo da API.
 * 
 * @author Felipe Nascimento
 *
 */

public class ApiConnect implements Serializable {
	private static final Logger LOG = LogManager.getLogger(ApiConnect.class.getName());
	private static final long serialVersionUID = 6615821843858678506L;
	private String apiCredentials;
	
	/**
	 * Método responsável pela obtenção da autorização de acesso à API.
	 */
	public void autenticar() {
		
		LOG.info("Realizando autenticação...");
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_AUTH", Bundle.getChaveProp("URL_API"), Bundle.getChaveProp("TOKEN_APP")));
		con.addHeader(Bundle.getChaveProp("KEY_HEADER_CONTENT_LENGTH"), Bundle.getChaveProp("VALUE_HEADER_CONTENT_LENGTH"));
		con.addMethod(Bundle.getChaveProp("METHOD_POST"));
		con.execute();
		
		LOG.info("Response Autenticação >> {}", con.getResultAsString());
		
		con.getConnection().getHeaderFields().get("Set-Cookie").forEach(cookie -> LOG.info("Cookie >> {}", cookie));
		con.getConnection().getHeaderFields().get("Set-Cookie").forEach(cookie -> apiCredentials = cookie);
		
		LOG.info("Autenticação realizada com sucesso.");
		
	}
	
	/**
	 * Método responsável por obter as informações sobre as linhas de ônibus de SP.
	 * 
	 * @param termoBusca - denominação ou número da linha (Ex.: 8000, Lapa ou Ramos)
	 * 
	 * @return informações sobre a linha em JSON
	 * 
	 */
	public String buscarLinha(String termoBusca) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_LINHA", Bundle.getChaveProp("URL_API"), termoBusca));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter as informações sobre as linhas de ônibus de SP com sentido.
	 * 
	 * @param termoBusca - denominação ou número da linha (Ex.: 8000, Lapa ou Ramos)
	 * @param sentido - código identificador do sentido de operação da linha (1 - início/destino // 2 - destino/início)
	 * 
	 * @return informações sobre a linha em JSON
	 * 
	 */
	public String buscarLinhaSentido(String termoBusca, String sentido) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_LINHA_SENTIDO", Bundle.getChaveProp("URL_API"), termoBusca, sentido));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter as informações sobre as paradas de ônibus de SP.
	 * 
	 * @param termosBusca - denominação da parada ou endereço de localização (Ex.: Afonso, ou Balthazar da Veiga)
	 * 
	 * @return informações sobre a parada em JSON
	 * 
	 */
	public String buscarParada(String termosBusca) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PARADA", Bundle.getChaveProp("URL_API"), termosBusca));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter as informações sobre as paradas de ônibus de SP por linha.
	 * 
	 * @param codigoLinha - código identificador único de cada linha
	 * 
	 * @return informações sobre a parada em JSON
	 * 
	 */
	public String buscarParadaPorLinha(String codigoLinha) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PARADA_POR_LINHA", Bundle.getChaveProp("URL_API"), codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter a lista de corredores dos ônibus de SP.
	 * 
	 * @return informações sobre os corredores em JSON
	 * 
	 */
	public String corredor() {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_CORREDOR", Bundle.getChaveProp("URL_API")));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter as informações sobre as paradas de ônibus de SP por corredor.
	 * 
	 * @param codigoCorredor - código identificador único de cada corredor
	 * 
	 * @return informações sobre a parada em JSON
	 * 
	 */
	public String buscarParadasPorCorredor(String codigoCorredor) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PARADAS_POR_CORREDOR", Bundle.getChaveProp("URL_API"), codigoCorredor));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter a lista de empresas operadoras dos ônibus de SP.
	 * 
	 * @return informações sobre as empresas operadoras em JSON
	 * 
	 */
	public String empresa() {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_EMPRESA", Bundle.getChaveProp("URL_API")));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter a lista de localização dos ônibus de SP.
	 * 
	 * @return informações sobre as localizações em JSON
	 * 
	 */
	public String posicao() {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_POSICAO", Bundle.getChaveProp("URL_API")));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter a lista de localização dos ônibus de SP por linha.
	 * 
	 * @param codigoLinha - código identificador único de cada linha
	 * 
	 * @return informações sobre as localizações em JSON
	 * 
	 */
	public String posicaoPorLinha(String codigoLinha) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_POSICAO_POR_LINHA", Bundle.getChaveProp("URL_API"), codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter a lista de localização dos ônibus de SP que estejam numa garagem.
	 * 
	 * @param codigoEmpresa - código identificador único de cada empresa operadora
	 * @param codigoLinha - código identificador único de cada linha
	 * 
	 * @return informações sobre as localizações em JSON
	 * 
	 */
	public String posicaoGaragem(String codigoEmpresa, String codigoLinha) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_POSICAO_GARAGEM", Bundle.getChaveProp("URL_API"), codigoEmpresa, codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter a previsão de chegada dos ônibus de SP de acordo com a linha que atende ao ponto informado.
	 * 
	 * @param codigoParada - código identificador único de cada ponto de parada
	 * @param codigoLinha - código identificador único de cada linha
	 * 
	 * @return informações sobre a previsão de chegada em JSON
	 * 
	 */
	public String previsaoChegada(String codigoParada, String codigoLinha) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PREVISAO", Bundle.getChaveProp("URL_API"), codigoParada, codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter a previsão de chegada dos ônibus de SP por linha.
	 * 
	 * @param codigoLinha - código identificador único de cada linha
	 * 
	 * @return informações sobre a previsão de chegada em JSON
	 * 
	 */
	public String previsaoChegadaPorLinha(String codigoLinha) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PREVISAO_POR_LINHA", Bundle.getChaveProp("URL_API"), codigoLinha));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
	/**
	 * Método responsável por obter a previsão de chegada dos ônibus de SP por parada.
	 * 
	 * @param codigoParada - código identificador único de cada ponto de parada
	 * 
	 * @return informações sobre a previsão de chegada em JSON
	 * 
	 */
	public String previsaoChegadaPorParada(String codigoParada) {
		
		ConnectUtils con = new ConnectUtils(Bundle.getChavePropComParametro("ENDPOINT_PREVISAO_POR_PARADA", Bundle.getChaveProp("URL_API"), codigoParada));
		con.addMethod(Bundle.getChaveProp("METHOD_GET"));
		con.addCookie(apiCredentials);
		con.execute();
		
		return con.getResultAsString();
		
	}
	
}