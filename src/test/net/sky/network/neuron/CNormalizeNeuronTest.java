package test.net.sky.network.neuron;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sky.network.neuron.CNormalizeNeuron;

public class CNormalizeNeuronTest {

	private CNormalizeNeuron neuron;

	@Before
	public void setUp() throws Exception {
		neuron = new CNormalizeNeuron();
	}

	@Test
	public void calculate() {
		neuron.calculate(-2.0f);
		assertTrue(0.053990968f == neuron.getValue());

		neuron.calculate(-0.2f);
		assertTrue(0.3910427f == neuron.getValue());

		neuron.calculate(0.0f);
		assertTrue(0.3989423f == neuron.getValue());

		neuron.calculate(0.2f);
		assertTrue(0.3910427f == neuron.getValue());

		neuron.calculate(0.7f);
		assertTrue(0.31225395f == neuron.getValue());

		neuron.calculate(1.0f);
		assertTrue(0.24197073f == neuron.getValue());

		neuron.calculate(20.0f);
		assertTrue(0.0f == neuron.getValue());
	}

}
