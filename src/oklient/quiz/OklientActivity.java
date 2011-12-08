package oklient.quiz;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import objects.OklientAPI;
import objects.Questionnaire;
import objects.Screen;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ViewFlipper;

public class OklientActivity extends Activity {
	QuizLayout quiz_view;
	IntroLayout intro_view;
	OutroLayout outro_view;

	private ViewFlipper viewFlipper;

	OklientAPI api;
	Questionnaire complaint;
	Questionnaire questionnaire;

	Bitmap bmImg;
	Bitmap bmImg2;	

	protected static final String PREFS_FILE = "preferences.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static final String PREFS_SERVER_ADDR = "server_addr";
    protected static final String PREFS_REGISTERED = "registered";
    
	private boolean m_bRegistered=false;
	private boolean m_bNeedUpdate=false;
	private boolean m_bReady=false;
	
	private String m_strServer; 
	private String m_strDeviceId;
	
	static final int m_nSyncPeriod_sec=60;
	private Handler mHandler = new Handler();
	//
	private Runnable mSyncTaskRunnable = new Runnable() 
	{
		public void run() 
		{
			// start sync task
			new SyncTask().execute("");
		}
	};

	private class SyncTask extends AsyncTask<String, Void, Bitmap> {
		
		protected Bitmap doInBackground(String... urls) {
/*
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/			
			// register device 
			if(!m_bRegistered) {	
				m_bRegistered=api.Register(); // TODO dont work!!!!
			}
			
			// load questionnaire
			api.UpdateQuestionnaire(getFilesDir()+"/"+"quiz");
			
			Questionnaire q_update=new Questionnaire();
			q_update.parseFile(getFilesDir()+"/"+"quiz");
			
		
			// load logo and welcome images
			api.LoadFile(q_update.logo, getFilesDir()+"/"+"logo");
			api.LoadFile(q_update.welcome_screen, getFilesDir()+"/"+"welcome");
			
			// decode images
			bmImg = BitmapFactory.decodeFile(getFilesDir()+"/"+"logo");
			bmImg2 = BitmapFactory.decodeFile(getFilesDir()+"/"+"welcome");

			// check if we need to update questionnaire
			if(questionnaire.id==null || !questionnaire.id.equals(q_update.id))
				m_bNeedUpdate=true;
			
			// send results
			String[] SavedFiles = getApplicationContext().fileList();
			for(String strFile: SavedFiles){
				if(strFile.indexOf("survey", 0)==0){
					
					if(api.SendFile(getFilesDir()+"/"+strFile));
						getApplicationContext().deleteFile(strFile);
				}
			}
			
			return null;//TODO//loadImageFromNetwork(urls[0]);
		}

		
		protected void onPostExecute(Bitmap result) {
			// TODO save m_bRegistered in preferences;
			final SharedPreferences prefs = getSharedPreferences( PREFS_FILE, 0);
			prefs.edit().putBoolean(PREFS_REGISTERED, m_bRegistered ).commit();
			
			intro_view.showProgress(false);
			intro_view.setImages(bmImg, bmImg2);
			
			m_bReady=true;
			
			// schedule next sync
			mHandler.postDelayed(mSyncTaskRunnable, m_nSyncPeriod_sec*1000) ;
		}
	}

	void startQuiz(){
		if(!m_bReady) return;
		
		if(m_bNeedUpdate) {
			questionnaire=new Questionnaire();
			questionnaire.parseFile(getFilesDir()+"/"+"quiz");
		}
		
		if(questionnaire.id==null) return; // TODO add message that no quiz available
		
		questionnaire.initQuestionnaire();
		complaint.initQuestionnaire();
		viewFlipper.showNext();
		quiz_view.startQuiz();
	}

	public void showPrevScreen() {
		viewFlipper.setInAnimation(this, R.anim.view_transition_in_right);
		viewFlipper.setOutAnimation(this, R.anim.view_transition_out_right);
		viewFlipper.showPrevious();
	}
	
	public void showNextScreen() {
		viewFlipper.setInAnimation(this, R.anim.view_transition_in_left);
		viewFlipper.setOutAnimation(this, R.anim.view_transition_out_left);
		viewFlipper.showNext();
	}
	
	private void loadPrefs() {
		Context context=this;
		final SharedPreferences prefs = context.getSharedPreferences( PREFS_FILE, 0);
		
		m_strDeviceId = prefs.getString(PREFS_DEVICE_ID, null );

		if (m_strDeviceId == null) {
			UUID uuid;
			 final String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

             // Use the Android ID unless it's broken, in which case fallback on deviceId,
             // unless it's not available, then fallback on a random number which we store
             // to a prefs file
             try {
                 if (!"9774d56d682e549c".equals(androidId)) {
                	 uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                 } else {
                     final String deviceId = ((TelephonyManager) context.getSystemService( Context.TELEPHONY_SERVICE )).getDeviceId();
                     uuid = deviceId!=null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                 }
             } catch (UnsupportedEncodingException e) {
                 throw new RuntimeException(e);
             }

             m_strDeviceId=uuid.toString();
             
			// Write the value out to the prefs file
			prefs.edit().putString(PREFS_DEVICE_ID, m_strDeviceId ).commit();
		}

		
		m_strServer = prefs.getString(PREFS_SERVER_ADDR, "oklient-dev.heroku.com" );
		
		m_bRegistered = prefs.getBoolean(PREFS_REGISTERED, false );
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);

		quiz_view = new QuizLayout(getApplicationContext(), this);
		intro_view = new IntroLayout(getApplicationContext(), this);
		outro_view = new OutroLayout(getApplicationContext(), this);

		viewFlipper.addView(intro_view);
		viewFlipper.addView(quiz_view);
		viewFlipper.addView(outro_view);	
		
		/////////////////////////////////////////////////////////////////
		
		// create questionnaire
		complaint=new Questionnaire();
		questionnaire=new Questionnaire();
		
		// load complaint
		try {
			InputStream is = getAssets().open("complaint.xml");
			complaint.parseFile(is);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
			
		// load preferences
		m_bRegistered=true; // TODO load from preferences
		m_strServer="http://oklient-dev.heroku.com";  // TODO from prefs 
		m_strDeviceId="andreev_123";
		loadPrefs();
		
		// create oklient api helper
		api=new OklientAPI(m_strServer, m_strDeviceId);
		
		// start synchronization
		mHandler.post(mSyncTaskRunnable);

	
		//String[] SavedFiles = getApplicationContext().fileList();
		//////////////////////////////////////////
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

	static final int DIALOG_INFO_ID = 0;
	static final int DIALOG_TIMER_ID = 1;
	static final int DIALOG_COMPLAINT_ID = 2;

	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch(id) {
		case DIALOG_INFO_ID:
			dialog = new InfoDialog(this);
			break;
		case DIALOG_TIMER_ID:
			dialog = new TimerDialog(this,this);
			break;
		case DIALOG_COMPLAINT_ID:
			dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen); 
			ComplaintLayout cl=new ComplaintLayout(this, this, dialog);
			cl.startQuiz();
			dialog.setContentView(cl);			
			WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();  
			lp.dimAmount=0.5f;  
			dialog.getWindow().setAttributes(lp); 
			dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			break;
		default:
			dialog = null;
		}
		return dialog;
	}
}