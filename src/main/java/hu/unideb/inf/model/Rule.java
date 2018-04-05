package hu.unideb.inf.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rule {

	private List<String> premises;
	private String head;

}
