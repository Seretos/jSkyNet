package net.sky.network.type;

import net.sky.network.CLayer;
import net.sky.network.CNetwork;
import net.sky.network.CSynapse;
import net.sky.network.INeuron;

public class CHebbNetwork extends CNetwork {
	private float learn;

	public CHebbNetwork(float l) {
		learn = l;
	}

	public void execute() {
		for (CLayer layer : layers) {
			layer.request();
		}
		for (CLayer layer : layers) {
			layer.response();
		}
		recalculate();
		for (CLayer layer : layers) {
			layer.clear();
		}
	}

	private void recalculate() {
		for (CLayer layer : layers) {
			for (INeuron neuron : layer.getNeurons()) {
				for (CSynapse syn : neuron.getInputSynapses()) {
					syn.setWeight(syn.getWeight() + (learn * syn.getInput() * neuron.getValue()));
				}
			}
		}
	}
}
