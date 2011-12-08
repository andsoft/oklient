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
import android.widget.TableLayout.LayoutParams;

public class QuestionLayout_SingleChoice extends QuestionLayout {
	
	private View.OnClickListener click_listener;
	private int option_id;
	
	public QuestionLayout_SingleChoice(Context context, Question quest, View.OnClickListener l) {
		super(context, quest);
		click_listener=l;
		option_id=-1;
		initComponent2();
    }
	
	@Override
	protected void initComponent() {
	}
	
	protected void initComponent2() {
		this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

		if(question.style.equals("special_meaning")){
			for(int i=0;i<question.options.size();i++){
				Option opt=question.options.get(i);

				Button b = new Button(getContext());
				b.setId(i);
				
				if(opt.meaning.equals("definitely_not")) b.setBackgroundResource(R.drawable.red_button_layers);
				else if(opt.meaning.equals("probably_not")) b.setBackgroundResource(R.drawable.yellow_button_layers);
				else if(opt.meaning.equals("probably_yes")) b.setBackgroundResource(R.drawable.green_button_layers);
				else if(opt.meaning.equals("definitely_yes")) b.setBackgroundResource(R.drawable.sgreen_button_layers);
				else if(opt.meaning.equals("yes")) b.setBackgroundResource(R.drawable.big_yes_layers);
				else if(opt.meaning.equals("no")) b.setBackgroundResource(R.drawable.big_no_layers);
				//else if(opt.meaning.equals("dont_know")) b.setBackgroundResource(R.drawable.green_button_layers);
				
				//b.setOnClickListener(click_listener);
				b.setOnClickListener(new OnClickListener()
				{

					//@Override
					public void onClick(View v) {
						option_id=v.getId();
						click_listener.onClick(v);
					}

				});

				//b.setText(opt.title);
				LayoutParams lp=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				lp.setMargins(5, 0, 5, 0);
				this.addView(b, lp);//new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			}
		}
		else{

			LinearLayout ll=new LinearLayout(getContext());
			ll.setOrientation(LinearLayout.VERTICAL);

			for(int i=0;i<question.options.size();i++){
				Option opt=question.options.get(i);

				Button b = new Button(getContext());
				b.setId(i);
				b.setTextColor(Color.WHITE);
				b.setBackgroundResource(R.drawable.small_blue_button_layers);
				//b.setOnClickListener(click_listener);
				b.setOnClickListener(new OnClickListener()
				{

					//@Override
					public void onClick(View v) {
						option_id=v.getId();
						click_listener.onClick(v);
					}

				});
				
				b.setText(opt.title);
				LayoutParams lp=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				lp.setMargins(0, 5, 0, 5);
				ll.addView(b, lp);//new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			}

			this.addView(ll, new TableRow.LayoutParams(450, LayoutParams.WRAP_CONTENT));
		}
	}

	@Override
	public void UpdateData(boolean b) {
		if(option_id==-1)return;
		Answer answer=new Answer();
		answer.question=question.id;
		answer.option=question.options.get(option_id).id;
		answer.value="";
		answer.created_at=TimeUtils.GetUTCdatetimeAsString();
		question.answers.add(answer);
	}

}
