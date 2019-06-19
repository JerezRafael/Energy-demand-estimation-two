package energyDemandEstimation;

import energyDemandEstimation.data.Data;
import energyDemandEstimation.misc.*;
import energyDemandEstimation.ILS.*;

public class EnergyDemandEstimation {

	private static final int pPerturbation = 20; // Porcentaje que se busca perturbar
	private static final int nShares = 10; // Numero de divisiones de los par�metros
	private static final int nAttempts = 10; // N�mero de intentos para buscar una soluci�n mejor

	public static void main(String[] args) {

		// Declarando variables e inicializando objetos
		RandomManager.setSeed(1234);
		Data data = new Data();
		Solution bestSolution;
		ILS iterativeLocalSearch = new ILS();

		// Llamada al Iterative Local Search para crear la soluci�n.
		bestSolution = iterativeLocalSearch.createSolution(pPerturbation, nShares, nAttempts);

	}

}
