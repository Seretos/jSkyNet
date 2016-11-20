package test.net.sky.network.type;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sky.network.CLayer;
import net.sky.network.CNetwork;
import net.sky.network.CNeuron;
import net.sky.network.CSynapse;
import net.sky.network.INeuron;
import net.sky.network.type.CHebbNetwork;

public class CHebbNetworkTest {
	@Test
	public void execute() {
		CHebbNetwork network = new CHebbNetwork(0.2f);
		CLayer inputLayer = new CLayer();
		CLayer outputLayer = new CLayer();

		INeuron input1 = new CNeuron();
		INeuron input2 = new CNeuron();
		INeuron output = new CNeuron();

		CSynapse syn1 = new CSynapse(input1, output, 0.4f);
		CSynapse syn2 = new CSynapse(input2, output, 0.5f);

		inputLayer.addNeuron(input1);
		inputLayer.addNeuron(input2);
		outputLayer.addNeuron(output);

		network.addLayer(inputLayer);
		network.addLayer(outputLayer);

		network.addSynapse(syn1);
		network.addSynapse(syn2);

		input1.calculate(0.6f);
		input2.calculate(0.7f);

		network.execute();
		assertTrue(0.59000003f == output.getValue());

		assertTrue(0.4708f == syn1.getWeight());
		assertTrue(0.5826f == syn2.getWeight());
	}
}
