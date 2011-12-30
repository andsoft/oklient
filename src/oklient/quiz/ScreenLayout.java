package oklient.quiz;

import java.util.ArrayList;
import java.util.List;

import objects.Question;
import objects.Screen;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScreenLayout extends LinearLayout/*TableLayout*/ {
    private Screen screen;
    private View.OnClickListener click_listener;
    private List<QuestionLayout> quest_layouts;
    private OnAnswerListener answer_listener;
    
	public ScreenLayout(Context context, Screen scr, View.OnClickListener l, OnAnswerListener al) {
		super(context);
		screen=scr;
		click_listener=l;
		answer_listener=al;
		initComponent();
	}

    private void initComponent() {
    	quest_layouts=new ArrayList<QuestionLayout>();
    	
    	this.setOrientation(LinearLayout.VERTICAL);
    	this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
    	
    	TextView title = new TextView(getContext());
		title.setText(screen.title);
		title.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		title.setTextSize(24);
		title.setTextColor(Color.BLACK);
		
		TextView hint = new TextView(getContext());
		hint.setText(screen.hint);
		hint.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		hint.setTextColor(Color.rgb(128, 128, 128));
		hint.setTextSize(12);
		
		TableLayout table = new TableLayout(getContext());//this;
		table.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		
		//table.setWeightSum(10);
		LayoutParams title_lp=new LayoutParams(/*LayoutParams.FILL_PARENT*/600, LayoutParams.WRAP_CONTENT);
		title_lp.bottomMargin=20;
		title_lp.setMargins(0, 0, 0, 20);
		//title_lp.weight=5;
		title.setLayoutParams(title_lp);
		//title.setBackgroundColor(Color.BLUE);
		// add title
		/*table*/this.addView(title/*, title_lp*/);//new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		// add questions
		for(int j=0;j<screen.questions.size();j++){
			Question quest=screen.questions.get(j);
			QuestionLayout q_layout=null;
			
			if(quest.type.equals("single_choice")){
				q_layout = new QuestionLayout_SingleChoice(getContext(), quest, click_listener, answer_listener);	
			}
			else if(quest.type.equals("multiple_choice")){
				q_layout = new QuestionLayout_MultipleChoice(getContext(), quest, click_listener, answer_listener);
			}
			else if(quest.type.equals("range")){
				q_layout = new QuestionLayout_Range(getContext(), quest, click_listener, answer_listener);
			}
			else if(quest.type.equals("string")){
				q_layout = new QuestionLayout_String(getContext(), quest, click_listener, answer_listener);
			}
			else if(quest.type.equals("confirmation")){
				q_layout = new QuestionLayout_Confirmation(getContext(), quest, click_listener, answer_listener);
			}
			else if(quest.type.equals("info")){
				q_layout = new QuestionLayout_Information(getContext(), quest, click_listener, answer_listener);	
			}
			//q_layout.setBackgroundColor(Color.BLUE);
			TableRow.LayoutParams q_lp=new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			q_lp.weight=0;
			table.addView((TableRow)q_layout,q_lp);///*, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)*/);
			quest_layouts.add(q_layout);
		}
		this.addView(table);
		// add hint
		TableRow.LayoutParams hint_lp=new TableRow.LayoutParams(600, LayoutParams.WRAP_CONTENT);
		//hint_lp.weight=5;
		hint_lp.topMargin=20;
		/*table*/this.addView(hint, hint_lp);//new TableRow.LayoutParams(600/*LayoutParams.FILL_PARENT*/, LayoutParams.FILL_PARENT/*WRAP_CONTENT*/));
		//hint.setBackgroundColor(Color.RED);
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
