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

package com.github.micli.cognitiveservice.api.face;

import java.net.URI;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.github.micli.cognitiveservice.api.common.CognitiveWebAPIBaseClass;
import com.github.micli.cognitiveservice.api.common.CognitiveWebAPIError;
import com.github.micli.cognitiveservice.api.common.HTTPVerb;
import com.github.micli.cognitiveservice.api.common.RawResult;
import com.github.micli.cognitiveservice.api.common.URIUtils;
import com.github.micli.cognitiveservice.api.result.BooleanResult;
import com.github.micli.cognitiveservice.api.result.ObjectResult;
import com.github.micli.cognitiveservice.api.result.StringValueResult;

public final class Person extends CognitiveWebAPIBaseClass {
	
	public Person(URI endPoint, String accessKey) {
		super(endPoint, accessKey);
	}
	// Add Person
	public StringValueResult addPerson(String groupId, String personName, String personData) {
		String relativeURI = String.format("/persongroups/%s/persons", 
				URIUtils.encode(groupId).toLowerCase());
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
        String jsonContent = String.format("{\"name\":\"%s\",\"userData\":\"%s\"}", 
        					personName, personData);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			String personId = retrieveSingleValue(rawResult.getRawJSON());
			return new StringValueResult(true, personId);
		}
		else {
			StringValueResult result = new StringValueResult(false, "");
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
			 		CognitiveWebAPIError.class));
			return result;
		}	
	}
	// Delete Person
	public BooleanResult delPersonFromGroup(String groupId, String personId) {
		String relativeURI = String.format("/persongroups/%s/persons/%s", 
				URIUtils.encode(groupId).toLowerCase(), personId);
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.DELETE, requestURI, null);
		if(rawResult.isSucceeded())
			return new BooleanResult(true);
		else {
			BooleanResult result = new BooleanResult(false);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
			return result;
		}			
	}
	public ObjectResult<PersonEntity> getPerson(String groupId, String personId) {
		String relativeURI = String.format("/persongroups/%s/persons/%s", 
				URIUtils.encode(groupId).toLowerCase(), personId);
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);		
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.GET, requestURI, null);
		if(rawResult.isSucceeded())
			return new ObjectResult<PersonEntity>(true, 
					JSON.parseObject(rawResult.getRawJSON(), PersonEntity.class));
		else {
			ObjectResult<PersonEntity> result = new ObjectResult<PersonEntity>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
			return result;
		}		
	}
	// Get Person Group List
	public ObjectResult<List<PersonEntity>> getPersonList(String groupId, String start, int top) {
		StringBuffer relativeURI = new StringBuffer(
				String.format("/persongroups/%s/persons", URIUtils.encode(groupId).toLowerCase()));
		if((start != "" && start != null) || top > 0)
			relativeURI.append("?");
		if(start != "" || start != null) 
			relativeURI.append("start=" + start);
		if(top > 0) {
			if(relativeURI.charAt(relativeURI.length() - 1) != '?')
				relativeURI.append('&');
			relativeURI.append("top=" + String.valueOf(top));
		}
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI.toString());
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.GET, requestURI, null);
		ObjectResult<List<PersonEntity>> result = null;
		if(rawResult.isSucceeded()) {
			List<PersonEntity> array = JSON.parseArray(
					rawResult.getRawJSON(), PersonEntity.class);
			result = new ObjectResult<List<PersonEntity>>(true, array);
		}
		else {
			result = new ObjectResult<List<PersonEntity>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
		}
		return result;		
	}
	// Update Person
	public BooleanResult updatePerson(String groupId, String personId, String personName, String personData) {
		String relativeURI = String.format("/persongroups/%s/persons/%s", 
				URIUtils.encode(groupId).toLowerCase(), personId);
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
        String jsonContent = String.format("{\"name\":\"%s\",\"userData\":\"%s\"}", 
        					personName, personData);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.PATCH, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			return new BooleanResult(true);
		}
		else {
			BooleanResult result = new BooleanResult(false);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
			 		CognitiveWebAPIError.class));
			return result;
		}	
	}
	// Add Person Face
	public StringValueResult addPersonFaceFromURL(String groupId, String personId, String data, URI imageURI) {

          	StringBuffer buff = new StringBuffer();
    		buff.append(String.format("/persongroups/%s/persons/%s/persistedFaces",
        			URIUtils.encode(groupId).toLowerCase(), personId));   	
        	if(null != data && "" != data)
        		buff.append("?" + URIUtils.encode(data));
        	URI requestURI = URIUtils.combine(getServiceEndPoint(), buff.toString());
        	String jsonContent = String.format("{\"url\":\"%s\"}", imageURI);
    		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
    		if(rawResult.isSucceeded()) {
    			return new StringValueResult(true, 
    					retrieveSingleValue(rawResult.getRawJSON()));
    		}
    		else {
    			StringValueResult result = new StringValueResult(false, "");
    			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
    			 		CognitiveWebAPIError.class));
    			return result;
    		}	
	}
	// Add Person Face From Local File
	public StringValueResult addPersonFaceFromLocalFile(String groupId, String personId, String data, String filePath) {

          	StringBuffer buff = new StringBuffer();
    		buff.append(String.format("/persongroups/%s/persons/%s/persistedFaces",
        			URIUtils.encode(groupId).toLowerCase(), personId));   	
        	if(null != data && "" != data)
        		buff.append("?" + URIUtils.encode(data));
        	URI requestURI = URIUtils.combine(getServiceEndPoint(), buff.toString());
    		RawResult rawResult = this.requestWebAPIWithFileContent(HTTPVerb.POST, requestURI, filePath);
    		if(null == rawResult) {
    			// local error occurred.
    			StringValueResult localErrorResult =  new StringValueResult(false, "");
    			localErrorResult.setError(new CognitiveWebAPIError("-1","read local file failed."));
    			return localErrorResult;
    		}
    		if(rawResult.isSucceeded()) {
    			return new StringValueResult(true, 
    					retrieveSingleValue(rawResult.getRawJSON()));
    		}
    		else {
    			StringValueResult result = new StringValueResult(false, "");
    			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
    			 		CognitiveWebAPIError.class));
    			return result;
    		}	
	}
	// Delete Person Face
	public BooleanResult deletePersonFace(String groupId, String personId, String faceId) {
		String relativeURI = String.format("persongroups/%s/persons/%s/persistedFaces/%s", 
				 URIUtils.encode(groupId).toLowerCase(), personId, faceId);
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
 		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.DELETE, requestURI, null);
 		if(rawResult.isSucceeded())
			return new BooleanResult(true);
		else {
			BooleanResult result = new BooleanResult(false);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
			return result;
		}
	}
	// Get Person Face
	public ObjectResult<PersistedFace> getPersonFace(String groupId, String personId, String faceId) {
		String relativeURI = String.format("persongroups/%s/persons/%s/persistedFaces/%s", 
				 URIUtils.encode(groupId).toLowerCase(), personId, faceId);
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.GET, requestURI, null);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<PersistedFace>(true, 
					JSON.parseObject(rawResult.getRawJSON(), PersistedFace.class));
		}
		else {
			ObjectResult<PersistedFace> result = new ObjectResult<PersistedFace>(false, 
					new PersistedFace("", ""));
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
			return result;
		}
	}
	// Update Person Face
	public BooleanResult updatePersonFace(String groupId, String personId, String faceId, String userData) {
		String relativeURI = String.format("persongroups/%s/persons/%s/persistedFaces/%s", 
				 URIUtils.encode(groupId).toLowerCase(), personId, faceId);
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		String jsonContent = String.format("{\"userData\":\"%s\"}", userData);		
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.PATCH, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			return new BooleanResult(true);
		}
		else {
			BooleanResult result = new BooleanResult(false);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
			return result;
		}	
	}
}