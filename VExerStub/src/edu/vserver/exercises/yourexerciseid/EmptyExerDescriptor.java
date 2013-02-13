package edu.vserver.exercises.yourexerciseid;

import edu.vserver.exercises.helpers.ExerTypeVoidImplCollection.NoDataExerciseDescriptor;
import edu.vserver.exercises.helpers.ExerTypeVoidImplCollection.VoidExerciseData;
import edu.vserver.exercises.helpers.ExerTypeVoidImplCollection.VoidSubmissionData;
import edu.vserver.exercises.model.Executor;
import edu.vserver.exercises.model.ResourceGiver;

public class EmptyExerDescriptor extends NoDataExerciseDescriptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4587736884427608942L;

	public static final EmptyExerDescriptor INSTANCE = new EmptyExerDescriptor();

	@Override
	public Executor<VoidExerciseData, VoidSubmissionData> newExerciseExecutor() {
		return new EmptyExecutor();
	}

	@Override
	public String getTypeName(ResourceGiver localizer) {
		return "EmptyTestExercise";
	}

}
