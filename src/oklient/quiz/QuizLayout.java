package oklient.quiz;

import objects.Answer;
import objects.Answers;
import objects.Question;
import objects.Screen;
import objects.Survey;
import objects.TimeUtils;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.TableRow.LayoutParams;

public class QuizLayout extends LinearLayout {
	final OklientActivity parent;

	private ProgressBar mProgress;
	private ViewFlipper viewFlipper;

	private Button nextButton;
	private Button prevButton;
	private Button infoButton;
	private Button complaintButton;
	private Button quitButton;
	
	// TODO remove
	int n=-1;
	LinearLayout l1;
	LinearLayout l2;
	//TextView tv1, tv2, tv3, tv4, tv5, tv6;

	//private Context _context;
	View.OnClickListener click_listener;
	
	public QuizLayout(Context context, OklientActivity _parent) {
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
	
	// Timer 
    private Handler mHandler = new Handler();
    //
    private Runnable mUpdateTimeTask = new Runnable() 
    {
       public void run() 
       {
           // Do something 
           //Log.d( this.toString(), "Do something !");
    	   TimerDialog dialog = new TimerDialog(parent);
			//dialog.setContentView(R.layout.timer);
			//TextProgressBar pb=(TextProgressBar)dialog.findViewById(R.id.progressBar1);
			//pb.setText("15");
			//dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,
           //         LayoutParams.FILL_PARENT);
			dialog.show(); 
			
    	   //TextProgressBar pb=(TextProgressBar)findViewById(R.id.progressBar1);
   			//pb.setText(String.valueOf(n));
   			
           // timer
           mHandler.removeCallbacks(mUpdateTimeTask);
           //mHandler.postDelayed(mUpdateTimeTask, (10000)) ;
       }
    };
	
	public void startQuiz(){
		parent.q.initQuestionnaire();
		showNextScreen();
	}
	
	public void stopQuiz(){

		Answers res=new Answers();
		Survey surv=new Survey();
		surv.questionnaire=parent.q.id;
		surv.created_at=TimeUtils.GetUTCdatetimeAsString();
		
		
		
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
		
		res.surveis.add(surv);
		
		// send results
		String xml_res=res.getXml();	
		
		parent.api.SendResults(xml_res);
	}
	
	private void showPrevScreen() {
		View current_view=viewFlipper.getCurrentView();
		
		viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_right);
		viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_right);
		viewFlipper.showPrevious();
		
		viewFlipper.removeView(current_view);
		
		ScreenLayout view=(ScreenLayout)viewFlipper.getCurrentView();
		
		
		Screen cur_scr=parent.q.screens.get(n);
		cur_scr.setPassed(false); // clear prev data
		
		
		//if(n>=1)n--;
		//else return;
		Screen s=view.getScreen();
		n=parent.q.screens.indexOf(s);
		mProgress.setProgress(n);
		
		Screen scr=parent.q.screens.get(n);
		scr.setPassed(false); // clear prev data
		prevButton.setEnabled(n>0);
		nextButton.setEnabled(!scr.questions.get(0).type.equals("single_choice"));
		
	}
	
	private void showNextScreen() {
		ScreenLayout current_view=(ScreenLayout) viewFlipper.getCurrentView();
		if(current_view!=null)current_view.updateFields(); // store data
		
		//Screen cur_scr=parent.q.screens.get(n);
		//cur_scr.setPassed(true); // store data
		
		n=parent.q.getNextScreen(n);
		if(n==-1){
			mProgress.setProgress(0);
			viewFlipper.removeAllViews();
			//parent.q.initQuestionnaire();
			//showNextScreen();
			// show outro view
			final ViewFlipper parentFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
			parentFlipper.showNext();
			
			stopQuiz();
			
			return;
		} //TODO no more screens
		
		
		Screen scr=parent.q.screens.get(n);
		
		prevButton.setEnabled(n>0);
		nextButton.setEnabled(!scr.questions.get(0).type.equals("single_choice"));
		
		mProgress.setProgress(n);
		
		ScreenLayout screen_layout=new ScreenLayout(getContext(), scr, click_listener);
		viewFlipper.addView(screen_layout);
		viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_left);
		viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_left);
		viewFlipper.showNext();
		
		mHandler.removeCallbacks(mUpdateTimeTask);
		mHandler.postDelayed(mUpdateTimeTask, (10000)) ;
	}

	private void initComponent() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.quiz, this);

		this.setBackgroundResource(R.drawable.background);

		ImageView imView2 = (ImageView) findViewById(R.id.imageView2);
        imView2.setImageBitmap(parent.bmImg);
        
		mProgress = (ProgressBar) findViewById(R.id.progressBar1);

		mProgress.setMax(parent.q.screens.size());
		mProgress.setProgressDrawable(getResources().getDrawable(R.drawable.progress_layers)); 
		
		viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
		
		View bottom = findViewById(R.id.relativeLayout1);
		bottom.setBackgroundResource(R.drawable.down_panel_background2);
		
