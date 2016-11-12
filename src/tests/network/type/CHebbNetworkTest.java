package tests.network.type;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import network.CLayer;
import network.CNeuron;
import network.CSynapse;
import network.INeuron;
import network.type.CHebbNetwork;

public class CHebbNetworkTest {

	private CHebbNetwork network;
	@Before
	public void setUp() throws Exception {
		network = new CHebbNetwork(0.2);
	}

	@Test
	public void execute() {
		CLayer inputLayer = new CLayer();
		CLayer outputLayer = new CLayer();
		
		INeuron input1 = new CNeuron();
		INeuron input2 = new CNeuron();
		INeuron output = new CNeuron();
		
		CSynapse syn1 = new CSynapse(input1,output,0.4);
		CSynapse syn2 = new CSynapse(input2,output,0.5);

		inputLayer.addNeuron(input1);
		inputLayer.addNeuron(input2);
		outputLayer.addNeuron(output);
		
		network.addLayer(inputLayer);
		network.addLayer(outputLayer);
		
		network.addSynapse(syn1);
		network.addSynapse(syn2);
		
		input1.calculate(0.6);
		input2.calculate(0.7);
		
		network.execute();
		assertEquals(0,output.getValue().compareTo(0.59));
		
		assertEquals(0,syn1.getWeight().compareTo(0.4708));
		System.out.println(syn2.getWeight());
		assertEquals(0,syn2.getWeight().compareTo(0.5826));
	}

}
