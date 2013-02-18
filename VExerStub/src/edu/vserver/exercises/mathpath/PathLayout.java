package edu.vserver.exercises.mathpath;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

public class PathLayout extends AbsoluteLayout {

	private static final long serialVersionUID = 1L;

	private Label label;
	
	public PathLayout() {
		super();
		
		this.setWidth("100%");
	    this.setHeight("300px");
	    
	    
	    Button b1 = new Button("9");
	    Button b2 = new Button("2 + 2");
	    Button b3 = new Button("3 + 3");
	    Button b4 = new Button("4 + 4");
	    
	    
	    
	    label = new Label("Temp");
		addComponent(label);
		addComponent(b1, "top:50%; right:50%");
		addComponent(b2, "top:25%; right:25%");
		addComponent(b3, "top:75%; right:25%");
		addComponent(b4, "top:50%; right:25%");
		
		
		
		
	}
	
	public void changeData(String s) {
		label.setValue(s);
	}
	

}
