package oklient.quiz;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import objects.Answer;
import objects.Answers;
import objects.Complaint;
import objects.Question;
import objects.Screen;
import objects.Survey;
import objects.TimeUtils;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

public class QuizLayout extends LinearLayout {
	final OklientActivity parent;

	private ProgressBar mProgress;
	private ViewFlipper viewFlipper;
	private ImageView imViewLogo;

	private Button nextButton;
	private Button prevButton;
	private Button infoButton;
	private Button complaintButton;
	private Button quitButton;
	
	private View.OnClickListener click_listener;
	private OnAnswerListener answer_listener;
	final Animation animation;
	int n=-1;
	
	public QuizLayout(Context context, OklientActivity _parent) {
		super(context);
		
		parent=_parent;
		
		animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
	    animation.setDuration(500); // duration - half a second
	    animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
	    animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
	    animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

		initComponent();
		
		click_listener=new OnClickListener() {
			//@Override
			public void onClick(View v) {	
				//showNextScreen();
			}
		};
		
		answer_listener=new OnAnswerListener() {
			//@Override
			public void onAnswer(Question quest) {	
				if(quest.type.equals("single_choice") || quest.type.equals("info"))
					showNextScreen();
				else
					startAnimationTimer();
			}
		};
	}
	
	// Timer 
    private Handler mHandler = new Handler();
    //
    private Runnable mUpdateTimeTask = new Runnable() {
       public void run() {
    	   /*TimerDialog dialog = new TimerDialog(parent,parent);
    	   dialog.show(); 
			*/
    	   parent.showDialog(OklientActivity.DIALOG_TIMER_ID);
    	 
           // timer
           mHandler.removeCallbacks(mUpdateTimeTask);
           //mHandler.postDelayed(mUpdateTimeTask, (10000)) ;
       }
    };
	
    private Runnable mAnimateTask = new Runnable() {
        public void run() {
        	nextButton.startAnimation(animation);
        }
     };
    
