package oklient.quiz;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class IntroLayout extends LinearLayout {
	final OklientActivity parent;
	ImageView imViewWelcome;
    ImageView imViewLogo;
    ProgressBar pbar;
    TextView message;
    
	public IntroLayout(Context context, OklientActivity _parent) {
        super(context);
        parent=_parent;
        initComponent();
    }

    private void initComponent() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.intro, this);
        
        imViewWelcome = (ImageView) findViewById(R.id.main_logo);
        imViewLogo = (ImageView) findViewById(R.id.client_logo);
        pbar = (ProgressBar) findViewById(R.id.progressBar1);
        message = (TextView) findViewById(R.id.textView1);
        
        this.setImages(parent.bmImg, parent.bmImg2);
        
        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	parent.startQuiz();
                return false;
            }
        });
    }
    
    public void showProgress(boolean bShow) {
    	pbar.setVisibility(bShow? VISIBLE:INVISIBLE);
    	message.setVisibility(bShow? VISIBLE:INVISIBLE);
    }

    public void setImages(Bitmap logo, Bitmap welcome) {
    	imViewLogo.setImageBitmap(logo);
    	imViewWelcome.setImageBitmap(welcome);
    }
}
