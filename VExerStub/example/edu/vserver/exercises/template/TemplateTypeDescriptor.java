package edu.vserver.exercises.template;

import com.vaadin.server.Resource;

import edu.vserver.exercises.model.ExerciseTypeDescriptor;
import edu.vserver.exercises.model.ResourceGiver;
import edu.vserver.exercises.model.SubmissionStatisticsGiver;
import edu.vserver.exercises.model.SubmissionVisualizer;
import edu.vserver.standardutils.StandardIcon;

public class TemplateTypeDescriptor implements
		ExerciseTypeDescriptor<TemplateExerciseData, TemplateSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5743225101617556960L;

	public static final TemplateTypeDescriptor INSTANCE = new TemplateTypeDescriptor();

	private TemplateTypeDescriptor() {

	}

	@Override
	public TemplateXMLHandler newExerciseXML() {
		return TemplateXMLHandler.INSTANCE;
	}

	@Override
	public TemplateExecutor newExerciseExecutor() {
		return new TemplateExecutor();
	}

	@Override
	public TemplateEditor newExerciseEditor() {
		return new TemplateEditor();
	}

	@Override
	public Class<TemplateExerciseData> getTypeDataClass() {
		return TemplateExerciseData.class;
	}

	@Override
	public Class<TemplateSubmissionInfo> getSubDataClass() {
		return TemplateSubmissionInfo.class;
	}

	@Override
	public SubmissionStatisticsGiver<TemplateExerciseData, TemplateSubmissionInfo> newStatisticsGiver() {
		return new TemplateSubmissionStatisticsGiver();
	}

	@Override
	public SubmissionVisualizer<TemplateExerciseData, TemplateSubmissionInfo> newSubmissionVisualizer() {
		return new TemplateSubmissionViewer();
	}

	@Override
	public String getTypeName(ResourceGiver localizer) {
		return localizer.getUIText(TemplateUiConstants.NAME);
	}

	@Override
	public String getTypeDescription(ResourceGiver localizer) {
		return "Template-desciption";
	}

	@Override
	public Resource getSmallTypeIcon() {
		return StandardIcon.EXERCISE_ICON_SMALL.getIcon();
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
