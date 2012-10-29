package de.hwrberlin.it11.tsp.unittest;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.junit.Test;

import de.hwrberlin.it11.tsp.model.Parameter;

public class ParameterTest extends TestCase {

	public ParameterTest(String pName) {
		super(pName);
	}
	
	public void testParameter() {
	
	Parameter parameter = new Parameter();
	parameter.setAntCount(0);
	assertFalse(parameter.getAntCount()==0);
	parameter.setEvaporationParameter(0);
	assertFalse(parameter.getEvaporationParameter()==0);
	parameter.setInitialPheromonParameter(0);
	assertFalse(parameter.getInitialPheromonParameter()==0);
	parameter.setIterationCount(0);
	assertFalse(parameter.getIterationCount()==0);
	parameter.setLocalInformation(0);
	assertFalse(parameter.getLocalInformation()==0);
	parameter.setMaximumTourLength(0);
	assertFalse(parameter.getMaximumTourLength()==0);
	parameter.setPheromonParameter(0);
	assertFalse(parameter.getPheromonParameter()==0);
	parameter.setPheromonUpdateParameter(0);
	assertFalse(parameter.getPheromonUpdateParameter()==0);
	parameter.setZoomFactor(0);
	assertFalse(parameter.getZoomFactor()==0);
	}	

	public static void main(String[] args) {
		TestRunner.run(ParameterTest.class);
	}

}
