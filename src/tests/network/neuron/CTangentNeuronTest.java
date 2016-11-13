package tests.network.neuron;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import network.neuron.CTangentNeuron;

public class CTangentNeuronTest {

	private CTangentNeuron neuron;
	@Before
	public void setUp() throws Exception {
		neuron = new CTangentNeuron();
	}

	@Test
	public void calculate() {
		neuron.calculate(-2.0f);
		assertTrue(-0.9640276f == neuron.getValue());
		
		neuron.calculate(-0.2f);
		assertTrue(-0.19737533f == neuron.getValue());
		
		neuron.calculate(0.0f);
		assertTrue(0.0f == neuron.getValue());
		
		neuron.calculate(0.2f);
		assertTrue(0.19737533f == neuron.getValue());
		
		neuron.calculate(0.7f);
		assertTrue(0.6043678f == neuron.getValue());
		
		neuron.calculate(1.0f);
		assertTrue(0.7615942f == neuron.getValue());
		
		neuron.calculate(20.0f);
		assertTrue(1.0f == neuron.getValue());
	}

}
