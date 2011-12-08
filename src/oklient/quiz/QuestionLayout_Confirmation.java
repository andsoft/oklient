package oklient.quiz;

import objects.Answer;
import objects.Option;
import objects.Question;
import objects.TimeUtils;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class QuestionLayout_Confirmation extends QuestionLayout {
	
	private CheckBox check;
	
	public QuestionLayout_Confirmation(Context context, Question quest) {
        super(context, quest);
   
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

		LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);


		this.addView(new TextView(getContext()), new TableRow.LayoutParams(250, LayoutParams.WRAP_CONTENT));
		this.addView(check, new TableRow.LayoutParams(450/*LayoutParams.FILL_PARENT*/, LayoutParams.WRAP_CONTENT));
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
