package edu.vserver.exercises.stub.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.vserver.exercises.example.ExampleQuestionExercise;
import edu.vserver.exercises.example.ExampleQuestionExercise.ExampleQuestion;
import edu.vserver.exercises.example.ExampleTypeDescriptor;
import edu.vserver.exercises.model.ExerciseException;
import edu.vserver.exercises.model.ExerciseXMLHandler;

public class XmlConversionTest {

	@Test
	public void test() {

		List<ExampleQuestion> testQuestions = new ArrayList<ExampleQuestion>();

		for (int i = 0; i < 10; i++) {
			testQuestions.add(new ExampleQuestion("test" + i, "test" + i,
					"test" + i, false, 0));
		}

		ExampleQuestionExercise testData = new ExampleQuestionExercise(
				testQuestions);

		ExerciseXMLHandler<ExampleQuestionExercise> xmlHandler = ExampleTypeDescriptor.INSTANCE
				.newExerciseXML();

		ExampleQuestionExercise convertedData = null;

		try {
			convertedData = xmlHandler.load(xmlHandler.save(testData));
		} catch (ExerciseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(testData.equals(convertedData));

	}
}
