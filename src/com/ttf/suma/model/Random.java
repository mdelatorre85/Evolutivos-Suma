package com.ttf.suma.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Stack;

public class Random {

	private static Stack<Double> randoms = new Stack<Double>();
	private static boolean isWebOK = true;

	public static double getRandomDouble() {
		if (isWebOK) {
			if (randoms.isEmpty()) {
				try {
					String newUrl = "http://www.random.org/decimal-fractions/?num=100&dec=20&col=1&format=plain&rnd=new";
					URL u = new URL(newUrl);
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(u.openStream(), "UTF-8"));
					String line;

					while ((line = reader.readLine()) != null) {
						randoms.push(Double.parseDouble(line));
					}
				} catch (IOException ex) {
					isWebOK = false;
					return Math.random();
				} catch (Exception e) {
					isWebOK = false;
					return Math.random();
				}
			}
			return randoms.pop();
		} else {
			return Math.random();
		}
	}

	public static boolean getRandomBoolean() {
		if (getRandomDouble() > .5d) {
			return true;
		} else {
			return false;
		}
	}

	public static double truncateDouble(double number, int numDigits) {
		double result = number;
		String arg = "" + number;
		int idx = arg.indexOf('.');
		if (idx != -1) {
			if (arg.length() > idx + numDigits) {
				arg = arg.substring(0, idx + numDigits + 1);
				result = Double.parseDouble(arg);
			}
		}
		return result;
	}

	public static int getRandomNotZeroInt(int max) {
		int retorno = (int) Math.floor(getRandomDouble() * max);
		if (retorno == 0) {
			return 1;
		} else if (retorno == max) {
			return max - 1;
		} else {
			return retorno;
		}

	}

	public static int getRandomInt(int max) {
		int retorno = (int) Math.floor(getRandomDouble() * max);
		if (retorno == max) {
			return max - 1;
		} else {
			return retorno;
		}

	}

}
