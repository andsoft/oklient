package objects;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import android.content.Context;

public class OklientAPI {
	
	final String strServer="http://oklient-dev.heroku.com";  // TODO from prefs 
	final String strDeviceId="andreev_123";
	final String strRegURL=strServer+"/system/devices";
	final String strGetQuestURL=strRegURL+"/"+strDeviceId+"/questionnaire.xml";

	public String xml;
	
	final HttpClient httpclient = new DefaultHttpClient();
	
	// Register new device on the server
	public void Register(){
		//HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(strServer+"/system/devices");

		try {
			// Add device[key] parameter 
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("device[key]", strDeviceId));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			StatusLine sl=response.getStatusLine();

			if(sl.getStatusCode()!=200){
				// TODO handle error
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	public void UpdateQuestionnaire(){
		//HttpClient httpclient = new DefaultHttpClient(); 
		HttpGet httpget = new HttpGet(strGetQuestURL);

		try {

			// Execute HTTP Get Request
			HttpResponse response = httpclient.execute(httpget);
			StatusLine sl=response.getStatusLine();

			if(sl.getStatusCode()!=200){
				// TODO handle error
			}

			HttpEntity httpEntity = response.getEntity();

			//passing the result into a string
			xml = EntityUtils.toString(httpEntity);  

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public void SendResults(String xml_res)
	{
		//HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://oklient-dev.heroku.com/system/devices/andreev_123/surveys");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("device[key]", "andreev_123"));
            
            //httppost.setEntity(new StringEntity(xml_res));

            StringEntity se = new StringEntity(xml_res, HTTP.UTF_8);

            se.setContentType("text/xml");  
            httppost.setHeader("Content-Type", "application/soap+xml;charset=UTF-8");
            httppost.setEntity(se);  
            //FileEntity entity = new FileEntity(new  File(filePath), "binary/octet-stream");
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            StatusLine sl=response.getStatusLine();
            
            if(sl.getStatusCode()!=200){
				// TODO handle error
			}
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } 		
	}
	
	public InputStream LoadFile(String url, String file){
		//HttpClient httpclient = new DefaultHttpClient(); 
		HttpGet httpget = new HttpGet(url);

		try {

			// Execute HTTP Get Request
			HttpResponse response = httpclient.execute(httpget);
			StatusLine sl=response.getStatusLine();

			if(sl.getStatusCode()!=200){
				// TODO handle error
			}

			HttpEntity httpEntity = response.getEntity();
	
			InputStream is=httpEntity.getContent();
			BufferedInputStream bis = new BufferedInputStream(is);
			 
             /*
              * Read bytes to the Buffer until there is nothing more to read(-1).
              */
             ByteArrayBuffer baf = new ByteArrayBuffer(50);
             int current = 0;
             while ((current = bis.read()) != -1) {
                     baf.append((byte) current);
             }

             /* Convert the Bytes read to a String. */
             FileOutputStream fos = new FileOutputStream(file);
             fos.write(baf.toByteArray());
             fos.close();
			
			return is;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
