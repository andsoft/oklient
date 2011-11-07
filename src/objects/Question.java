package objects;

import java.util.ArrayList;
import java.util.List;

public class Question {
	public String title;
	public String id;
	public String type;
	public String style;
	
	public String range_min;
	public String range_max;
	public String range_step;
	public String range_default;
	public String hint;
	public String keyboard;
	
	public List<Option> options;
	
	public List<Answer> answers;
	
	public Question(){
		options=new ArrayList<Option>();
		answers=new ArrayList<Answer>();
    }
}
