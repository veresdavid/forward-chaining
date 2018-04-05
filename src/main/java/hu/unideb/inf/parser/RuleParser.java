package hu.unideb.inf.parser;

import hu.unideb.inf.model.Rule;

import java.util.Arrays;
import java.util.List;

public class RuleParser {

	public Rule parse(String rawRule) {

		String[] parts = rawRule.split(">");

		List<String> premises = Arrays.asList(parts[0].split("&"));
		String head = parts[1];

		return new Rule(premises, head);

	}

}
