package com.ttf.suma.model.generations;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.ttf.suma.model.Random;
import com.ttf.suma.model.items.Item;
import com.ttf.suma.model.items.SimpleItem;

public class SimpleGeneration extends Generation {

	private static final long serialVersionUID = -6609943368061143053L;
	private static final int TOURNAMENTSIZE = 4;

	public SimpleGeneration() {
		super();
		generation = 0;
		SimpleItem item;
		for (int i = 0; i < GENERATIONSIZE; i++) {
			item = new SimpleItem();
			orderedItems.add(item);
			items.add(item);
		}
	}

	public SimpleGeneration(Integer generation) {
		super();
		this.generation = generation;
	}

	@Override
	public List<Item> getParents() {
		List<Item> retorno = new ArrayList<Item>();
		PriorityQueue<SimpleItem> ordenados = new PriorityQueue<SimpleItem>();
		// for (int i = 0; i < TOURNAMENTSIZE; i++) {
		while (ordenados.size() < TOURNAMENTSIZE) {
			ordenados.add((SimpleItem) items.get(Random
					.getRandomInt(GENERATIONSIZE)));
		}
		int i = 0;
		for (Item item : ordenados) {
			if (!(i < 2)) {
				break;
			}
			retorno.add(item);
			i++;
		}
		return retorno;
	}

	public Generation getClone() {
		Generation retorno = new SimpleGeneration(generation);
		retorno.average = average;
		for (Item item : items) {
			Item clonedItem = ((SimpleItem) item).getClone();
			retorno.items.add(clonedItem);
			retorno.orderedItems.add(clonedItem);
		}
		return retorno;
	}

}
