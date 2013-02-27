package edu.vserver.exercises.mathpath;

import com.vaadin.shared.ui.AlignmentInfo.Bits;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import edu.vserver.exercises.helpers.ExerTypeVoidImplCollection.RealSimpleExerciseExecutor;

public class EmptyExecutor extends RealSimpleExerciseExecutor {

    /**
	 * 
	 */
    private static final long serialVersionUID = 645228793345434162L;

    private double score = 0.0;
    private PathModel path;
    private PathLayout pathLayout;
    private ArithmeticsInterface calc;

    @Override
    protected void doLayout() {

        path = new PathModel(5, 15, 5);
        calc = new AdditionGenerator();

        VerticalLayout verticalLayout = new VerticalLayout();

        Button pushThis = new Button("push this");
        pathLayout = new PathLayout();
        Label label2 = new Label("t�h�n tulee alalaita");

        pushThis.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 2906960442004272295L;

            @Override
            public void buttonClick(ClickEvent event) {

                StringBuffer result = new StringBuffer("Correct answers: ");

                for (int i = 0; i < path.getLength(); i++) {
                    result.append(calc.calculate(path.getNode(i)) + " = "
                            + path.getNode(i) + "; ");
                }

                pathLayout.changeData(result.toString());
                pathLayout.addOption(calc.calculate(path.getNode(1)));
                score = 1.0;
            }
        });

        verticalLayout.setWidth("90%");

        verticalLayout.addComponent(pushThis);
        verticalLayout.addComponent(pathLayout);
        verticalLayout.addComponent(label2);

        this.addComponent(verticalLayout);

        // this centers the whole thing
        setComponentAlignment(verticalLayout, new Alignment(
                Bits.ALIGNMENT_VERTICAL_CENTER
                        | Bits.ALIGNMENT_HORIZONTAL_CENTER));
    }

    @Override
    protected double getCorrectness() {
        return score;
    }

}
