package hu.unideb.inf.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RawKnowledgeBase {

	private List<String> rules;
	private List<String> facts;

}
