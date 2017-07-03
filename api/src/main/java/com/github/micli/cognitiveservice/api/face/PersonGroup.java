// 
// Michael Li(http://www.github.com/micli). All rights reserved.
// Licensed under the MIT license.
// 
// Microsoft Cognitive Services: https://www.microsoft.com/cognitive-services
// 
// 
// Copyright (c) Microsoft Corporation
// All rights reserved.
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

public final class PersonGroup extends CognitiveWebAPIBaseClass{

	public PersonGroup(URI endPoint, String accessKey) {
		super(endPoint, accessKey);
	}
	// Add Person Group
	public BooleanResult addPersonGroup(String groupId, String data) {
		String relativeURI = "/persongroups/" + URIUtils.encode(groupId).toLowerCase();
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
        String jsonContent = String.format("{\"name\":\"%s\",\"userData\":\"%s\"}", 
        				groupId.toLowerCase(), data);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.PUT, requestURI, jsonContent);
		if(rawResult.isSucceeded())
			return new BooleanResult(true);
		else {
			BooleanResult result = new BooleanResult(false);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
			 		CognitiveWebAPIError.class));
			return result;
		}		
	}
	// Delete Person Group
	public BooleanResult deletePersonGroup(String groupId) {
		String relativeURI = "/persongroups/" + URIUtils.encode(groupId).toLowerCase();
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
	// Update Person Group
	public BooleanResult updatePersonGroup(String groupId, String data) {
		String relativeURI = "/persongroups/" + URIUtils.encode(groupId).toLowerCase();
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
        String jsonContent = String.format("{\"name\":\"%s\",\"userData\":\"%s\"}", 
        				groupId.toLowerCase(), data);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.PATCH, requestURI, jsonContent);
		if(rawResult.isSucceeded())
			return new BooleanResult(true);
		else {
			BooleanResult result = new BooleanResult(false);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
			return result;
		}		
	}
	// Get Person Group
	public ObjectResult<PersonGroupEntity> getPersonGroup(String groupId) {
		String relativeURI = "/persongroups/" + URIUtils.encode(groupId).toLowerCase();
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.GET, requestURI, null);
		ObjectResult<PersonGroupEntity> result = null;
		if(rawResult.isSucceeded()) {
			result = new ObjectResult<PersonGroupEntity>(true,
					JSON.parseObject(rawResult.getRawJSON(), PersonGroupEntity.class));
		}
		else {
			result = new ObjectResult<PersonGroupEntity>(false, new PersonGroupEntity());
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
		}
		return result;
	}
	// Get Person Group List
	public ObjectResult<List<PersonGroupEntity>> getPersonGroupList(String start, int top) {
		StringBuffer relativeURI = new StringBuffer("/persongroups");
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
		ObjectResult<List<PersonGroupEntity>> result = null;
		if(rawResult.isSucceeded()) {
			List<PersonGroupEntity> array = JSON.parseArray(
					rawResult.getRawJSON(), PersonGroupEntity.class);
			result = new ObjectResult<List<PersonGroupEntity>>(true, array);
		}
		else {
			result = new ObjectResult<List<PersonGroupEntity>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
		}
		return result;		
	}
	// Train Person Group
	public BooleanResult trainPersonGroup(String groupId) {
		String relativeURI = "/persongroups/" + URIUtils.encode(groupId).toLowerCase() + "/train";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, null);
	    BooleanResult result = null;
		if(rawResult.isSucceeded()) {
			result = new BooleanResult(true);
			return result;
		}
		else {
			result = new BooleanResult(false);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
			return result;
		}		
	}
	// Get Person Group Training Status
	public ObjectResult<TrainingStatus> getPersonGroupTrainingStatus(String groupId) {
		String relativeURI = "/persongroups/" + URIUtils.encode(groupId).toLowerCase() + "/train";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.GET, requestURI, null);
		ObjectResult<TrainingStatus> result = null;
		if(rawResult.isSucceeded()) {
			result = new ObjectResult<TrainingStatus>(true, 
					JSON.parseObject(rawResult.getRawJSON(), TrainingStatus.class));
		}
		else {
			result = new ObjectResult<TrainingStatus>(false, new TrainingStatus());
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
					CognitiveWebAPIError.class));
		}
		return result;
	}
}
