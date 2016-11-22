package net.sky.network;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a network without any learning rules
 */
public class CNetwork implements INetwork {
	protected List<CLayer> layers;
	protected List<CSynapse> synapses;
	protected CLayer inputLayer;
	protected CLayer outputLayer;

	public CNetwork() {
		layers = new ArrayList<CLayer>();
		synapses = new ArrayList<CSynapse>();
		inputLayer = null;
		outputLayer = null;
	}

	public void initializeNeuronIds() {
		int id = 0;
		for (CLayer layer : layers) {
			id = layer.initializeNeuronIds(id);
		}
	}

	public void setInputLayer(CLayer layer) {
		addLayer(layer);
		inputLayer = layer;
	}

	public void setOutputLayer(CLayer layer) {
		addLayer(layer);
		outputLayer = layer;
	}

	public void addLayer(CLayer layer) {
		layers.add(layer);
	}

	public CLayer getLayer(int index) {
		return layers.get(index);
	}

	public List<CLayer> getLayers() {
		return layers;
	}

	public CLayer getInputLayer() {
		return inputLayer;
	}

	public CLayer getOutputLayer() {
		return outputLayer;
	}

	public void addSynapse(CSynapse synapse) {
		synapses.add(synapse);
		synapse.getSourceNeuron().addOutputSynapse(synapse);
		synapse.getTargetNeuron().addInputSynapse(synapse);
	}

	public List<CSynapse> getSynapses() {
		return synapses;
	}

	public void execute() {
		for (CLayer layer : layers) {
			layer.clear();
		}
		for (CLayer layer : layers) {
			layer.request();
		}
		for (CLayer layer : layers) {
			layer.response();
		}
	}

	public void run(int steps) {
		for (int i = 0; i < steps; i++) {
			execute();
		}
	}
	
	public void importNetwork(INetwork net){
		layers = net.getLayers();
		inputLayer = net.getInputLayer();
		outputLayer = net.getOutputLayer();
		synapses = net.getSynapses();
	}
}
