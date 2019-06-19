package energyDemandEstimation;

import energyDemandEstimation.data.Data;
import energyDemandEstimation.misc.*;
import energyDemandEstimation.ILS.*;

public class EnergyDemandEstimation {

	private static final int pPerturbation = 20; // Porcentaje máximo que va a perturbar cada parámetro
	private static final double nShares = 50; // Numero de divisiones de los parámetros que realiza la búsqueda local
	private static final int nAttempts = 10; // Número de intentos para buscar una solución mejor

	public static void main(String[] args) {

		// Declarando variables e inicializando objetos
		RandomManager.setSeed(1234);
		Data data = new Data();
		Solution bestSolution;
		ILS iterativeLocalSearch = new ILS();

		// Llamada al Iterative Local Search para crear la solución.
		bestSolution = iterativeLocalSearch.createSolution(pPerturbation, nShares, nAttempts);

		
		
		
		
		
		
		
		
		
	}

}
