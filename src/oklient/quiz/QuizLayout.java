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
		mProgress.setProgress(n);


		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		
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
			l1.setOrientation(LinearLayout.VERTICAL);
			//l1.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

			TextView text= new TextView(_context);
			text.setText("How mach you spend");
			text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			text.setTextSize(24);
			
			TextView hint= new TextView(_context);
			hint.setText("Select option");
			hint.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

			tv1 = new Button(/*OklientActivity.this*/_context);
			tv2 = new Button(/*OklientActivity.this*/_context);
			tv3 = new Button(/*OklientActivity.this*/_context);
			//tv1.setBackgroundColor(0xFF00FF00);
			//tv1.invalidate();

			tv1.setText("option 1111");
			tv2.setText("option 2222");
			tv3.setText("option 3333333333\nsdfsdfsf");

			LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

			l1.addView(text, params2);
			l1.addView(tv1, params2);
			l1.addView(tv2, params2);
			l1.addView(tv3, params2);
			l1.addView(hint, new LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			//l1.addView(button1, params);
			viewFlipper.addView(l1);
		}
		else if(n/10==2){
			TextView text= new TextView(_context);
			text.setText("How mach you spend");
			text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			text.setTextSize(24);
			//sb=new SeekBar(_context); 
			//sb.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
			//		    LayoutParams.WRAP_CONTENT));// setMinimumWidth(200);
			//sb.layout(0,0, 100, 10);
			//l1.addView(sb, params);
			//*
			// Find Tablelayout defined in main.xml 
			TableLayout tl = new TableLayout(_context);
			tl.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tl.setStretchAllColumns(true);
			//tl.setLayoutParams(new LayoutParams(
			//        LayoutParams.FILL_PARENT,
			//        LayoutParams.FILL_PARENT));
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
			tr.addView(b1);
			tr.addView(b2);
			tr.addView(b3);
			tr.addView(b4);
			tr.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,
					150));
			// Add row to TableLayout.
			tl.addView(text, new TableRow.LayoutParams(
					LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			tl.addView(tr, new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			//tl.addView(sb, new TableRow.LayoutParams(
			//         LayoutParams.WRAP_CONTENT,
			//         LayoutParams.WRAP_CONTENT));
			viewFlipper.addView(tl);
			//*/
			//viewFlipper.addView(l1);
		}
		else if(n/10==3){
			l1.setOrientation(LinearLayout.VERTICAL);

			TextView text= new TextView(_context);
			text.setText("How mach you spend");
			text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			text.setTextSize(24);
			
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


			l1.addView(text, params2);
			l1.addView(check1, params2);
			l1.addView(check2, params2);
			l1.addView(check3, params2);
			l1.addView(edit, params2);

			viewFlipper.addView(l1);
		}
		else if(n/10==4){
			LinearLayout  q1, q2;

			TextView text= new TextView(_context);
			text.setText("How mach you spend");
			text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			text.setTextSize(24);
			
			TextView q1_t, q2_t, q1_hint, q2_hint;
			q1_t=new TextView(/*this*/_context); 
			q2_t=new TextView(/*this*/_context);
			q1_hint=new TextView(/*this*/_context);
			q2_hint=new TextView(/*this*/_context);

			q1_t.setText("1111");
			q2_t.setText("2222");
			q1_hint.setText("hint 1111");
			q2_hint.setText("hint 2222");

			SeekBar sb1, sb2;
			sb1=new SeekBar(/*this*/_context); 
			sb1.setMinimumWidth(200);
			sb2=new SeekBar(/*this*/_context); 
			sb2.setMinimumWidth(200);


			//l = new LinearLayout(OklientActivity.this);
			q1 = new LinearLayout(/*this*/_context);
			q2 = new LinearLayout(/*this*/_context);
			//l.setOrientation(LinearLayout.VERTICAL);
			q1.setOrientation(LinearLayout.HORIZONTAL);
			q2.setOrientation(LinearLayout.HORIZONTAL);

			LayoutParams params2 = new LayoutParams(/*LayoutParams.WRAP_CONTENT*/100,
					LayoutParams.WRAP_CONTENT);
			q1.addView(q1_t, params2);
			q1.addView(sb1, params2);
			q1.addView(q1_hint, params2);

			q2.addView(q2_t, params2);
			q2.addView(sb2, params2);
			q2.addView(q2_hint, params);

			l1.setOrientation(LinearLayout.VERTICAL);
			l1.addView(text, params);
			l1.addView(q1, params);
			l1.addView(q2, params);
			viewFlipper.addView(l1);
		}
		else if(n/10==5){
			TextView text= new TextView(_context);
			text.setText("How mach you spend");
			text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			text.setTextSize(24);
			
			TextView text1= new TextView(_context);
			text1.setText("label 1");
			TextView text2= new TextView(_context);
			text2.setText("label 22222");
			
			check1 = new CheckBox(/*OklientActivity.this*//*this*/_context);
			check1.setText("I want to recieve results");
			
			edit=new EditText(/*OklientActivity.this*//*this*/_context);
			EditText edit2=new EditText(/*OklientActivity.this*//*this*/_context);
			
			TableLayout tl = new TableLayout(_context);
			tl.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

			// Create a new row to be added. 
			TableRow tr = new TableRow(_context);
			tr.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr.setBackgroundColor(Color.BLUE);
			tr.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			TableRow tr2 = new TableRow(_context);
			tr2.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
			//tr2.setBackgroundColor(Color.BLUE);
			tr2.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			
			
			tr.addView(text1);
			tr.addView(edit);
			tr2.addView(text2);
			tr2.addView(edit2);
			
			// Add row to TableLayout.
			tl.addView(text, new TableRow.LayoutParams(
					LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			tl.addView(tr, new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			tl.addView(tr2, new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			tl.addView(check1, new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));

			viewFlipper.addView(tl);
		}
		else{
			n=0;
			final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
			viewFlipper.showNext();
		}

		//viewFlipper.addView(l1);
		viewFlipper.showNext();
	}
	
	private void initComponent() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.quiz, this);


		mProgress = (ProgressBar) findViewById(R.id.progressBar1);

		viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);

		viewFlipper.setInAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_in_left);
		viewFlipper.setOutAnimation(/*OklientActivity.this*/_context, R.anim.view_transition_out_left);
		
		
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		

		Button nextButton = (Button) this.findViewById(R.id.nextButton);
		nextButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				
				getNextScreen();
				return;
/*				
				viewFlipper.setInAnimation(_context, R.anim.view_transition_in_left);
				viewFlipper.setOutAnimation(_context, R.anim.view_transition_out_left);
				viewFlipper.showNext();

				n+=10;
				mProgress.setProgress(n);


				LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT);
				LinearLayout l1;

				EditText edit;
				Button tv1, tv2, tv3;
				CheckBox check1,check2,check3;
				SeekBar sb;
				l1 = new LinearLayout(_context);

				l1.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
				l1.setOrientation(LinearLayout.HORIZONTAL);
				//l1.setBackgroundColor(0xFF00FFFF);
				l1.invalidate();
				if(n/10==1){
					l1.setOrientation(LinearLayout.VERTICAL);
					//l1.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

					TextView text= new TextView(_context);
					text.setText("How mach you spend");
					text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
					text.setTextSize(24);
					
					TextView hint= new TextView(_context);
					hint.setText("Select option");
					hint.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

					tv1 = new Button(_context);
					tv2 = new Button(_context);
					tv3 = new Button(_context);
					//tv1.setBackgroundColor(0xFF00FF00);
					//tv1.invalidate();

					tv1.setText("option 1111");
					tv2.setText("option 2222");
					tv3.setText("option 3333333333\nsdfsdfsf");

					LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

					l1.addView(text, params2);
					l1.addView(tv1, params2);
					l1.addView(tv2, params2);
					l1.addView(tv3, params2);
					l1.addView(hint, new LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
					//l1.addView(button1, params);
					viewFlipper.addView(l1);
				}
				else if(n/10==2){
					TextView text= new TextView(_context);
					text.setText("How mach you spend");
					text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
					text.setTextSize(24);
					//sb=new SeekBar(_context); 
					//sb.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					//		    LayoutParams.WRAP_CONTENT));// setMinimumWidth(200);
					//sb.layout(0,0, 100, 10);
					//l1.addView(sb, params);
					//*
					// Find Tablelayout defined in main.xml 
					TableLayout tl = new TableLayout(_context);
					tl.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
					//tl.setStretchAllColumns(true);
					//tl.setLayoutParams(new LayoutParams(
					//        LayoutParams.FILL_PARENT,
					//        LayoutParams.FILL_PARENT));
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
					tr.addView(b1);
					tr.addView(b2);
					tr.addView(b3);
					tr.addView(b4);
					tr.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,
							150));
					// Add row to TableLayout.
					tl.addView(text, new TableRow.LayoutParams(
							LayoutParams.FILL_PARENT,
							LayoutParams.WRAP_CONTENT));
					tl.addView(tr, new TableRow.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
					//tl.addView(sb, new TableRow.LayoutParams(
					//         LayoutParams.WRAP_CONTENT,
					//         LayoutParams.WRAP_CONTENT));
					viewFlipper.addView(tl);
					
					//viewFlipper.addView(l1);
				}
				else if(n/10==3){
					l1.setOrientation(LinearLayout.VERTICAL);

					TextView text= new TextView(_context);
					text.setText("How mach you spend");
					text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
					text.setTextSize(24);
					
					check1 = new CheckBox(_context);
					check2 = new CheckBox(_context);
					check3 = new CheckBox(_context);
					//tv1.setBackgroundColor(0xFF00FF00);
					//tv1.invalidate();

					check1.setText("option 1111");
					check2.setText("option 2222");
					check3.setText("option 3333");
					LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT);

					edit=new EditText(_context);


					l1.addView(text, params2);
					l1.addView(check1, params2);
					l1.addView(check2, params2);
					l1.addView(check3, params2);
					l1.addView(edit, params2);

					viewFlipper.addView(l1);
				}
				else if(n/10==4){
					LinearLayout  q1, q2;

					TextView text= new TextView(_context);
					text.setText("How mach you spend");
					text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
					text.setTextSize(24);
					
					TextView q1_t, q2_t, q1_hint, q2_hint;
					q1_t=new TextView(_context); 
					q2_t=new TextView(_context);
					q1_hint=new TextView(_context);
					q2_hint=new TextView(_context);

					q1_t.setText("1111");
					q2_t.setText("2222");
					q1_hint.setText("hint 1111");
					q2_hint.setText("hint 2222");

					SeekBar sb1, sb2;
					sb1=new SeekBar(_context); 
					sb1.setMinimumWidth(200);
					sb2=new SeekBar(_context); 
					sb2.setMinimumWidth(200);


					//l = new LinearLayout(OklientActivity.this);
					q1 = new LinearLayout(_context);
					q2 = new LinearLayout(_context);
					//l.setOrientation(LinearLayout.VERTICAL);
					q1.setOrientation(LinearLayout.HORIZONTAL);
					q2.setOrientation(LinearLayout.HORIZONTAL);

					LayoutParams params2 = new LayoutParams(100,
							LayoutParams.WRAP_CONTENT);
					q1.addView(q1_t, params2);
					q1.addView(sb1, params2);
					q1.addView(q1_hint, params2);

					q2.addView(q2_t, params2);
					q2.addView(sb2, params2);
					q2.addView(q2_hint, params);

					l1.setOrientation(LinearLayout.VERTICAL);
					l1.addView(text, params);
					l1.addView(q1, params);
					l1.addView(q2, params);
					viewFlipper.addView(l1);
				}
				else if(n/10==5){
					TextView text= new TextView(_context);
					text.setText("How mach you spend");
					text.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
					text.setTextSize(24);
					
					TextView text1= new TextView(_context);
					text1.setText("label 1");
					TextView text2= new TextView(_context);
					text2.setText("label 22222");
					
					check1 = new CheckBox(_context);
					check1.setText("I want to recieve results");
					
					edit=new EditText(_context);
					EditText edit2=new EditText(_context);
					
					TableLayout tl = new TableLayout(_context);
					tl.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

					// Create a new row to be added. 
					TableRow tr = new TableRow(_context);
					tr.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
					//tr.setBackgroundColor(Color.BLUE);
					tr.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					
					TableRow tr2 = new TableRow(_context);
					tr2.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
					//tr2.setBackgroundColor(Color.BLUE);
					tr2.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					
					
					tr.addView(text1);
					tr.addView(edit);
					tr2.addView(text2);
					tr2.addView(edit2);
					
					// Add row to TableLayout.
					tl.addView(text, new TableRow.LayoutParams(
							LayoutParams.FILL_PARENT,
							LayoutParams.WRAP_CONTENT));
					tl.addView(tr, new TableRow.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
					tl.addView(tr2, new TableRow.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
					tl.addView(check1, new TableRow.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));

					viewFlipper.addView(tl);
				}
				else if(n/10==6){
					
				}
				else{
					final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
					viewFlipper.showNext();
				}

				//viewFlipper.addView(l1);
				
				 */
			}

		});

		Button previousButton = (Button) this.findViewById(R.id.previousButton);
		previousButton.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				viewFlipper.setInAnimation(/*this*/_context, R.anim.view_transition_in_right);
				viewFlipper.setOutAnimation(/*this*/_context, R.anim.view_transition_out_right);
				viewFlipper.showPrevious();

				if(n>=10)n-=10;
				mProgress.setProgress(n);
			}

		});

		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				// show help
				AlertDialog.Builder builder = new AlertDialog.Builder(parent);
				builder.setMessage("This is help?\nsjdsdhas aksjdh asj ashd djdj  asjdksdh akj jd k kjd dh dh ahkj hkd dj")
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

		final Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener()
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

				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View v1=inflater.inflate(R.layout.complaint, q1);
				View v2=inflater.inflate(R.layout.complaint_info, q1);

				final ViewFlipper viewFlipper = new ViewFlipper(_context);

				viewFlipper.addView(v1);
				viewFlipper.addView(v2);

				q1.addView(viewFlipper, params2);
				dialog.setContentView(q1);

				//dialog.setContentView(R.layout.complaint);
				//dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,
				//         LayoutParams.FILL_PARENT);
				dialog.show(); 
				//*/
				//final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				//viewFlipper.showNext();
			}

		});
	}

}
