package com.ttf.suma.util;

import java.io.FileWriter;
import java.io.IOException;

import com.ttf.suma.model.SimpleExecutionInstance;
import com.ttf.suma.model.generations.Generation;

public class CSVUtil {

	private FileWriter writer;
	private CSVKind kind;

	/**
	 * Crea un archivo de texto - CSV
	 * 
	 * @param fileName
	 *            El nombre del archivo con extensión
	 */
	public CSVUtil(String fileName, CSVKind kind) {
		this.kind = kind;
		try {
			writer = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeFile() {
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addExecution(SimpleExecutionInstance executionInstance) {
		StringBuilder sb = new StringBuilder();
		for (Generation generation : executionInstance.getGenerations()) {
			if (kind == CSVKind.AVERAGE) {
				sb.append(generation.getAverageFitness());
				sb.append(",");
			} else if (kind == CSVKind.BEST) {
				sb.append(generation.getBest().fitness());
				sb.append(",");
			}
		}
		sb.append("\n");
		try {
			writer.append(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public enum CSVKind {
		BEST, AVERAGE;
	}
}
