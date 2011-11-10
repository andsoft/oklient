package oklient.quiz;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ComplaintOutroLayout extends TableLayout {
final ComplaintLayout parent;
	
	public ComplaintOutroLayout(Context context, ComplaintLayout _parent) {
        super(context);
        parent=_parent;
        initComponent();
    }

    private void initComponent() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.complaint_outro, this);
        
        //this.setBackgroundResource(R.drawable.background);
 /*       
        TextView text=(TextView) this.findViewById(R.id.textView1);
        text.setTextSize(24);
		text.setTextColor(Color.BLACK);
        
        final Button button = (Button) findViewById(R.id.button1);
        button.setBackgroundResource(R.drawable.small_blue_button_layers);
        button.setTextColor(Color.WHITE);
        button.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				viewFlipper.showNext();
			}

		});
   */     
    }
}
