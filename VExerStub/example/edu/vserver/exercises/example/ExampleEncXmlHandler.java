package edu.vserver.exercises.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.vserver.exercises.example.ExampleQuestionExercise.ExampleQuestion;
import edu.vserver.exercises.helpers.XmlEncHelper.BeanExerciseDataWrapper;

public class ExampleEncXmlHandler implements
		BeanExerciseDataWrapper<ExampleQuestionExercise> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2397168823961536031L;

	private List<ExampleBeanQuestion> questions;

	/*
	 * Bean methods
	 */

	/**
	 * @return the questions
	 */
	public List<ExampleBeanQuestion> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(List<ExampleBeanQuestion> questions) {
		this.questions = questions;
	}

	public ExampleEncXmlHandler() {

	}

	/*
	 * Interface methods
	 */

	@Override
	public void loadFromData(ExampleQuestionExercise loadFrom) {
		List<ExampleBeanQuestion> newQuests = new ArrayList<ExampleBeanQuestion>();

		for (int i = 0, n = loadFrom.getQuestionCount(); i < n; i++) {
			ExampleBeanQuestion toAdd = new ExampleBeanQuestion();
			toAdd.loadFrom(loadFrom.getQuestion(i));
			newQuests.add(toAdd);
		}

		this.questions = newQuests;

	}

	@Override
	public ExampleQuestionExercise toExerData() {
		List<ExampleQuestion> newQuests = new ArrayList<ExampleQuestion>();

		for (ExampleBeanQuestion toAdd : questions) {
			newQuests.add(toAdd.toQuestion());
		}

		return new ExampleQuestionExercise(newQuests);
	}

	public static class ExampleBeanQuestion implements Serializable {

		public ExampleQuestion toQuestion() {
			return new ExampleQuestion(text, corr, wrong, hasPicture,
					pictureMaterialId);
		}

		public void loadFrom(ExampleQuestion q) {
			text = q.getText();
			corr = q.getCorr();
			wrong = q.getWrong();
			hasPicture = q.hasAttachedPicture();
			pictureMaterialId = q.getQuestionPictureMaterialId();
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 3476275479527667657L;
		private String text;
		private String corr;
		private String wrong;
		private boolean hasPicture;
		private int pictureMaterialId;

		public ExampleBeanQuestion() {
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @param text
		 *            the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}

		/**
		 * @return the corr
		 */
		public String getCorr() {
			return corr;
		}

		/**
		 * @param corr
		 *            the corr to set
		 */
		public void setCorr(String corr) {
			this.corr = corr;
		}

		/**
		 * @return the wrong
		 */
		public String getWrong() {
			return wrong;
		}

		/**
		 * @param wrong
		 *            the wrong to set
		 */
		public void setWrong(String wrong) {
			this.wrong = wrong;
		}

		/**
		 * @return the hasPicture
		 */
		public boolean isHasPicture() {
			return hasPicture;
		}

		/**
		 * @param hasPicture
		 *            the hasPicture to set
		 */
		public void setHasPicture(boolean hasPicture) {
			this.hasPicture = hasPicture;
		}

		/**
		 * @return the pictureMaterialId
		 */
		public int getPictureMaterialId() {
			return pictureMaterialId;
		}

		/**
		 * @param pictureMaterialId
		 *            the pictureMaterialId to set
		 */
		public void setPictureMaterialId(int pictureMaterialId) {
			this.pictureMaterialId = pictureMaterialId;
		}

	}

}
