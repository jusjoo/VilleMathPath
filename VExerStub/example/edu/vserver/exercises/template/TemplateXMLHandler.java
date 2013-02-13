package edu.vserver.exercises.template;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import edu.vserver.exercises.helpers.XMLHelper;
import edu.vserver.exercises.model.ExerciseException;
import edu.vserver.exercises.model.ExerciseXMLHandler;

public final class TemplateXMLHandler implements
		ExerciseXMLHandler<TemplateExerciseData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3150033834959652936L;
	public static final TemplateXMLHandler INSTANCE = new TemplateXMLHandler();

	private TemplateXMLHandler() {

	}

	@Override
	public TemplateExerciseData load(InputStream inStream)
			throws ExerciseException {

		TemplateExerciseData res = null;

		try {

			Document doc = XMLHelper.parseFromStream(inStream);

			doc.getDocumentElement().normalize();

			String parsedQuestion = doc.getDocumentElement().getAttribute(
					"question");

			res = new TemplateExerciseData(parsedQuestion);

		} catch (ParserConfigurationException e) {
			throw new ExerciseException(
					ExerciseException.ErrorType.XML_LOAD_ERROR, e);
		} catch (SAXException e) {
			throw new ExerciseException(
					ExerciseException.ErrorType.XML_LOAD_ERROR, e);
		} catch (IOException e) {
			throw new ExerciseException(
					ExerciseException.ErrorType.XML_LOAD_ERROR, e);
		}

		return res;
	}

	@Override
	public InputStream save(TemplateExerciseData toWrite)
			throws ExerciseException {

		InputStream res = null;
		try {
			Document doc = XMLHelper.createEmptyDocument();

			Element root = doc.createElement("template-exercise");

			doc.appendChild(root);

			root.setAttribute("question", toWrite.getQuestion());

			res = XMLHelper.xmlToInputStream(doc);

		} catch (ParserConfigurationException e) {
			throw new ExerciseException(
					ExerciseException.ErrorType.XML_WRITE_ERROR, e);
		} catch (TransformerConfigurationException e) {
			throw new ExerciseException(
					ExerciseException.ErrorType.XML_WRITE_ERROR, e);
		} catch (TransformerException e) {
			throw new ExerciseException(
					ExerciseException.ErrorType.XML_WRITE_ERROR, e);
		} catch (TransformerFactoryConfigurationError e) {
			throw new ExerciseException(
					ExerciseException.ErrorType.XML_WRITE_ERROR, e);
		}
		return res;
	}

}