package oklient.quiz;

import objects.Answer;
import objects.Answers;
import objects.Complaint;
import objects.Question;
import objects.Screen;
import objects.Survey;
import objects.TimeUtils;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.TableRow.LayoutParams;

public class ComplaintLayout extends LinearLayout {
	final OklientActivity parent;

	ComplaintMsgLayout   msg_view;
	ComplaintInfoLayout  info_view;
	ComplaintOutroLayout outro_view;
	
	private ViewFlipper viewFlipper;

	private Button nextButton;
	private Button prevButton;
	
	int n=-1;
	
	View.OnClickListener click_listener;
	
	public ComplaintLayout(Context context, OklientActivity _parent) {
		super(context);
		//_context=context;
		parent=_parent;
		initComponent();
		
		//showNextScreen();
		click_listener=new OnClickListener()
		{
			//@Override
			public void onClick(View v) {
				
				showNextScreen();
			}

		};
	}
	
	
	public void startQuiz(){
		parent.complaint.initQuestionnaire();
		showNextScreen();
	}
	
	public void stopQuiz(){

		Answers res=new Answers();
		Survey surv=new Survey();
		surv.questionnaire=parent.q.id;
		surv.created_at=TimeUtils.GetUTCdatetimeAsString();
		
		
/*		
		for(int i=0; i<parent.q.screens.size();i++){
			Screen scr=parent.q.screens.get(i);
			
			if(!scr.getPassed())continue;
			
			for(int j=0;j<scr.questions.size();j++){
				Question quest=scr.questions.get(j);
				
				for(int x=0;x<quest.answers.size();x++){
					Answer answ=quest.answers.get(x);
				
				
				surv.answers.add(answ);
				}
			}
		}
*/		
		surv.complaint=new Complaint();
		surv.complaint.created_at=TimeUtils.GetUTCdatetimeAsString();
		surv.complaint.body=parent.complaint.screens.get(0).questions.get(0).answers.get(0).value;
		surv.complaint.title=parent.complaint.screens.get(0).questions.get(1).answers.get(0).value;
		surv.complaint.name=parent.complaint.screens.get(1).questions.get(0).answers.get(0).value;
		surv.complaint.contacts=parent.complaint.screens.get(1).questions.get(1).answers.get(0).value;
		
		res.surveis.add(surv);
		
		// send results
		String xml_res=res.getComplaintXml();	
		
		parent.api.SendResults(xml_res);
	}
	
	private void showPrevScreen() {
		/*viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_right);
		viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_right);
		viewFlipper.showPrevious();*/
		/////////////////////////////////////
		View current_view=viewFlipper.getCurrentView();
		
		viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_right);
		viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_right);
		viewFlipper.showPrevious();

		viewFlipper.removeView(current_view);
		
		ScreenLayout view=(ScreenLayout)viewFlipper.getCurrentView();
		
		
		Screen cur_scr=parent.complaint.screens.get(n);
		cur_scr.setPassed(false); // clear prev data
		
		
		//if(n>=1)n--;
		//else return;
		Screen s=view.getScreen();
		n=parent.complaint.screens.indexOf(s);
		
		Screen scr=parent.complaint.screens.get(n);
		scr.setPassed(false); // clear prev data
		prevButton.setEnabled(n>0);
		nextButton.setEnabled(!scr.questions.get(0).type.equals("single_choice"));
		
	}
	
	private void showNextScreen() {
/*		viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_left);
		viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_left);
		viewFlipper.showNext();
*/		/////////////////////////////////////////
		ScreenLayout current_view=(ScreenLayout) viewFlipper.getCurrentView();
		if(current_view!=null)current_view.updateFields(); // store data
		
		//Screen cur_scr=parent.q.screens.get(n);
		//cur_scr.setPassed(true); // store data
		
		n=parent.complaint.getNextScreen(n);
		if(n==-1){
			viewFlipper.removeAllViews();
			// show outro view
			//final ViewFlipper parentFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
			//parentFlipper.showNext();
			//TODO quit
			stopQuiz();
			
			return;
		} //TODO no more screens
		
		
		Screen scr=parent.complaint.screens.get(n);
		
		prevButton.setEnabled(n>0);
		nextButton.setEnabled(!scr.questions.get(0).type.equals("single_choice"));
		prevButton.setVisibility(scr.questions.get(0).type.equals("info")? INVISIBLE: VISIBLE);
		nextButton.setVisibility(scr.questions.get(0).type.equals("info")? INVISIBLE: VISIBLE);
		
		ScreenLayout screen_layout=new ScreenLayout(getContext(), scr, click_listener);
		viewFlipper.addView(screen_layout);
		viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_left);
		viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_left);
		viewFlipper.showNext();
		
		//mHandler.removeCallbacks(mUpdateTimeTask);
		//mHandler.postDelayed(mUpdateTimeTask, (10000)) ;
	}

	private void initComponent() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.complaint, this);
		
		Drawable drawable = getResources().getDrawable(R.drawable.background_complaint);
		drawable.setAlpha(240);
		this.setBackgroundDrawable(drawable);
		//this.setBackgroundResource(R.drawable.background_complaint);
		
		viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper1);
/*		
		for(int i=0;i<parent.complaint.screens.size();i++) {
			Screen scr=parent.complaint.screens.get(i);
	
			ScreenLayout screen_layout=new ScreenLayout(getContext(), scr, click_listener);
			viewFlipper.addView(screen_layout);
		}*/
/*		
		msg_view = new ComplaintMsgLayout(getContext(), this);
		info_view = new ComplaintInfoLayout(getContext(), this);
		outro_view = new ComplaintOutroLayout(getContext(), this);
		
		viewFlipper.addView(msg_view);
		viewFlipper.addView(info_view);
		viewFlipper.addView(outro_view);	
*/		
		nextButton = (Button) this.findViewById(R.id.buttonNext);
		nextButton.setBackgroundResource(R.drawable.forward_button_layers);
		nextButton.setText("");
		//nextButton.setSoundEffectsEnabled(true);
		nextButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				//nextButton.playSoundEffect(SoundEffectConstants.NAVIGATION_RIGHT);
				viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_left);
				viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_left);
				
				showNextScreen();
			}

		});

		prevButton = (Button) this.findViewById(R.id.buttonPrev);
		prevButton.setBackgroundResource(R.drawable.back_button_layers);
		prevButton.setText("");
		//prevButton.setSoundEffectsEnabled(true);
		prevButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				//prevButton.playSoundEffect(SoundEffectConstants.CLICK);
				viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_right);
				viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_right);
				
				showPrevScreen();
			}

		});

	}

}
