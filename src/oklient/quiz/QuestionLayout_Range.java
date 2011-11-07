package oklient.quiz;

import objects.Answer;
import objects.Question;
import objects.TimeUtils;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

public class QuestionLayout_Range extends QuestionLayout {
	
	private SeekBar sbar;
	private int range_min;
	private int range_max;
	private int range_step;
	private int range_default;
	
	public QuestionLayout_Range(Context context, Question quest) {
        super(context, quest);
       
    }
	
	@Override
	protected void initComponent() {
		
		this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		//tr.setBackgroundColor(Color.BLUE);
		this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		
		TextView text, hint;
		text=new TextView(getContext()); 
		
		hint=new TextView(getContext());
		

		text.setText(question.title);
		
		hint.setText(question.hint);
		
		text.setTextColor(Color.BLACK);
		hint.setTextColor(Color.BLACK);
		//LayoutParams params2 = new LayoutParams(/*LayoutParams.WRAP_CONTENT*/300,
		//		LayoutParams.WRAP_CONTENT);
		range_min=Integer.valueOf(question.range_min);
		range_max=Integer.valueOf(question.range_max);
		range_step=Integer.valueOf(question.range_step);
		range_default=range_min+(range_max-range_min)/2;
		if(question.range_default!=null && !question.range_default.equals(""))	
			range_default=Integer.valueOf(question.range_default);
		int max=(range_max-range_min)/range_step;
		int def=(range_default-range_min)/range_step;
		
		//SeekBar sbar;
		sbar=new SeekBar(getContext());
		//sb1.setMinimumWidth(200);
		sbar.setLayoutParams(new TableRow.LayoutParams(200,LayoutParams.WRAP_CONTENT));
		
		
		sbar.setThumb(getResources().getDrawable(R.drawable.slider));
		sbar.setPadding(20, 0, 20,0);
		sbar.setMax(max); // TODO
		sbar.setProgress(def);
		//sb1.set
		
		addView(text);
		addView(sbar);
		addView(hint);

	}

	@Override
	public void UpdateData(boolean b) {
		Answer answer=new Answer();
		answer.question=question.id;
		answer.option="";
		answer.value=String.valueOf(range_min+sbar.getProgress()*range_step); // TODO
		answer.created_at=TimeUtils.GetUTCdatetimeAsString();
		question.answers.add(answer);
	}

}
