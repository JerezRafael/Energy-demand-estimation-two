package energyDemandEstimation;

import energyDemandEstimation.data.Data;
import energyDemandEstimation.misc.*;
import energyDemandEstimation.ILS.*;

public class EnergyDemandEstimation {
	
	private static final int perturbation = 20;
	private static final int nShares = 10;

	public static void main(String[] args) {
		
		// Declarando variables e inicializando objetos
		RandomManager.setSeed(1234);
		Data data = new Data();
		Solution bestSolution;
		ILS iterativeLocalSearch = new ILS(data);
		
		// Llamada al Iterative Local Search para crear la solución.
		bestSolution = iterativeLocalSearch.createSolution();
		
	}

}
