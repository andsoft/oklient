package objects;

import java.util.ArrayList;
import java.util.List;

public class Screen {
		public String title;
		public String id;
		public String hint;
		
		public List<Question> questions;
		public List<String> conditions;
		
		private boolean passed;
		
		public Screen(){
	    	questions=new ArrayList<Question>();
			conditions=new ArrayList<String>();
			
			passed=false;
	    }
		
		public void setPassed(boolean bPassed){
			if(!bPassed){
				for(int i=0;i<questions.size();i++)
				{
					Question q=questions.get(i);
					q.answers.clear();
				}
			}
			passed=bPassed;
		}
		
		public boolean getPassed(){
			return passed;
		}
		
		public boolean checkConditions(Screen scr){
			
			for(int i=0;i<questions.size();i++)
	    	{
	    		Question quest=questions.get(i);
	    		
	    		for(int j=0;j<quest.answers.size();j++)
	    		{
	    			Answer answ=quest.answers.get(j);
	    			
	    			for(int x=0;x<scr.conditions.size();x++)
	    			{
	    				String condition=scr.conditions.get(x);
	    				if(answ.option.equalsIgnoreCase(condition))
	    					return true;
	    			}
	    		}
	    	}    	
			return false;
		}
}
