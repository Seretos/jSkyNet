package net.sky.json;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.sky.network.CLayer;
import net.sky.network.CSynapse;
import net.sky.network.INetwork;
import net.sky.network.INeuron;

public class CJSONConverter {
	private List<IJSONNeuronConverter> neuronConverters;
	private IJSONNeuronConverter defaultConverter;
	
	public CJSONConverter(IJSONNeuronConverter defConverter) {
		neuronConverters = new ArrayList<IJSONNeuronConverter>();
		defaultConverter = defConverter;
	}
	
	public void addNeuronConverter(IJSONNeuronConverter converter){
		neuronConverters.add(converter);
	}
	
	public void convertJSONObject(JSONObject jsonNetwork,INetwork network){
		List<INeuron> neurons = this.convertJSONNeurons(jsonNetwork, network);
		this.convertJSONLayers(jsonNetwork, network, neurons);
		this.convertJSONSynapses(jsonNetwork, network, neurons);
	}

	private void convertJSONSynapses(JSONObject jsonNetwork, INetwork network, List<INeuron> neurons){
		JSONArray jsonSynapses = (JSONArray)jsonNetwork.get("synapses");
		
		for(Object obj : jsonSynapses){
			JSONObject jsonSynapse = (JSONObject)obj;
			INeuron source = neurons.get((int)jsonSynapse.get("source"));
			INeuron target = neurons.get((int)jsonSynapse.get("target"));
			CSynapse syn = new CSynapse(source,target,(float)jsonSynapse.get("weight"));
			network.addSynapse(syn);
		}
	}
	
	private List<INeuron> convertJSONNeurons(JSONObject jsonNetwork, INetwork network){
		List<INeuron> neurons = new ArrayList<INeuron>();
		JSONArray jsonNeurons = (JSONArray)jsonNetwork.get("neurons");
		
		for(Object obj : jsonNeurons){
			JSONObject jsonNeuron = (JSONObject) obj;
			
			INeuron neuron = null;
			for(IJSONNeuronConverter conv : neuronConverters){
				INeuron current = conv.convertJSONNeuron(jsonNeuron);
				if(current != null){
					neuron = current;
					break;
				}
			}
			
			if(neuron == null){
				neuron = defaultConverter.convertJSONNeuron(jsonNeuron);
			}
			neurons.add(neuron);
		}
		
		return neurons;
	}
	
	private void convertJSONLayers(JSONObject jsonNetwork, INetwork network, List<INeuron> neurons){
		JSONArray layers = (JSONArray)jsonNetwork.get("layers");
		
		for(Object obj : layers){
			JSONObject jsonLayer = (JSONObject)obj;
			JSONArray jsonNeurons = (JSONArray)jsonLayer.get("neurons");
			CLayer layer = new CLayer();
			
			for(Object neuronObj : jsonNeurons){
				layer.addNeuron(neurons.get((int)neuronObj));
			}
			
			if(jsonLayer.containsKey("inputLayer")){
				network.setInputLayer(layer);
			}else if(jsonLayer.containsKey("outputLayer")){
				network.setOutputLayer(layer);
			}else{
				network.addLayer(layer);
			}
		}
	}
	
	public JSONObject convertNetwork(INetwork network){
		JSONObject jsonNetwork = new JSONObject();
		
		network.initializeNeuronIds();
		
		jsonNetwork.put("layers", this.convertLayers(network));
		jsonNetwork.put("neurons", this.convertNeurons(network));
		jsonNetwork.put("synapses", this.convertSynapses(network));
		
		return jsonNetwork;
	}
	
	private JSONArray convertLayers(INetwork network){
		JSONArray jsonLayers = new JSONArray();

		for(int i=0;i<network.getLayers().size();i++){
			CLayer layer = network.getLayer(i);
			JSONObject jsonLayer = new JSONObject();
			JSONArray jsonLayerNeurons = new JSONArray();
			
			if(network.getInputLayer() == layer){
				jsonLayer.put("inputLayer", true);
			}else if(network.getOutputLayer() == layer){
				jsonLayer.put("outputLayer", true);
			}
			
			for(INeuron neuron : layer.getNeurons()){
				jsonLayerNeurons.add(neuron.getId());
			}
			
			jsonLayer.put("index", i);
			jsonLayer.put("neurons", jsonLayerNeurons);
			jsonLayers.add(i, jsonLayer);
		}
		
		return jsonLayers;
	}
	
	private JSONArray convertNeurons(INetwork network){
		JSONArray jsonNeurons = new JSONArray();
		
		for(CLayer layer : network.getLayers()){
			for(INeuron neuron : layer.getNeurons()){
				JSONObject jsonNeuron = null;
				
				for(IJSONNeuronConverter conv : neuronConverters){
					JSONObject current = conv.convertNeuron(neuron);
					if(current != null){
						jsonNeuron = current;
						break;
					}
				}
				
				if(jsonNeuron == null){
					jsonNeuron = defaultConverter.convertNeuron(neuron);
				}
				jsonNeurons.add(neuron.getId(), jsonNeuron);
			}
		}
		
		return jsonNeurons;
	}
	
	private JSONArray convertSynapses(INetwork network){
		JSONArray jsonSynapses = new JSONArray();
		
		for(CSynapse syn : network.getSynapses()){
			JSONObject jsonSynapse = new JSONObject();
			jsonSynapse.put("source", syn.getSourceNeuron().getId());
			jsonSynapse.put("target", syn.getTargetNeuron().getId());
			jsonSynapse.put("weight", syn.getWeight());
			jsonSynapses.add(jsonSynapse);
		}
		
		return jsonSynapses;
	}
}
