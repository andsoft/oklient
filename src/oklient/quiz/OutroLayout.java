package oklient.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class OutroLayout extends LinearLayout {
final OklientActivity parent;
	
	public OutroLayout(Context context, OklientActivity _parent) {
        super(context);
        parent=_parent;
        initComponent();
    }

    private void initComponent() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.outro, this);
        
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener()
		{

			//@Override
			public void onClick(View v) {
				final ViewFlipper viewFlipper = (ViewFlipper)parent.findViewById(R.id.viewFlipper);
				viewFlipper.showNext();
			}

		});
        
    }
}
