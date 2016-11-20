package net.sky.network;

public class CSynapse {
	private INeuron sourceNeuron;
	private INeuron targetNeuron;
	private float weight;
	private float input;

	public CSynapse(INeuron source, INeuron target, float w) {
		sourceNeuron = source;
		targetNeuron = target;
		weight = w;
		input = 0.0f;
	}

	public INeuron getSourceNeuron() {
		return sourceNeuron;
	}

	public INeuron getTargetNeuron() {
		return targetNeuron;
	}

	public void setWeight(float w) {
		weight = w;
	}

	public float getWeight() {
		return weight;
	}

	public void setInput(float i) {
		input = i;
	}

	public float getInput() {
		return input;
	}

	public float calculate() {
		return input * weight;
	}
}
