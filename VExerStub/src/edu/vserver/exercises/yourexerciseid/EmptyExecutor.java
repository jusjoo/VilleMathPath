package edu.vserver.exercises.yourexerciseid;

import com.vaadin.ui.Label;

import edu.vserver.exercises.helpers.ExerTypeVoidImplCollection.RealSimpleExerciseExecutor;

public class EmptyExecutor extends RealSimpleExerciseExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 645228793345434162L;

	private double score = 0.0;

	@Override
	protected void doLayout() {
		this.addComponent(new Label("Empty-Executor"));

		// You can uncomment this as your first modification !!!

		// Button pushThis = new Button("push this");
		//
		// pushThis.addClickListener(new Button.ClickListener() {
		//
		// /**
		// *
		// */
		// private static final long serialVersionUID = 2906960442004272295L;
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		// Notification.show("Bravo!");
		// score = 1.0;
		// }
		// });
		//
		// this.addComponent(pushThis);
	}

	@Override
	protected double getCorrectness() {
		return score;
	}

}