/*
		// add empty view to provide animated transition to the first view
		LinearLayout empty_view=new LinearLayout(_context);
		viewFlipper.addView(empty_view);
*/
		nextButton = (Button) this.findViewById(R.id.nextButton);
		nextButton.setBackgroundResource(R.drawable.forward_button_layers);
		nextButton.setText("");
		nextButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_left);
				viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_left);
				
				showNextScreen();
			}

		});

		prevButton = (Button) this.findViewById(R.id.previousButton);
		prevButton.setBackgroundResource(R.drawable.back_button_layers);
		prevButton.setText("");
		prevButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				viewFlipper.setInAnimation(getContext(), R.anim.view_transition_in_right);
				viewFlipper.setOutAnimation(getContext(), R.anim.view_transition_out_right);
				
				showPrevScreen();
			}

		});

		infoButton = (Button) findViewById(R.id.button1);
		infoButton.setBackgroundResource(R.drawable.info_button_layers);
		infoButton.setText("");
		infoButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				// show help
				AlertDialog.Builder builder = new AlertDialog.Builder(parent);
				builder.setMessage("This is help!\nbla bla bla")
				.setCancelable(false)
				.setNeutralButton("Close", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

				AlertDialog alert = builder.create();
				// Title for AlertDialog
				//alert.setTitle("Help");
				// Icon for AlertDialog
				alert.setIcon(R.drawable.icon);
				alert.show();
			}

		});

		complaintButton = (Button) findViewById(R.id.button2);
		complaintButton.setBackgroundResource(R.drawable.complaint_button_layers);
		complaintButton.setText("");
		complaintButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				// send message to bosses 
				/*TimerDialog dialog = new TimerDialog(parent);
				//dialog.setContentView(R.layout.timer);
				//TextProgressBar pb=(TextProgressBar)dialog.findViewById(R.id.progressBar1);
				//pb.setText("15");
				//dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,
	            //         LayoutParams.FILL_PARENT);
				dialog.show(); 
				 */

				if(true)return;//TODO
				LinearLayout q1 = new LinearLayout(getContext());
				q1.setOrientation(LinearLayout.HORIZONTAL);


				LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);



				Dialog dialog = new Dialog(parent/*, android.R.style.Theme_Translucent_NoTitleBar/*, R.style.popupStyle*/); 
/*
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View v1=inflater.inflate(R.layout.complaint, q1);
				View v2=inflater.inflate(R.layout.complaint_info, q1);

				final ViewFlipper viewFlipper = new ViewFlipper(_context);

				viewFlipper.addView(v1);
				viewFlipper.addView(v2);

				q1.addView(viewFlipper, params2);
				dialog.setContentView(q1);
*/
				dialog.setContentView(R.layout.complaint);
				dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
				dialog.show(); 
				//*/
				//final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				//viewFlipper.showNext();
			}

		});
		
		quitButton = (Button) this.findViewById(R.id.button3);
		quitButton.setBackgroundResource(R.drawable.stop_button_layers);
		quitButton.setText("");
		quitButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				n=-1;
				mProgress.setProgress(0);
				viewFlipper.removeAllViews();
				//parent.q.initQuestionnaire();
				//showNextScreen();
				// show outro view
				final ViewFlipper parentFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				parentFlipper.showNext();
				
				stopQuiz();
				
				return;
			}

		});
	}

}
