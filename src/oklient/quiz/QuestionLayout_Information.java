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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class QuestionLayout_Information extends QuestionLayout {
	private View.OnClickListener click_listener;
	
	public QuestionLayout_Information(Context context, Question quest, View.OnClickListener l) {
		super(context, quest);
		click_listener=l;
		initComponent2();
    }
	
	@Override
	protected void initComponent() {
	}
	
	protected void initComponent2() {
		this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));


			LinearLayout ll=new LinearLayout(getContext());
			ll.setOrientation(LinearLayout.VERTICAL);
			ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
			
			TextView text=new TextView(getContext());
			text.setText(question.title);
			ll.addView(text, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			
				Button b = new Button(getContext());
				b.setTextColor(Color.WHITE);
				b.setBackgroundResource(R.drawable.info_close_button_layers); // TODO 
				//b.setOnClickListener(click_listener);
				b.setOnClickListener(new OnClickListener()
				{

					//@Override
					public void onClick(View v) {
						click_listener.onClick(v);
					}

				});
				
				b.setText("Close"); // TODO from res
				ll.addView(b, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			

			this.addView(ll, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
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
