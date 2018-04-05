package hu.unideb.inf.validator;

import java.util.regex.Pattern;

public class RawInputValidator {

	public static final String FACT_PATTERN = "[A-Z]";
	public static final String RULE_PATTERN = "[A-Z](&[A-Z])*>[A-Z]";

	private Pattern factPattern;
	private Pattern rulePattern;

	public RawInputValidator() {

		factPattern = Pattern.compile(FACT_PATTERN);
		rulePattern = Pattern.compile(RULE_PATTERN);

	}

	public boolean isValidFact(String fact) {

		return factPattern.matcher(fact).matches();

	}

	public boolean isValidRule(String rule) {

		return rulePattern.matcher(rule).matches();

	}

}
