package oklient.quiz;

import objects.Question;
import android.content.Context;
import android.view.View;
import android.widget.TableRow;

public class QuestionLayout extends TableRow{

	protected Question question;
	protected View.OnClickListener click_listener;
	OnAnswerListener onAnswerListener = null;
	
	public QuestionLayout(Context context, Question quest, View.OnClickListener l, OnAnswerListener al) {
        super(context);
        question=quest;
        click_listener=l;
        onAnswerListener=al;
        initComponent();
    }
	
	protected void initComponent() {
	}
	
	public void UpdateData(boolean b) {
	}

	// Allows the user to set an Listener and react to the event
	public void setOnAnswerListener(OnAnswerListener listener) {
		onAnswerListener = listener;
	}
	
	protected void OnAnswer(){
		// Check if the Listener was set, otherwise we'll get an Exception when we try to call it
		if(onAnswerListener!=null) {
			onAnswerListener.onAnswer(question);
		}
	}
}
