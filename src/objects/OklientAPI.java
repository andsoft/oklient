package objects;

import java.io.BufferedInputStream;
import java.io.File;
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
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import android.content.Context;

public class OklientAPI {
	
	private String strServer;
	private String strDeviceId;
	private String strRegURL;
	private String strGetQuestURL;
	private String strSendResultsURL;

	public String xml;
	
	private HttpClient httpclient;
	
	public OklientAPI(String server, String id) {
		strServer=server;
		strDeviceId=id;
		strRegURL="http://"+strServer+"/system/devices";
		strGetQuestURL=strRegURL+"/"+strDeviceId+"/questionnaire.xml";
		strSendResultsURL=strRegURL+"/"+strDeviceId+"/surveys";
		
		httpclient = new DefaultHttpClient();
	}
	
	// Register new device on the server
	public boolean Register() {
		HttpPost httppost = new HttpPost(strRegURL);

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
				return false;
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}

		return true;
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
	
	public void UpdateQuestionnaire(String file){
		//HttpClient httpclient = new DefaultHttpClient(); 
		LoadFile(strGetQuestURL, file);
	}
	
	public void SendResults(String xml_res)
	{
		// todo uri
        HttpPost httppost = new HttpPost(strSendResultsURL);

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("device[key]", strDeviceId));
            
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
	
	public boolean SendFile(String xml_res)
	{
		// todo uri
        HttpPost httppost = new HttpPost(strSendResultsURL);

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("device[key]", strDeviceId));
            
            //httppost.setEntity(new StringEntity(xml_res));

            FileEntity se = new FileEntity(new File(xml_res), "text/xml");

            se.setContentType("text/xml");  
            httppost.setHeader("Content-Type", "application/soap+xml;charset=UTF-8");
            httppost.setEntity(se);  
            //FileEntity entity = new FileEntity(new  File(filePath), "binary/octet-stream");
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            StatusLine sl=response.getStatusLine();
            
            if(sl.getStatusCode()!=200){
				// TODO handle error
            	return false;
			}
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        	return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	return false;
        } 	
        return true;
	}
	
	public boolean LoadFile(String url, String file){
		
		if(url==null || url.equals("") || file.equals(""))
			return false;
			
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

			

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		return true;
	}
}
