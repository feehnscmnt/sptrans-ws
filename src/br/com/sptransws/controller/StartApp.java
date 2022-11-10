package br.com.sptransws.controller;

import br.com.sptransws.service.ApiConnect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.JOptionPane;

/**
 * Classe responsável pela inicialização do WebService!
 * 
 * @author Felipe Nascimento
 * 
 */

public class StartApp {
	private static final Logger LOG = LogManager.getLogger(StartApp.class.getName());

	public static void main(String[] args) {
		String tipoConsulta = (String) JOptionPane.showInputDialog(
				null, "Escolha o tipo de consulta a ser realizada", "Tipo de Consulta",
				JOptionPane.INFORMATION_MESSAGE, null, tipos(), tipos()[0]);
		
		ApiConnect apiCon = new ApiConnect();		
		apiCon.autenticar();
		
		switch (tipoConsulta) {
			case "Selecione..":
				JOptionPane.showMessageDialog(null, "Opção inválida. Execute o programa novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
				break;
			case "Linha":
				String responseBuscarLinha = apiCon.buscarLinha("8000");
				LOG.info("Response Buscar Linha >> {}", responseBuscarLinha);
				break;
			case "Linha Sentido":
				String responseBuscarLinhaSentido = apiCon.buscarLinhaSentido("8000", "1");
				LOG.info("Response Buscar Linha Sentido >> {}", responseBuscarLinhaSentido);
				break;
			case "Parada":
				String responseBuscarParada = apiCon.buscarParada("8000");
				LOG.info("Response Buscar Parada >> {}", responseBuscarParada);
				break;
			case "Parada por Linha":
				String responseBuscarParadaPorLinha = apiCon.buscarParadaPorLinha("2506");
				LOG.info("Response Buscar Parada Por Linha >> {}", responseBuscarParadaPorLinha);
				break;
			case "Corredor":
				String responseCorredor = apiCon.corredor();
				LOG.info("Response Corredor >> {}", responseCorredor);
				break;
			case "Paradas por Corredor":
				String responseBuscarParadasPorCorredor = apiCon.buscarParadasPorCorredor("8");
				LOG.info("Response Buscar Paradas Por Corredor >> {}", responseBuscarParadasPorCorredor);
				break;
			case "Empresa":
				String responseEmpresa = apiCon.empresa();
				LOG.info("Response Empresa >> {}", responseEmpresa);
				break;
			case "Posi��o":
				String responsePosicao = apiCon.posicao();
				LOG.info("Response Posição >> {}", responsePosicao);
				break;
			case "Posi��o por Linha":
				String responsePosicaoPorLinha = apiCon.posicaoPorLinha("2506");
				LOG.info("Response Posição Por Linha >> {}", responsePosicaoPorLinha);
				break;
			case "Posi��o Garagem":
				String responsePosicaoGaragem = apiCon.posicaoGaragem("37", "35274");
				LOG.info("Response Posição Garagem >> {}", responsePosicaoGaragem);
				break;
			case "Previs�o de Chegada":
				String responsePrevisaoChegada = apiCon.previsaoChegada("7014417", "2506");
				LOG.info("Response Previsão Chegada >> {}", responsePrevisaoChegada);
				break;
			case "Previs�o de Chegada por Linha":
				String responsePrevisaoChegadaPorLinha = apiCon.previsaoChegadaPorLinha("2506");
				LOG.info("Response Previsão Chegada Por Linha >> {}", responsePrevisaoChegadaPorLinha);
				break;
			case "Previsão de Chegada por Parada":
				String responsePrevisaoChegadaPorParada = apiCon.previsaoChegadaPorParada("4203724");
				LOG.info("Response Previsão Chegada Por Parada >> {}", responsePrevisaoChegadaPorParada);
				break;
			default: break;
		}
	}
	
	private static String[] tipos() {
		return new String[] { "Selecione..", "Linha", "Linha Sentido", "Parada", "Parada por Linha", "Corredor", "Paradas por Corredor", "Empresa",
				"Posição", "Posição por Linha", "Posição Garagem", "Previsão de Chegada", "Previsão de Chegada por Linha", "Previsão de Chegada por Parada" };
	}

}