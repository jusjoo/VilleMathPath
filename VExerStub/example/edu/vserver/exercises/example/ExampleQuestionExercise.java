package edu.vserver.exercises.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.vserver.exercises.model.ExerciseData;

public class ExampleQuestionExercise implements ExerciseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -716445297446246493L;

	private final List<ExampleQuestion> questions;

	public ExampleQuestionExercise(List<ExampleQuestion> questions) {
		this.questions = new ArrayList<ExampleQuestion>(questions);
	}

	public ExampleQuestion getQuestion(int index) {
		return questions.get(index);
	}

	public int getQuestionCount() {
		return questions.size();
	}

	@Override
	public int getRevision() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionExercise [questions=" + questions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((questions == null) ? 0 : questions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExampleQuestionExercise other = (ExampleQuestionExercise) obj;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		return true;
	}

	public static class ExampleQuestion implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1995444910821408394L;

		private final String text;
		private final String corr;
		private final String wrong;
		private final boolean hasAttachedPicture;
		private final int questionPictureMaterialId;

		public ExampleQuestion(String text, String corr, String wrong,
				boolean hasAttachedPicture, int questionPictureMaterialId) {
			this.text = text;
			this.corr = corr;
			this.wrong = wrong;
			this.hasAttachedPicture = hasAttachedPicture;
			this.questionPictureMaterialId = questionPictureMaterialId;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @return the corr
		 */
		public String getCorr() {
			return corr;
		}

		/**
		 * @return the wrong
		 */
		public String getWrong() {
			return wrong;
		}

		public boolean hasAttachedPicture() {
			return hasAttachedPicture;
		}

		public int getQuestionPictureMaterialId() {
			return questionPictureMaterialId;
		}

		public boolean isCorrect(String answer) {
			return corr.equals(answer);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Question [text=" + text + ", corr=" + corr + ", wrong="
					+ wrong + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((corr == null) ? 0 : corr.hashCode());
			result = prime * result + (hasAttachedPicture ? 1231 : 1237);
			result = prime * result + questionPictureMaterialId;
			result = prime * result + ((text == null) ? 0 : text.hashCode());
			result = prime * result + ((wrong == null) ? 0 : wrong.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ExampleQuestion other = (ExampleQuestion) obj;
			if (corr == null) {
				if (other.corr != null)
					return false;
			} else if (!corr.equals(other.corr))
				return false;
			if (hasAttachedPicture != other.hasAttachedPicture)
				return false;
			if (questionPictureMaterialId != other.questionPictureMaterialId)
				return false;
			if (text == null) {
				if (other.text != null)
					return false;
			} else if (!text.equals(other.text))
				return false;
			if (wrong == null) {
				if (other.wrong != null)
					return false;
			} else if (!wrong.equals(other.wrong))
				return false;
			return true;
		}

	}

}
