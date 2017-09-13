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
import com.github.micli.cognitiveservice.api.result.StringValueResult;

public final class FaceList extends CognitiveWebAPIBaseClass {

	public FaceList(URI endPoint, String accessKey) {
		super(endPoint, accessKey);
	}
	// Create Face List
	public BooleanResult addFaceList(String faceListId, String faceListName, String userData) {
		String relativeURI = "/facelists/" + URIUtils.encode(faceListId).toLowerCase();
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
        String jsonContent = String.format("{\"name\":\"%s\",\"userData\":\"%s\"}", 
        				faceListName, userData);
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
	// Delete Face List
	public BooleanResult deleteFaceList(String faceListId) {
		String relativeURI = "/facelists/" + URIUtils.encode(faceListId).toLowerCase();
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
	// Delete Face from Face List
	public BooleanResult deleteFaceFromFaceList(String faceListId, String persistedFaceId) {
		String relativeURI = String.format("/facelists/%s/persistedFaces/%s", 
				URIUtils.encode(faceListId).toLowerCase(), 
				URIUtils.encode(persistedFaceId).toLowerCase());
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
	// Create Face List
	public BooleanResult updateFaceList(String faceListId, String faceListName, String userData) {
		String relativeURI = "/facelists/" + URIUtils.encode(faceListId).toLowerCase();
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
        String jsonContent = String.format("{\"name\":\"%s\",\"userData\":\"%s\"}", 
        				faceListName, userData);
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
	// Get a Face List
	public ObjectResult<FaceListEntity> getFaceList(String faceListId) {
		String relativeURI = "/facelists/" + URIUtils.encode(faceListId).toLowerCase();
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.GET, requestURI, null);
		if(rawResult.isSucceeded())
			return new ObjectResult<FaceListEntity>(true,
					JSON.parseObject(rawResult.getRawJSON(), FaceListEntity.class));
		else {
			ObjectResult<FaceListEntity> result = new ObjectResult<FaceListEntity>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
			 		CognitiveWebAPIError.class));
			return result;
		}		
	}
	// Get Face Lists of current account.
	public ObjectResult<List<FaceListEntity>> getAllFaceList() {
		String relativeURI = "/facelists";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.GET, requestURI, null);
		if(rawResult.isSucceeded())
			return new ObjectResult<List<FaceListEntity>>(true,
					JSON.parseArray(rawResult.getRawJSON(), FaceListEntity.class));
		else {
			ObjectResult<List<FaceListEntity>> result = new ObjectResult<List<FaceListEntity>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
			 		CognitiveWebAPIError.class));
			return result;
		}			
	}
	// Add Face To Face List.
	public StringValueResult addFaceToFaceListFromURI(
			String faceListId, String imageURI, String userData, FaceRectangle faceRect) {
		StringBuffer buff = new StringBuffer(String.format("facelists/%s/persistedFaces", faceListId));
		if(null != userData && "" == userData) {
			buff.append("?userData=");
			buff.append(URIUtils.encode(userData));
		}
		if(null != faceRect) {
			if('/' == buff.charAt(buff.length() - 1))
				buff.append("?");
			else
				buff.append("&");
			buff.append("targetFace=");
			buff.append(faceRect.toString());
		}
		URI requestURI = URIUtils.combine(getServiceEndPoint(), buff.toString());
		String jsonContent = String.format("{\"url\":\"%s\"}", imageURI);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		if(rawResult.isSucceeded())
			return new StringValueResult(true, 
					retrieveSingleValue(rawResult.getRawJSON()));
		else {
			StringValueResult result = new StringValueResult(false, "");
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
			 		CognitiveWebAPIError.class));
			return result;
		}			
		
	}	
	// Add Face To Face List.
	public StringValueResult addFaceToFaceListFromLocalFile(
			String faceListId, String filePath, String userData, FaceRectangle faceRect) {
		StringBuffer buff = new StringBuffer(String.format("facelists/%s/persistedFaces", faceListId));
		if(null != userData && "" == userData) {
			buff.append("?userData=");
			buff.append(URIUtils.encode(userData));
		}
		if(null != faceRect) {
			if('/' == buff.charAt(buff.length() - 1))
				buff.append("?");
			else
				buff.append("&");
			buff.append("targetFace=");
			buff.append(faceRect.toString());
		}
		URI requestURI = URIUtils.combine(getServiceEndPoint(), buff.toString());
		RawResult rawResult = this.requestWebAPIWithFileContent(HTTPVerb.POST, requestURI, filePath);
		if(rawResult.isSucceeded())
			return new StringValueResult(true, 
					retrieveSingleValue(rawResult.getRawJSON()));
		else {
			StringValueResult result = new StringValueResult(false, "");
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
			 		CognitiveWebAPIError.class));
			return result;
		}			
		
	}
}
