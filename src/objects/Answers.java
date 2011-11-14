package objects;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class Answers {
	 public List<Survey> surveis; 
	 
	 public Answers(){
		 surveis=new ArrayList<Survey>();
	 }
	 
	 public String getXml(){
		 XmlSerializer serializer = Xml.newSerializer();
		 StringWriter writer = new StringWriter();
		 try {
			 serializer.setOutput(writer);
			 serializer.startDocument("UTF-8", true);
			 serializer.startTag("", "answers");
			 //serializer.attribute("", "number", String.valueOf(messages.size()));
			 for (Survey surv: surveis){
				 serializer.startTag("", "survey");
				 serializer.attribute("", "questionnaire", surv.questionnaire);
				 serializer.attribute("", "created_at", surv.created_at);

				 for (Answer answ: surv.answers){
					 serializer.startTag("", "answer");
					 serializer.attribute("", "question", answ.question);
					 serializer.attribute("", "created_at", answ.created_at);
					 // todo
					 if(answ.option!=null)serializer.attribute("", "option", answ.option);
					 if(answ.value!=null)serializer.attribute("", "value", answ.value);
					 serializer.endTag("", "answer");
				 }
				 serializer.endTag("", "survey");
			 }
			 serializer.endTag("", "answers");
			 serializer.endDocument();
			 return writer.toString();
		 } catch (Exception e) {
			 throw new RuntimeException(e);
		 } 

	 }
	 
	 public String getComplaintXml(){
		 XmlSerializer serializer = Xml.newSerializer();
		 StringWriter writer = new StringWriter();
		 try {
			 serializer.setOutput(writer);
			 serializer.startDocument("UTF-8", true);
			 serializer.startTag("", "answers");
			 //serializer.attribute("", "number", String.valueOf(messages.size()));
			 for (Survey surv: surveis){
				 serializer.startTag("", "survey");
				 serializer.attribute("", "questionnaire", surv.questionnaire);
				 serializer.attribute("", "created_at", surv.created_at);

				 //for (Answer answ: surv.answers){
					 serializer.startTag("", "complaint");
					 //serializer.attribute("", "question", answ.question);
					 serializer.attribute("", "created_at", surv.complaint.created_at);
					 
					 // todo
					 serializer.startTag("", "title");
					 if(surv.complaint.title!=null)serializer.text(surv.complaint.title);
					 serializer.endTag("", "title");
					 serializer.startTag("", "body");
					 if(surv.complaint.body!=null)serializer.text(surv.complaint.body);
					 serializer.endTag("", "body");
					 serializer.startTag("", "name");
					 if(surv.complaint.name!=null)serializer.text(surv.complaint.name);
					 serializer.endTag("", "name");
					 serializer.startTag("", "contacts");
					 if(surv.complaint.contacts!=null)serializer.text(surv.complaint.contacts);
					 serializer.endTag("", "contacts");
					 
					 serializer.endTag("", "complaint");
				 //}
				 serializer.endTag("", "survey");
			 }
			 serializer.endTag("", "answers");
			 serializer.endDocument();
			 return writer.toString();
		 } catch (Exception e) {
			 throw new RuntimeException(e);
		 } 

	 }
}
