package energyDemandEstimation;

import energyDemandEstimation.data.Data;
import energyDemandEstimation.misc.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import energyDemandEstimation.ILS.*;
import energyDemandEstimation.PredictionModel.PredictionModel;

public class EnergyDemandEstimation {

	private static final int nExecutions = 100; // Deben cambiarse las formulas del excel
	private static final int nIterations = 1;

	private static final int pPerturbation = 20; // Porcentaje máximo que va a perturbar cada parámetro
	private static final double nShares = 50; // Numero de divisiones de los parámetros que realiza la búsqueda local
	private static final int nAttempts = 10; // Número de intentos para buscar una solución mejor

	public static void main(String[] args) {

		// Declarando variables e inicializando objetos
		RandomManager.setSeed(1234);
		Data data = new Data();
		Solution bestSolution, currentSolution;
		ILS iterativeLocalSearch = new ILS();

		double output;

		int referenceYear = 0;
		StringBuilder sb;
		String sbS;
		PrintWriter pw;
		long startTime, endTime, duration;

		try {
			sb = new StringBuilder();

			for (int y = 0; y < 16; y++) {

				// Cada iteración del for será la ejecución de un año
				switch (y) {
				case 0:
					referenceYear = 1985;
					break;
				case 1:
					referenceYear = 1986;
					break;
				case 2:
					referenceYear = 1988;
					break;
				case 3:
					referenceYear = 1989;
					break;
				case 4:
					referenceYear = 1992;
					break;
				case 5:
					referenceYear = 1993;
					break;
				case 6:
					referenceYear = 1994;
					break;
				case 7:
					referenceYear = 1997;
					break;
				case 8:
					referenceYear = 1998;
					break;
				case 9:
					referenceYear = 1999;
					break;
				case 10:
					referenceYear = 2000;
					break;
				case 11:
					referenceYear = 2002;
					break;
				case 12:
					referenceYear = 2003;
					break;
				case 13:
					referenceYear = 2008;
					break;
				case 14:
					referenceYear = 2010;
					break;
				case 15:
					referenceYear = 2011;
					break;
				}

				System.out.println("Año buscado: " + referenceYear);

				/* CSV */

				// Obtiene nEjecuciones resultados y los exporta a un csv
				for (int n = 0; n < nExecutions; n++) {

					System.out.print(".");

					sb.append(n + 1);
					sb.append(";");

					startTime = System.nanoTime();

					bestSolution = iterativeLocalSearch.createSolution(pPerturbation, nShares, nAttempts);

					for (int i = 0; i < nIterations; i++) {
						
						// Llamada al Iterative Local Search para crear la solución.
						currentSolution = iterativeLocalSearch.createSolution(pPerturbation, nShares, nAttempts);
						
						if (MSE.error(currentSolution) < MSE.error(bestSolution))
							bestSolution = currentSolution;

					}

					endTime = System.nanoTime();
					duration = (endTime - startTime) / 1000000;

					output = PredictionModel.apply(data.getYear(referenceYear), bestSolution);

					sb.append(output);
					sb.append(";");
					sb.append(duration);
					sb.append("\n");
				}

				System.out.println();
			}

			sbS = sb.toString();
			sbS = sbS.replace(".", ",");

			pw = new PrintWriter(new File("export.csv"));
			pw.write(sbS);
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
