package oklient.quiz;

import java.util.ArrayList;
import java.util.List;

import objects.Question;
import objects.Screen;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScreenLayout extends TableLayout {
    private Screen screen;
    private View.OnClickListener click_listener;
    private List<QuestionLayout> quest_layouts;
    
	public ScreenLayout(Context context, Screen scr, View.OnClickListener l) {
		super(context);
		screen=scr;
		click_listener=l;
		initComponent();
	}

    private void initComponent() {
    	quest_layouts=new ArrayList<QuestionLayout>();
    	
    	TextView title = new TextView(getContext());
		title.setText(screen.title);
		title.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		title.setTextSize(24);
		title.setTextColor(Color.BLACK);
		
		TextView hint = new TextView(getContext());
		hint.setText(screen.hint);
		hint.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		hint.setTextColor(Color.BLACK);
		
		TableLayout table = this;
		table.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		
		// add title
		table.addView(title, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		// add questions
		for(int j=0;j<screen.questions.size();j++){
			Question quest=screen.questions.get(j);
			QuestionLayout q_layout=null;
			
			if(quest.type.equals("single_choice")){
				q_layout = new QuestionLayout_SingleChoice(getContext(), quest, click_listener);	
			}
			else if(quest.type.equals("multiple_choice")){
				q_layout = new QuestionLayout_MultipleChoice(getContext(), quest);
			}
			else if(quest.type.equals("range")){
				q_layout = new QuestionLayout_Range(getContext(), quest);
			}
			else if(quest.type.equals("string")){
				q_layout = new QuestionLayout_String(getContext(), quest);
			}
			else if(quest.type.equals("confirmation")){
				q_layout = new QuestionLayout_Confirmation(getContext(), quest);
			}
			else if(quest.type.equals("info")){
				q_layout = new QuestionLayout_Information(getContext(), quest, click_listener);	
			}
			//q_layout.setBackgroundColor(Color.BLUE);
			table.addView((TableRow)q_layout/*, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)*/);
			quest_layouts.add(q_layout);
		}
		
		// add hint
		table.addView(hint, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		//table.setBackgroundColor(Color.MAGENTA);
    }

    public void updateFields() {
    	for(int i=0;i<quest_layouts.size();i++){
    		QuestionLayout q_layout=quest_layouts.get(i);
    		q_layout.UpdateData(true);
    	}
    	screen.setPassed(true);
    }
    
    public Screen getScreen() {
        return screen;
    }

}
