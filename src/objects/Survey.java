package objects;

import java.util.ArrayList;
import java.util.List;

public class Survey {
	public String questionnaire;
	public String created_at;
	
	public List<Answer> answers; 
	public Complaint complaint;
	
	public Survey(){
		 answers=new ArrayList<Answer>();
	 }
}
