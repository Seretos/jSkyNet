package tests.network.type.backpropagation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import network.INeuron;
import network.type.backpropagation.CDeltaNeuronBag;

public class CDeltaNeuronBagTest {

	@Test
	public void test() {
		INeuron neuron1 = Mockito.mock(INeuron.class);
		INeuron neuron2 = Mockito.mock(INeuron.class);

		CDeltaNeuronBag bag = new CDeltaNeuronBag();
		
		bag.addDelta(neuron1, 0.1f);
		bag.addDelta(neuron2, 0.2f);
		bag.addDelta(neuron1, 0.3f);
		
		assertEquals(2,bag.getDeltaNeuron().size());
		assertSame(neuron1,bag.getDeltaNeuron().get(0).getNeuron());
		assertSame(neuron2,bag.getDeltaNeuron().get(1).getNeuron());
		assertTrue(bag.getDeltaNeuron().get(0).getDelta() == 0.030000001f);
		assertTrue(bag.getDeltaNeuron().get(1).getDelta() == 0.2f);
	}

}
