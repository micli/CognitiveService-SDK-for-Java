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

package com.github.micli.cognitiveservice.api.emotion;

import java.net.URI;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.github.micli.cognitiveservice.api.common.CognitiveWebAPIBaseClass;
import com.github.micli.cognitiveservice.api.common.CognitiveWebAPIError;
import com.github.micli.cognitiveservice.api.common.HTTPVerb;
import com.github.micli.cognitiveservice.api.common.RawResult;
import com.github.micli.cognitiveservice.api.common.URIUtils;
import com.github.micli.cognitiveservice.api.face.FaceRectangle;
import com.github.micli.cognitiveservice.api.result.ObjectResult;

public class Emotion extends CognitiveWebAPIBaseClass {
	
	public Emotion(URI endPoint, String accessKey) {
		super(endPoint, accessKey);
		// TODO Auto-generated constructor stub
	}
	// Recognize Face from Internet resource file.
	public ObjectResult<List<EmotionValue>> recognizeFromInternetURL(String imageUri) {
		return recognizeFromInternetURL(imageUri, null);
	}
	// Recognize Face from local file.
	public ObjectResult<List<EmotionValue>> recognizeFromLocalFile(String filePath) {
		return recognizeFromLocalFile(filePath, null);
	}	
	// Recognize Face with faceRectangle from Internet resource file.
	public ObjectResult<List<EmotionValue>> recognizeFromInternetURL(
			String imageUri, List<FaceRectangle> faceRectangles) {
		StringBuffer buff = new StringBuffer();
		if(null != faceRectangles) {
			for(FaceRectangle fr : faceRectangles) {
				buff.append(fr.toString());
				buff.append(";");
			}			
		}
		String relativeURI = "/recognize";
		if(buff.length() > 0) {
			buff = buff.delete(buff.length() - 1, buff.length());
			relativeURI = relativeURI + "?faceRectangles=" + buff.toString();
		}
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		String jsonContent = String.format("{ \"url\": \"%s\" }", imageUri);
		RawResult rawResult = requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<List<EmotionValue>>(true, 
					JSON.parseArray(rawResult.getRawJSON(), EmotionValue.class));
		}
		else {
			ObjectResult<List<EmotionValue>> result = 
					new ObjectResult<List<EmotionValue>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}
	}
	// Recognize Face with faceRectangle from local file.
	public ObjectResult<List<EmotionValue>> recognizeFromLocalFile(
			String filePath, List<FaceRectangle> faceRectangles) {
		StringBuffer buff = new StringBuffer();
		if(null != faceRectangles) {
			for(FaceRectangle fr : faceRectangles) {
				buff.append(fr.toString());
				buff.append(";");
			}			
		}
		String relativeURI = "/recognize";
		if(buff.length() > 0) {
			buff.delete(buff.length() - 1, buff.length());
			relativeURI = relativeURI + "?faceRectangles=" + buff.toString();
		}
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithFileContent(HTTPVerb.POST, requestURI, filePath);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<List<EmotionValue>>(true, 
					JSON.parseArray(rawResult.getRawJSON(), EmotionValue.class));
		}
		else {
			ObjectResult<List<EmotionValue>> result = 
					new ObjectResult<List<EmotionValue>>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}		
	}	

}
