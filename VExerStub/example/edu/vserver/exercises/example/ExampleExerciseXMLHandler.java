//package edu.vserver.exercises.example;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactoryConfigurationError;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import edu.vserver.exercises.example.ExampleQuestionExercise.ExampleQuestion;
//import edu.vserver.exercises.helpers.XMLHelper;
//import edu.vserver.exercises.model.ExerciseXMLHandler;
//
//public final class ExampleExerciseXMLHandler implements
//		ExerciseXMLHandler<ExampleQuestionExercise> {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -3150033834959652936L;
//	public static final ExampleExerciseXMLHandler INSTANCE = new ExampleExerciseXMLHandler();
//
//	private ExampleExerciseXMLHandler() {
//
//	}
//
//	@Override
//	public ExampleQuestionExercise load(InputStream inStream) {
//
//		ExampleQuestionExercise res = null;
//
//		try {
//			ArrayList<ExampleQuestion> parsedQuestions = new ArrayList<ExampleQuestion>();
//
//			DocumentBuilder dBuilder = null;
//			Document doc = null;
//			dBuilder = DocumentBuilderFactory.newInstance()
//					.newDocumentBuilder();
//			doc = dBuilder.parse(inStream);
//
//			doc.getDocumentElement().normalize();
//
//			// armInit
//			NodeList questionNodes = doc.getElementsByTagName("question");
//
//			for (int i = 0, n = questionNodes.getLength(); i < n; i++) {
//
//				Node questionNode = questionNodes.item(i);
//
//				if (questionNode.getNodeType() == Node.ELEMENT_NODE) {
//
//					Element questionEl = (Element) questionNode;
//
//					String text = questionEl.getElementsByTagName("text")
//							.item(0).getChildNodes().item(0).getNodeValue();
//					String corr = questionEl.getElementsByTagName("corr")
//							.item(0).getChildNodes().item(0).getNodeValue();
//					String wrong = questionEl.getElementsByTagName("wrong")
//							.item(0).getChildNodes().item(0).getNodeValue();
//
//					boolean hasPicture = Boolean.parseBoolean(questionEl
//							.getElementsByTagName("hasPicture").item(0)
//							.getChildNodes().item(0).getNodeValue());
//
//					int picId = Integer.parseInt(questionEl
//							.getElementsByTagName("pictureId").item(0)
//							.getChildNodes().item(0).getNodeValue());
//
//					parsedQuestions.add(new ExampleQuestion(text, corr, wrong,
//							hasPicture, picId));
//
//				}
//			}
//
//			res = new ExampleQuestionExercise(parsedQuestions);
//
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		} catch (SAXException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return res;
//	}
//
//	@Override
//	public InputStream save(ExampleQuestionExercise toWrite) {
//
//		InputStream res = null;
//		try {
//			Document doc = XMLHelper.createEmptyDocument();
//
//			Element topEl = doc.createElement("exercise");
//
//			for (int i = 0; i < toWrite.getQuestionCount(); i++) {
//
//				Element question = doc.createElement("question");
//				topEl.appendChild(question);
//
//				Element text = doc.createElement("text");
//				text.setTextContent(toWrite.getQuestion(i).getText());
//				question.appendChild(text);
//
//				Element corr = doc.createElement("corr");
//				corr.setTextContent(toWrite.getQuestion(i).getCorr());
//				question.appendChild(corr);
//
//				Element wrong = doc.createElement("wrong");
//				wrong.setTextContent(toWrite.getQuestion(i).getWrong());
//				question.appendChild(wrong);
//
//				Element hasPic = doc.createElement("hasPicture");
//				hasPic.setTextContent(""
//						+ toWrite.getQuestion(i).hasAttachedPicture());
//				question.appendChild(hasPic);
//
//				Element pictureId = doc.createElement("pictureId");
//				pictureId
//						.setTextContent(""
//								+ toWrite.getQuestion(i)
//										.getQuestionPictureMaterialId());
//				question.appendChild(pictureId);
//
//			}
//
//			doc.appendChild(topEl);
//
//			res = XMLHelper.xmlToInputStream(doc);
//
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		} catch (TransformerConfigurationException e) {
//			e.printStackTrace();
//		} catch (TransformerException e) {
//			e.printStackTrace();
//		} catch (TransformerFactoryConfigurationError e) {
//			e.printStackTrace();
//		}
//		return res;
//	}
//
// }