package oklient.quiz;

import objects.Answer;
import objects.Question;
import objects.TimeUtils;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

public class QuestionLayout_Range extends QuestionLayout implements SeekBar.OnSeekBarChangeListener {
	
	private SeekBar sbar;
	private int range_min;
	private int range_max;
	private int range_step;
	private int range_default;
	private TextView text, value, hint;
	
	public QuestionLayout_Range(Context context, Question quest) {
        super(context, quest);
       
    }
	
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
		value.setText(""+progress);
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	protected void initComponent() {
		
		this.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		//tr.setBackgroundColor(Color.BLUE);
		this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View bar=inflater.inflate(R.layout.complaint_msg, null);
		SeekBar sb=(SeekBar) bar.findViewById(R.id.seekBar1);
		
		//TextView text, value, hint;
		text=new TextView(getContext()); 
		value=new TextView(getContext());
		hint=new TextView(getContext());
		
		text.setTextSize(12);
		value.setTextSize(14);
		hint.setTextSize(12);
		
		text.setGravity(Gravity.RIGHT);
		value.setGravity(Gravity.CENTER);
		hint.setGravity(Gravity.LEFT);
		
		text.setText(question.title);
		
		hint.setText(question.hint);
		
		text.setTextColor(Color.BLACK);
		value.setTextColor(Color.BLACK);
		hint.setTextColor(Color.BLACK);
		
		ScaleDrawable b=new ScaleDrawable(getResources().getDrawable(R.drawable.scale_round), Gravity.CENTER, 100,100);
		value.setBackgroundDrawable(b);
		//value.setBackgroundResource(R.drawable.scale_round);
		
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
		
		sbar.setMinimumHeight(4);
		
		sbar.setProgressDrawable(getResources().getDrawable(R.drawable.scale_full/*seekbar_layers*/));
		sbar.setThumb(getResources().getDrawable(R.drawable.slider));
		sbar.setPadding(20, 0, 20,0);
		sbar.setMax(max); // TODO
		sbar.setProgress(def);
		//sb1.set
		
		sb.setOnSeekBarChangeListener(this);
		sb.setMax(max); // TODO
		sb.setProgress(def);
		
		
		bar.setLayoutParams(new TableRow.LayoutParams(600,LayoutParams.WRAP_CONTENT));
		
		TextView round=new TextView(getContext());
		round.setBackgroundResource(R.drawable.scale_round);
		
		FrameLayout fl=new FrameLayout(getContext());
		//fl.setGravity(Gravity.CENTER);
		FrameLayout.LayoutParams lp1=new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp1.gravity=Gravity.CENTER;
		
		value.setLayoutParams(lp1);
		fl.addView(round, lp1);//new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		fl.addView(value, lp1);
		//value.setBackgroundColor(Color.GREEN);
		
		LinearLayout ll=new LinearLayout(getContext());
		ll.addView(/*value*/fl, new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.leftMargin=10;
		lp.gravity=Gravity.CENTER_VERTICAL|Gravity.RIGHT;
		ll.addView(hint, lp);

		
		addView(text, new TableRow.LayoutParams(250, LayoutParams.WRAP_CONTENT));
		addView(bar, new TableRow.LayoutParams(450,LayoutParams.WRAP_CONTENT));
		//addView(sbar, new TableRow.LayoutParams(500,LayoutParams.WRAP_CONTENT));
		//addView(value, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		//addView(hint, new TableRow.LayoutParams(250, LayoutParams.WRAP_CONTENT));
		addView(ll, new TableRow.LayoutParams(250, LayoutParams.WRAP_CONTENT));
//this.setBackgroundColor(Color.RED);
//bar.setBackgroundColor(Color.GREEN);
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
