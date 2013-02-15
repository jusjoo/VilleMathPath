package edu.vserver.exercises.mathpath;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

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
		
		Button pushThis = new Button("push this");
		
		pushThis.addClickListener(new Button.ClickListener() {	
			private static final long serialVersionUID = 2906960442004272295L;

			public void buttonClick(ClickEvent event) {
				Notification.show("Bravo!");
				score = 1.0;
			}
		});
		
		 this.addComponent(pushThis);
	}

	@Override
	protected double getCorrectness() {
		return score;
	}

}
