/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samirAtivacao.repository;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.samirAtivacao.DAO.DAOInformacoesCessado;
import com.samirAtivacao.modelo.InformacoesCessado;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.samirAtivacao.modelo.ProcessoValido;
import com.samirAtivacao.modelo.InfomacoesDosPrev;
import com.samirAtivacao.modelo.Usuario;

/**
 *
 * @author AGU
 */
public class SeleniumRepositorio {
	WebDriver driver;
	WebDriverWait wait;




	public String open(Usuario usuario) {
		String url = "https://sapiens.agu.gov.br/login";
		System.setProperty("webdriver.gecko.driver", "GeckoDriver.exe");
		driver = new FirefoxDriver();

		driver.get(url);

		String campoUserPath = "/html/body/div[1]/div[1]/div/div/div[2]/div/div/div/table[1]/tbody/tr/td[2]/input";
		WebElement campoUserElemt = driver.findElement(By.xpath(campoUserPath));
		// String user = "039.669.222-23";
		campoUserElemt.sendKeys(usuario.getCpf());

		String campoPassPath = "/html/body/div[1]/div[1]/div/div/div[2]/div/div/div/table[2]/tbody/tr/td[2]/input";
		WebElement campoPassElemt = driver.findElement(By.xpath(campoPassPath));
		// String pass = "AfonsoSoVacuo1";
		campoPassElemt.sendKeys(usuario.getSenha());

		String sendLoginPath = "button-1019-btnIconEl";
		WebElement sendLoginElem = driver.findElement(By.id(sendLoginPath));
		sendLoginElem.click();

		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// WebElement myDynamicElement = (new WebDriverWait(driver,
		// 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/a[3]/span/span/span[1]")));
		// wait.until(ExpectedConditions.elementToBeClickable(
		// By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		long time = 100;
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		boolean confirmacaoDeLogin1 = driver
				.findElement(
						By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/a[2]/span/span/span[1]"))

				.getText().toUpperCase().contains("Tramitações");
		boolean confirmacaoDeLogin2 = driver
				.findElement(
						By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/a[3]/span/span/span[1]"))
				.getText().toUpperCase().contains("Comunicações");

		if (confirmacaoDeLogin1 == true && confirmacaoDeLogin2 == true) {
			return driver
					.findElement(By.xpath(
							"/html/body/div[4]/div[1]/div[2]/div/div[1]/div[1]/div[2]/div/a[2]/span/span/span[1]"))
					.getText();
		} else {
			return "ACESSO NEGADO";
		}

	}

	public String clicarNaPrincipal(){
		this.driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS).pageLoadTimeout(100, TimeUnit.SECONDS);

		try {
			List<String> janela = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(janela.get(1));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[1]/td[2]/div/span")));


			driver.switchTo().frame(0);
			do {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.tagName("html")));
					wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
					driver.findElement(By.tagName("html")).click();
					break;
				} catch (Exception e) {
					//
				}
			} while (true);
			this.driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS).pageLoadTimeout(100, TimeUnit.SECONDS);
//		WebElement capa = driver.findElement(By.id("iframe-myiframe"));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[4]/table/tbody/tr[13]/td[2]/a[1]/b")));
			WebElement clique = driver.findElement(By.xpath("/html/body/div/div[4]/table/tbody/tr[13]/td[2]/a[1]/b"));
			clique.click();
		}catch (Exception e){
			System.out.println("entrei no cat clicarNaPrincipal");
			System.out.println(e);
		}

