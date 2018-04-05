package hu.unideb.inf.algorithm;

import hu.unideb.inf.model.KnowledgeBase;
import hu.unideb.inf.model.Rule;

import java.util.ArrayList;
import java.util.List;

public class ForwardChaining {

	public boolean entails(KnowledgeBase knowledgeBase, String query, boolean verbose) {

		if (verbose) printHeader();

		List<String> agenda = new ArrayList<>(knowledgeBase.getFacts());
		List<String> consequences = new ArrayList<>();
		List<Rule> rules = knowledgeBase.getRules();

		int[] counter = new int[rules.size()];
		for (int i = 0; i < rules.size(); i++) {
			counter[i] = rules.get(i).getPremises().size();
		}

		while (!agenda.isEmpty()) {

			if (verbose) {
				printState(agenda, consequences, counter);
				System.out.println();
				System.out.println("OPERATIONS");
			}

			String p = agenda.remove(0);

			if (verbose) System.out.println("We choose: " + p);

			if (p.equals(query)) {

				if (verbose) {
					System.out.println(p + " equals " + query);
					printSeparator();
					printStop();
				}

				return true;

			}

			if (!consequences.contains(p)) {

				if (verbose) printIsPInConsequences(p, false);

				consequences.add(p);

				if (verbose) System.out.println("Modifying counter!");

				for (int i = 0; i < rules.size(); i++) {

					if (rules.get(i).getPremises().contains(p)) {

						counter[i]--;

						if (counter[i] == 0) {
							agenda.add(rules.get(i).getHead());
							if (verbose) System.out.println(rules.get(i).getHead() + " has been added to agenda.");
						}

					}

				}

			} else {
				if (verbose) printIsPInConsequences(p, true);
			}

			if (verbose) printNextIteration();

		}

		printStop();
		return false;

	}

	public boolean entails(KnowledgeBase knowledgeBase, String query) {

		return entails(knowledgeBase, query, false);

	}

	// Helper methods for verbose mode

	private void printSeparator() {
		System.out.println("------------------------------------------------");
	}

	private void printHeader() {
		printSeparator();
		System.out.println("FORWARD CHAINING");
		printSeparator();
	}

	private void printStop() {
		System.out.println("ALGORITHM STOPS");
		printSeparator();
	}

	private void printIsPInConsequences(String p, boolean b) {

		if (b) {
			System.out.println(p + " is in consequences!");
		} else {
			System.out.println(p + " is not in consequences!");
		}

	}

	private void printObjectList(String title, List list) {

		System.out.print(title + ": ");

		for (int i = 0; i < list.size(); i++) {

			System.out.print(list.get(i));

			if (i != list.size() - 1) {
				System.out.print(", ");
			}

		}

		System.out.println();

	}

	private void printIntArray(String title, int[] array) {

		System.out.print(title + ": ");

		for (int i = 0; i < array.length; i++) {

			System.out.print(array[i]);

			if (i != array.length - 1) {
				System.out.print(", ");
			}

		}

		System.out.println();

	}

	private void printState(List<String> agenda, List<String> consequences, int[] counter) {
		System.out.println("STATE");
		printObjectList("Agenda", agenda);
		printObjectList("Consequences", consequences);
		printIntArray("Counter", counter);
	}

	private void printNextIteration() {
		System.out.println();
		System.out.println("NEXT ITERATION!");
		printSeparator();
	}

}
