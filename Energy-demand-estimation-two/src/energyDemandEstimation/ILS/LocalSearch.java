package energyDemandEstimation.ILS;

import energyDemandEstimation.misc.MSE;
import energyDemandEstimation.misc.RandomManager;
import energyDemandEstimation.misc.Solution;

public class LocalSearch {

	public LocalSearch() {
	}

	// Aplica una búsqueda local a la solución pasada por parámetro y la devuelve
	public Solution improve(Solution solution, double nshares) {

		double[] bestParameters = solution.getParameters();
		double[] currentParameters;
		boolean mejora = true;
		double originalValue;

		// Crea dos arrays con todos los posibles valores que pueden tener los
		// parámetros dependiendo de nShares
		double[] test1 = new double[(int) nshares + 1];
		double[] test2 = new double[(int) nshares + 1];
		double n;
		int aux, index;

		n = 10 / nshares;
		for (int i = 0; i < test1.length; i++) {
			test1[i] = (double) Math.round(((n * (i + 1)) - (5 + 2 * (5 / nshares))) * 100) / 100;
		}

		n = 2 / nshares;
		for (int i = 0; i < test1.length; i++) {
			test2[i] = (double) Math.round(((n * (i + 1)) - (1 + 2 * (1 / nshares))) * 100) / 100;
		}

		// Crea un array con todas las posiciones para aleatorizar los parametros sin
		// tocar el array original
		int[] positions = new int[29];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = i;
		}

		while (mejora) {

			mejora = false;

			// Shuffle manual con el propio RandomManager
			for (int i = positions.length - 1; i > 0; i--) {
				index = RandomManager.getRandom().nextInt(i + 1);
				aux = positions[index];
				positions[index] = positions[i];
				positions[i] = aux;
			}

			// Va recorriendo todas las posiciones aleatoriamente
			for (int i = 0; i < positions.length; i++) {

				currentParameters = bestParameters.clone();

				// Si la posicion es 0, hay que coger otro array distinto de posibles parametros
				if (positions[i] == 0) {

					originalValue = currentParameters[positions[i]];

					for (int j = 0; j < test1.length; j++) {

						// Cambia el parámetro por el siguiente de la lista y prueba si es mejor
						currentParameters[positions[i]] = test1[j];

						// Si es mejor, se empieza a intentar mejorar todo de nuevo
						if (MSE.error(new Solution(currentParameters)) < MSE.error(new Solution(bestParameters))) {

							bestParameters = currentParameters.clone();
							mejora = true;
							break;
						}
					}

					// Si no encuentra ningún valor mejor, ponemos el original
					if (!mejora)
						currentParameters[positions[i]] = originalValue;

				} else {

					originalValue = currentParameters[positions[i]];

					for (int j = 0; j < test2.length; j++) {

						// Cambia el parámetro por el siguiente de la lista y prueba si es mejor
						currentParameters[positions[i]] = test2[j];

						// Si es mejor, se empieza a intentar mejorar todo de nuevo
						if (MSE.error(new Solution(currentParameters)) < MSE.error(new Solution(bestParameters))) {

							bestParameters = currentParameters.clone();
							mejora = true;
							break;
						}
					}

					// Si no encuentra ningún valor mejor, ponemos el original
					if (!mejora)
						currentParameters[positions[i]] = originalValue;
				}
				if (mejora)
					break;
			}
		}

		return new Solution(bestParameters);
	}
}
