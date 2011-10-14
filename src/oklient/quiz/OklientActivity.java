package oklient.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

public class OklientActivity extends Activity {
	 QuizLayout quiz_view;
	 IntroLayout intro_view;
	 OutroLayout outro_view;
	 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final ViewFlipper viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
		
		quiz_view = new QuizLayout(getApplicationContext(), this);
		intro_view = new IntroLayout(getApplicationContext(), this);
		outro_view = new OutroLayout(getApplicationContext(), this);
		
		viewFlipper.addView(intro_view);
		viewFlipper.addView(quiz_view);
		viewFlipper.addView(outro_view);		
	
		//when a view is displayed
		viewFlipper.setInAnimation(this,android.R.anim.fade_in);
		//when a view disappears
		viewFlipper.setOutAnimation(this, android.R.anim.fade_out);
	}
}