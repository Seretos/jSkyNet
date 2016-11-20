package test.net.sky.network;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
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
	public void getNeurons() {
		INeuron neuron1 = Mockito.mock(INeuron.class);
		INeuron neuron2 = Mockito.mock(INeuron.class);

		layer.addNeuron(neuron1);
		layer.addNeuron(neuron2);

		List<INeuron> neurons = layer.getNeurons();
		assertEquals(2, neurons.size());
		assertSame(neuron1, neurons.get(0));
		assertSame(neuron2, neurons.get(1));

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