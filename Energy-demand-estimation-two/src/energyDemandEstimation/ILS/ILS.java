package energyDemandEstimation.ILS;

import energyDemandEstimation.misc.*;

public class ILS {

	public ILS() {
	}

	// Devuelve una soluci�n creada desde cero
	public Solution createSolution(int pPerturbation, int nShares, int nAttempts) {

		Solution bestSolution, currentSolution;
		LocalSearch localSearch = new LocalSearch();
		Perturbation perturbation = new Perturbation();
		int n = 0;

		// Inicializamos la soluci�n, declarando la primera totalmente aleatoria
		bestSolution = initSol();

		// Usamos la b�squeda local para mejorar lo m�ximo posible esa soluci�n
		bestSolution = localSearch.improve(bestSolution, nShares);

		currentSolution = bestSolution;

		while (n < nAttempts) { // Cuando se lleven nAttempts seguidos sin mejorar, se detiene la b�squeda

			// Aplicamos perturbaci�n y b�squeda local a la mejor soluci�n encontrada
			currentSolution = perturbation.perturbate(currentSolution, pPerturbation);
			currentSolution = localSearch.improve(currentSolution, nShares);

			// Si la nueva soluci�n es mejor que la mejor hasta el momento, la guardamos y
			// reiniciamos el contador de intentos, si no, incrementamos el contador
			if (MSE.error(currentSolution) < MSE.error(bestSolution)) {
				bestSolution = currentSolution;
				n = 0;
			} else {
				n++;
			}
		}

		return bestSolution;
	}

	// Crea una soluci�n totalmente aleatoria
	private Solution initSol() {

		double[] parameters = new double[29];

		parameters[0] = (RandomManager.getRandom().nextInt(101) - 50) / 10.0;
		for (int i = 1; i < parameters.length; i++) {
			parameters[i] = (RandomManager.getRandom().nextInt(21) - 10) / 10.0;
		}

		return new Solution(parameters);
	}

}
