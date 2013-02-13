package edu.vserver.exercises.example;

import java.io.Serializable;
import java.util.Random;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import edu.vserver.exercises.example.ExampleQuestionExercise.ExampleQuestion;
import edu.vserver.exercises.helpers.ExerciseExecutionHelper;
import edu.vserver.exercises.model.ExecutionSettings;
import edu.vserver.exercises.model.ExecutionState;
import edu.vserver.exercises.model.ExecutionStateChangeListener;
import edu.vserver.exercises.model.Executor;
import edu.vserver.exercises.model.ExerciseException;
import edu.vserver.exercises.model.ExerciseMaterialManager;
import edu.vserver.exercises.model.ResourceGiver;
import edu.vserver.exercises.model.SubmissionListener;
import edu.vserver.exercises.model.SubmissionType;

public class ExampleExerciseExecutor extends VerticalLayout implements
		Executor<ExampleQuestionExercise, ExampleSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2682119786422750060L;

	private ExampleExerciseView exView;
	private ExampleExerciseState exState;

	private final ExerciseExecutionHelper<ExampleSubmissionInfo> execHelper =

	new ExerciseExecutionHelper<ExampleSubmissionInfo>();

	public ExampleExerciseExecutor() {

	}

	@Override
	public void initialize(ResourceGiver localizer,
			ExampleQuestionExercise exerciseData,
			ExampleSubmissionInfo oldSubm, ExerciseMaterialManager materials,
			ExecutionSettings execSettings) throws ExerciseException {
		exState = new ExampleExerciseState(exerciseData);

		exView = new ExampleExerciseView(materials);

		UI.getCurrent().addStyleName("example-bg");

		ExampleAnswerButtonListener listener = new ExampleAnswerButtonListener(
				exView, exState);

		exView.attachController(listener);

		exView.drawQuestion(exState.nextQuestion());

	}

	@Override
	public void registerSubmitListener(
			SubmissionListener<ExampleSubmissionInfo> submitListener) {
		execHelper.registerSubmitListener(submitListener);
	}

	@Override
	public Layout getView() {
		return exView;
	}

	@Override
	public void shutdown() {
		UI.getCurrent().removeStyleName("example-bg");
	}

	@Override
	public void askReset() {
		exState.reset();
		exView.drawQuestion(exState.nextQuestion());
	}

	@Override
	public void askSubmit(SubmissionType submType) {
		double corr = exState.getCorrectness();

		execHelper.informOnlySubmit(corr, null, submType, null);

	}

	@Override
	public void registerExecutionStateChangeListener(
			ExecutionStateChangeListener execStateListener) {
		execHelper.registerExerciseExecutionStateListener(execStateListener);

	}

	public ExecutionState getCurrentExecutionState() {
		return execHelper.getState();
	}

	private static class ExampleExerciseView extends VerticalLayout {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3208138670473891387L;
		private final Label questionText = new Label();
		private final Embedded questionPict = new Embedded(null);
		private final Button opt1 = new Button();
		private final Button opt2 = new Button();
		private final Random rand = new Random();

		private final ExerciseMaterialManager materials;

		public ExampleExerciseView(ExerciseMaterialManager materials) {

			this.materials = materials;

			setWidth("100%");
			setMargin(true);
			setSpacing(true);
			addComponent(questionText);
			addComponent(questionPict);
			addComponent(opt1);
			addComponent(opt2);
			setComponentAlignment(opt1, Alignment.MIDDLE_CENTER);
			setComponentAlignment(opt2, Alignment.MIDDLE_CENTER);
			questionText.setSizeUndefined();
			setComponentAlignment(questionText, Alignment.MIDDLE_CENTER);
		}

		public void attachController(ExampleAnswerButtonListener controller) {
			opt1.addClickListener(controller);
			opt2.addClickListener(controller);
		}

		public void drawQuestion(ExampleQuestion question) {

			questionText.setValue(question.getText());

			if (question.hasAttachedPicture()) {
				questionPict.setSource(new FileResource(materials.getMaterial(
						question.getQuestionPictureMaterialId()).getFile()));
			} else {
				questionPict.setSource(null);
			}

			if (rand.nextBoolean()) {
				opt1.setCaption(question.getCorr());
				opt2.setCaption(question.getWrong());
			} else {
				opt1.setCaption(question.getWrong());
				opt2.setCaption(question.getCorr());
			}

			opt1.setEnabled(true);
			opt2.setEnabled(true);
		}

		public void lockControls() {
			opt1.setEnabled(false);
			opt2.setEnabled(false);
		}
	}

	private static class ExampleExerciseState implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8519281223528590799L;

		private int currentQuestion = 0;

		private int correctCount = 0;

		private final ExampleQuestionExercise qex;

		public ExampleExerciseState(ExampleQuestionExercise exer) {
			qex = exer;
		}

		public boolean hasNextQuestion() {
			return currentQuestion < qex.getQuestionCount();
		}

		public ExampleQuestion nextQuestion() {
			return qex.getQuestion(currentQuestion++);
		}

		public void tryAnswer(String answer) {
			if (qex.getQuestion(currentQuestion - 1).isCorrect(answer))
				correctCount++;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "SimpleExerciseState [currentQuestion=" + currentQuestion
					+ ", correctCount=" + correctCount + ", qex=" + qex + "]";
		}

		public void reset() {
			currentQuestion = 0;
			correctCount = 0;
		}

		public double getCorrectness() {
			return correctCount / (double) qex.getQuestionCount();
		}

	}

	private static class ExampleAnswerButtonListener implements ClickListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5508691190197789575L;
		private final ExampleExerciseView view;
		private final ExampleExerciseState state;

		public ExampleAnswerButtonListener(ExampleExerciseView view,
				ExampleExerciseState state) {
			this.view = view;
			this.state = state;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			String answer = event.getButton().getCaption();

			state.tryAnswer(answer);

			if (state.hasNextQuestion()) {
				view.drawQuestion(state.nextQuestion());
			} else {
				view.lockControls();
			}

		}

	}

}
