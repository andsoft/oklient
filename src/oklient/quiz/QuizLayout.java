package oklient.quiz;

import objects.Question;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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

	private Button nextButton;
	private Button prevButton;
	private ProgressBar mProgress;
	private ViewFlipper viewFlipper;

	// TODO remove
	int n=0;
	LinearLayout l1;
	LinearLayout l2;
	TextView tv1, tv2, tv3, tv4, tv5, tv6;

	private Context _context;

	public QuizLayout(Context context, OklientActivity _parent) {
		super(context);
		_context=context;
		parent=_parent;
		initComponent();
		
		getNextScreen();
	}
	
	private void getNextScreen() {
		n+=10;
		
		if(n/10>5){
			n=0;
			mProgress.setProgress(n);
			viewFlipper.removeAllViews();
			getNextScreen();
			// show outro view
			final ViewFlipper parentFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
			parentFlipper.showNext();
			return;
		}
		
		mProgress.setProgress(n);

		TextView title = new TextView(_context);
		title.setText("How mach you spend"); // TODO
		title.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		title.setTextSize(24);
		
		TextView hint = new TextView(_context);
		hint.setText("Select option"); // TODO
		hint.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

		TableLayout table = new TableLayout(_context);
		table.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		
		// add title
		table.addView(title, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
		LinearLayout l1;

		EditText edit;
		Button tv1, tv2, tv3;
		CheckBox check1,check2,check3;
		SeekBar sb;
		l1 = new LinearLayout(/*OklientActivity.this*/_context);

		l1.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		l1.setOrientation(LinearLayout.HORIZONTAL);
		//l1.setBackgroundColor(0xFF00FFFF);
		l1.invalidate();
		if(n/10==1){
			title.setText("Single choice question"); // TODO
			TableRow tr1 = new TableRow(_context);
			tr1.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr.setBackgroundColor(Color.BLUE);
			tr1.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			TableRow tr2 = new TableRow(_context);
			tr2.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr.setBackgroundColor(Color.BLUE);
			tr2.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			TableRow tr3 = new TableRow(_context);
			tr3.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr.setBackgroundColor(Color.BLUE);
			tr3.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			tv1 = new Button(/*OklientActivity.this*/_context);
			tv2 = new Button(/*OklientActivity.this*/_context);
			tv3 = new Button(/*OklientActivity.this*/_context);
			//tv1.setBackgroundColor(0xFF00FF00);
			//tv1.invalidate();
			tv1.setOnClickListener(new OnClickListener()
			{
				//@Override
				public void onClick(View v) {
					viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
					viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);	
					getNextScreen();
				}
			});
			tv2.setOnClickListener(new OnClickListener()
			{
				//@Override
				public void onClick(View v) {
					viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
					viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);	
					getNextScreen();
				}
			});
			tv3.setOnClickListener(new OnClickListener()
			{
				//@Override
				public void onClick(View v) {
					viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
					viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);	
					getNextScreen();
				}
			});

			tv1.setText("option 1111");
			tv2.setText("option 2222");
			tv3.setText("option 3333333333\nsdfsdfsf");

			LayoutParams params2 = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

			tr1.addView(tv1);
			tr2.addView(tv2);
			tr3.addView(tv3);
			
			table.addView(tr1, params2);
			table.addView(tr2, params2);
			table.addView(tr3, params2);
			
		}
		else if(n/10==2){
			title.setText("Single choice question\nspecial meaning"); // TODO
			// Create a new row to be added. 
			TableRow tr = new TableRow(_context);
			tr.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr.setBackgroundColor(Color.BLUE);
			tr.setLayoutParams(new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			// Create a Button to be the row-content. 
			Button b1 = new Button(_context);
			b1.setText("definitely\nNO");
			//b1.setBackgroundColor(Color.RED);
			b1.setLayoutParams(new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			Button b2 = new Button(_context);
			b2.setText("probably\nNO");
			//b2.setBackgroundColor(Color.MAGENTA);

			Button b3 = new Button(_context);
			b3.setText("probably\nYES");
			//b3.setBackgroundColor(Color.CYAN);

			//b.setGravity(Gravity.RIGHT);
			Button b4 = new Button(_context);
			b4.setText("definitely\nYES");
			//b4.setTextColor(Color.GREEN);
			// setBackgroundColor(Color.GREEN);
			b4.setLayoutParams(new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			//b1.setGravity(Gravity.LEFT);
			// Add Button to row.
			//tr.addView(sb);
			b1.setOnClickListener(new OnClickListener()
			{
				//@Override
				public void onClick(View v) {
					viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
					viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);	
					getNextScreen();
				}
			});
			b2.setOnClickListener(new OnClickListener()
			{
				//@Override
				public void onClick(View v) {
					viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
					viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);	
					getNextScreen();
				}
			});
			b3.setOnClickListener(new OnClickListener()
			{
				//@Override
				public void onClick(View v) {
					viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
					viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);	
					getNextScreen();
				}
			});
			b4.setOnClickListener(new OnClickListener()
			{
				//@Override
				public void onClick(View v) {
					viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
					viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);	
					getNextScreen();
				}
			});
			
			
			
			
			
			tr.addView(b1);
			tr.addView(b2);
			tr.addView(b3);
			tr.addView(b4);
			//tr.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,150));
			// Add row to TableLayout.
			table.addView(tr, new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			//tl.addView(sb, new TableRow.LayoutParams(
			//         LayoutParams.WRAP_CONTENT,
			//         LayoutParams.WRAP_CONTENT));
			
			//*/
			//viewFlipper.addView(l1);
		}
		else if(n/10==3){
			title.setText("Multiple choice question"); // TODO
			TableRow tr1 = new TableRow(_context);
			tr1.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			tr1.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			TableRow tr2 = new TableRow(_context);
			tr2.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			tr2.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			TableRow tr3 = new TableRow(_context);
			tr3.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			tr3.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

			TableRow tr4 = new TableRow(_context);
			tr4.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			tr4.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			
			check1 = new CheckBox(/*OklientActivity.this*//*this*/_context);
			check2 = new CheckBox(/*OklientActivity.this*//*this*/_context);
			check3 = new CheckBox(/*OklientActivity.this*//*this*/_context);
			//tv1.setBackgroundColor(0xFF00FF00);
			//tv1.invalidate();

			check1.setText("option 1111");
			check2.setText("option 2222");
			check3.setText("option 3333");
			LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);

			edit=new EditText(/*OklientActivity.this*//*this*/_context);
			edit.setHint("Other");
			
			tr1.addView(check1);
			tr2.addView(check2);
			tr3.addView(check3);
			tr4.addView(edit);
			
			table.addView(tr1, params2);
			table.addView(tr2, params2);
			table.addView(tr3, params2);
			table.addView(tr4, params2);

		}
		else if(n/10==4){
			title.setText("Range question"); // TODO
			// Create a new row to be added. 
			TableRow q1 = new TableRow(_context);
			q1.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr.setBackgroundColor(Color.BLUE);
			q1.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			TableRow q2 = new TableRow(_context);
			q2.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr.setBackgroundColor(Color.BLUE);
			q2.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			TableRow q3 = new TableRow(_context);
			q3.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr.setBackgroundColor(Color.BLUE);
			q3.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			
			TextView q1_t, q2_t, q3_t, q1_hint, q2_hint, q3_hint;
			q1_t=new TextView(/*this*/_context); 
			q2_t=new TextView(/*this*/_context);
			q3_t=new TextView(/*this*/_context);
			q1_hint=new TextView(/*this*/_context);
			q2_hint=new TextView(/*this*/_context);
			q3_hint=new TextView(/*this*/_context);

			q1_t.setText("1111");
			q2_t.setText("2222");
			q3_t.setText("333333");
			q1_hint.setText("hint 1");
			q2_hint.setText("hint 2");
			q3_hint.setText("hint 3");

			//LayoutParams params2 = new LayoutParams(/*LayoutParams.WRAP_CONTENT*/300,
			//		LayoutParams.WRAP_CONTENT);
			
			SeekBar sb1, sb2, sb3;
			sb1=new SeekBar(/*this*/_context);
			//sb1.setMinimumWidth(200);
			sb1.setLayoutParams(new TableRow.LayoutParams(200,LayoutParams.WRAP_CONTENT));
			sb2=new SeekBar(/*this*/_context);
			//sb2.setMinimumWidth(200);
			//sb2.setLayoutParams(params2);
			sb3=new SeekBar(/*this*/_context);
			

			
			
			q1.addView(q1_t);
			q1.addView(sb1);
			q1.addView(q1_hint);

			q2.addView(q2_t);
			q2.addView(sb2);
			q2.addView(q2_hint);

			q3.addView(q3_t);
			q3.addView(sb3);
			q3.addView(q3_hint);
			
			table.addView(q1, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			table.addView(q2, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			table.addView(q3, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
		}
		else if(n/10==5){
			title.setText("String question\nConfirmation"); // TODO
			TextView text1= new TextView(_context);
			text1.setText("label 1");
			TextView text2= new TextView(_context);
			text2.setText("label 22222");
			
			check1 = new CheckBox(/*OklientActivity.this*//*this*/_context);
			check1.setText("I want to recieve results");
			
			edit=new EditText(/*OklientActivity.this*//*this*/_context);
			EditText edit2=new EditText(/*OklientActivity.this*//*this*/_context);
			
			
			// Create a new row to be added. 
			TableRow tr = new TableRow(_context);
			tr.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr.setBackgroundColor(Color.BLUE);
			tr.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			TableRow tr2 = new TableRow(_context);
			tr2.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr2.setBackgroundColor(Color.BLUE);
			tr2.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			
			//edit.setHint("hint");
			edit.setMinimumWidth(200);
			edit2.setMinimumWidth(200);
			
			tr.addView(text1);
			tr.addView(edit);
			tr2.addView(text2);
			tr2.addView(edit2);
			
			// Add row to TableLayout.
			table.addView(tr, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			table.addView(tr2, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			table.addView(check1, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		}
		else{
			n=0;
			// show outro view
			final ViewFlipper parentFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
			parentFlipper.showNext();
		}

		// add hint
		table.addView(hint, new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		viewFlipper.addView(table);
		viewFlipper.showNext();
	}
	
	private void initComponent() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.quiz, this);

		this.setBackgroundResource(R.drawable.background);

		mProgress = (ProgressBar) findViewById(R.id.progressBar1);

		mProgress.setMax(60);
		mProgress.setProgressDrawable(getResources().getDrawable(R.drawable.progress_layers)); 
		
		viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);

		viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
		viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);
		
		View bottom = findViewById(R.id.relativeLayout1);
		bottom.setBackgroundResource(R.drawable.down_panel_background);
		
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
/*
		// add empty view to provide animated transition to the first view
		LinearLayout empty_view=new LinearLayout(_context);
		viewFlipper.addView(empty_view);
*/
		Button nextButton = (Button) this.findViewById(R.id.nextButton);
		nextButton.setBackgroundResource(R.drawable.forward_button_layers);
		nextButton.setText("");
		nextButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
				viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);
				
				getNextScreen();
			}

		});

		Button previousButton = (Button) this.findViewById(R.id.previousButton);
		previousButton.setBackgroundResource(R.drawable.back_button_layers);
		previousButton.setText("");
		previousButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				if(n>=20)n-=10;
				else return;
				mProgress.setProgress(n);
				
				viewFlipper.setInAnimation(/*this*/_context, R.anim.view_transition_in_right);
				viewFlipper.setOutAnimation(/*this*/_context, R.anim.view_transition_out_right);
				viewFlipper.showPrevious();
			}

		});

		final Button infoButton = (Button) findViewById(R.id.button1);
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
				alert.setTitle("Help");
				// Icon for AlertDialog
				alert.setIcon(R.drawable.icon);
				alert.show();
			}

		});

		final Button complaintButton = (Button) findViewById(R.id.button2);
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


				LinearLayout q1 = new LinearLayout(/*this*/_context);
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
		
		Button quitButton = (Button) this.findViewById(R.id.button3);
		quitButton.setBackgroundResource(R.drawable.stop_button_layers);
		quitButton.setText("");
		quitButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				n=0;
				mProgress.setProgress(n);
				viewFlipper.removeAllViews();
				getNextScreen();
				// show outro view
				final ViewFlipper parentFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				parentFlipper.showNext();
				return;
			}

		});
	}

}
