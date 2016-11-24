package test.net.sky.network.neuron;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sky.network.neuron.CBiasNeuron;

public class CBiasNeuronTest {
	private CBiasNeuron neuron;

	@Before
	public void setUp() throws Exception {
		neuron = new CBiasNeuron();
	}

	@Test
	public void calculate() {
		neuron.calculate(0.1f);
		assertTrue(neuron.getValue() == 1.0f);
		
		neuron.calculate(0.0f);
		assertTrue(neuron.getValue() == 1.0f);
		
		neuron.calculate(-3.0f);
		assertTrue(neuron.getValue() == 1.0f);
		
		neuron.setThreshold(3.4f);
		
		neuron.calculate(0.1f);
		assertTrue(neuron.getValue() == 3.4f);
		
		neuron.calculate(0.0f);
		assertTrue(neuron.getValue() == 3.4f);
		
		neuron.calculate(-3.0f);
		assertTrue(neuron.getValue() == 3.4f);
	}
}
