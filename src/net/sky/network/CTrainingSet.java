package net.sky.network;

import java.util.ArrayList;
import java.util.List;

public class CTrainingSet {
	private List<Float> inputs;
	private List<Float> outputs;

	public CTrainingSet() {
		inputs = new ArrayList<Float>();
		outputs = new ArrayList<Float>();
	}

	public CTrainingSet addInput(float in) {
		inputs.add(in);
		return this;
	}

	public CTrainingSet addOutput(float out) {
		outputs.add(out);
		return this;
	}

	public List<Float> getInputs() {
		return inputs;
	}

	public List<Float> getOutputs() {
		return outputs;
	}
}
