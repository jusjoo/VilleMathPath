package edu.vserver.exercises.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import edu.vserver.exercises.example.ExampleQuestionExercise.ExampleQuestion;
import edu.vserver.exercises.helpers.SaveListenerHelper;
import edu.vserver.exercises.helpers.SimpleExerciseMaterialUploader;
import edu.vserver.exercises.helpers.TestingExecutionSettings;
import edu.vserver.exercises.helpers.SimpleExerciseMaterialUploader.ExerciseMaterialUploadSuccessListener;
import edu.vserver.exercises.model.Editor;
import edu.vserver.exercises.model.EditorMaterialManager;
import edu.vserver.exercises.model.ExerciseException;
import edu.vserver.exercises.model.ExerciseMaterial;
import edu.vserver.exercises.model.ExerciseSaveListener;
import edu.vserver.exercises.model.GeneralExerciseInfoEditor;
import edu.vserver.exercises.model.ResourceGiver;
import edu.vserver.standardutils.StandardUIConstants;

public class ExampleEditor extends VerticalLayout implements
		Editor<ExampleQuestionExercise>, ClickListener {

	private static final int MAX_UPLOAD_SIZE = 1024;
	private static final String UPLOAD_MIME_TYPE_FILTER = "^image/.*$";
	/**
	 * 
	 */
	private static final long serialVersionUID = -4600841604409240872L;
	private final SaveListenerHelper<ExampleQuestionExercise> saveListeners =

	new SaveListenerHelper<ExampleQuestionExercise>();

	private ExampleQuestionsEditor qEditor;

	private GeneralExerciseInfoEditor exerInfoEditor;
	private ResourceGiver localizer;
	private EditorMaterialManager matManager;
	private Button save;
	private Button test;

	public ExampleEditor() {

	}

	@Override
	public Layout getView() {
		return this;
	}

	@Override
	public void registerSaveListener(
			ExerciseSaveListener<ExampleQuestionExercise> listener) {
		saveListeners.registerListener(listener);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == save) {
			qEditor.actualizeMaterialChanges();
			saveListeners.informListeners(qEditor.getCurrentStateAsExercise());
		} else if (event.getButton() == test) {
			Window testWindow = new Window(
					localizer.getUIText(StandardUIConstants.TEST));

			ExampleExerciseExecutor toTest = new ExampleExerciseExecutor();

			try {
				toTest.initialize(localizer, qEditor.getCurrentStateAsExercise(),
						null, matManager, TestingExecutionSettings.INSTANCE);
			} catch (ExerciseException e) {
				e.printStackTrace();
				toTest = null;
			}

			testWindow.setModal(true);
			testWindow.setWidth("80%");
			Component cont = localizer.getTestingExerciseView(toTest,
					exerInfoEditor.getExerciseInfo());

			testWindow.setContent(cont);

			UI.getCurrent().addWindow(testWindow);

		}

	}

	@Override
	public void initialize(ResourceGiver localizer,
			ExampleQuestionExercise oldData,
			GeneralExerciseInfoEditor genExerInfoEditor,
			EditorMaterialManager materialManager) {
		if (oldData == null) {
			oldData = new ExampleQuestionExercise(
					Collections.<ExampleQuestion> emptyList());
		}
		this.localizer = localizer;

		exerInfoEditor = genExerInfoEditor;

		this.matManager = materialManager;
		materialManager.initialize();

		qEditor = new ExampleQuestionsEditor(oldData, localizer,
				materialManager);

		doLayout();
	}

	private void doLayout() {

		this.setMargin(true);
		this.setSpacing(true);
		this.setWidth("100%");

		save = new Button(localizer.getUIText(StandardUIConstants.SAVE));
		save.addClickListener(this);

		this.addComponent(save);

		test = new Button(localizer.getUIText(StandardUIConstants.TEST));
		test.addClickListener(this);

		this.addComponent(test);

		this.addComponent(qEditor);

	}

	// public ExampleQuestionExercise saveExercise() {
	// return exerciseUnderEdit.currentStateAsQuestionExercise();
	// }

	private static class ExampleQuestionsEditor extends VerticalLayout {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1610485593251461423L;
		// private final ExampleMutableQuestionExercise exerState;
		private final ResourceGiver localizer;
		private final EditorMaterialManager materialManager;
		private final ExampleQuestionExercise oldExer;
		private final List<QuestionRow> questionEditors = new ArrayList<QuestionRow>();

		public ExampleQuestionsEditor(ExampleQuestionExercise oldExer,
				ResourceGiver localizer, EditorMaterialManager materialManager) {
			this.oldExer = oldExer;
			this.localizer = localizer;
			this.materialManager = materialManager;
			doLayout();
		}

		public ExampleQuestionExercise getCurrentStateAsExercise() {

			List<ExampleQuestion> newQuestions = new ArrayList<ExampleQuestion>();
			for (QuestionRow editor : questionEditors) {
				newQuestions.add(editor.getQuestionEditor()
						.getQuestionFromFields());
			}

			ExampleQuestionExercise newQuestion = new ExampleQuestionExercise(
					newQuestions);

			return newQuestion;
		}

		public void actualizeMaterialChanges() {
			// delete old materials
			for (QuestionRow editor : questionEditors) {
				System.out.println("actualizing-mat-changes");
				editor.getQuestionEditor().actualizeMaterialSave();
			}

		}

		private void doLayout() {
			for (int i = 0, n = oldExer.getQuestionCount(); i < n; i++) {
				addQuestionEditor(oldExer.getQuestion(i));
			}
			Button newButton = new Button("add new");

			newButton.addClickListener(new Button.ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 3359106621789144975L;

				@Override
				public void buttonClick(ClickEvent event) {
					addQuestionEditor(new ExampleQuestion("", "", "", false, -1));
				}
			});

			addComponent(newButton);

		}

		private void addQuestionEditor(ExampleQuestion toQuestion) {
			QuestionRow newRow = new QuestionRow(toQuestion,
					questionEditors.size());
			questionEditors.add(newRow);
			addComponent(newRow);

		}

		private class QuestionRow extends VerticalLayout implements
				ClickListener {

			/**
			 * 
			 */
			private static final long serialVersionUID = -3157229234766799426L;
			private final int index;
			private final Button remove;
			private final ExampleQuestionEditor qEditor;

			public QuestionRow(ExampleQuestion q, int index) {
				this.index = index;
				qEditor = new ExampleQuestionEditor(q, localizer,
						materialManager);
				addComponent(qEditor);
				remove = new Button("remove");
				remove.addClickListener(this);
				addComponent(remove);
			}

			public ExampleQuestionEditor getQuestionEditor() {
				return qEditor;
			}

			@Override
			public void buttonClick(ClickEvent event) {

				if (event.getButton() == remove) {
					questionEditors.remove(index);
					removeComponent(QuestionRow.this);
				}

			}

		}

	}

	private static class ExampleQuestionEditor extends VerticalLayout {

		/**
		 * 
		 */
		private static final long serialVersionUID = 8306954723102500190L;
		private final TextField text = new TextField();
		private final TextField corr = new TextField();
		private final TextField wrong = new TextField();
		private Embedded questionImage;
		private final ResourceGiver localizer;
		private final SimpleExerciseMaterialUploader uploadComp;
		private final ExerciseMaterial originalMaterial;
		private ExerciseMaterial attachedPictureMaterial = null;
		private final EditorMaterialManager matManager;

		public ExampleQuestionEditor(ExampleQuestion oldQuestion,
				ResourceGiver localizer, EditorMaterialManager materialManager) {
			this.localizer = localizer;
			this.matManager = materialManager;
			if (oldQuestion.hasAttachedPicture()) {
				originalMaterial = matManager.getMaterial(oldQuestion
						.getQuestionPictureMaterialId());
			} else {
				originalMaterial = null;
			}
			this.uploadComp = new SimpleExerciseMaterialUploader(
					localizer
							.getUIText(ExampleUiConstants.QUESTION_PICTURE_UPLOAD_CAPTION),
					localizer, materialManager, MAX_UPLOAD_SIZE,
					UPLOAD_MIME_TYPE_FILTER);
			doLayout(oldQuestion);
		}

		private void doLayout(ExampleQuestion oldQuestion) {
			text.setCaption(localizer
					.getUIText(ExampleUiConstants.EDITOR_QUESTION_TEXT_CAPTION));
			corr.setCaption(localizer
					.getUIText(ExampleUiConstants.EDITOR_CORRECT_ANSWER_CAPTION));
			wrong.setCaption(localizer
					.getUIText(ExampleUiConstants.EDITOR_WRONG_ANSWER_CAPTION));

			if (oldQuestion.hasAttachedPicture()) {
				attachedPictureMaterial = matManager.getMaterial(oldQuestion
						.getQuestionPictureMaterialId());
				questionImage = new Embedded("", new FileResource(
						attachedPictureMaterial.getFile()));
				// ,
				// localizer.getVaadinApplication()));

			} else {
				questionImage = new Embedded("", null);
			}
			questionImage.setHeight("64px");

			text.setValue(oldQuestion.getText());
			corr.setValue(oldQuestion.getCorr());
			wrong.setValue(oldQuestion.getWrong());

			uploadComp
					.registerSuccessListener(new ExerciseMaterialUploadSuccessListener() {

						/**
						 * 
						 */
						private static final long serialVersionUID = -3423013083389102328L;

						@Override
						public void materialUploadSucceeded(
								ExerciseMaterial newMaterial, String fileName,
								String mimeType) {
							if (attachedPictureMaterial != null) {
								matManager
										.deleteMaterial(attachedPictureMaterial);
							}
							attachedPictureMaterial = newMaterial;
							System.out.println("Setting source to: "
									+ newMaterial.getFile());

							Embedded newImag = new Embedded(fileName,
									new FileResource(newMaterial.getFile()));
							replaceComponent(questionImage, newImag);
							questionImage = newImag;

						}
					});

			addComponent(text);
			addComponent(questionImage);
			addComponent(uploadComp);
			addComponent(corr);
			addComponent(wrong);

		}

		public void actualizeMaterialSave() {
			System.out.println("actualizing in editor");
			if (attachedPictureMaterial != null) {
				if (originalMaterial != null
						&& originalMaterial.getId() != attachedPictureMaterial
								.getId()) {
					matManager.deleteMaterial(originalMaterial);
					attachedPictureMaterial = matManager
							.saveNewMaterial(attachedPictureMaterial);
				} else if (originalMaterial == null) {
					attachedPictureMaterial = matManager
							.saveNewMaterial(attachedPictureMaterial);
				}
			} else {
				if (originalMaterial != null) {
					matManager.deleteMaterial(originalMaterial);
				}
			}
		}

		public ExampleQuestion getQuestionFromFields() {
			return new ExampleQuestion((String) text.getValue(),
					(String) corr.getValue(), (String) wrong.getValue(),
					attachedPictureMaterial != null,
					(attachedPictureMaterial != null ? attachedPictureMaterial
							.getId() : -1));
		}
	}

	// public static class ExampleMutableQuestionExercise implements
	// Serializable {
	//
	// /**
	// *
	// */
	// private static final long serialVersionUID = -5532637104066450092L;
	// private final List<ExampleQuestion> questions;
	//
	// public ExampleMutableQuestionExercise(
	// ExampleQuestionExercise oldQuestions) {
	// this.questions = new ArrayList<ExampleQuestion>();
	// for (int i = 0, n = oldQuestions.getQuestionCount(); i < n; i++) {
	// questions.add(oldQuestions.getQuestion(i));
	// }
	//
	// }
	//
	// public ExampleQuestionExercise currentStateAsQuestionExercise() {
	// return new ExampleQuestionExercise(questions);
	// }
	//
	// public void overrideQuestion(int index, ExampleQuestion newQuestion) {
	// System.out.println("overriding question");
	// questions.add(index, newQuestion);
	// questions.remove(index + 1);
	// System.out.println(this);
	// }
	//
	// public void removeQuestion(int index) {
	// questions.remove(index);
	// }
	//
	// public void addQuestion(ExampleQuestion newQuestion) {
	// questions.add(newQuestion);
	// }
	//
	// public List<ExampleQuestion> getQuestions() {
	// return questions;
	// }
	//
	// }

}
