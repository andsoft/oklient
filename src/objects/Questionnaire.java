package objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.ElementListener;
import android.sax.RootElement;
import android.util.Xml;


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
	    
	    public boolean parseFile(String filePath) {
	    	FileInputStream is=null;
	    	try {
				is=new FileInputStream(filePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	    	
	    	return parseFile(is);
	    }
	    
	    public boolean parseFile(InputStream is) {

	    	final Questionnaire currentQuestionnaire=this;
	        
	        RootElement root = new RootElement("questionnaire");
	        root.setElementListener(new ElementListener(){
	        	public void start(Attributes attributes){
	        		currentQuestionnaire.logo	= attributes.getValue("logo");
	        		currentQuestionnaire.id	= attributes.getValue("id");
	        		currentQuestionnaire.scheme	= attributes.getValue("scheme");
	        		currentQuestionnaire.welcome_screen	= attributes.getValue("welcome_screen");
	        		currentQuestionnaire.accepts_complaints	= attributes.getValue("accepts_complaints");
	            }
	        	
	        	public void end() {
	                
	            }
	        });
	    
	        
	        Element screen = root.getChild("screen");
	        screen.setElementListener(new ElementListener(){
	        	Screen currentScreen=null;
	        	
	        	public void start(Attributes attributes){
	                currentScreen=new Screen();
	                currentQuestionnaire.addScreen(currentScreen);
	                currentScreen.title	= attributes.getValue("title");
	                currentScreen.id	= attributes.getValue("id");
	                currentScreen.hint	= attributes.getValue("hint");
	            }
	        	
	        	public void end() {
	                //currentQuestionnaire.addScreen(currentScreen);
	                currentScreen=null;
	            }
	        });
	        
	        
	        Element question = screen.getChild("question");
	        question.setElementListener(new ElementListener(){
	        	Question currentQuestion=null;
	        	
	        	public void start(Attributes attributes){
	                currentQuestion=new Question();
	                currentQuestionnaire.addQuestion(currentQuestion);
	                currentQuestion.title= attributes.getValue("title");
	                currentQuestion.id= attributes.getValue("id");
	                currentQuestion.type= attributes.getValue("type");
	                currentQuestion.style= attributes.getValue("style");
	                currentQuestion.range_min= attributes.getValue("range_min");
	                currentQuestion.range_max= attributes.getValue("range_max");
	                currentQuestion.range_step= attributes.getValue("range_step");
	                currentQuestion.range_default= attributes.getValue("range_default");
	                currentQuestion.hint= attributes.getValue("hint");
	                currentQuestion.keyboard= attributes.getValue("keyboard");
	            }
	        	
	        	public void end() {
	        		//currentQuestionnaire.addQuestion(currentQuestion);
	        		currentQuestion=null;
	            }
	        });
	        
	        Element condition = screen.getChild("condition");
	        condition.setElementListener(new ElementListener(){
	        	String currentCondition=null;
	        	
	        	public void start(Attributes attributes){
	                currentCondition=new String();
	                currentCondition= attributes.getValue("option_id");
	                currentQuestionnaire.addCondition(currentCondition);
	            }
	        	
	        	public void end() {
	        		//currentQuestionnaire.addCondition(currentCondition);
	        		currentCondition=null;
	            }
	        });
	        
	        Element option = question.getChild("option");
	        option.setElementListener(new ElementListener(){
	        	Option currentOption=null;
	        	
	        	public void start(Attributes attributes){
	                currentOption=new Option();
	                currentQuestionnaire.addOption(currentOption);
	                currentOption.title= attributes.getValue("title");
	                currentOption.id= attributes.getValue("id");
	                currentOption.meaning= attributes.getValue("meaning");
	            }
	        	
	        	public void end() {
	        		//currentQuestionnaire.addOption(currentOption);
	        		currentOption=null;
	            }
	        });
	        
	        
	        try {
	        	//FileInputStream is=new FileInputStream(filePath);
	            Xml.parse(new InputStreamReader(is), root.getContentHandler());
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return false; // file not found or not parsed
	        }
	        
	    	return true;
	    }
}
