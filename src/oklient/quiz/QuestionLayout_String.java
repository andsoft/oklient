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
		
		edit=new EditText(getContext());
		edit.setBackgroundResource(R.drawable.edit_text_layers);
		edit.setMinimumWidth(400);
		
		// init table row
		this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		this.addView(text);
		this.addView(edit);
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
