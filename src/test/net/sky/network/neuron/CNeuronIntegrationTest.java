package test.net.sky.network.neuron;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sky.network.CNeuron;
import net.sky.network.CSynapse;
import net.sky.network.INeuron;
import net.sky.network.neuron.CBinaryNeuron;
import net.sky.network.neuron.CLinearNeuron;
import net.sky.network.neuron.CLogisticNeuron;
import net.sky.network.neuron.CNormalizeNeuron;
import net.sky.network.neuron.CTangentNeuron;
import net.sky.network.neuron.CUnsignedLinearNeuron;

public class CNeuronIntegrationTest {

	@Test
	public void neuron_withoutThreshold() {
		INeuron input = new CNeuron();
		INeuron neuron = new CNeuron();

		CSynapse synapse = new CSynapse(input, neuron, 1.0f);
		input.addOutputSynapse(synapse);
		neuron.addInputSynapse(synapse);
		input.calculate(1.0f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 1.0f);

		input.calculate(0.5f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.5f);

		input.calculate(0.49f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.49f);

		input.calculate(-1.0f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == -1.0f);
	}

	@Test
	public void neuron_threshold() {
		INeuron input = new CNeuron();
		INeuron neuron = new CNeuron();

		neuron.setThreshold(0.5f);

		CSynapse synapse = new CSynapse(input, neuron, 1.0f);
		input.addOutputSynapse(synapse);
		neuron.addInputSynapse(synapse);
		input.calculate(1.0f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 1.0f);

		input.calculate(0.5f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.5f);

		input.calculate(0.49f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.0f);
	}

	@Test
	public void binary_threshold() {
		INeuron input = new CNeuron();
		INeuron neuron = new CBinaryNeuron(0.5f);

		CSynapse synapse = new CSynapse(input, neuron, 1.0f);
		input.addOutputSynapse(synapse);
		neuron.addInputSynapse(synapse);
		input.calculate(1.0f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 1.0f);

		input.calculate(0.5f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 1.0f);

		input.calculate(0.49f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.0f);
	}

	@Test
	public void linear_threshold() {
		INeuron input = new CNeuron();
		INeuron neuron = new CLinearNeuron();

		neuron.setThreshold(0.5f);

		CSynapse synapse = new CSynapse(input, neuron, 1.0f);
		input.addOutputSynapse(synapse);
		neuron.addInputSynapse(synapse);
		input.calculate(1.0f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 1.0f);

		input.calculate(0.5f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.5f);

		input.calculate(0.49f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.0f);
	}

	@Test
	public void logistic_threshold() {
		INeuron input = new CNeuron();
		INeuron neuron = new CLogisticNeuron();

		neuron.setThreshold(0.5f);

		CSynapse synapse = new CSynapse(input, neuron, 1.0f);
		input.addOutputSynapse(synapse);
		neuron.addInputSynapse(synapse);
		input.calculate(1.0f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.7310586f);

		input.calculate(0.5f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.62245935f);

		input.calculate(0.49f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.5f);
	}

	@Test
	public void normalize_threshold() {
		INeuron input = new CNeuron();
		INeuron neuron = new CNormalizeNeuron();

		neuron.setThreshold(0.5f);

		CSynapse synapse = new CSynapse(input, neuron, 1.0f);
		input.addOutputSynapse(synapse);
		neuron.addInputSynapse(synapse);
		input.calculate(1.0f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.24197073f);

		input.calculate(0.5f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.35206532f);

		input.calculate(0.49f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.3989423f);
	}

	@Test
	public void tangent_threshold() {
		INeuron input = new CNeuron();
		INeuron neuron = new CTangentNeuron();

		neuron.setThreshold(0.5f);

		CSynapse synapse = new CSynapse(input, neuron, 1.0f);
		input.addOutputSynapse(synapse);
		neuron.addInputSynapse(synapse);
		input.calculate(1.0f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.7615942f);

		input.calculate(0.5f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.46211717f);

		input.calculate(0.49f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.0f);
	}

	@Test
	public void unsignedLinear_threshold() {
		INeuron input = new CNeuron();
		INeuron neuron = new CUnsignedLinearNeuron();

		neuron.setThreshold(0.5f);

		CSynapse synapse = new CSynapse(input, neuron, 1.0f);
		input.addOutputSynapse(synapse);
		neuron.addInputSynapse(synapse);
		input.calculate(1.0f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 1.0f);

		input.calculate(0.5f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.5f);

		input.calculate(0.49f);
		input.request();
		neuron.response();
		assertTrue(neuron.getValue() == 0.0f);
	}
}
