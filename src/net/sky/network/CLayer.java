package net.sky.network;

import java.util.ArrayList;
import java.util.List;

/**
 * a layer for the network
 *
 */
public class CLayer {
	private List<INeuron> neurons;

	public CLayer() {
		neurons = new ArrayList<INeuron>();
	}

	/**
	 * set a unique id for all neurons
	 * @param id
	 * @return
	 */
	public int initializeNeuronIds(int id) {
		for (INeuron neuron : neurons) {
			neuron.setId(id);
			id++;
		}
		return id;
	}

	/**
	 * add a neuron to the network
	 * @param neuron
	 */
	public void addNeuron(INeuron neuron) {
		neurons.add(neuron);
	}

	/**
	 * get a neuron by index
	 * @param index
	 * @return
	 */
	public INeuron getNeuron(int index) {
		return neurons.get(index);
	}

	/**
	 * get all neurons of this layer
	 * @return
	 */
	public List<INeuron> getNeurons() {
		return neurons;
	}

	/**
	 * call the request method for every neuron
	 */
	public void request() {
		for (INeuron neuron : neurons) {
			neuron.request();
		}
	}

	/**
	 * call the response method for every neuron
	 */
	public void response() {
		for (INeuron neuron : neurons) {
			neuron.response();
		}
	}

	/**
	 * clear all input synapses of the layered neurons
	 */
	public void clear() {
		for (INeuron neuron : neurons) {
			neuron.clear();
		}
	}
}
