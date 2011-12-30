package oklient.quiz;

import objects.Answer;
import objects.Option;
import objects.Question;
import objects.TimeUtils;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
//import android.widget.TableLayout.LayoutParams;

public class QuestionLayout_Information extends QuestionLayout {
	
	public QuestionLayout_Information(Context context, Question quest, View.OnClickListener l, OnAnswerListener al) {
		super(context, quest, l, al);
    }
	
	@Override
	protected void initComponent() {

		this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		
		//this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		//this.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));

			LinearLayout ll=new LinearLayout(getContext());
			ll.setOrientation(LinearLayout.VERTICAL);
			//ll.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
			ll.setGravity(Gravity.CENTER);
			
			TextView text=new TextView(getContext());
			text.setText(question.title);
			text.setTextSize(20);
			text.setTextColor(Color.BLACK);
			text.setMinimumWidth(600);
			text.setLayoutParams(new LayoutParams(/*LayoutParams.FILL_PARENT*/600, LayoutParams.FILL_PARENT));
			text.setGravity(Gravity.CENTER);
			ll.addView(text);
			
				Button b = new Button(getContext());
				//b.setTextColor(Color.WHITE);
				b.setBackgroundResource(R.drawable.info_close_button_layers); // TODO 
				b.setHeight(b.getHeight());
				//b.setOnClickListener(click_listener);
				b.setOnClickListener(new OnClickListener()
				{

					//@Override
					public void onClick(View v) {
						click_listener.onClick(v);
						onAnswerListener.onAnswer(question);
					}

				});
				
				//b.setText("Close");
				LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp.topMargin=20;
				b.setLayoutParams(lp);//new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				
				ll.addView(b);
				
				
			//ll.setBackgroundColor(Color.GREEN);

			this.addView(ll/*, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)*/);
		//this.setBackgroundColor(Color.RED);
	}

	@Override
	public void UpdateData(boolean b) {
/*		Answer answer=new Answer();
		answer.question=question.id;
		answer.option=question.options.get(option_id).id;
		answer.value="";
		answer.created_at=TimeUtils.GetUTCdatetimeAsString();
		question.answers.add(answer);*/
	}
}
