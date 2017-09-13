// 
// Michael Li(http://www.github.com/micli). All rights reserved.
// Licensed under the MIT license.
// 
// Microsoft Cognitive Services: https://www.microsoft.com/cognitive-services
// 
// 
// 
// MIT License:
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
// 
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED ""AS IS"", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//  

package com.github.micli.cognitiveservice.api.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class CognitiveWebAPIBaseClass implements ICognitiveServiceWebAPIRequest {

	private URI serviceEndPoint = null;
	private String accessKey = "";

	public URI getServiceEndPoint() {
		return serviceEndPoint;
	}
	public void setServiceEndPoint(URI serviceEndPoint) {
		this.serviceEndPoint = serviceEndPoint;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public CognitiveWebAPIBaseClass(URI endPoint, String  accessKey) {
		setServiceEndPoint(endPoint);
		this.setAccessKey(accessKey);
	}	
	public RawResult requestWebAPIWithJSONContent(HTTPVerb httpVerb, URI uri, String jsonContent) {
		RawResult result = null;
        HttpClient httpclient = HttpClients.createDefault();
        try
        {
            // Request body
        	if(null == jsonContent)
        		jsonContent = "";
            StringEntity reqEntity = new StringEntity(jsonContent);
        	HttpUriRequest request = getRequest(httpVerb, uri, reqEntity);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", accessKey);
            HttpResponse response = httpclient.execute(request);            
            result = getRawResultFromHttpResponse(response);
        }
        catch (Exception e) {
        	return new RawResult(-1, e.getMessage());
        }
		return result;
	}
	public RawResult requestWebAPIWithFileContent(HTTPVerb httpVerb, URI uri, String filePath) {
		RawResult result = null;
        HttpClient httpclient = HttpClients.createDefault();
        try {
            // Request body
        	File localFile = new File(filePath);
            if(!localFile.exists())
            	return null;
            FileInputStream inStream = new FileInputStream(localFile);
            InputStreamEntity reqEntity = new InputStreamEntity(
            		inStream, inStream.available(), ContentType.APPLICATION_OCTET_STREAM);
        	HttpUriRequest request = getRequest(httpVerb, uri, reqEntity);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", accessKey);
            HttpResponse response = httpclient.execute(request);      
            inStream.close();
            result = getRawResultFromHttpResponse(response);
        }
        catch (Exception e) {
        	return new RawResult(-1, e.getMessage());
        }
		return result;
	}
	
	private HttpUriRequest getRequest(HTTPVerb verb, URI uri, HttpEntity entity) {
		HttpUriRequest request = null;
		switch(verb) {
		case POST:
			HttpPost post = new HttpPost(uri);
			if(entity != null)
				post.setEntity((entity));
			request = post;
			break;
		case PUT:
			HttpPut put = new HttpPut(uri);
			if(entity != null)
				put.setEntity(entity);
			request = put;
			break;
		case GET:
			request = new HttpGet(uri);
			break;
		case DELETE:
			request = new HttpDelete(uri);
			break;			
		case PATCH:
			HttpPatch patch = new HttpPatch(uri);
			if(entity != null)
				patch.setEntity(entity);
			request = patch;
			break;
		default:
			return null;
		}
		return request;
	}	
	private RawResult getRawResultFromHttpResponse(HttpResponse response) 
			throws UnsupportedOperationException, IOException {
		RawResult result = null;	
        int status = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        InputStream contentStream = entity.getContent();
        byte[] buff = new byte[contentStream.available()];
        contentStream.read(buff, 0, contentStream.available());
        String rawData = new String(buff);
        contentStream.close();            
        result = new RawResult(status, rawData);
        return result;
	}
	// 
	// Because of create a class with only one String property is too expensive,
	// to use this method retrieve string value from simple JSON String.
	// 
	protected static String retrieveSingleValue(String jsonValue) {
		String[] array = jsonValue.split(":");
		if(array.length != 2)
			return "";
		return array[1].replace("\"", "").replace("}", "");
	}
}
