package test.java.network;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.network.CTrainingSet;

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
		
		trainingSet.addInput(0.1f).addInput(0.2f).addInput(0.3f).addOutput(0.4f).addOutput(0.5f);
		
		assertEquals(3,trainingSet.getInputs().size());
		assertEquals(2,trainingSet.getOutputs().size());
		
		assertTrue(0.1f == trainingSet.getInputs().get(0));
		assertTrue(0.2f == trainingSet.getInputs().get(1));
		assertTrue(0.3f == trainingSet.getInputs().get(2));
		assertTrue(0.4f == trainingSet.getOutputs().get(0));
		assertTrue(0.5f == trainingSet.getOutputs().get(1));
	}

}
