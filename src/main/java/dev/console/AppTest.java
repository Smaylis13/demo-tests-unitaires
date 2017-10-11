package dev.console;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;

public class AppTest {

	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Rule
	public final TextFromStandardInputStream systemInMok = TextFromStandardInputStream
			.emptyStandardInputStream();
	private App app;
	private CalculService calculService;

	@Before
	public void setUp() throws Exception {
		// this.calculService = new CalculService();
		this.calculService = mock(CalculService.class);
		this.app = new App(new Scanner(System.in), calculService);
	}
	
	@Test
	public void TestDeuxExpressionEtSaisieFin(){
		String exp1 = "5+6";
		String exp2 = "54+63";
		systemInMok.provideLines(exp1,exp2,"fin");

				this.app.demarrer();

		assertThat(systemOutRule.getLog()).contains("Au revoir");

	}
	
	@Test
	public void TestInvalidFin(){
		systemInMok.provideLines("AAAA","fin");

		this.app.demarrer();
		
		assertThat(systemOutRule.getLog()).contains("Au revoir");

	}
	
	@Test
	public void TestExpressionEtSaisieFin(){
		systemInMok.provideLines("5+6","fin");

		this.app.demarrer();
		
		assertThat(systemOutRule.getLog()).contains("Au revoir");

	}

	@Test
	public void TestSaisieFin(){
		systemInMok.provideLines("fin");
		
		this.app.demarrer();
		
		assertThat(systemOutRule.getLog()).contains("Au revoir");

	}

	@Test
	public void testExpressionInvalide() {
		// calculService.additionner("a");
		String exp = "a";
		Mockito.when(calculService.additionner(exp)).thenThrow(new CalculException(exp));
		assertThat(systemOutRule.getLog()).contains("invalid");
	}

	@Test
	public void testEvaluer() throws Exception {
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1+34";
		when(calculService.additionner(expression)).thenReturn(35);

		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);

		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);

		LOG.info("Alors dans la console, s'affiche 1+34=35");
		assertThat(systemOutRule.getLog()).contains("1+34=35");
	}

	@Test
	public void testAfficherTitre() throws Exception {
		this.app.afficherTitre();
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("**** Application Calculatrice ****");
	}
}
