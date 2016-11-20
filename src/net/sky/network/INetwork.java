package net.sky.network;

import java.util.List;

public interface INetwork {
	public void initializeNeuronIds();

	public void setInputLayer(CLayer layer);

	public void setOutputLayer(CLayer layer);

	public void addLayer(CLayer layer);

	public CLayer getLayer(int index);

	public List<CLayer> getLayers();

	public CLayer getInputLayer();

	public CLayer getOutputLayer();

	public void addSynapse(CSynapse synapse);

	public List<CSynapse> getSynapses();

	public void execute();

	public void run(int steps);
}
