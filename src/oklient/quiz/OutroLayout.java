package oklient.quiz;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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
        
        this.setBackgroundResource(R.drawable.background);
        
        TextView text=(TextView) this.findViewById(R.id.textView1);
        text.setTextSize(24);
		text.setTextColor(Color.BLACK);
        
		String str="";
        int startPos = 0;
        
        SpannableStringBuilder sb=new SpannableStringBuilder();
        
        // info1
        str=getContext().getResources().getString(R.string.outro_header);
        sb.append(str);
        startPos = sb.length() - str.length();
        sb.setSpan(new AbsoluteSizeSpan(22, true), startPos, sb.length(), 0);
        //sb.setSpan(new StyleSpan(Typeface.BOLD), startPos, sb.length(), 0);
        
        str=getContext().getResources().getString(R.string.outro_content);
        sb.append(str);
        startPos = sb.length() - str.length();
        sb.setSpan(new AbsoluteSizeSpan(16, true), startPos, sb.length(), 0);
		
        text.setText(sb);
        
        final Button button = (Button) findViewById(R.id.button1);
        button.setBackgroundResource(R.drawable.small_blue_button_layers);
        button.setTextColor(Color.WHITE);
        button.setText(R.string.button_close);
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
