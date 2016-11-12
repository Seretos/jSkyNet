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
		neuron.calculate(-2.0);
		assertEquals(0,neuron.getValue().compareTo(-0.9640275800758169));
		
		neuron.calculate(-0.2);
		assertEquals(0,neuron.getValue().compareTo(-0.197375320224904));
		
		neuron.calculate(0.0);
		assertEquals(0,neuron.getValue().compareTo(0.0));
		
		neuron.calculate(0.2);
		assertEquals(0,neuron.getValue().compareTo(0.197375320224904));
		
		neuron.calculate(0.7);
		assertEquals(0,neuron.getValue().compareTo(0.6043677771171636));
		
		neuron.calculate(1.0);
		assertEquals(0,neuron.getValue().compareTo(0.7615941559557649));
		
		neuron.calculate(20.0);
		assertEquals(0,neuron.getValue().compareTo(1.0));
	}

}