//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(capa));


		return "clicado";
	}

	public String colocarFiltro(String etiqueta) throws InterruptedException {
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		WebElement setaAparecer = driver.findElement(
				By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div[1]/div[3]/div/div/div[33]/div/span"));
		setaAparecer.click();
		WebElement seta = driver.findElement(
				By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div[1]/div[3]/div/div/div[33]/div/div"));
		seta.click();

		WebElement filtro = driver.findElement(By.xpath("/html/body/div[11]/div/div[2]/div/div[6]/a/div[1]"));
		filtro.click();
		WebElement filtroEs = driver.findElement(By.xpath("/html/body/div[13]/div/div[2]"));
		filtroEs.click();
		WebElement filtroSpace = driver
				.findElement(By.xpath("/html/body/div[13]/div/div[2]/div/table/tbody/tr/td[2]/input"));
		filtroSpace.click();
		System.out.println("etiqueta no filtro: " + etiqueta);
		filtroSpace.sendKeys(etiqueta);
		Thread.sleep(1000);
		long time = 100;
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
		Thread.sleep(1000);

		return "fitro colocado";

	}

	public boolean entrarNoProcessoAutomatico(String etiqueta) throws InterruptedException {
		try {
			this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
			Thread.sleep(1000);
			String verificacao = driver
					.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[2]/div/div/div[7]"))
					.getText();

			System.out.println(verificacao);
			String falso = "Sem registros para exibir";

			boolean confirmacaoDeExistencia = verificacao.equals(falso);
			System.out.println(confirmacaoDeExistencia);
			if (confirmacaoDeExistencia == true) {
				return false;
			} else {
				long time = 15000;
				wait = new WebDriverWait(driver, time);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]")));

				WebElement processo = driver.findElement(By.xpath(
						"/html/body/div[4]/div[1]/div[2]/div/div[2]/div[1]/div[4]/div/table/tbody/tr[1]/td[3]/div/a[1]"));
				processo.click();
				return true;

			}
		}catch (Exception e){
			System.out.println("entrei no cat entrarNoProcessoAutomatico");
			System.out.println(e);
			return entrarNoProcessoAutomatico(etiqueta);

		}
	}

	/*
	 * public String procurarProcesso(Usuario usuario) {
	 * this.driver.manage().timeouts().implicitlyWait(60,
	 * TimeUnit.SECONDS).pageLoadTimeout(60, TimeUnit.SECONDS);
	 * WebElement seta1 = driver
	 * .findElement(By.xpath(
	 * "/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[3]/div/div/div[3]/div"));
	 * seta1.click();
	 * WebElement seta = driver
	 * .findElement(By.xpath(
	 * "/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[3]/div/div/div[3]/div/div"
	 * ));
	 * seta.click();
	 * 
	 * WebElement filtro =
	 * driver.findElement(By.xpath("/html/body/div[11]/div/div[2]/div/div[6]/a"));
	 * filtro.click();
	 * WebElement filtroEs = driver
	 * .findElement(By.xpath(
	 * "/html/body/div[13]/div/div[2]/div/table/tbody/tr/td[2]/input"));
	 * // /html/body/div[13]/div/div[2]/div/table/tbody/tr/td[2]/input
	 * // /html/body/div[13]/div/div[2]/div/table/tbody/tr/td[2]/input
	 * filtroEs.click();
	 * filtroEs.sendKeys(usuario.getProcesso());
	 * 
	 * WebElement preocessoElement = driver.findElement(
	 * By.xpath(
	 * "/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[4]/div/table/tbody/tr/td[3]/div/a[1]"
	 * ));
	 * preocessoElement.click();
	 * 
	 * // WebElement dosPrev = this.driver.findElement(By.cssSelector("
	 * span[id*=DOSSIÊ
	 * // PREVIDENCIÁRIO]"));
	 * // dosPrev.click();
	 * // WebElement colunaNome =
	 * // linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
	 * 
	 * return "pronto para fazer a leitura";
	 * 
	 * }
	 */

	public boolean dataDeValidacaoDosPrev() throws InterruptedException {
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(janela.get(2));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("treeview-1015")));
		WebElement TabelaTref = driver.findElement(By.id("treeview-1015"));
		List<WebElement> listaMovimentacao = new ArrayList<WebElement>(TabelaTref.findElements(By.cssSelector("tr")));
		for (int i = listaMovimentacao.size(); i > 2; i--) {

			// Providência Jurídica é o título da movimentação
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[" + i + "]/td[2]/div/span")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[" + i + "]/td[2]/div/span")));
			if (i > listaMovimentacao.size() - 3) {
				Thread.sleep(500);
			}
			/*
			 * if(i < listaMovimentacao.size() - 2) {
			 * this.driver.manage().timeouts().implicitlyWait(1,
			 * TimeUnit.MILLISECONDS).pageLoadTimeout(1, TimeUnit.MILLISECONDS);
			 * }
			 */

			Boolean existeDosPrev = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span")).getText()
					.toUpperCase().contains("DOSSIÊ PREVIDENCIÁRIO");
			if (existeDosPrev == true) {
				WebElement dosClick = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span"));
				dosClick.click();
				/*
				 * String campoPassPath =
				 * "/html/body/div[3]/div[1]/div/div/table[1]/tbody/tr/td[2]/input"; WebElement
				 * campoPassElemt = driver.findElement(By.xpath(campoPassPath));
				 * campoPassElemt.click(); campoPassElemt.sendKeys("LIDO"); WebElement
				 * salvarEtiqueta = driver
				 * .findElement(By.xpath("/html/body/div[3]/div[1]/div/div/a/span/span/span[2]")
				 * ); salvarEtiqueta.click();
				 */

				driver.switchTo().frame(0);
				try {
					String dataValiadcaoString = driver.findElement(By.xpath("/html/body/div/p[2]/b")).getText();
					System.out.println(dataValiadcaoString);
					dataValiadcaoString = dataValiadcaoString
							.replace("* Informações extraídas dos sistemas informatizados do INSS em: ", "");
					System.out.println(dataValiadcaoString);

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					formatter = formatter.withLocale(Locale.US);
					// DateTimeFormatter dataForma = DateTimeFormatter.ofPattern("dd/MM/yyyy
					// HH:mm:ss");
					System.out.println("passou1");
					LocalDateTime dataValidacao = LocalDateTime.parse(dataValiadcaoString, formatter);
					System.out.println("passou2");
					System.out.println(dataValidacao);
					// Date data =
					// Date.from(dataValidacao.atZone(ZoneId.systemDefault()).toInstant());
					LocalDateTime dataATUALocalDateTime = LocalDateTime.now();
					System.out.println("data de validacao ano: " + dataValidacao.getYear());
					System.out.println("data atual ano: " + dataATUALocalDateTime.getYear());
					System.out.println("data de validacao: " + dataValidacao.getDayOfYear());
					System.out.println("data atual: " + dataATUALocalDateTime.getDayOfYear());
					System.out.println("data de validacao mes: " + dataValidacao.getMonthValue());
					System.out.println("data atual mes: " + dataATUALocalDateTime.getMonthValue());
					if (dataATUALocalDateTime.getYear() == dataValidacao.getYear()) {
						System.out.println("mesmo ano");
						int x = 0;
						x = dataATUALocalDateTime.getDayOfYear() - dataValidacao.getDayOfYear();
						if (x <= 30) {
							System.out.println("x é menor ou igual a 30  x = " + x);
							return true;
						} else {

							System.out.println("x maior que 30 dias x= " + x);
							return false;

						}
					} else {
						if (dataATUALocalDateTime.getYear() == dataValidacao.getYear() + 1 && dataValidacao.getMonthValue() == 12 && dataATUALocalDateTime.getMonthValue() == 1) {
								int x = dataValidacao.getDayOfYear() - 333;
								// dataATUALocalDateTime.getDayOfYear()<= x
								if ( x > dataATUALocalDateTime.getDayOfYear()) {
									System.out.println("x é menor ou igual a 30 , x " + x);
									return true;
								} else {
									System.out.println("x maior que 30 dias. x: " + x );
									return false;
								}
						} else {
							System.out.println("ano diferente");
							return false;
						}

					}

				} catch (Exception e) {
					System.out.println("Vish entrei no tal do catch 2");
					// dataAjuizamento =
					// driver.findElement(By.xpath("/html/body/div/div[5]/table/tbody/tr[3]/td[2]")).getText();
				}

				return false;
			}
		}

		return false;
	}

	public InfomacoesDosPrev procurarDosPrevAtivo() {
		boolean verificarAtivo = false;
		InfomacoesDosPrev informacao = new InfomacoesDosPrev();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(janela.get(2));
		WebElement TabelaTref = driver.findElement(By.id("treeview-1015"));
		List<WebElement> listaMovimentacao = new ArrayList<WebElement>(TabelaTref.findElements(By.cssSelector("tr")));

		for (int i = listaMovimentacao.size(); i > 2; i--) {

			// Providência Jurídica é o título da movimentação
			Boolean existeDosPrev = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span")).getText()
					.toUpperCase().contains("DOSSIÊ PREVIDENCIÁRIO");
			if (existeDosPrev == true) {
				WebElement dosClick = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span"));
				dosClick.click();

				System.out.println("informaçoes: " + informacao);
				driver.switchTo().frame(0);
				try {
					String cnj = driver.findElement(By.xpath("//html/body/div/div[1]/table/tbody/tr[1]/td")).getText();
					System.out.println("CNJ: " + cnj);
					String dataAjuizamento = driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[2]/td"))
							.getText();
					System.out.println("Data de Ajuizamento: " + dataAjuizamento + "funcinou");
					String dataValiadcaoString = driver.findElement(By.xpath("/html/body/div/p[2]/b")).getText();
					System.out.println(dataValiadcaoString);
					String nome = driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[6]/td")).getText();
					System.out.println("Nome: " + nome);
					String cpf = driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[7]/td")).getText();
					System.out.println("CPF: " + cpf);
					informacao.setNumeroDoProcesso(cnj);
					informacao.setDataAjuizamento(dataAjuizamento);
					informacao.setNome(nome);
					informacao.setCpf(cpf);

					this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS).pageLoadTimeout(1,
							TimeUnit.SECONDS);
					String beneficio = null;
					String nb = null;
					String dip;
					String rmi;
					String dibInicial;
					String dibFinal;
					String dibAnterior;
					String aps;
					String nbUnido;
					List<String> nbsAtivos = new ArrayList<String>();
					for (int j = 2; j < 100; j++) {
						try {

							verificarAtivo = driver
									.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[6]"))
									.getText().toUpperCase().contains("ATIVO");


						} catch (Exception e) {
							System.out.println("Entrei no Catch procurarDosPrev");
							System.out.println(e);
							break;

						}
						if (verificarAtivo) {
							nb = driver
									.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[1]"))
									.getText();
							nbsAtivos.add(nb);

						}
					}
							for (int z = 6; z >= 5; z--) {

								for (int t = 1; t <= 50; t++) {

									try {
										if (z == 6 && t == 1) {
											Thread.sleep(150);
										} else {
											this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
													.pageLoadTimeout(1, TimeUnit.SECONDS);
										}

										for (int j = 0; j < nbsAtivos.size(); j++) {
											String ativo = nbsAtivos.get(j);
											verificarAtivo = driver
													.findElement(
															By.xpath("/html/body/div/div[" + z + "]/div[" + t
																	+ "]/table[1]/tbody/tr[2]/td[2]"))
													.getText().toUpperCase().contains(ativo);

											if (verificarAtivo) {

													beneficio = driver
															.findElement(By.xpath(
																	"/html/body/div/div[" + z + "]/div[" + t
																			+ "]/table[1]/tbody/tr[2]/td[3]"))
															.getText();
													dibInicial = driver
															.findElement(By.xpath(
																	"/html/body/div/div[" + z + "]/div[" + t
																			+ "]/table[1]/tbody/tr[2]/td[6]"))
															.getText();
													System.out.println(dibInicial);
													dip = driver
															.findElement(By.xpath(
																	"/html/body/div/div[" + z + "]/div[" + t
																			+ "]/table[1]/tbody/tr[2]/td[8]"))
															.getText();
													System.out.println("DIP: " + dip);
													dibFinal = driver
															.findElement(By.xpath(
																	"/html/body/div/div[" + z + "]/div[" + t
																			+ "]/table[1]/tbody/tr[2]/td[7]"))
															.getText();
													System.out.println("DIP FINAL: " + dibFinal);
													rmi = driver
															.findElement(By.xpath(
																	"/html/body/div/div[" + z + "]/div[" + t
																			+ "]/table[2]/tbody/tr[2]/td[1]"))
															.getText();
													System.out.println("RMI: " + rmi);

													dibAnterior = driver.findElement(By.xpath("/html/body/div/div[" + z + "]/div[" + t
															+ "]/table[2]/tbody/tr[2]/td[6]")).getText();
													if (dibAnterior.contains("-")) {
														dibAnterior = "";
													}

													aps = driver
															.findElement(By.xpath(
																	"/html/body/div/div[" + z + "]/div[" + t
																			+ "]/table[3]/tbody/tr[2]/td[8]"))
															.getText();


													System.out.println("APS: " + aps);
													//z = 100;
													//t = 100;
												boolean ativoComDatasIguais = dibInicial.equals(dip);
												if(!ativoComDatasIguais || nbsAtivos.size() == 1){
													informacao.setNb(nb);
													informacao.setBeneficio(beneficio);
													informacao.setAps(aps);
													informacao.setRmi(rmi);
													informacao.setDibInicial(dibInicial);
													informacao.setDibFinal(dibFinal);
													informacao.setDip(dip);
													informacao.setDibAnterior(dibAnterior);
													informacao.setTipo("ATIVO");
													informacao.setUrlProcesso(driver.getCurrentUrl());
													System.out.println("Url da pagina " + driver.getCurrentUrl());
													nbUnido = unirNbInformacoesCessado(procurarCessado());
													informacao.setCessado(nbUnido);
													procurarCitacao(informacao, listaMovimentacao);


													return informacao;
												}

												}

											}
										} catch(Exception e){
											System.out.println("Entrei no Catch forever " + e);
											if(z == 6){
												z -=1;
												t = 0;
											}

										//	t = 1000;

										}

										/*
										 * dataValiadcaoString = dataValiadcaoString
										 * .replace("* Informações extraídas dos sistemas informatizados do INSS em: ",
										 * ""); System.out.println(dataValiadcaoString);
										 */

									}
								}


					System.out.println("Nb: " + nb);


				} catch (Exception e) {
					System.out.println("Vish entrei no tal do catch 2");
					// dataAjuizamento =
					// driver.findElement(By.xpath("/html/body/div/div[5]/table/tbody/tr[3]/td[2]")).getText();
				}
				procurarCitacao(informacao, listaMovimentacao);
				return informacao;
			}
		}



		return null;

	}

	public InfomacoesDosPrev procurarDosPrevCessado() {
		boolean verificarAtivo = false;
		InfomacoesDosPrev informacao = new InfomacoesDosPrev();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(janela.get(2));
		WebElement TabelaTref = driver.findElement(By.id("treeview-1015"));
		List<WebElement> listaMovimentacao = new ArrayList<WebElement>(TabelaTref.findElements(By.cssSelector("tr")));

		for (int i = listaMovimentacao.size(); i > 2; i--) {

			// Providência Jurídica é o título da movimentação
			Boolean existeDosPrev = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span")).getText()
					.toUpperCase().contains("DOSSIÊ PREVIDENCIÁRIO");
			if (existeDosPrev == true) {
				WebElement dosClick = driver.findElement(By.xpath("//tr[" + i + "]/td[2]/div/span"));
				dosClick.click();

				System.out.println("informaçoes: " + informacao);
				driver.switchTo().frame(0);
				try {
					String cnj = driver.findElement(By.xpath("//html/body/div/div[1]/table/tbody/tr[1]/td")).getText();
					System.out.println("CNJ: " + cnj);
					String dataAjuizamento = driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[2]/td"))
							.getText();
					System.out.println("Data de Ajuizamento: " + dataAjuizamento + "funcinou");
					String dataValiadcaoString = driver.findElement(By.xpath("/html/body/div/p[2]/b")).getText();
					System.out.println(dataValiadcaoString);
					String nome = driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[6]/td")).getText();
					System.out.println("Nome: " + nome);
					String cpf = driver.findElement(By.xpath("/html/body/div/div[1]/table/tbody/tr[7]/td")).getText();
					System.out.println("CPF: " + cpf);
					informacao.setNumeroDoProcesso(cnj);
					informacao.setDataAjuizamento(dataAjuizamento);
					informacao.setNome(nome);
					informacao.setCpf(cpf);

					this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS).pageLoadTimeout(1,
							TimeUnit.SECONDS);
					String beneficio = null;
					String nb = null;
					String dip;
					String rmi;
					String dibInicial;
					String dibFinal;
					String dibAnterior;
					String aps;
					String nbUnido;
					List<String> nbsAtivos = new ArrayList<String>();
					for (int j = 2; j < 100; j++) {
						try {

							verificarAtivo = driver
									.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[6]"))
									.getText().toUpperCase().contains("CESSADO");


						} catch (Exception e) {
							System.out.println("Entrei no Catch procurarDosPrev");
							System.out.println(e);
							break;

						}
						if (verificarAtivo) {
							nb = driver
									.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[1]"))
									.getText();
							nbsAtivos.add(nb);

						}
					}
					for (int z = 6; z >= 5; z--) {

						for (int t = 1; t <= 50; t++) {

							try {
								if (z == 6 && t == 1) {
									Thread.sleep(150);
								} else {
									this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
											.pageLoadTimeout(1, TimeUnit.SECONDS);
								}

								for (int j = 0; j < nbsAtivos.size(); j++) {
									String ativo = nbsAtivos.get(j);
									verificarAtivo = driver
											.findElement(
													By.xpath("/html/body/div/div[" + z + "]/div[" + t
															+ "]/table[1]/tbody/tr[2]/td[2]"))
											.getText().toUpperCase().contains(ativo);

									if (verificarAtivo) {
										if(j == nbsAtivos.size() - 1){
										beneficio = driver
												.findElement(By.xpath(
														"/html/body/div/div[" + z + "]/div[" + t
																+ "]/table[1]/tbody/tr[2]/td[3]"))
												.getText();
										dibInicial = driver
												.findElement(By.xpath(
														"/html/body/div/div[" + z + "]/div[" + t
																+ "]/table[1]/tbody/tr[2]/td[6]"))
												.getText();
										System.out.println(dibInicial);
										dip = driver
												.findElement(By.xpath(
														"/html/body/div/div[" + z + "]/div[" + t
																+ "]/table[1]/tbody/tr[2]/td[8]"))
												.getText();
										System.out.println("DIP: " + dip);
										dibFinal = driver
												.findElement(By.xpath(
														"/html/body/div/div[" + z + "]/div[" + t
																+ "]/table[1]/tbody/tr[2]/td[7]"))
												.getText();
										System.out.println("DIP FINAL: " + dibFinal);
										rmi = driver
												.findElement(By.xpath(
														"/html/body/div/div[" + z + "]/div[" + t
																+ "]/table[2]/tbody/tr[2]/td[1]"))
												.getText();
										System.out.println("RMI: " + rmi);

										dibAnterior = driver.findElement(By.xpath("/html/body/div/div[" + z + "]/div[" + t
												+ "]/table[2]/tbody/tr[2]/td[6]")).getText();
										if (dibAnterior.contains("-")) {
											dibAnterior = "";
										}

										aps = driver
												.findElement(By.xpath(
														"/html/body/div/div[" + z + "]/div[" + t
																+ "]/table[3]/tbody/tr[2]/td[8]"))
												.getText();


										System.out.println("APS: " + aps);
										//z = 100;
										//t = 100;
								//		boolean ativoComDatasIguais = dibInicial.equals(dip);

											informacao.setNb(nb);
											informacao.setBeneficio(beneficio);
											informacao.setAps(aps);
											informacao.setRmi(rmi);
											informacao.setDibInicial(dibInicial);
											informacao.setDibFinal(dibFinal);
											informacao.setDip(dip);
											informacao.setDibAnterior(dibAnterior);
											informacao.setTipo("CESSADO");
											informacao.setUrlProcesso(driver.getCurrentUrl());
											System.out.println("Url da pagina " + driver.getCurrentUrl());
											nbUnido = unirNbInformacoesCessado(procurarCessado());
											informacao.setCessado(nbUnido);
											procurarCitacao(informacao, listaMovimentacao);


											return informacao;
										}

									}

								}
							} catch(Exception e){
								System.out.println("Entrei no Catch forever " + e);
								if(z == 6){
									z -=1;
									t = 0;
								}

								//	t = 1000;

							}

							/*
							 * dataValiadcaoString = dataValiadcaoString
							 * .replace("* Informações extraídas dos sistemas informatizados do INSS em: ",
							 * ""); System.out.println(dataValiadcaoString);
							 */

						}
					}


					System.out.println("Nb: " + nb);


				} catch (Exception e) {
					System.out.println("Vish entrei no tal do catch 2");
					// dataAjuizamento =
					// driver.findElement(By.xpath("/html/body/div/div[5]/table/tbody/tr[3]/td[2]")).getText();
				}
				procurarCitacao(informacao, listaMovimentacao);
				return informacao;
			}
		}



		return null;

	}


	private void procurarCitacao(InfomacoesDosPrev informacao, List<WebElement> listaMovimentacao) {
		try {

			for (int h = 2; h < listaMovimentacao.size(); h++) {
				driver.switchTo().defaultContent();
				Boolean existeCitacao = driver.findElement(By.xpath("//tr[" + h + "]/td[2]/div/span[1]")).getText()
						.toUpperCase().contains("CITAÇÃO");
				if (existeCitacao) {
					driver.findElement(By.xpath("//tr[" + h + "]/td[2]/div/span")).click();
					String citacaoProcesso = driver
							.findElement(By.xpath(
									"//tr[" + h + "]/td[2]/div/span"))
							.getText();
					// System.out.println("texto: " + citacaoProcesso);
					String[] dataCitacao = citacaoProcesso.split("-");
					System.out.println("texto: " + Arrays.toString(dataCitacao));
					String[] anoCitacao = dataCitacao[3].split(" ");
					System.out.println("citacao: " + dataCitacao[1] + "/" + dataCitacao[2] + "/" + anoCitacao[0]);
					informacao.setCitacao(dataCitacao[1] + "/" + dataCitacao[2] + "/" + anoCitacao[0]);
					h = listaMovimentacao.size();
				}
			}
		} catch (Exception e) {
			System.out.println("Erro: true; " + e);
		}
	}

	public List<InformacoesCessado> procurarCessado() {

		List<InformacoesCessado> listInformacao = new ArrayList<InformacoesCessado>();
		for (int x = 2; x < 100; x++) {
			InformacoesCessado informacao = new InformacoesCessado();

			boolean verificarCessado = false;
			String beneficio;
			String nb;
			String dib;
			String dcb;
			String rmi;

			try {
				verificarCessado = driver
						.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + x + "]/td[6]"))
						.getText().toUpperCase().contains("CESSADO");
				if (verificarCessado) {
					beneficio = driver
							.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + x + "]/td[2]"))
							.getText();
					System.out.println(beneficio);
					nb = driver
							.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + x + "]/td[1]"))
							.getText();

					for (int z = 5; z < 9; z++) {
						for (int j = 1; j < 100; j++) {

							try {
								if (z == 5) {
									Thread.sleep(150);
								} else {
									this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
											.pageLoadTimeout(1, TimeUnit.SECONDS);
								}

								verificarCessado = driver
										.findElement(
												By.xpath("/html/body/div/div[" + z + "]/div[" + j
														+ "]/table[1]/tbody/tr[2]/td[2]"))
										.getText().toUpperCase().contains(nb);
								// /html/body/div/div[6]/div[17]/table[1]/tbody/tr[2]/td[2]
								// /html/body/div/div[6]/div[18]/table[1]/tbody/tr[2]/td[2]

								if (verificarCessado) {
									dib = driver
											.findElement(By.xpath(
													"/html/body/div/div[" + z + "]/div[" + j
															+ "]/table[1]/tbody/tr[2]/td[6]"))
											.getText();
									System.out.println(dib);

									dcb = driver
											.findElement(By.xpath(
													"/html/body/div/div[" + z + "]/div[" + j
															+ "]/table[1]/tbody/tr[2]/td[7]"))
											.getText();
									System.out.println("DIP FINAL: " + dcb);
									rmi = driver
											.findElement(By.xpath(
													"/html/body/div/div[" + z + "]/div[" + j
															+ "]/table[2]/tbody/tr[2]/td[1]"))
											.getText();
									System.out.println("RMI: " + rmi);
									j = 100;
									z = 100;

									informacao.setEspecie(beneficio);
									informacao.setRmi(rmi);
									informacao.setDataDeinicio(dib);
									informacao.setDataFinalBeneficio(dcb);
									informacao.setNb(nb);
									listInformacao.add(informacao);

								}
							} catch (Exception e) {
								System.out.println("Entrei no Catch forever " + e);
								j = 1000;

							}

							/*
							 * dataValiadcaoString = dataValiadcaoString
							 * .replace("* Informações extraídas dos sistemas informatizados do INSS em: ",
							 * ""); System.out.println(dataValiadcaoString);
							 */

						}
					}

				}
			} catch (Exception e) {
				System.out.println("Entrei no Catch");
				break;

			}
			if (informacao.getNb() != null) {
				DAOInformacoesCessado daoInfo = new DAOInformacoesCessado();
				daoInfo.salvarInformacoesCessados(informacao);
			}

		}

		return listInformacao;
	}

	public String unirNbInformacoesCessado(List listaCessado) {
		String nbUnida = "";
		InformacoesCessado informacao = new InformacoesCessado();
		for (int i = 0; i < listaCessado.size(); i++) {
			informacao = (InformacoesCessado) listaCessado.get(i);
			if (i != listaCessado.size() - 1) {
				nbUnida = nbUnida + informacao.getNb() + ",";
			} else {
				nbUnida = nbUnida + informacao.getNb();
			}

		}
		return nbUnida;
	}

	public void obterNbCessado(InfomacoesDosPrev lista) {

	}

	public void etiquetar(Boolean validacao, String beneficio, int seletar) {
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS).pageLoadTimeout(1, TimeUnit.SECONDS);
		driver.switchTo().window(janela.get(1));

		String campoPassPath = "/html/body/div[3]/div[1]/div/div/table[1]/tbody/tr/td[2]/input";
		WebElement campoPassElemt = driver.findElement(By.xpath(campoPassPath));
		campoPassElemt.click();
		if (seletar == 1) {
			if (validacao == true) {
				campoPassElemt.sendKeys("ATIVO " + beneficio);
			} else {
				campoPassElemt.sendKeys("INDEFERIDO");
			}
		} else if (seletar == 0) {
			if (validacao == true) {
				campoPassElemt.sendKeys("LIDO BOOT");
			} else {
				campoPassElemt.sendKeys("DOSPREV VENCIDO");
			}
		}

		WebElement salvarEtiqueta = driver
				.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/a/span/span/span[2]"));
		salvarEtiqueta.click();

		driver.switchTo().window(janela.get(1)).close();
		driver.switchTo().window(janela.get(2)).close();
		driver.switchTo().window(janela.get(0));
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[2]/div/div/a[5]/span/span/span[2]")));
		WebElement filtroSpace = driver.findElement(
				By.xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/div/div[2]/div/div/a[5]/span/span/span[2]"));
		filtroSpace.click();
	}

	public ProcessoValido verificacaoDeAtivo() {
		this.driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS).pageLoadTimeout(100,
				TimeUnit.MILLISECONDS);
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(janela.get(2));
		ProcessoValido ativo = new ProcessoValido();
		String beneficio = null;
		boolean verificarAtivo = false;
		driver.switchTo().frame(0);
		for (int j = 2; j < 100; j++) {
			try {
				verificarAtivo = driver.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[6]"))
						.getText().toUpperCase().contains("ATIVO");
				if (verificarAtivo) {
					beneficio = driver.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[2]"))
							.getText();
					System.out.println(beneficio);
					j = 100;
				}
			} catch (Exception e) {
				System.out.println("Entrei no Catch");
				break;

			}
		}
		ativo.setAtivo(verificarAtivo);
		ativo.setBeneficio(beneficio);
		return ativo;
	}

	public ProcessoValido verificacaoDeCessado() {
		this.driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS).pageLoadTimeout(100,
				TimeUnit.MILLISECONDS);
		List<String> janela = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(janela.get(2));
		ProcessoValido ativo = new ProcessoValido();
		String beneficio = null;
		boolean verificarAtivo = false;
		driver.switchTo().frame(0);
		for (int j = 2; j < 100; j++) {
			try {
				verificarAtivo = driver.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[6]"))
						.getText().toUpperCase().contains("CESSADO");
				if (verificarAtivo) {
					beneficio = driver.findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr[" + j + "]/td[2]"))
							.getText();
					System.out.println(beneficio);
					j = 100;
				}
			} catch (Exception e) {
				System.out.println("Entrei no Catch");
				break;

			}
		}
		ativo.setAtivo(verificarAtivo);
		ativo.setBeneficio(beneficio);
		return ativo;
	}
	public void quit() {
		driver.quit();
	}

	public void openSamirFront(Usuario usuario) {
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("https://relaxed-hopper-ba9dd1.netlify.app");
		driver.findElement(
				By.xpath("/html/body/div/div/main/div/div/span/div[2]/div/div/div/form/div[1]/div/div[1]/div/input"))
				.sendKeys(usuario.getNome());
		driver.findElement(
				By.xpath("/html/body/div/div/main/div/div/span/div[2]/div/div/div/form/div[2]/div/div[1]/div/input"))
				.sendKeys(usuario.getCpf());
		driver.findElement(By.xpath("/html/body/div/div/main/div/div/span/div[2]/div/div/div/form/button")).click();
	}
	public void diminuirTela () throws AWTException {
		Actions action = new Actions(driver);
		Robot robot = new Robot();
		for (int i = 0; i<=3; i++){

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}

	}
	public void aumentarTela() throws AWTException {
		Actions action = new Actions(driver);
		Robot robot = new Robot();
		for (int i = 0; i<=3; i++){

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}

	}
	public void openFront(Usuario usuario) {
		if (driver == null) {
			driver = new FirefoxDriver();
			System.setProperty("webdriver.gecko.driver", "GeckoDriver.exe");
		}
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("https://relaxed-hopper-ba9dd1.netlify.app");
		driver.findElement(
				By.xpath("/html/body/div/div/main/div/div/span/div[2]/div/div/div/form/div[1]/div/div[1]/div/input"))
				.sendKeys(usuario.getNome());
		driver.findElement(
				By.xpath("/html/body/div/div/main/div/div/span/div[2]/div/div/div/form/div[2]/div/div[1]/div/input"))
				.sendKeys(usuario.getCpf());
		driver.findElement(By.xpath("/html/body/div/div/main/div/div/span/div[2]/div/div/div/form/button")).click();
	}

	public void inserirDosPrev(InfomacoesDosPrev lista, List<InformacoesCessado> listaCessado) {

		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(30, TimeUnit.SECONDS); 
		driver.findElement(By.id("numeroProcesso")).sendKeys(lista.getNumeroDoProcesso());
		driver.findElement(By.id("nome")).sendKeys(lista.getNome());
		driver.findElement(By.id("dataAjuizamento")).click();
		driver.findElement(By.id("dataAjuizamento")).sendKeys(lista.getDataAjuizamento());
		driver.findElement(By.id("cpf")).sendKeys(lista.getCpf());
		driver.findElement(By.id("dtDibInicial")).sendKeys(lista.getDibInicial());
		driver.findElement(By.id("dtDibFinal")).sendKeys(lista.getDibFinal());
		driver.findElement(By.id("rmi")).sendKeys(lista.getRmi());
		driver.findElement(By.id("beneficio")).sendKeys(lista.getBeneficio());
		driver.findElement(By.id("nb")).sendKeys(lista.getNb());
		driver.findElement(By.id("dip")).sendKeys(lista.getDip());
		if (lista.getCitacao() != null) {
			driver.findElement(By.id("citacao")).sendKeys(lista.getCitacao());
		}
		driver.findElement(By.id("urlProcesso")).sendKeys(lista.getUrlProcesso());
		driver.findElement(By.id("aps")).sendKeys(lista.getAps());
		driver.findElement(By.id("dibAnterior")).sendKeys(lista.getDibAnterior());
		driver.findElement(By.id("tipo")).sendKeys(lista.getTipo());
		String[] listaNb = lista.getCessado().split(",");

		try {

			for (int x = 0; x < listaNb.length; x++) {
				for (int i = 0; i < listaCessado.size(); i++) {
					boolean conferirCessado = false;
					if (listaCessado.get(i).getNb() == null) {
						System.out.println("Deu null");
					}else if (listaNb[x].equals("")){
						System.out.println("Não tem Benefício");
						break;
					} else{
						conferirCessado = listaCessado.get(i).getNb().contains(listaNb[x]);
					}

					if (conferirCessado) {
						driver.findElement(By.id("beneficioAcumulado_beneficio"))
								.sendKeys(listaCessado.get(i).getEspecie());
						driver.findElement(By.id("beneficioAcumulado_dib"))
								.sendKeys(listaCessado.get(i).getDataDeinicio());
						driver.findElement(By.id("beneficioAcumulado_dif"))
								.sendKeys(listaCessado.get(i).getDataFinalBeneficio());
						driver.findElement(By.id("beneficioAcumulado_rmi")).sendKeys(listaCessado.get(i).getRmi());
						driver.findElement(By.id("beneficioBtn")).click();
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("erro: true, mensagem: " + e);
		}

		driver.findElement(By.id("adicionarButton")).click();
		/*
		 * try {
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * 
		 * wait.until(ExpectedConditions
		 * .elementToBeClickable(By.id("aps")));
		 * WebElement ele = driver.findElement(By.id("aps"));
		 * // action.moveToElement(driver.findElement(By.xpath("//td["+i+"]/div"))).
		 * doubleClick().build().perform();
		 * // driver.findElement(By.xpath("//td["+i+"]/div")).click();
		 * //Thread.sleep(500);
		 * //driver.findElement(By.xpath("//td["+i+"]/div")).click();
		 * js.executeScript(("var evt = document.createEvent('MouseEvents');"+
		 * "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
		 * +
		 * "arguments[0].dispatchEvent(evt);"), ele);
		 * //action.doubleClick(driver.findElement(By.xpath("//td["+i+"]/div"))).build()
		 * .perform();
		 * wait.until(ExpectedConditions
		 * .elementToBeClickable(By.id("aps")));
		 * 
		 * } catch (Exception e) {
		 * System.out.println("erro true: " + e);
		 * }
		 */
	}

	public String finalizar() {
		try {
			driver.findElement(
					By.xpath("/html/body/div/div/main/div/div/span/div[2]/div/div/div[2]/div/div/div[6]/button"))
					.click();
			driver.findElement(
					By.xpath("/html/body/div/div/main/div/div/span/div[2]/div/div/div[2]/div/div/div[6]/button"))
					.click();

			return "deu certo";
		} catch (Exception e) {
			return "deu errado: " + e;
			// TODO: handle exception
		}
	}


	public void parar() {
		try {
			System.out.println(this.driver);
			driver.quit();


		} catch (Exception e) {

		}

	}

}
