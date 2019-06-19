package energyDemandEstimation.ILS;

import energyDemandEstimation.misc.*;

public class ILS {

	public ILS() {
	}

	// Devuelve una solución creada desde cero
	public Solution createSolution(int pPerturbation, double nshares, int nAttempts) {

		Solution bestSolution, currentSolution;
		LocalSearch localSearch = new LocalSearch();
		Perturbation perturbation = new Perturbation();
		int n = 0;

		// Inicializa la solución, siendo la primera totalmente aleatoria
		bestSolution = initSol();

		// Usa la búsqueda local para mejorar lo máximo posible esa solución
		bestSolution = localSearch.improve(bestSolution, nshares);

		currentSolution = bestSolution;

		while (n < nAttempts) { // Cuando lleve nAttempts seguidos sin mejorar, detiene la búsqueda

			// Aplica la perturbación y la búsqueda local a la mejor solución encontrada
			currentSolution = perturbation.perturbate(currentSolution, pPerturbation);
			currentSolution = localSearch.improve(currentSolution, nshares);

			// Si la nueva solución es mejor que la mejor hasta el momento, la guarda y
			// reinicia el contador de intentos, si no, incrementa el contador
			if (MSE.error(currentSolution) < MSE.error(bestSolution)) {
				bestSolution = currentSolution;
				n = 0;
			} else {
				n++;
			}
		}

		return bestSolution;
	}

	// Crea una solución totalmente aleatoria
	private Solution initSol() {

		double[] parameters = new double[29];

		parameters[0] = (RandomManager.getRandom().nextInt(101) - 50) / 10.0;
		for (int i = 1; i < parameters.length; i++) {
			parameters[i] = (RandomManager.getRandom().nextInt(21) - 10) / 10.0;
		}

		return new Solution(parameters);
	}

}
