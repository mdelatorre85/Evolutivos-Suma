package com.ttf.suma.model;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

import com.ttf.suma.model.generations.Generation;
import com.ttf.suma.model.generations.SimpleGeneration;
import com.ttf.suma.model.items.Item;

public class SimpleExecutionInstance implements Serializable {

	private static final long serialVersionUID = 3614549184247110568L;

	private int maxGenerations = 0;
	private Generation seedGeneration;
	private Stack<Generation> generations;

	public SimpleExecutionInstance(int maxGenerations, Generation generation) {
		generations = new Stack<Generation>();
		this.maxGenerations = maxGenerations;
		this.seedGeneration = generation;
	}

	public Generation getGeneration() {
		return seedGeneration;
	}

	public int getMaxGenerations() {
		return maxGenerations;
	}

	public Stack<Generation> getGenerations() {
		if (generations == null) {
			generations = new Stack<Generation>();
		}
		return generations;
	}

	public void evolve() {
		generations.push(seedGeneration);

		Generation currentGeneration;
		Generation nextGeneration = null;

		currentGeneration = seedGeneration;
		for (int i = 1; i < maxGenerations; i++) {
			nextGeneration = new SimpleGeneration(i);
			while (nextGeneration.getItems().size() < Generation.GENERATIONSIZE) {
				List<Item> parents = currentGeneration.getParents();
				parents = parents.get(0).cross(parents.get(1));

				parents.get(0).mutate();
				parents.get(1).mutate();

				nextGeneration.getItems().addAll(parents);
				nextGeneration.getOrderedItems().addAll(parents);
			}
			currentGeneration.calculateAverage();
			generations.push(currentGeneration);
			currentGeneration = nextGeneration;
			System.out.println("Generación :" + i);
		}

		if (nextGeneration != null)
			generations.push(nextGeneration);

	}
}
