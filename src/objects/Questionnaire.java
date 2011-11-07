package objects;

import java.util.ArrayList;
import java.util.List;


public class Questionnaire {
	    public String logo;
	    public String scheme;
	    public String welcome_screen;
	    public String id;
	    public String accepts_complaints;

	    public List<Screen> screens; 
	    
	    private Screen currentScreen=null;
	    private Question currentQuestion=null;
	    private Option currentOption=null;
	    
	    public Questionnaire(){
	    	screens=new ArrayList<Screen>();
	    }
	    
	    public void addScreen(Screen scr){
	    	screens.add(scr);
	    	currentScreen=scr;
	    }
	    
	    public void addQuestion(Question ques){
	    	currentScreen.questions.add(ques);
	    	currentQuestion=ques;
	    }
	    
	    public void addCondition(String cond){
	    	currentScreen.conditions.add(cond);
	    }
	    
	    public void addOption(Option opt){
	    	currentQuestion.options.add(opt);
	    	currentOption=opt;
	    }
	    
	    public int getNextScreen(int cur_scr){
	    	//TODO
	    	//Screen scr=screens.get(cur_scr+1);
	    	
	    	for(int i=cur_scr+1;i<screens.size();i++)
	    	{
	    		Screen scr=screens.get(i);
	    		if(scr.conditions.size()==0) return i;
	    		
	    		boolean passed=false;
	    		for(int j=0;j<=cur_scr;j++)
	    		{
	    			Screen scr1=screens.get(j);
	    			if(scr1.checkConditions(scr)){
	    				passed=true;
	    				break;
	    			}
	    		}
	    		
	    		if(passed)return i;
	    	}    	

	    	return -1;
	    	
	    	//return (cur_scr<screens.size()-1)? cur_scr+1 : -1;
	    }
	    
	    public void initQuestionnaire(){
	    	for(int i=0;i<screens.size();i++)
	    	{
	    		Screen scr=screens.get(i);
	    		scr.setPassed(false);
	    		
	    	}    	
	    }
}
