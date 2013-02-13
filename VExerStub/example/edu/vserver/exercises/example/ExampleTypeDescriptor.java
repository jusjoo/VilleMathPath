package edu.vserver.exercises.example;

import com.vaadin.server.Resource;

import edu.vserver.exercises.helpers.ExerTypeVoidImplCollection;
import edu.vserver.exercises.helpers.XmlEncHelper;
import edu.vserver.exercises.model.ExerciseTypeDescriptor;
import edu.vserver.exercises.model.ResourceGiver;
import edu.vserver.exercises.model.SubmissionStatisticsGiver;
import edu.vserver.exercises.model.SubmissionVisualizer;
import edu.vserver.standardutils.StandardIcon;

public class ExampleTypeDescriptor implements
		ExerciseTypeDescriptor<ExampleQuestionExercise, ExampleSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5743225101617556960L;

	public static final ExampleTypeDescriptor INSTANCE = new ExampleTypeDescriptor();

	private static final XmlEncHelper<ExampleQuestionExercise> xmlBeanWrapper = new XmlEncHelper<ExampleQuestionExercise>(
			ExampleEncXmlHandler.class);

	private ExampleTypeDescriptor() {

	}

	@Override
	public XmlEncHelper<ExampleQuestionExercise> newExerciseXML() {
		// return SimpleExerciseXMLHandler.INSTANCE;
		return xmlBeanWrapper;
	}

	@Override
	public ExampleExerciseExecutor newExerciseExecutor() {
		return new ExampleExerciseExecutor();
	}

	@Override
	public ExampleEditor newExerciseEditor() {
		return new ExampleEditor();
	}

	@Override
	public Class<ExampleQuestionExercise> getTypeDataClass() {
		return ExampleQuestionExercise.class;
	}

	@Override
	public Class<ExampleSubmissionInfo> getSubDataClass() {
		return ExampleSubmissionInfo.class;
	}

	@Override
	public SubmissionStatisticsGiver<ExampleQuestionExercise, ExampleSubmissionInfo> newStatisticsGiver() {
		return new ExerTypeVoidImplCollection.VoidSubmissionDataStatsGiver<ExampleQuestionExercise, ExampleSubmissionInfo>();
	}

	@Override
	public SubmissionVisualizer<ExampleQuestionExercise, ExampleSubmissionInfo> newSubmissionVisualizer() {
		return new ExerTypeVoidImplCollection.VoidSubmissionVisualizer<ExampleQuestionExercise, ExampleSubmissionInfo>();
	}

	@Override
	public String getTypeName(ResourceGiver localizer) {
		return localizer.getUIText(ExampleUiConstants.NAME);
	}

	@Override
	public Resource getSmallTypeIcon() {
		return StandardIcon.EXERCISE_ICON_SMALL.getIcon();
	}

	@Override
	public String getTypeDescription(ResourceGiver localizer) {
		return "example-description";
	}

	@Override
	public Resource getMediumTypeIcon() {
		return StandardIcon.EXERCISE_ICON_SMALL.getIcon();
	}

	@Override
	public Resource getLargeTypeIcon() {
		return StandardIcon.EXERCISE_ICON_SMALL.getIcon();
	}

}
