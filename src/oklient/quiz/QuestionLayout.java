package oklient.quiz;

import java.text.SimpleDateFormat;

import objects.Question;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuestionLayout extends LinearLayout {
    private Question parentQuestion;
    private ImageView channel_logo;
    private TextView channel_name;
    private TextView program_time;
    private TextView program_name;
    private TextView program_description;
    private Button want_to_watch_button;
    private String programName = "";

	public QuestionLayout(Context context) {
		super(context);
		initComponent();
	}

    private void initComponent() {
/*        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.channel_layout, this);
        channel_logo = (ImageView) findViewById(R.id.channel_logo);
        channel_name = (TextView) findViewById(R.id.channel_name);
        program_time = (TextView) findViewById(R.id.program_time);
        program_name = (TextView) findViewById(R.id.program_name);
        program_description = (TextView) findViewById(R.id.program_description);
        want_to_watch_button = (Button) findViewById(R.id.want_to_watch_button);
        want_to_watch_button.setOnClickListener(buttonListener);
        updateFields();*/
    }

    private void updateFields() {
 /*       if (isWannaWatch) {
            program_name.setText(programName + "*");
            this.setBackgroundResource(R.drawable.frame_bg_selected);
        } else {
            program_name.setText(programName);
            this.setBackgroundResource(R.drawable.frame_bg);
        }
*/
    }
    
    public Question getParentQuestion() {
        return parentQuestion;
    }

    public void setParentQuestion(Question parentQuestion) {
        this.parentQuestion = parentQuestion;
        updateFieldsByParent();
    }

    private void updateFieldsByParent() {
 /*       setProgramName(parentProgram.getName());
        setProgramDescription(parentProgram.getDesc());
        setProgramTime(SimpleDateFormat.getInstance().format(parentProgram.getTime()));
        setChannelLogo(parentProgram.getChannelLogo());
        setChannelName(parentProgram.getChannelName());*/
    }
}
