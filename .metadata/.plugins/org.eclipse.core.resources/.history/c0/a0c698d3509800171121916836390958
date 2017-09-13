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

// Notice:
// Due to the limitation of Cognitive Service free access key(20 calls per minutes, 30K calls per month).
// You could not run below test cases at one go. It might cause some of below cases occurs error randomly.
// If you want to run below test cases together, you'd better acquire a business level key to access Cognitive Service.
// If you just want to konw how to call these APIs. You can run below cases one by one.
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
import com.github.micli.cognitiveservice.api.result.ObjectResult;

public final class Face extends CognitiveWebAPIBaseClass{

	public Face(URI endPoint, String accessKey) {
		super(endPoint, accessKey);
	}
	// Face Detect From URL.
	public ObjectResult<List<FaceDetectionData>> detectFromURL(boolean requrieReturnFaceId, 
			boolean requrieReturnFaceLandMarks, List<FaceAttributesOptions> faceAttributesOptions, String imageURI) {
		StringBuffer buff = new StringBuffer();
		if(!requrieReturnFaceId)
			buff.append("&returnFaceId=false");
		if(requrieReturnFaceLandMarks)
			buff.append("&returnFaceLandmarks=true");
		if(null != faceAttributesOptions) {
			buff.append("&returnFaceAttributes=");
			for(FaceAttributesOptions options : faceAttributesOptions) {
				buff.append(options.name().toLowerCase() + ",");
			}
		}
		String relativeURI = "";
		if(buff.length() > 0) {
			buff.delete(buff.length() - 1, buff.length());
			buff.delete(0, 1);
			relativeURI = String.format("/detect/?%s", buff.toString());
		}
		else
			relativeURI = "/detect";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		
		String jsonContent = String.format("{\"url\":\"%s\"}", imageURI);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<List<FaceDetectionData>>(true, 
					JSON.parseArray(rawResult.getRawJSON(), FaceDetectionData.class));
		}
		else {
			ObjectResult<List<FaceDetectionData>> result = new ObjectResult<List<FaceDetectionData>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
			 		CognitiveWebAPIError.class));
			return result;
		}
	}
	// Face Detect From Local File.
	public ObjectResult<List<FaceDetectionData>> detectFromLocalFile(boolean requrieReturnFaceId, 
			boolean requrieReturnFaceLandMarks, List<FaceAttributesOptions> faceAttributesOptions, String filePath) {
		StringBuffer buff = new StringBuffer();
		if(!requrieReturnFaceId)
			buff.append("&returnFaceId=false");
		if(requrieReturnFaceLandMarks)
			buff.append("&returnFaceLandmarks=true");
		if(null != faceAttributesOptions) {
			buff.append("&returnFaceAttributes=");
			for(FaceAttributesOptions options : faceAttributesOptions) {
				buff.append(options.name().toLowerCase() + ",");
			}
		}

		String relativeURI = "";
		if(buff.length() > 0) {
			buff.delete(buff.length() - 1, buff.length());
			buff.delete(0, 1);
			relativeURI = String.format("/detect/?%s", buff.toString());
		}
		else
			relativeURI = "/detect";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithFileContent(HTTPVerb.POST, requestURI, filePath);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<List<FaceDetectionData>>(true, 
					JSON.parseArray(rawResult.getRawJSON(), FaceDetectionData.class));
		}
		else {
			ObjectResult<List<FaceDetectionData>> result = new ObjectResult<List<FaceDetectionData>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), 
			 		CognitiveWebAPIError.class));
			return result;
		}
	}
	// Find Similar Face From Face List.
	public ObjectResult<List<SimilarValue>> findSimilar(String faceId, 
			String faceListId, int maxNumOfCandidatesReturned, SimilarMode mode) {
		String relativeURI = "/findsimilars";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		String jsonContent = String.format("{\"faceId\":\"%s\",\"faceListId\":\"%s\",\"maxNumOfCandidatesReturned\":\"%d\",\"mode\":\"%s\"}", 
				faceId, faceListId, maxNumOfCandidatesReturned, mode.name());
		RawResult rawResult = this.requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		ObjectResult<List<SimilarValue>> result = null;
		if(rawResult.isSucceeded()) {
			List<SimilarValue> arrayList = JSON.parseArray(rawResult.getRawJSON(), SimilarValue.class);
			result = new ObjectResult<List<SimilarValue>>(true, arrayList);
			return result;
		}
		else {
			result = new ObjectResult<List<SimilarValue>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}
	}
	// Find Similar Face From Face Ids.
	public ObjectResult<List<SimilarValue>> findSimilar(String faceId, 
			List<String> faceIds, int maxNumOfCandidatesReturned, SimilarMode mode) {
		String relativeURI = "/findsimilars";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		String jsonContent = String.format("{\"faceId\":\"%s\",\"faceIds\":%s,\"maxNumOfCandidatesReturned\":\"%d\",\"mode\":\"%s\"}", 
				faceId, JSON.toJSONString(faceIds), maxNumOfCandidatesReturned, mode.name());
		RawResult rawResult = this.requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		ObjectResult<List<SimilarValue>> result = null;
		if(rawResult.isSucceeded()) {
			List<SimilarValue> arrayList = JSON.parseArray(rawResult.getRawJSON(), SimilarValue.class);
			result = new ObjectResult<List<SimilarValue>>(true, arrayList);
			return result;
		}
		else {
			result = new ObjectResult<List<SimilarValue>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}
	}
	// Group faces.
	public ObjectResult<FaceGroupList> group(List<String> faceIds) {
		String relativeURI = "/group";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		String jsonContent = String.format("{\"faceIds\":%s}", JSON.toJSONString(faceIds));
		
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<FaceGroupList>(true, JSON.parseObject(
					rawResult.getRawJSON(), FaceGroupList.class));
		}
		else {
			ObjectResult<FaceGroupList> result = new ObjectResult<FaceGroupList>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}
	}
	// Identify.
	public ObjectResult<List<IdentifyValue>> identify(String groupId, List<String> faceIds, 
			int maxNumOfCandidatesReturned, float confidenceThreshold) {
		String relativeURI = "/identify";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		String jsonContent = String.format(
				"{\"personGroupId\":\"%s\",\"faceIds\":%s,\"maxNumOfCandidatesReturned\":%d,\"confidenceThreshold\": %f}", 
				URIUtils.encode(groupId).toLowerCase(), JSON.toJSONString(faceIds), 
				maxNumOfCandidatesReturned, confidenceThreshold);
		RawResult rawResult = this.requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<List<IdentifyValue>>(true, 
					JSON.parseArray(rawResult.getRawJSON(), IdentifyValue.class));
		}
		else {
			ObjectResult<List<IdentifyValue>> result = new ObjectResult<List<IdentifyValue>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}
	}
	// Verify Face to Face
	public ObjectResult<VerifyValue> verify(String faceId1, String faceId2) {
		String relativeURI = "/verify";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		String jsonContent = String.format("{\"faceId1\":\"%s\",\"faceId2\":\"%s\"}", faceId1, faceId2);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<VerifyValue>(true, 
					JSON.parseObject(rawResult.getRawJSON(), VerifyValue.class)); 
		}
		else {
			ObjectResult<VerifyValue> result = new ObjectResult<VerifyValue>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}
	}
	// Verify Face to Person.
	public ObjectResult<VerifyValue> Verify(String faceId, String groupId, String personId) {
		String relativeURI = "/verify";
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		String jsonContent = String.format("{\"faceId\":\"%s\",\"personId\":\"%s\", \"personGroupId\":\"%s\"}", 
				faceId, personId, URIUtils.encode(groupId).toLowerCase());
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<VerifyValue>(true, 
					JSON.parseObject(rawResult.getRawJSON(), VerifyValue.class)); 
		}
		else {
			ObjectResult<VerifyValue> result = new ObjectResult<VerifyValue>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}
	}
}
