package edu.vserver.exercises.template;

import edu.vserver.exercises.model.SubmissionInfo;

public class TemplateSubmissionInfo implements SubmissionInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702870727095225372L;

	private final String answer;

	public TemplateSubmissionInfo(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	@Override
	public int getCorrespondingRevision() {
		// TODO Auto-generated method stub
		return 0;
	}

}
