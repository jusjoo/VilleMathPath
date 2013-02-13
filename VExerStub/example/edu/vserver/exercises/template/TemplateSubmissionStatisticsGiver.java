package edu.vserver.exercises.template;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import edu.vserver.exercises.model.ExerciseException;
import edu.vserver.exercises.model.ResourceGiver;
import edu.vserver.exercises.model.StatisticalSubmissionInfo;
import edu.vserver.exercises.model.SubmissionStatisticsGiver;

public class TemplateSubmissionStatisticsGiver extends VerticalLayout implements
		SubmissionStatisticsGiver<TemplateExerciseData, TemplateSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1410253605264134011L;

	private List<StatisticalSubmissionInfo<TemplateSubmissionInfo>> data;
	private TemplateExerciseData exer;

	@Override
	public void initialize(
			TemplateExerciseData exercise,
			List<StatisticalSubmissionInfo<TemplateSubmissionInfo>> dataObjects,
			ResourceGiver localizer) throws ExerciseException {
		exer = exercise;
		data = dataObjects;

		System.out.println("data: " + data);

		doLayout();
	}

	private Table getAllSubmissionsTable() {
		Table res = new Table("all submission");

		res.addContainerProperty("Done time", Date.class, null);
		res.addContainerProperty("Time on task", Integer.class, null);
		res.addContainerProperty("Correctness", Double.class, null);
		res.addContainerProperty("Answer", String.class, null);

		for (StatisticalSubmissionInfo<TemplateSubmissionInfo> statSubmInf : data) {
			res.addItem(new Object[] { new Date(

			statSubmInf.getDoneTime()),

			statSubmInf.getTimeOnTask(),

			statSubmInf.getEvalution(),

			statSubmInf.getSubmissionData().getAnswer() }, null);
		}

		return res;
	}

	private Table getAnswerLengthFreqTable() {
		Table res = new Table("Answer-length frequencies");

		res.addContainerProperty("Length", Integer.class, null);
		res.addContainerProperty("Frequency", Integer.class, null);

		for (Entry<Integer, Integer> freq : getAnswerLengthFrequencies()
				.entrySet()) {

			System.out.println("Adding to ans-freq table: " + freq.getKey()
					+ freq.getValue());

			res.addItem(new Object[] { freq.getKey(), freq.getValue() }, null);
		}

		return res;
	}

	private Map<Integer, Integer> getAnswerLengthFrequencies() {
		Map<Integer, Integer> res = new HashMap<Integer, Integer>();

		for (StatisticalSubmissionInfo<TemplateSubmissionInfo> statSubmInf : data) {
			int answerLength = statSubmInf.getSubmissionData().getAnswer()
					.length();
			Integer currValue = res.get(answerLength);

			int newValue;
			if (currValue != null) {
				newValue = currValue + 1;
			} else {
				newValue = 1;
			}

			res.put(answerLength, newValue);
		}

		return res;
	}

	private void doLayout() {
		this.setWidth("100%");

		this.addComponent(new Label("Question: " + exer.getQuestion()));
		this.addComponent(getAllSubmissionsTable());
		this.addComponent(getAnswerLengthFreqTable());
	}

	@Override
	public Component getView() {
		return this;
	}

	@Override
	public String exportStatisticsDataAsText() {
		// TODO Auto-generated method stub
		return null;
	}

}
