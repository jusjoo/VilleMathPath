package edu.vserver.exercises.template;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import edu.vserver.exercises.helpers.SaveListenerHelper;
import edu.vserver.exercises.helpers.TestingExecutionSettings;
import edu.vserver.exercises.model.Editor;
import edu.vserver.exercises.model.EditorMaterialManager;
import edu.vserver.exercises.model.ExerciseException;
import edu.vserver.exercises.model.ExerciseSaveListener;
import edu.vserver.exercises.model.GeneralExerciseInfoEditor;
import edu.vserver.exercises.model.ResourceGiver;
import edu.vserver.standardutils.StandardUIConstants;

public class TemplateEditor extends VerticalLayout implements
		Editor<TemplateExerciseData>, ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4600841604409240872L;
	private final SaveListenerHelper<TemplateExerciseData> saveListeners =

	new SaveListenerHelper<TemplateExerciseData>();

	private GeneralExerciseInfoEditor exerInfoEditor;

	private Button save;
	private Button test;

	private TextField questionText;

	private ResourceGiver localizer;

	public TemplateEditor() {
	}

	@Override
	public Layout getView() {
		return this;
	}

	@Override
	public void registerSaveListener(
			ExerciseSaveListener<TemplateExerciseData> listener) {
		saveListeners.registerListener(listener);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == save) {
			saveListeners.informListeners(getCurrentExercise());
		} else if (event.getButton() == test) {
			Window testWindow = new Window(
					localizer.getUIText(StandardUIConstants.TEST));

			TemplateExecutor toTest = new TemplateExecutor();

			try {
				toTest.initialize(localizer, getCurrentExercise(), null, null,
						TestingExecutionSettings.INSTANCE);
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
			TemplateExerciseData oldData,
			GeneralExerciseInfoEditor genExerInfoEditor,
			EditorMaterialManager materialManager) {

		this.localizer = localizer;

		exerInfoEditor = genExerInfoEditor;

		doLayout(oldData == null ? "" : oldData.getQuestion());
	}

	private TemplateExerciseData getCurrentExercise() {
		return new TemplateExerciseData(questionText.getValue());
	}

	private void doLayout(String oldQuestion) {

		this.setMargin(true);
		this.setSpacing(true);
		this.setWidth("100%");

		questionText = new TextField(
				localizer.getUIText(TemplateUiConstants.QUESTION_TEXT),
				oldQuestion);

		save = new Button(localizer.getUIText(StandardUIConstants.SAVE));
		save.addClickListener(this);

		this.addComponent(save);

		test = new Button(localizer.getUIText(StandardUIConstants.TEST));
		test.addClickListener(this);

		this.addComponent(test);

		this.addComponent(questionText);

	}

}
