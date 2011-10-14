package oklient.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
 /*       
        final Button start_button = (Button) findViewById(R.id.buttonStart);
        start_button.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				viewFlipper.showNext();
			}

		});
 */       
        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                //show dialog here
            	final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				viewFlipper.showNext();
                return false;
            }
        });
    }

}
