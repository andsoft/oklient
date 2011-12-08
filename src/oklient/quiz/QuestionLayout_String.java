package oklient.quiz;

import objects.Answer;
import objects.Question;
import objects.TimeUtils;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class QuestionLayout_String extends QuestionLayout {
	
	private TextView text;
	private EditText edit;
	
	public QuestionLayout_String(Context context, Question quest) {
        super(context, quest);
        
    }
	
	@Override
	protected void initComponent() {
		
		text = new TextView(getContext());
		text.setText(question.title);
		text.setTextColor(Color.BLACK);
		text.setGravity(Gravity.RIGHT);
		text.setTextSize(12);
		
		edit=new EditText(getContext());
		edit.setBackgroundResource(R.drawable.edit_text_layers);
		edit.setMinimumWidth(400);
		edit.setHeight(edit.getHeight());
		edit.setPadding(15, 5, 15, 5);
		edit.setTextSize(12);
		
		TableRow.LayoutParams edit_lp=new TableRow.LayoutParams(/*LayoutParams.WRAP_CONTENT*/450, LayoutParams.WRAP_CONTENT);
		edit_lp.leftMargin=10;
		edit_lp.rightMargin=10;
		
		// init table row
		this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		this.addView(text, new TableRow.LayoutParams(250, LayoutParams.WRAP_CONTENT));
		this.addView(edit, edit_lp);
		this.addView(new TextView(getContext()), new TableRow.LayoutParams(250, LayoutParams.WRAP_CONTENT));
	}

	@Override
	public void UpdateData(boolean b) {
		Answer answer=new Answer();
		answer.question=question.id;
		answer.option="";
		answer.value=edit.getText().toString();
		answer.created_at=TimeUtils.GetUTCdatetimeAsString();
		question.answers.add(answer);
	}

}
