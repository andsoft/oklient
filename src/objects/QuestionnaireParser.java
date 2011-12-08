package objects;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;
import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.ElementListener;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

public class QuestionnaireParser {
    final URL feedUrl;

    public QuestionnaireParser(String feedUrl){
        try {
            this.feedUrl = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    protected InputStream getInputStream() {
/*        try {
            return feedUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    	return null;
    }

    public Questionnaire parse(String xml) {
    	final Questionnaire currentQuestionnaire=new Questionnaire();
        
    	
    	
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
            Xml.parse(/*this.getInputStream()*/xml, /*Xml.Encoding.UTF_8,*/ root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return currentQuestionnaire;
    }
    
    public Questionnaire parse(InputStream xml) {
    	

    	String response="";
    	BufferedReader buffer = new BufferedReader(
				new InputStreamReader(xml));
		String s = "";
		try {
			while ((s = buffer.readLine()) != null) {
				response += s;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.parse(response);
		
		/*
    	final Questionnaire currentQuestionnaire=new Questionnaire();
        
    	
    	
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
            Xml.parse(xml, Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return currentQuestionnaire;*/
    }
}