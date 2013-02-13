package edu.vserver.exercises.stub.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.vserver.exercises.stub.StubUIConstantsFileTester;
import edu.vserver.standardutils.StandardUIConstants;

public class LanguageFileTest {

	@Test
	public void test() {
		List<String> testFor = Arrays.asList("en");
		// should be at least this, but for starters only en...
		// List<String> testFor =Arrays.asList("en", "fi");
		boolean res = false;
		try {
			res = StubUIConstantsFileTester.testLangFiles(
					StandardUIConstants.class,
					getClass().getResource("../resources/lang").getPath(),
					testFor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(res);
	}

}
