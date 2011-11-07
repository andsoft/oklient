package oklient.quiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TimerDialog extends Dialog {
    // Timer 
    private Handler mHandler = new Handler();
    //
    private Runnable mUpdateTimeTask = new Runnable() 
    {
       public void run() 
       {
           // Do something 
           //Log.d( this.toString(), "Do something !");
    	   
    	   //TextProgressBar pb=(TextProgressBar)findViewById(R.id.progressBar1);
   			//pb.setText(String.valueOf(n));
    	   TextView t=(TextView)findViewById(R.id.textView3);
    	   t.setText(String.valueOf(n));
           // timer
           if(n>0)mHandler.postDelayed(mUpdateTimeTask, (1000)) ;
           else {
        	   mHandler.removeCallbacks(mUpdateTimeTask);
        	   cancel();
           }
           
           n--;
       }
    };
    int n=15;
	public TimerDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}

	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        // Hide the title bar 
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.fireworks);
    
        setContentView(R.layout.timer);
		
        this.getWindow().setBackgroundDrawableResource(R.drawable.edit_text_layers);
        
		//TextProgressBar pb=(TextProgressBar)findViewById(R.id.progressBar1);
		//pb.setText("15");
        TextView t=(TextView)findViewById(R.id.textView3);
		t.setTextColor(Color.BLACK);
        
		TextView t1=(TextView)findViewById(R.id.textView1);
		t1.setTextColor(Color.BLACK);
		TextView t2=(TextView)findViewById(R.id.textView2);
		t2.setTextColor(Color.BLACK);
		
		Button b=(Button)findViewById(R.id.button1);
		b.setBackgroundResource(R.drawable.small_blue_button_layers);
		b.setTextColor(Color.WHITE);
		b.setTextSize(20);
		b.setOnClickListener(new View.OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				cancel();
			}

		});
        // timer
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    @Override
    public void onStop() 
    {
        super.onStop();
        
        // timer 
        mHandler.removeCallbacks(mUpdateTimeTask);
    }
}
