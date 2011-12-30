package oklient.quiz;

import objects.Answer;
import objects.Question;
import objects.TimeUtils;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class QuestionLayout_String extends QuestionLayout {
	
	private TextView text;
	private EditText edit;
	
	public QuestionLayout_String(Context context, Question quest, View.OnClickListener l, OnAnswerListener al) {
        super(context, quest, l, al);
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
		edit.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
				onAnswerListener.onAnswer(question);
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			public void onTextChanged(CharSequence s, int start, int before, int count){}
		}); 
		
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
