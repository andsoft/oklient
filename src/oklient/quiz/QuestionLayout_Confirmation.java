package oklient.quiz;

import objects.Answer;
import objects.Option;
import objects.Question;
import objects.TimeUtils;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class QuestionLayout_Confirmation extends QuestionLayout {
	
	private CheckBox check;
	
	public QuestionLayout_Confirmation(Context context, Question quest, View.OnClickListener l, OnAnswerListener al) {
        super(context, quest, l, al);
   
    }
	
	@Override
	protected void initComponent() {
		this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));


		/*CheckBox */check = new CheckBox(getContext());
		check.setButtonDrawable(R.drawable.checkbox_layers);
		check.setBackgroundResource(R.drawable.checkbox_fortext);
		check.setPadding(70, 5, 5, 5);
		check.setTextColor(Color.BLACK);
		check.setText(question.title);
		check.setTextSize(10);
		check.setOnCheckedChangeListener( new OnCheckedChangeListener() {
			public void onCheckedChanged (CompoundButton buttonView, boolean isChecked){
				onAnswerListener.onAnswer(question);
			}
		});
		
		TableRow.LayoutParams edit_lp=new TableRow.LayoutParams(/*LayoutParams.WRAP_CONTENT*/450, LayoutParams.WRAP_CONTENT);
		edit_lp.leftMargin=10;
		edit_lp.rightMargin=10;

		this.addView(new TextView(getContext()), new TableRow.LayoutParams(250, LayoutParams.WRAP_CONTENT));
		this.addView(check, edit_lp);
		this.addView(new TextView(getContext()), new TableRow.LayoutParams(250, LayoutParams.WRAP_CONTENT));
	}

	@Override
	public void UpdateData(boolean b) {
		Answer answer=new Answer();
		answer.question=question.id;
		answer.option=question.id+(check.isChecked()?"_true":"_false"); // custom usage in complaint only!!!
		answer.value=check.isChecked()?"1":"0";
		answer.created_at=TimeUtils.GetUTCdatetimeAsString();
		question.answers.add(answer);
	}

}
