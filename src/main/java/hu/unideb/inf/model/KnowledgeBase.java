package hu.unideb.inf.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KnowledgeBase {

	private List<Rule> rules;
	private List<String> facts;

}
