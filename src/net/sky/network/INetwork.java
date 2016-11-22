package net.sky.network;

import java.util.List;

public interface INetwork {
	/**
	 * generate ids for all neurons in the network. only required to save networks
	 */
	public void initializeNeuronIds();

	/**
	 * add an layer as input layer to the network
	 */
	public void setInputLayer(CLayer layer);

	/**
	 * add an layer as output layer to the network
	 */
	public void setOutputLayer(CLayer layer);

	/**
	 * add a hidden layer to the network
	 */
	public void addLayer(CLayer layer);

	/**
	 * get a layer by index
	 */
	public CLayer getLayer(int index);

	/**
	 * get a list of layers
	 */
	public List<CLayer> getLayers();

	/**
	 * get the setted input layer
	 */
	public CLayer getInputLayer();

	/**
	 * get the setted output layer
	 */
	public CLayer getOutputLayer();

	/**
	 * add a synapse to the network and add the synapse to the defined
	 * source- and destination- neuron
	 */
	public void addSynapse(CSynapse synapse);

	/**
	 * get all synapses from the network
	 */
	public List<CSynapse> getSynapses();

	/**
	 * - clear all synapses
	 * - send the current value from all neurons into all output synapses
	 * - response all values into all neurons from input synapses
	 */
	public void execute();

	/**
	 * run the execute x steps
	 */
	public void run(int steps);
	
	/**
	 * convert a network to current
	 */
	public void importNetwork(INetwork net);
}
