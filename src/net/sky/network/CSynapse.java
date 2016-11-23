package net.sky.network;

/**
 * synapse between to neurons
 *
 */
public class CSynapse {
	private INeuron sourceNeuron;
	private INeuron targetNeuron;
	private float weight;
	private float input;

	/**
	 * create a synapse between to neurons
	 * @param source
	 * @param target
	 * @param w
	 */
	public CSynapse(INeuron source, INeuron target, float w) {
		sourceNeuron = source;
		targetNeuron = target;
		weight = w;
		input = 0.0f;
	}

	/**
	 * get the source neuron of this synapse
	 * @return
	 */
	public INeuron getSourceNeuron() {
		return sourceNeuron;
	}

	/**
	 * get the destination neuron of this synapse
	 * @return
	 */
	public INeuron getTargetNeuron() {
		return targetNeuron;
	}

	/**
	 * set the weight of this synapse
	 * @param w
	 */
	public void setWeight(float w) {
		weight = w;
	}

	/**
	 * get the current weight
	 * @return
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * the source neuron set the input value and the target neuron get them
	 * @param i
	 */
	public void setInput(float i) {
		input = i;
	}

	public float getInput() {
		return input;
	}

	/**
	 * calculate the value for the target neuron
	 * @return
	 */
	public float calculate() {
		return input * weight;
	}
}
