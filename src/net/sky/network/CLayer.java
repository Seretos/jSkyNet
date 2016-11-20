package net.sky.network;

import java.util.ArrayList;
import java.util.List;

public class CLayer {
	private List<INeuron> neurons;

	public CLayer() {
		neurons = new ArrayList<INeuron>();
	}

	public void addNeuron(INeuron neuron) {
		neurons.add(neuron);
	}

	public List<INeuron> getNeurons() {
		return neurons;
	}

	public void request() {
		for (INeuron neuron : neurons) {
			neuron.request();
		}
	}

	public void response() {
		for (INeuron neuron : neurons) {
			neuron.response();
		}
	}

	public void clear() {
		for (INeuron neuron : neurons) {
			neuron.clear();
		}
	}
}
