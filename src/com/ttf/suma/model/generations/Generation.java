package com.ttf.suma.model.generations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.ttf.suma.model.items.Item;

public abstract class Generation implements Serializable,
		Comparable<Generation> {

	private static final long serialVersionUID = 1100029552248155963L;

	public static final int GENERATIONSIZE = 70;

	PriorityQueue<Item> orderedItems;
	ArrayList<Item> items;
	int average;
	Integer generation;

	public int getAverageFitness() {
		return average;
	}

	public Item getBest() {
		return orderedItems.peek();
	}

	Generation() {
		orderedItems = new PriorityQueue<Item>();
		items = new ArrayList<Item>();
	}

	public void calculateAverage() {
		if (orderedItems != null) {
			int i = 0;
			for (Item item : orderedItems) {
				i++;
				average += item.fitness();
			}
			average /= i;
		}
	}

	public PriorityQueue<Item> getOrderedItems() {
		if (orderedItems == null) {
			orderedItems = new PriorityQueue<Item>();
		}
		return orderedItems;
	}

	public abstract List<Item> getParents();

	public Integer getGeneration() {
		return generation;
	}

	public void setGeneration(Integer generation) {
		this.generation = generation;
	}

	public ArrayList<Item> getItems() {
		if (items == null) {
			items = new ArrayList<Item>();
		}
		return items;
	}

	@Override
	public int compareTo(Generation o) {
		return generation - o.generation;
	}

	public abstract Generation getClone();
}
