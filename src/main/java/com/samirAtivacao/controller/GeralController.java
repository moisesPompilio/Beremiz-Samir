/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samirAtivacao.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.samirAtivacao.DAO.DAOInformacoesCessado;
import com.samirAtivacao.DAO.DAOInformacoesDosPrev;
import com.samirAtivacao.modelo.Ativo;
import com.samirAtivacao.modelo.InfomacoesDosPrev;
import com.samirAtivacao.modelo.InformacoesCessado;
import com.samirAtivacao.modelo.Usuario;
import com.samirAtivacao.repository.SeleniumRepositorio;


/**
 *
 * @author AGU
 */
public class GeralController {
    private SeleniumRepositorio repository = new SeleniumRepositorio();



    public String beremiz( Usuario usuario) {

			/*try {
	            //URL do som que no caso esta no pendrive, mais ainda é uma fase de teste.
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("espartanos.wav").getAbsoluteFile());
	            //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("autodestruição.wav").getAbsoluteFile());
	            Clip clip = AudioSystem.getClip();
	            clip.open(audioInputStream);
	            clip.start();
	        } catch (Exception ex) {
	            System.out.println("Erro ao executar SOM!");
	            ex.printStackTrace();
	        }*/
			repository.open(usuario);
			try {
				repository.colocarFiltro(usuario.getEtiqueta());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Ativo ativo = new Ativo();
			int x = 0;
			int y = 0;
			boolean validacao;
		    ativo.setAtivo(false);
			try {
				while(repository.entrarNoProcessoAutomatico(usuario.getEtiqueta())) {
					validacao = repository.dataDeValidacaoDosPrev();
					if ( validacao == true) {
						ativo = repository.verificacaoDeAtivo();
						repository.etiquetar(ativo.getAtivo(), ativo.getBeneficio(), 1);
						x++;
					}
					else {
						repository.etiquetar(ativo.getAtivo(), ativo.getBeneficio(), 0);
						y++;
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String finalizacao ="Processos Validos: " + x + "          invalidos: " + y;
			//sadbja
			/*try {
	            //URL do som que no caso esta no pendrive, mais ainda é uma fase de teste.
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("gemido.wav").getAbsoluteFile());
				// AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("autodestruição.wav").getAbsoluteFile());
	            Clip clip = AudioSystem.getClip();
	            clip.open(audioInputStream);
	            clip.start();
	        } catch (Exception ex) {
	            System.out.println("Erro ao executar SOM!");
	            ex.printStackTrace();
	        }*/
			repository.quit();
			
			 return finalizacao;
			
		}
    public String samir(Usuario  usuario) {
    	System.out.println("etiqueta do funcionar: " + usuario.getEtiqueta());
    	InfomacoesDosPrev info = new InfomacoesDosPrev();
    	repository.open(usuario);
    	DAOInformacoesDosPrev daoInfo = new DAOInformacoesDosPrev();
		DAOInformacoesCessado daoInfoCessado = new DAOInformacoesCessado();
    	try {
    		repository.colocarFiltro(usuario.getEtiqueta());
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		int x = 0;
		String letra = "";
		daoInfo.excluirUInfomacoesDosPrev();
		daoInfoCessado.excluirUInfomacoesDosPrev();

		try {
			while(repository.entrarNoProcessoAutomatico(usuario.getEtiqueta())) {
				Ativo ativo = new Ativo();
				ativo.setAtivo(false);
				try {
					repository.clicarNaPrincipal();
				}catch  (Exception e) {
					System.out.println("entrei no cath clicarNaPrincipal");
				}
				boolean validacao = repository.dataDeValidacaoDosPrev();
				if (validacao) {
					ativo = repository.verificacaoDeAtivo();
					if(ativo.getAtivo() == true) {
							info = repository.procurarDosPrev();
							daoInfo.salvarInformacoesDosPrev(info);
							repository.etiquetar(true, letra, 0);
							x++;
					}
					else {
						repository.etiquetar(ativo.getAtivo(), ativo.getBeneficio(), 1);
					}
				}
				else {
					repository.etiquetar(false, ativo.getBeneficio(), 0);

				}
			}

		} catch (Exception e) {
			System.out.println("entrei no cath controller");
			System.out.println(e);
		}

		 return "deu certo";
		
		
	}
    public String InserirNoFront(Usuario usuario, Boolean driever) throws AWTException {
    	//repository.open(usuario);
    	if(driever == true){
    		repository.openFront(usuario);
    	}
    	else {
    		repository.openSamirFront(usuario);
    	}
    	repository.diminuirTela();
    	BancoController banco = new BancoController();
		List<InfomacoesDosPrev> lista = banco.litaDosPrev();
		List<InformacoesCessado> listaCessado = banco.listaCessado();
		System.out.println("Cpf: ");
		int x = 0;
		while(x <lista.size()) {

			repository.inserirDosPrev(lista.get(x),listaCessado);
			x++;
		}
		repository.aumentarTela();
		repository.finalizar();
		return lista.get(0).getCpf();
    }

    public void pararTriagem(){
    	repository.parar();
	}
}
