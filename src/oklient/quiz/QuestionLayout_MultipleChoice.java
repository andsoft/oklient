package oklient.quiz;

import objects.Answer;
import objects.Option;
import objects.Question;
import objects.TimeUtils;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TableLayout.LayoutParams;

public class QuestionLayout_MultipleChoice extends QuestionLayout {

	private LinearLayout ll;
	
	public QuestionLayout_MultipleChoice(Context context, Question quest) {
        super(context, quest);        
    }
	
	@Override
	protected void initComponent() {


		this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	
		/*LinearLayout */ll=new LinearLayout(getContext());
		ll.setOrientation(LinearLayout.VERTICAL);
		
		for(int i=0;i<question.options.size();i++){
			Option opt=question.options.get(i);
			
			if(opt.meaning.equals("string")){
				EditText edit=new EditText(getContext());
				edit.setBackgroundResource(R.drawable.edit_text_layers);
				edit.setHint("Other");
				edit.setMinimumWidth(200);
				edit.setId(i);
				ll.addView(edit, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			}
			else{	
				CheckBox check = new CheckBox(getContext());
				check.setButtonDrawable(R.drawable.checkbox_layers);
				check.setBackgroundResource(R.drawable.checkbox_fortext);
				check.setPadding(100, 5, 5, 5);
				check.setTextColor(Color.BLACK);
				check.setText(opt.title);
				check.setId(i);
				ll.addView(check, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			}
		}

		this.addView(ll);

	}

	@Override
	public void UpdateData(boolean b) {
		
		for(int i=0;i<question.options.size();i++){
			Option opt=question.options.get(i);
			if(opt.meaning.equals("string")){
				EditText edit=(EditText) ll.findViewById(i);
				if(!edit.getText().toString().equals("")){
				Answer answer=new Answer();
				answer.question=question.id;
				answer.option=opt.id;
				answer.value=edit.getText().toString();
				answer.created_at=TimeUtils.GetUTCdatetimeAsString();
				question.answers.add(answer);
				}
			}
			else {
				CheckBox check=(CheckBox) ll.findViewById(i);
				if(check.isChecked()){
				Answer answer=new Answer();
				answer.question=question.id;
				answer.option=opt.id;
				answer.value="";
				answer.created_at=TimeUtils.GetUTCdatetimeAsString();
				question.answers.add(answer);
				}
			}
		}
	}

}
