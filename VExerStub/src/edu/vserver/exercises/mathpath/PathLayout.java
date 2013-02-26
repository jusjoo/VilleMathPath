package edu.vserver.exercises.mathpath;

import java.util.ArrayList;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;

public class PathLayout extends AbsoluteLayout {

	private static final long serialVersionUID = 1L;
	private ArrayList<Button> currentOptions;
	private Label label;
	
	public PathLayout() {
		super();
		
		currentOptions = new ArrayList<Button>();
		
		this.setWidth("100%");
	    this.setHeight("300px");
	    
	    
	    
	    
	    Button b1 = new Button("9");

	    
	    
	    
	    label = new Label("Temp");
		addComponent(label);
		addComponent(b1, "top:50%; right:50%");
		
		
		
		
		
	}
	
	public void addOption(String string) {
		Button b = new Button(string);
		
		// add a click listener for the new option
		b.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// currently just removes the clicked option
				removeOption(event.getButton());	
			}
		});
		currentOptions.add(b);
		updatePositions();
	}
	
	public void removeOption(Button b){
		removeComponent(b);
		currentOptions.remove(b);
		updatePositions();
	}
	
	
	private void updatePositions() {
		// first remove old buttons, so we can reposition (there might be a better way to do it?)
		for (Button b : currentOptions){
			removeComponent(b);
		}
		
		// calculate the vertical distance between buttons (in css %)
		int heightIncrement = 100 / (currentOptions.size()+1);
		
		
		for (int i=0; i < currentOptions.size(); i++) {
			// format the vertical alignment
			String position = "top:"+ heightIncrement*(i+1) + "%; right:25%";
			
			addComponent(currentOptions.get(i), position);
			
		}		
	}

	public void changeData(String s) {
		label.setValue(s);
	}
	

}
