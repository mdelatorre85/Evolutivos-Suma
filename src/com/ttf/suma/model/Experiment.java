package com.ttf.suma.model;

import java.util.Stack;

import com.ttf.suma.model.generations.Generation;
import com.ttf.suma.util.CSVUtil;
import com.ttf.suma.util.CSVUtil.CSVKind;

public class Experiment {

	private int maxGenerations = 0;
	private int maxExecutions = 0;
	private Generation seedGeneration;
	private Stack<SimpleExecutionInstance> executionInstances;

	public Experiment(int maxGenerations, int maxExecutions,
			Generation generation) {
		executionInstances = new Stack<SimpleExecutionInstance>();
		this.maxExecutions = maxExecutions;
		this.maxGenerations = maxGenerations;
		this.seedGeneration = generation;
	}

	public Stack<SimpleExecutionInstance> getExecutionInstances() {
		if (executionInstances == null) {
			executionInstances = new Stack<SimpleExecutionInstance>();
		}
		return executionInstances;
	}

	public void performExperiment() {
		CSVUtil csvBest = new CSVUtil("BestWithoutLocalSearch.CSV",
				CSVKind.BEST);
		CSVUtil csvAverage = new CSVUtil("AverageWithoutLocalSearch.CSV",
				CSVKind.AVERAGE);
		for (int i = 0; i < maxExecutions; i++) {
			executionInstances.push(new SimpleExecutionInstance(maxGenerations,
					seedGeneration.getClone()));
			executionInstances.peek().evolve();
			csvBest.addExecution(executionInstances.peek());
			csvAverage.addExecution(executionInstances.peek());
		}

		csvBest.closeFile();
		csvAverage.closeFile();

	}
}
