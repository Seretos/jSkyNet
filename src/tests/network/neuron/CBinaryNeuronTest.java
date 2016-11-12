package tests.network.neuron;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import network.INeuron;
import network.neuron.CBinaryNeuron;

public class CBinaryNeuronTest {
	private INeuron neuron;
	
	@Before
	public void setUp() throws Exception {
		neuron = new CBinaryNeuron(0.0);
	}

	@Test
	public void calculate() {
		assertEquals(0,neuron.getThreshold().compareTo(0.0));
		
		neuron.calculate(-0.1);
		assertEquals(0,neuron.getValue().compareTo(0.0));
		
		neuron.calculate(0.1);
		assertEquals(0,neuron.getValue().compareTo(1.0));
		
		neuron.setThreshold(0.2);
		
		neuron.calculate(0.1);
		assertEquals(0,neuron.getValue().compareTo(0.0));
		
		neuron.calculate(0.2);
		assertEquals(0,neuron.getValue().compareTo(1.0));
	}

}
