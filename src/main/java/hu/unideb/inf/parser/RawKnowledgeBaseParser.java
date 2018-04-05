package hu.unideb.inf.parser;

import hu.unideb.inf.model.KnowledgeBase;
import hu.unideb.inf.model.RawKnowledgeBase;
import hu.unideb.inf.model.Rule;

import java.util.ArrayList;
import java.util.List;

public class RawKnowledgeBaseParser {

	public KnowledgeBase parse(RawKnowledgeBase rawKnowledgeBase) {

		RuleParser ruleParser = new RuleParser();

		List<String> rawRules = rawKnowledgeBase.getRules();
		List<Rule> rules = new ArrayList<>();

		for (String rawRule : rawRules) {
			rules.add(ruleParser.parse(rawRule));
		}

		List<String> facts = new ArrayList<>(rawKnowledgeBase.getFacts());

		return new KnowledgeBase(rules, facts);

	}

}
