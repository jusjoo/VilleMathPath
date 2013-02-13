package edu.vserver.exercises.template;

import edu.vserver.exercises.model.ExerciseData;

public class TemplateExerciseData implements ExerciseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -716445297446246493L;

	private final String question;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionExercise [question=" + question + "]";
	}

	public TemplateExerciseData(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	@Override
	public int getRevision() {
		// TODO Auto-generated method stub
		return 0;
	}

}
