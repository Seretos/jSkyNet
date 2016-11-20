package net.sky.network;

import java.util.ArrayList;
import java.util.List;

public class CLayer {
	private List<INeuron> neurons;

	public CLayer() {
		neurons = new ArrayList<INeuron>();
	}

	public int initializeNeuronIds(int id) {
		for (INeuron neuron : neurons) {
			neuron.setId(id);
			id++;
		}
		return id;
	}

	public void addNeuron(INeuron neuron) {
		neurons.add(neuron);
	}

	public INeuron getNeuron(int index) {
		return neurons.get(index);
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
