package test.net.sky.network.type.backpropagation;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import net.sky.network.INeuron;
import net.sky.network.type.backpropagation.CDeltaNeuron;

public class CDeltaNeuronTest {
	@Test
	public void test() {
		INeuron neuron = Mockito.mock(INeuron.class);
		CDeltaNeuron deltaNeuron = new CDeltaNeuron(neuron, 0.1f);

		assertSame(neuron, deltaNeuron.getNeuron());
		assertTrue(0.1f == deltaNeuron.getDelta());

		deltaNeuron.addDelta(0.2f);
		assertTrue(0.020000001f == deltaNeuron.getDelta());
	}

}