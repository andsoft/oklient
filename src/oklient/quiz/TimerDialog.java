package oklient.quiz;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

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
    	   
    	   TextProgressBar pb=(TextProgressBar)findViewById(R.id.progressBar1);
   			pb.setText(String.valueOf(n));
   			
           // timer
           if(n>0)mHandler.postDelayed(mUpdateTimeTask, (1000)) ;
           else mHandler.removeCallbacks(mUpdateTimeTask);
           
           n--;
       }
    };
    int n=15;
	public TimerDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setContentView(R.layout.timer);
		
		TextProgressBar pb=(TextProgressBar)findViewById(R.id.progressBar1);
		pb.setText("15");
	}

	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        // Hide the title bar 
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.fireworks);
    
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
