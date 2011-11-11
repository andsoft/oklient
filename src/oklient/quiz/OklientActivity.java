package oklient.quiz;

import java.io.IOException;
import java.io.InputStream;

import objects.OklientAPI;
import objects.Question;
import objects.Questionnaire;
import objects.QuestionnaireParser;
import objects.Screen;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ViewFlipper;

public class OklientActivity extends Activity {
	 QuizLayout quiz_view;
	 IntroLayout intro_view;
	 OutroLayout outro_view;
	 
	 OklientAPI api=new OklientAPI();
	 Questionnaire complaint=new Questionnaire();
	 Questionnaire q=new Questionnaire();
	 QuestionnaireParser qp=new QuestionnaireParser("http://oklient-dev.heroku.com/system/devices/andreev_123/questionnaire.xml");
	 Bitmap bmImg;
	 Bitmap bmImg2;	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


/////////////////////////////////////////////////////////////////
//api.Register();
api.UpdateQuestionnaire();
/*		
		// Programmatically load text from an asset and place it into the
        // text view.  Note that the text we are loading is ASCII, so we
        // need to convert it to UTF-16.
        try {
            InputStream is = getAssets().open("qestinnaire.xml");
            
            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();
            
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            
            // Convert the buffer into a string.
            api.xml = new String(buffer);
            
            // Finally stick the string into the text view.
            
        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        } 
	*/	
// TODO put this to assets and load
Screen scr=new Screen();
scr.title="Книга жалоб и предложений";
scr.hint="";
scr.id="1";

complaint.addScreen(scr);

Question quest=new Question();
quest.title= "Опишите жалобу или предложение:";
quest.id= "1";
quest.type= "string";
quest.style= "";
quest.keyboard= ""; // todo
complaint.addQuestion(quest);

Question quest_2=new Question();
quest_2.title= "Кратко озаглавьте, пожалуйста:";
quest_2.id= "2";
quest_2.type= "string";
quest_2.style= "";
quest_2.keyboard= ""; // todo
complaint.addQuestion(quest_2);

Question quest_3=new Question();
quest_3.title= "Я хочу получить ответ на свою жалобу/предложение";
quest_3.id= "3";
quest_3.type= "confirmation";
quest_3.style= "";
quest_3.keyboard= ""; // todo
complaint.addQuestion(quest_3);


Screen scr2=new Screen();
scr2.title="Книга жалоб и предложений";
scr2.hint="complaint hint2";
scr2.id="2";

complaint.addScreen(scr2);

Question quest2=new Question();
quest2.title= "Ваше имя:";
quest2.id= "2";
quest2.type= "string";
quest2.style= "";
quest2.keyboard= ""; // todo
complaint.addQuestion(quest2);

Question quest2_1=new Question();
quest2_1.title= "Телефон или e-mail:";
quest2_1.id= "2";
quest2_1.type= "string";
quest2_1.style= "";
quest2_1.keyboard= ""; // todo
complaint.addQuestion(quest2_1);

Screen scr3=new Screen();
scr3.title="Спасибо!";
scr3.hint="complaint hint3";
scr3.id="3";

complaint.addScreen(scr3);

Question quest3=new Question();
quest3.title= "В ближайшее время информация будет рассмотрена руководством";
quest3.id= "3";
quest3.type= "info"; // internal type
quest3.style= "";
quest3.keyboard= ""; // todo
complaint.addQuestion(quest3);
///////////////////////////////////////

q=qp.parse(api.xml);
String imagePath = getFilesDir() + "/" + "logo";
String imagePath2 = getFilesDir() + "/" + "welcome";
if(q.logo!=null && !q.logo.equals(""))
api.LoadFile(q.logo, imagePath);
bmImg = BitmapFactory.decodeFile(imagePath);//decodeStream(is);
//imView.setImageBitmap(bmImg);
if(q.welcome_screen!=null && !q.welcome_screen.equals(""))
api.LoadFile(q.welcome_screen, imagePath2);
bmImg2 = BitmapFactory.decodeFile(imagePath2);//decodeStream(is2);
//////////////////////////////////////////

		final ViewFlipper viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
		
		quiz_view = new QuizLayout(getApplicationContext(), this);
		intro_view = new IntroLayout(getApplicationContext(), this);
		outro_view = new OutroLayout(getApplicationContext(), this);
		
		viewFlipper.addView(intro_view);
		viewFlipper.addView(quiz_view);
		viewFlipper.addView(outro_view);		
	
		//when a view is displayed
		//viewFlipper.setInAnimation(this,android.R.anim.fade_in);
		//when a view disappears
		//viewFlipper.setOutAnimation(this, android.R.anim.fade_out);
	}
	
	   /**
     * Upon being resumed we can retrieve the current state.  This allows us
     * to update the state if it was changed at any time while paused.
     */
    @Override
    protected void onResume() {
        super.onResume();
/*
        SharedPreferences prefs = getPreferences(0); 
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            mSaved.setText(restoredText, TextView.BufferType.EDITABLE);

            int selectionStart = prefs.getInt("selection-start", -1);
            int selectionEnd = prefs.getInt("selection-end", -1);
            if (selectionStart != -1 && selectionEnd != -1) {
                mSaved.setSelection(selectionStart, selectionEnd);
            }
        }*/
    }

    /**
     * Any time we are paused we need to save away the current state, so it
     * will be restored correctly when we are resumed.
     */
    @Override
    protected void onPause() {
        super.onPause();
/*
        SharedPreferences.Editor editor = getPreferences(0).edit();
        editor.putString("text", mSaved.getText().toString());
        editor.putInt("selection-start", mSaved.getSelectionStart());
        editor.putInt("selection-end", mSaved.getSelectionEnd());
        editor.commit();*/
    }

}