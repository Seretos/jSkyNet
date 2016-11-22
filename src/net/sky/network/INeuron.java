package net.sky.network;

import java.util.List;

public interface INeuron {
	/**
	 * add an input Synapse to the neuron
	 * the neuron use this synapses to response input values
	 */
	public void addInputSynapse(CSynapse synapse);

	/**
	 * add an output synapse to the neuron
	 * the neuron use this synapses to request the output value
	 */
	public void addOutputSynapse(CSynapse synapse);

	/**
	 * get all input synapses
	 */
	public List<CSynapse> getInputSynapses();

	/**
	 * get all output synapses
	 */
	public List<CSynapse> getOutputSynapses();

	/**
	 * this method set the value
	 * please use the calculate method to set input values
	 */
	public void setValue(float val);

	/**
	 * get the current calculated output value of this neuron
	 */
	public float getValue();

	/**
	 * send the current value into all output synapses
	 */
	public void request();

	/**
	 * response all input values from synapses
	 */
	public void response();

	/**
	 * clear all input synapses of this neuron
	 */
	public void clear();

	/**
	 * run the neuron specific activation function
	 * @param val
	 */
	public void calculate(float val);

	/**
	 * return true, if an threshold was setted for this neuron
	 */
	public boolean isThreshold();

	/**
	 * set the threshold. only inputs that gte then this value will be activated the neuron
	 */
	public void setThreshold(float t);

	/**
	 * remove the threshold for this neuron
	 */
	public void unsetThreshold();
	
	/**
	 * get the current threshold value
	 */
	public float getThreshold();

	/**
	 * automaticly called if the network initializeNeuronIds method was called
	 */
	public void setId(int i);

	/**
	 * get the setted id. available if initializeNeuronIds method in the network was called
	 */
	public int getId();
}
