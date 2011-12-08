package oklient.quiz;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class InfoDialog extends Dialog {
	public InfoDialog(Context context) {
		super(context/*, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen*/);
		
	}

	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        // Hide the title bar 
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    
        setContentView(R.layout.page); // TODO select color theme
        
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        
        String text="";
        int startPos = 0;
        
        SpannableStringBuilder sb=new SpannableStringBuilder();
        
        // info1
        text=getContext().getResources().getString(R.string.info1_header);
        sb.append(text);
        startPos = sb.length() - text.length();
        sb.setSpan(new AbsoluteSizeSpan(14, true), startPos, sb.length(), 0);
        //sb.setSpan(new StyleSpan(Typeface.BOLD), startPos, sb.length(), 0);
        
        text=getContext().getResources().getString(R.string.info1_content);
        sb.append(text);
        startPos = sb.length() - text.length();
        sb.setSpan(new AbsoluteSizeSpan(9, true), startPos, sb.length(), 0);
        
        // info2
        text=getContext().getResources().getString(R.string.info2_header);
        sb.append(text);
        startPos = sb.length() - text.length();
        sb.setSpan(new AbsoluteSizeSpan(14, true), startPos, sb.length(), 0);
        //sb.setSpan(new StyleSpan(Typeface.BOLD), startPos, sb.length(), 0);
        
        text=getContext().getResources().getString(R.string.info2_content);
        sb.append(text);
        startPos = sb.length() - text.length();
        sb.setSpan(new AbsoluteSizeSpan(9, true), startPos, sb.length(), 0);
        
        // info3
        text=getContext().getResources().getString(R.string.info3_header);
        sb.append(text);
        startPos = sb.length() - text.length();
        sb.setSpan(new AbsoluteSizeSpan(14, true), startPos, sb.length(), 0);
        //sb.setSpan(new StyleSpan(Typeface.BOLD), startPos, sb.length(), 0);
        
        text=getContext().getResources().getString(R.string.info3_content);
        sb.append(text);
        startPos = sb.length() - text.length();
        sb.setSpan(new AbsoluteSizeSpan(9, true), startPos, sb.length(), 0);
        
		TextView t1=(TextView)findViewById(R.id.textView1);
		//t1.setTextColor(Color.BLACK);
		t1.setText(sb);
		
		Button b=(Button)findViewById(R.id.button1);
		//b.setBackgroundResource(R.drawable.info_x_button_layers);
		b.setText("");
		b.setOnClickListener(new View.OnClickListener()
		{
			//@Override
			public void onClick(View v) {
				cancel();
			}
		});

		Button b2=(Button)findViewById(R.id.button2);
		//b2.setBackgroundResource(R.drawable.info_close_button_layers);
		b2.setText("");
		b2.setOnClickListener(new View.OnClickListener()
		{
			//@Override
			public void onClick(View v) {
				cancel();
			}
		});
    }

    @Override
    public void onStop() 
    {
        super.onStop();
    }
}
