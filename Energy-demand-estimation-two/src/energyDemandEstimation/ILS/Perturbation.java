package energyDemandEstimation.ILS;

import energyDemandEstimation.misc.RandomManager;
import energyDemandEstimation.misc.Solution;

public class Perturbation {

	public Perturbation() {
	}

	// Aplica una perturbación a la solución pasada por parámetro y la devuelve
	public Solution perturbate(Solution solution, int pPerturbation) {

		double[] parameters = solution.getParameters();

		// Suma o resta el pPerturbation% a cada parámetro, incluido epsilon
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = parameters[i]
					* (((RandomManager.getRandom().nextInt(pPerturbation * 2) - pPerturbation) / 100.0) + 1);
		}

		return new Solution(parameters);
	}
}
