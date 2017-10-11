package dev.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slf4j.*;

import dev.exception.CalculException;

public class CalculServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class);

	@Test
	public void testAdditionner() throws Exception {
		LOG.info("Etant donné, une instance de la classe CalculService");
		CalculService calculService = new CalculService();
		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		// TODO
		int somme = calculService.additionner("1+3+4");
		LOG.info("Alors j'obtiens le résultat 8");
		// TODO
		assertThat(8, is(somme));
	}

	@Test(expected = CalculException.class)
	public void testException() {
		CalculService calculService = new CalculService();
		/// pour que le test soit passant
		calculService.additionner("a");
	}
}
