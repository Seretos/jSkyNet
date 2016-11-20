package test.net.sky.network.neuron;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sky.network.INeuron;
import net.sky.network.neuron.CBinaryNeuron;

public class CBinaryNeuronTest {
	private INeuron neuron;

	@Before
	public void setUp() throws Exception {
		neuron = new CBinaryNeuron(0.0f);
	}

	@Test
	public void calculate() {
		assertTrue(0.0f == neuron.getThreshold());

		neuron.calculate(-0.1f);
		assertTrue(0.0f == neuron.getValue());

		neuron.calculate(0.1f);
		assertTrue(1.0f == neuron.getValue());

		neuron.setThreshold(0.2f);

		neuron.calculate(0.1f);
		assertTrue(0.0f == neuron.getValue());

		neuron.calculate(0.2f);
		assertTrue(1.0f == neuron.getValue());
	}

}
