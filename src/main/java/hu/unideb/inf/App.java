package hu.unideb.inf;

import hu.unideb.inf.algorithm.ForwardChaining;
import hu.unideb.inf.exception.InvalidFactException;
import hu.unideb.inf.exception.InvalidRuleException;
import hu.unideb.inf.model.KnowledgeBase;
import hu.unideb.inf.model.RawKnowledgeBase;
import hu.unideb.inf.parser.RawKnowledgeBaseParser;
import hu.unideb.inf.reader.RawKnowledgeBaseReader;
import hu.unideb.inf.validator.RawInputValidator;

import java.io.IOException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws InvalidFactException, IOException, InvalidRuleException {

		boolean verbose;

		// handle command line arguments
		if (args.length == 0) {
			verbose = false;
		} else if (args.length == 1 && args[0].equals("-v")) {
			verbose = true;
		} else {
			System.out.println("Error!");
			System.out.println("Invalid command line arguments!");
			return;
		}

		// read knowledge base
		RawKnowledgeBaseReader rawKnowledgeBaseReader = new RawKnowledgeBaseReader();
		RawKnowledgeBase rawKnowledgeBase = rawKnowledgeBaseReader.read();

		// parse knowledge base
		RawKnowledgeBaseParser rawKnowledgeBaseParser = new RawKnowledgeBaseParser();
		KnowledgeBase knowledgeBase = rawKnowledgeBaseParser.parse(rawKnowledgeBase);

		// read query
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter query:");
		String query = scanner.nextLine();

		RawInputValidator rawInputValidator = new RawInputValidator();
		if (!rawInputValidator.isValidFact(query)) {
			throw new InvalidFactException();
		}

		scanner.close();

		// forward chaining
		ForwardChaining forwardChaining = new ForwardChaining();
		boolean result = forwardChaining.entails(knowledgeBase, query, verbose);

		// print result
		System.out.println();
		if (result) {
			System.out.println("The given knowledge base entails the query!");
		} else {
			System.out.println("The given knowledge base not entails the query!");
		}

	}

}
