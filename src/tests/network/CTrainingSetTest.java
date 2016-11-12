package tests.network;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import network.CTrainingSet;

public class CTrainingSetTest {
	private CTrainingSet trainingSet;
	
	@Before
	public void setUp() throws Exception {
		trainingSet = new CTrainingSet();
	}

	@Test
	public void test() {
		assertEquals(0,trainingSet.getInputs().size());
		assertEquals(0,trainingSet.getOutputs().size());
		
		trainingSet.addInput(0.1).addInput(0.2).addInput(0.3).addOutput(0.4).addOutput(0.5);
		
		assertEquals(3,trainingSet.getInputs().size());
		assertEquals(2,trainingSet.getOutputs().size());
		
		assertEquals(0,trainingSet.getInputs().get(0).compareTo(0.1));
		assertEquals(0,trainingSet.getInputs().get(1).compareTo(0.2));
		assertEquals(0,trainingSet.getInputs().get(2).compareTo(0.3));
		assertEquals(0,trainingSet.getOutputs().get(0).compareTo(0.4));
		assertEquals(0,trainingSet.getOutputs().get(1).compareTo(0.5));
	}

}
