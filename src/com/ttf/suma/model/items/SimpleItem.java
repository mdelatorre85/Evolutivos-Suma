package com.ttf.suma.model.items;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.ttf.suma.model.Random;

public class SimpleItem extends Item {

	private static final long serialVersionUID = 7954201710436407651L;

	SimpleItem(Void voidy) {
		super(voidy);
	}

	public SimpleItem() {
		super();
	}

	/**
	 * Implementación de la mutación aleatoria en un solo punto
	 */
	@Override
	public Item mutate() {
		for (int i = 0; i < genotype.length; i++) {
			if (Random.getRandomDouble() < MUTATIONPROBABILITY) {
				genotype[i] = !genotype[i];
				fitness();
			}
		}
		return this;
	}

	@Override
	public List<Item> cross(Item item) {
		ArrayList<Item> retorno = new ArrayList<Item>();
		if (Random.getRandomDouble() < CROSSPROBABILITY) {
			SimpleItem uno = new SimpleItem(null);
			SimpleItem dos = new SimpleItem(null);
			int splitPoint = Random.getRandomNotZeroInt(LENGHT);

			for (int i = 0; i < LENGHT; i++) {
				if (i < splitPoint) {
					uno.genotype[i] = genotype[i];
					dos.genotype[i] = item.genotype[i];
				} else {
					dos.genotype[i] = genotype[i];
					uno.genotype[i] = item.genotype[i];
				}
			}

			uno.fitness();
			dos.fitness();

			uno = (SimpleItem) uno.localSearch();
			dos = (SimpleItem) dos.localSearch();

			retorno.add(uno);
			retorno.add(dos);

		} else {
			retorno.add(item);
			retorno.add(this);
		}

		return retorno;
	}

	@Override
	public Item localSearch() {

		TreeSet<Item> retorno = new TreeSet<Item>();
		if (fitnessValue == Integer.MIN_VALUE) {
			fitness();
		}
		retorno.add(this);

		for (int i = 0; i < LENGHT; i++) {
			if (fitnessValue > 0) {
				if (genotype[i]) {
					try {
						SimpleItem nuevo = (SimpleItem) clone();
						nuevo.genotype[i] = false;
						nuevo.fitness();
						retorno.add(nuevo);
					} catch (CloneNotSupportedException ex) {
						ex.printStackTrace();
					}
				}
			} else if (fitnessValue < 0) {
				if (!genotype[i]) {
					try {
						SimpleItem nuevo = (SimpleItem) clone();
						nuevo.genotype[i] = true;
						nuevo.fitness();
						retorno.add(nuevo);
					} catch (CloneNotSupportedException ex) {
						ex.printStackTrace();
					}
				}
			} else {
				return this;
			}
		}

		return retorno.first();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		SimpleItem retorno = new SimpleItem(null);
		for (int i = 0; i < LENGHT; i++) {
			retorno.genotype[i] = genotype[i];
		}
		retorno.fitnessValue = fitnessValue;
		return retorno;
	}

	public Item getClone() {
		Item retorno = new SimpleItem(null);
		for (int i = 0; i < LENGHT; i++) {
			retorno.genotype[i] = genotype[i];
		}
		retorno.fitnessValue = fitnessValue;
		return retorno;
	}

	public String toString() {
		return super.toString();
	};

}
