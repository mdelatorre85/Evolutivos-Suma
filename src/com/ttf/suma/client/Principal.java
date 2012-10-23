package com.ttf.suma.client;

import com.ttf.suma.model.Experiment;
import com.ttf.suma.model.generations.SimpleGeneration;

public class Principal {

	public static void main(String[] args) {
		SimpleGeneration sg = new SimpleGeneration();
		Experiment exp = new Experiment(20, 50, sg);
		exp.performExperiment();
		System.out.println("Ta·Dah");
	}
}
