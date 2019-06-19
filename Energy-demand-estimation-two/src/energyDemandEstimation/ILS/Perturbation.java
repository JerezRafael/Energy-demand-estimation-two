package energyDemandEstimation.ILS;

import energyDemandEstimation.misc.RandomManager;
import energyDemandEstimation.misc.Solution;

public class Perturbation {

	public Perturbation() {
	}

	// Aplica una perturbaci�n a la soluci�n pasada por par�metro y la devuelve
	public Solution perturbate(Solution solution, int pPerturbation) {

		double[] parameters = solution.getParameters();

		// Suma o resta el pPerturbation% a cada par�metro, incluido epsilon
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = parameters[i]
					* (((RandomManager.getRandom().nextInt(pPerturbation * 2) - pPerturbation) / 100.0) + 1);
		}

		return new Solution(parameters);
	}
}
