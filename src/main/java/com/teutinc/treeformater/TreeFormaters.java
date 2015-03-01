package com.teutinc.treeformater;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * @author apeyrard
 */
public class TreeFormaters {

	/**
	 * Prints a tree.
	 *
	 * @param rootVertex the root vertex
	 * @param printNode the function to transform a vertex into a printable string
	 * @param findChildren the function to get children from a vertex
	 * @param <V> the vertices type
	 * @return the list of string generated
	 */
	public static <V> List<String> prettyPrint(V rootVertex, Function<V, String> printNode, Function<V, List<V>> findChildren) {
		List<String> output = new ArrayList<>();
		addDependencyInTree(rootVertex, new LinkedList<>(), output, printNode, findChildren);

		return output;
	}

	private static <V> void addDependencyInTree(
			V vertex,
			Deque<LevelType> levels,
			List<String> output,
			Function<V, String> printNode,
			Function<V, List<V>> findChildren) {

		StringBuilder line = new StringBuilder();
		for (Iterator<LevelType> iterator = levels.iterator(); iterator.hasNext(); ) {
			LevelType level = iterator.next();
			if (iterator.hasNext()) {
				line.append(level.trailingOutput);
			} else {
				line.append(level.currentOutput);
			}
		}

		output.add(line.append(printNode.apply(vertex)).toString());

		List<V> children = findChildren.apply(vertex);
		for (Iterator<V> it = children.iterator(); it.hasNext(); ) {
			V child = it.next();

			if (it.hasNext()) {
				levels.addLast(LevelType.CONTINUE);
			} else {
				levels.addLast(LevelType.LAST);
			}

			addDependencyInTree(child, levels, output, printNode, findChildren);

			levels.removeLast();
		}
	}

	private static enum LevelType {
		CONTINUE("│    ", "├── "), LAST("    ", "└── ");

		private final String trailingOutput;
		private final String currentOutput;

		LevelType(String trailingOutput, String currentOutput) {
			this.trailingOutput = trailingOutput;
			this.currentOutput = currentOutput;
		}
	}
}
