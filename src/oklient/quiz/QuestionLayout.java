package oklient.quiz;

import objects.Question;
import android.content.Context;
import android.widget.TableRow;

public class QuestionLayout extends TableRow{

	protected Question question;
	
	public QuestionLayout(Context context, Question quest) {
        super(context);
        question=quest;
        initComponent();
    }
	
	protected void initComponent() {
	}
	
	public void UpdateData(boolean b) {
	}
}
