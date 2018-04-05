package hu.unideb.inf.reader;

import hu.unideb.inf.exception.InvalidFactException;
import hu.unideb.inf.exception.InvalidRuleException;
import hu.unideb.inf.model.RawKnowledgeBase;
import hu.unideb.inf.validator.RawInputValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RawKnowledgeBaseReader {

	private BufferedReader reader;
	private RawInputValidator rawInputValidator;

	public RawKnowledgeBaseReader() {

		reader = new BufferedReader(new InputStreamReader(System.in));
		rawInputValidator = new RawInputValidator();

	}

	public RawKnowledgeBase read() throws IOException, InvalidRuleException, InvalidFactException {

		List<String> rules = readRules();
		List<String> facts = readFacts();

		return new RawKnowledgeBase(rules, facts);

	}

	private List<String> readRules() throws IOException, InvalidRuleException {

		System.out.println("Enter rules:");

		List<String> rules = new ArrayList<>();

		String rule;

		while (!(rule = reader.readLine()).isEmpty()) {

			// validation
			if (!rawInputValidator.isValidRule(rule)) {
				throw new InvalidRuleException();
			}

			rules.add(rule);

		}

		return rules;

	}

	private List<String> readFacts() throws IOException, InvalidFactException {

		System.out.println("Enter facts:");

		List<String> facts = new ArrayList<>();

		String fact;

		while (!(fact = reader.readLine()).isEmpty()) {

			// validation
			if (!rawInputValidator.isValidFact(fact)) {
				throw new InvalidFactException();
			}

			facts.add(fact);

		}

		return facts;

	}

}
