package dev.service;


import java.util.Arrays;
import java.util.stream.Stream;

import org.slf4j.*;

import dev.exception.CalculException;

public class CalculService {
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	
	/**
	 * 
	 * @param expression
	 * @return int
	 */
	public int additionner(String expression) {
		LOG.debug("Evaluation de l'expression "+expression);
		int resultat;
		try{
		resultat = Stream.of(expression.trim().split("\\+")).mapToInt(s -> Integer.parseInt(s)).sum();
		
			

		}catch (Exception e) {
			throw new CalculException(expression);
		}
		return  resultat;
	}
}
