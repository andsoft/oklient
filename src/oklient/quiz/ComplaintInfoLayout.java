package oklient.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.ViewFlipper;

public class ComplaintInfoLayout extends TableLayout {
	final ComplaintLayout parent;
	
	public ComplaintInfoLayout(Context context, ComplaintLayout _parent) {
        super(context);
        parent=_parent;
        initComponent();
    }

    private void initComponent() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.complaint_info, this);
/* 
        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                
            	final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				viewFlipper.showNext();
				parent.quiz_view.startQuiz();
                return false;
            }
        });*/
    }

}
