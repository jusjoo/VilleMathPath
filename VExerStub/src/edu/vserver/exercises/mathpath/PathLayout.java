package edu.vserver.exercises.mathpath;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;

public class PathLayout extends AbsoluteLayout {

	private static final long serialVersionUID = 1L;

	private Label label;
	
	public PathLayout() {
		super();
		
		this.setWidth("100%");
	    this.setHeight("300px");
	    
	    
	    label = new Label("Temp");
		addComponent(label);
		
	}
	
	public void changeData(String s) {
		label.setValue(s);
	}
	

}
