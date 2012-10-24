package com.ttf.suma.model.items;

import java.io.Serializable;
import java.util.List;

import com.ttf.suma.model.Random;
import com.ttf.suma.model.Target;

public abstract class Item implements Serializable, Comparable<Item> {

	private static final long serialVersionUID = -6208594491298428910L;

	private static int MAXFITNESSVALUE = Integer.MIN_VALUE;
	static final int LENGHT = 16;
	static double MUTATIONPROBABILITY = 0.001d;
	static double CROSSPROBABILITY = 0.8d;

	boolean[] genotype;
	int fitnessValue = Integer.MIN_VALUE;

	public Item() {
		genotype = new boolean[LENGHT];
		for (int i = 0; i < genotype.length; i++) {
			genotype[i] = Random.getRandomBoolean();
		}
	}

	Item(Void voidy) {
		genotype = new boolean[LENGHT];
	}

	public boolean[] getGenotype() {
		return genotype;
	}

	private int maxFitnessValue() {
		if (MAXFITNESSVALUE == Integer.MIN_VALUE) {
			MAXFITNESSVALUE = 0;
			for (int i = 0; i < LENGHT; i++) {
				MAXFITNESSVALUE += i;
			}
		}
		return MAXFITNESSVALUE;
	}

	public int fitness() {
		fitnessValue = 0;
		for (int i = 0; i < LENGHT; i++) {
			if (genotype[i]) {
				fitnessValue += i;
			}
		}
		fitnessValue -= Target.TARGET;
		return maxFitnessValue() - Math.abs(fitnessValue);
	}

	public abstract Item mutate();

	public abstract List<Item> cross(Item item);

	public abstract Item localSearch();

	public abstract Item getClone();

	@Override
	public String toString() {
		if (genotype != null) {
			StringBuilder sb = new StringBuilder("[");
			for (int i = 0; i < genotype.length; i++) {
				if (genotype[i]) {
					sb.append(i);
					sb.append(" ");
				}

			}
			sb.append("]");
			sb.toString();
		}
		return super.toString();
	}

	public int compareTo(Item o) {
		if (fitnessValue == Integer.MIN_VALUE) {
			fitness();
		}

		if (o.fitnessValue == Integer.MIN_VALUE) {
			o.fitness();
		}

		return Math.abs(fitnessValue) - Math.abs(o.fitnessValue);
	}

	public boolean equals(Object obj) {
		if (obj != null)
			if (obj instanceof Item) {
				return toString().equals(obj.toString());
			}
		return false;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

}
