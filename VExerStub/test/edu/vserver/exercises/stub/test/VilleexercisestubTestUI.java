package edu.vserver.exercises.stub.test;

import java.util.ArrayList;
import java.util.List;

import edu.vserver.exercises.example.ExampleTypeDescriptor;
import edu.vserver.exercises.model.ExerciseTypeDescriptor;
import edu.vserver.exercises.stub.AbstractExerStubUI;
import edu.vserver.exercises.template.TemplateTypeDescriptor;
import edu.vserver.exercises.yourexerciseid.EmptyExerDescriptor;

public class VilleexercisestubTestUI extends AbstractExerStubUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3676925860038993322L;

	@Override
	protected List<ExerciseTypeDescriptor<?, ?>> getTypesToLoad() {
		ArrayList<ExerciseTypeDescriptor<?, ?>> typesToTest =

		new ArrayList<ExerciseTypeDescriptor<?, ?>>();

		typesToTest.add(EmptyExerDescriptor.INSTANCE);
		typesToTest.add(TemplateTypeDescriptor.INSTANCE);
		typesToTest.add(ExampleTypeDescriptor.INSTANCE);

		return typesToTest;
	}

	@Override
	protected String getStubResourceBaseDir() {
		/*
		 * At least when using Eclipse and Tomcat, using this value will cause
		 * the actual directories to be created inside to eclipse workspace.
		 * This means that all the resources stored there will be lost when
		 * Tomcat is cleaned. Translation files placed in the source-path are
		 * not lost however, since they will be re-added on build.
		 */
		return getClass().getResource("resources").getPath();
		/*
		 * If the following is uncommented needed directories will be created to
		 * the user's home directory on the first start-up. This will ease
		 * accessing the files directly, when for ecample debugging stored
		 * xml-format.
		 */
		// return System.getProperty("user.home");
	}

}
