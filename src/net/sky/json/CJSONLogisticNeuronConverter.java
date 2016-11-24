package net.sky.json;

import org.json.simple.JSONObject;

import net.sky.network.INeuron;
import net.sky.network.neuron.CBinaryNeuron;
import net.sky.network.neuron.CLinearNeuron;
import net.sky.network.neuron.CLogisticNeuron;

public class CJSONLogisticNeuronConverter extends CJSONDefaultNeuronConverter implements IJSONNeuronConverter{
	public JSONObject convertNeuron(INeuron neuron) {
		JSONObject jsonNeuron = null;

		if(neuron instanceof CLogisticNeuron){
			jsonNeuron = super.convertNeuron(neuron);
			jsonNeuron.put("type", "CLogisticNeuron");
		}
		
		return jsonNeuron;
	}
	
	public INeuron convertJSONNeuron(JSONObject jsonNeuron) {
		if(jsonNeuron.get("type") == "CLogisticNeuron"){
			CLogisticNeuron neuron = new CLogisticNeuron();
			this.setNeuronSettings(neuron,jsonNeuron);
			return neuron;
		}
		
		return null;
	}
}
