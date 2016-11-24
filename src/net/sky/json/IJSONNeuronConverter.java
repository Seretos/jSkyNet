package net.sky.json;

import org.json.simple.JSONObject;

import net.sky.network.INeuron;

public interface IJSONNeuronConverter {
	public JSONObject convertNeuron(INeuron neuron);
	
	public INeuron convertJSONNeuron(JSONObject jsonNeuron);
}
