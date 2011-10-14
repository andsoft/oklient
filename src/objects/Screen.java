package objects;

import java.util.ArrayList;
import java.util.List;

public class Screen {
		public String title;
		public String id;
		public String hint;
		
		public List<Question> questions;
		public List<String> conditions;
		
		public Screen(){
	    	questions=new ArrayList<Question>();
			conditions=new ArrayList<String>();
	    }
}