    public void startTimer() {
    	mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, (30000)) ;
	}
	
	public void stopTimer() {
		mHandler.removeCallbacks(mUpdateTimeTask);
	}
	
	public void startAnimationTimer() {
    	mHandler.removeCallbacks(mAnimateTask);
        mHandler.postDelayed(mAnimateTask, (5000)) ;
	}
	
	public void stopAnimationTimer() {
		mHandler.removeCallbacks(mAnimateTask);
	}
	
	public void startQuiz() {
		imViewLogo.setImageBitmap(parent.bmImg); // set image cause it can be updated in synctask
		mProgress.setMax(parent.questionnaire.screens.size());
		complaintButton.setVisibility(parent.questionnaire.accepts_complaints.equals("true")?VISIBLE:INVISIBLE);
		showNextScreen();
	}
	
	public void stopQuiz() {
		
		Answers res=new Answers();
		Survey surv=new Survey();
		surv.questionnaire=parent.questionnaire.id;
		surv.created_at=TimeUtils.GetUTCdatetimeAsString();
		
		
		// answers
		for(int i=0; i<parent.questionnaire.screens.size();i++){
			Screen scr=parent.questionnaire.screens.get(i);
			
			if(!scr.getPassed())continue;
			
			for(int j=0;j<scr.questions.size();j++){
				Question quest=scr.questions.get(j);
				
				for(int x=0;x<quest.answers.size();x++){
					Answer answ=quest.answers.get(x);
				
				
				surv.answers.add(answ);
				}
			}
		}
		
		// complaint
		if(parent.complaint.screens.get(0).getPassed()) {
			surv.complaint=new Complaint();
			surv.complaint.created_at=TimeUtils.GetUTCdatetimeAsString();
			surv.complaint.body=parent.complaint.screens.get(0).questions.get(0).answers.get(0).value;
			surv.complaint.title=parent.complaint.screens.get(0).questions.get(1).answers.get(0).value;
			if(parent.complaint.screens.get(1).questions.get(0).answers.size()>0)
			surv.complaint.name=parent.complaint.screens.get(1).questions.get(0).answers.get(0).value;
			else surv.complaint.name="";
			if(parent.complaint.screens.get(1).questions.get(1).answers.size()>0)
			surv.complaint.contacts=parent.complaint.screens.get(1).questions.get(1).answers.get(0).value;
			else surv.complaint.contacts="";
		}
		else
			surv.complaint=null;
		
		res.surveis.add(surv);
		
		// send results
		String xml_res=res.getXml();	
		// TODO check for empty survey
		try {
	        FileOutputStream fos = getContext().openFileOutput("survey_"+TimeUtils.GetTimeStamp(), Context.MODE_PRIVATE);
	        fos.write(xml_res.getBytes());
	        fos.close();
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		
		//parent.api.SendResults(xml_res);
	}
	
	public void quitQuiz(boolean bShowOutro) {
		stopTimer();
		
		parent.removeDialog(OklientActivity.DIALOG_COMPLAINT_ID);
		parent.removeDialog(OklientActivity.DIALOG_INFO_ID);
		parent.removeDialog(OklientActivity.DIALOG_TIMER_ID);
		
		n=-1;
		mProgress.setProgress(0);
		viewFlipper.removeAllViews();
		//parent.q.initQuestionnaire();
		//showNextScreen();
		// show outro view
		//final ViewFlipper parentFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
		if(bShowOutro) parent.showNextScreen();//parentFlipper.showNext();
		else parent.showPrevScreen();//parentFlipper.showPrevious();
		
		stopQuiz();
	}
	
	private void showPrevScreen() {
		startTimer();
		stopAnimationTimer();
		nextButton.clearAnimation();
		//startAnimationTimer(); // maybe cause already have answers in previous screen 
		
		View current_view=viewFlipper.getCurrentView();
		
		viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_right);
		viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_right);
		viewFlipper.showPrevious();
		
		viewFlipper.removeView(current_view);
		
		ScreenLayout view=(ScreenLayout)viewFlipper.getCurrentView();
		
		
		Screen cur_scr=parent.questionnaire.screens.get(n);
		cur_scr.setPassed(false); // clear prev data
		
		
		//if(n>=1)n--;
		//else return;
		Screen s=view.getScreen();
		n=parent.questionnaire.screens.indexOf(s);
		mProgress.setProgress(n);
		
		Screen scr=parent.questionnaire.screens.get(n);
		scr.setPassed(false); // clear prev data
		prevButton.setEnabled(n>0);
		nextButton.setEnabled(!scr.questions.get(0).type.equals("single_choice"));
		
	}
	
	private void showNextScreen() {
		startTimer();
		stopAnimationTimer();
		nextButton.clearAnimation();
		
		ScreenLayout current_view=(ScreenLayout) viewFlipper.getCurrentView();
		if(current_view!=null)current_view.updateFields(); // store data
		
		//Screen cur_scr=parent.q.screens.get(n);
		//cur_scr.setPassed(true); // store data
		
		n=parent.questionnaire.getNextScreen(n);
		if(n==-1){
			quitQuiz(true);
			return;
		} //TODO no more screens
		
		
		Screen scr=parent.questionnaire.screens.get(n);
		
		prevButton.setEnabled(n>0);
		nextButton.setEnabled(!scr.questions.get(0).type.equals("single_choice"));
		
		mProgress.setProgress(n);
		
		ScreenLayout screen_layout=new ScreenLayout(getContext(), scr, click_listener, answer_listener);
		viewFlipper.addView(screen_layout);
		viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_left);
		viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_left);
		viewFlipper.showNext();

	}
	
	private void initComponent() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.quiz, this);

		imViewLogo = (ImageView) findViewById(R.id.imageView2);
        imViewLogo.setImageBitmap(parent.bmImg);

        mProgress = (ProgressBar) findViewById(R.id.progressBar1);
		mProgress.setMax(100/*parent.q.screens.size()*/); // will be changed on startquiz
		
		viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
			    
		nextButton = (Button) this.findViewById(R.id.buttonNext);
		nextButton.setOnClickListener(new OnClickListener()
		{
			//@Override
			public void onClick(View v) {
				showNextScreen();
			}
		});

		prevButton = (Button) this.findViewById(R.id.buttonPrev);
		prevButton.setOnClickListener(new OnClickListener()
		{
			//@Override
			public void onClick(View v) {
				showPrevScreen();
			}

		});

		infoButton = (Button) findViewById(R.id.buttonHelp);
		infoButton.setOnClickListener(new OnClickListener()
		{
			//@Override
			public void onClick(View v) {
				startTimer();
				parent.showDialog(OklientActivity.DIALOG_INFO_ID);
			}
		});

		complaintButton = (Button) findViewById(R.id.buttonComplaint);
		complaintButton.setOnClickListener(new OnClickListener()
		{
			//@Override
			public void onClick(View v) {
				startTimer();
				parent.showDialog(OklientActivity.DIALOG_COMPLAINT_ID);
			}
		});
		
		quitButton = (Button) this.findViewById(R.id.buttonQuit);
		quitButton.setOnClickListener(new OnClickListener()
		{
			//@Override
			public void onClick(View v) {
				quitQuiz(false);
			}
		});
	}
}
