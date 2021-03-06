package test.net.sky.network;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import net.sky.network.CLayer;
import net.sky.network.INeuron;

public class CLayerTest {
	private CLayer layer;

	@Before
	public void setUp() throws Exception {
		layer = new CLayer();
	}

	@Test
	public void initializeNeuronIds() {
		INeuron neuron1 = Mockito.mock(INeuron.class);
		INeuron neuron2 = Mockito.mock(INeuron.class);

		layer.addNeuron(neuron1);
		layer.addNeuron(neuron2);

		int nextId = layer.initializeNeuronIds(2);

		assertEquals(4, nextId);

		verify(neuron1).setId(Matchers.eq(2));
		verify(neuron2).setId(Matchers.eq(3));
	}

	@Test
	public void getNeurons() {
		INeuron neuron1 = Mockito.mock(INeuron.class);
		INeuron neuron2 = Mockito.mock(INeuron.class);

		layer.addNeuron(neuron1);
		layer.addNeuron(neuron2);

		List<INeuron> neurons = layer.getNeurons();
		assertEquals(2, neurons.size());
		assertSame(neuron1, neurons.get(0));
		assertSame(neuron2, neurons.get(1));

		assertSame(neuron1, layer.getNeuron(0));
		assertSame(neuron2, layer.getNeuron(1));

		// fail("Not yet implemented");
	}

	@Test
	public void request() {
		INeuron neuron1 = Mockito.mock(INeuron.class);
		INeuron neuron2 = Mockito.mock(INeuron.class);

		layer.addNeuron(neuron1);
		layer.addNeuron(neuron2);

		layer.request();

		verify(neuron1).request();
		verify(neuron2).request();
	}

	@Test
	public void response() {
		INeuron neuron1 = Mockito.mock(INeuron.class);
		INeuron neuron2 = Mockito.mock(INeuron.class);

		layer.addNeuron(neuron1);
		layer.addNeuron(neuron2);

		layer.response();

		verify(neuron1).response();
		verify(neuron2).response();
	}
}