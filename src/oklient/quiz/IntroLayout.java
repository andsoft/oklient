package oklient.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class IntroLayout extends LinearLayout {
	final OklientActivity parent;
	
	public IntroLayout(Context context, OklientActivity _parent) {
        super(context);
        parent=_parent;
        initComponent();
    }

    private void initComponent() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.intro, this);
        
        ImageView imView = (ImageView) findViewById(R.id.main_logo);
        imView.setImageBitmap(parent.bmImg2);
        
        ImageView imView2 = (ImageView) findViewById(R.id.client_logo);
        imView2.setImageBitmap(parent.bmImg);
 
        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                
            	final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				viewFlipper.showNext();
				parent.quiz_view.startQuiz();
                return false;
            }
        });
    }

}
