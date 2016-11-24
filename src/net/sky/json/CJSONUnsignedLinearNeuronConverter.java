package net.sky.json;

import org.json.simple.JSONObject;

import net.sky.network.INeuron;
import net.sky.network.neuron.CLinearNeuron;
import net.sky.network.neuron.CTangentNeuron;
import net.sky.network.neuron.CUnsignedLinearNeuron;

public class CJSONUnsignedLinearNeuronConverter extends CJSONDefaultNeuronConverter implements IJSONNeuronConverter{
	public JSONObject convertNeuron(INeuron neuron) {
		JSONObject jsonNeuron = null;

		if(neuron instanceof CUnsignedLinearNeuron){
			CUnsignedLinearNeuron linNeuron = (CUnsignedLinearNeuron)neuron;
			jsonNeuron = super.convertNeuron(neuron);
			jsonNeuron.put("type", "CUnsignedLinearNeuron");
			jsonNeuron.put("gradient", linNeuron.getGradient());
			jsonNeuron.put("intersection", linNeuron.getIntersection());
		}
		
		return jsonNeuron;
	}
	
	public INeuron convertJSONNeuron(JSONObject jsonNeuron) {
		if(jsonNeuron.get("type") == "CUnsignedLinearNeuron"){
			CUnsignedLinearNeuron neuron = new CUnsignedLinearNeuron();
			this.setNeuronSettings(neuron,jsonNeuron);
			neuron.setGradient((float)jsonNeuron.get("gradient"));
			neuron.setIntersection((float)jsonNeuron.get("intersection"));
			return neuron;
		}
		
		return null;
	}
}
