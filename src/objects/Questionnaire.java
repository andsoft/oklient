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
}
