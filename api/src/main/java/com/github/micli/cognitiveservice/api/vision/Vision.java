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

package com.github.micli.cognitiveservice.api.vision;

import java.net.URI;

import com.alibaba.fastjson.JSON;
import com.github.micli.cognitiveservice.api.common.CognitiveWebAPIBaseClass;
import com.github.micli.cognitiveservice.api.common.CognitiveWebAPIError;
import com.github.micli.cognitiveservice.api.common.HTTPVerb;
import com.github.micli.cognitiveservice.api.common.RawResult;
import com.github.micli.cognitiveservice.api.common.URIUtils;
import com.github.micli.cognitiveservice.api.result.ObjectResult;

public class Vision extends CognitiveWebAPIBaseClass {
	
	public Vision(URI endPoint, String accessKey) {
		super(endPoint, accessKey);
		// TODO Auto-generated constructor stub
	}

	public ObjectResult<OCRValue> OCRFromLocalFile(String filePath) {
		return OCRFromLocalFile("unk", filePath);
	}
	public ObjectResult<OCRValue> OCRFromLocalFile(String language, String filePath) {
		String relativeURI = String.format(
				"%s?language=%s&detectOrientation=%s", "/ocr", language, "true");
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		RawResult rawResult = requestWebAPIWithFileContent(HTTPVerb.POST, requestURI, filePath);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<OCRValue>(true, 
					JSON.parseObject(rawResult.getRawJSON(), OCRValue.class));
		}
		else {
			ObjectResult<OCRValue> result = 
					new ObjectResult<OCRValue>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}	
	}
	public ObjectResult<OCRValue> OCRFromInternetURL(String imageUri) {
		return OCRFromInternetURL("unk", imageUri);
	}
	public ObjectResult<OCRValue> OCRFromInternetURL(String language, String imageUri) {
		String relativeURI = String.format(
				"%s?language=%s&detectOrientation=%s", "/ocr", language, "true");
		URI requestURI = URIUtils.combine(getServiceEndPoint(), relativeURI);
		String jsonContent = String.format("{ \"url\": \"%s\" }", imageUri);
		RawResult rawResult = this.requestWebAPIWithJSONContent(HTTPVerb.POST, requestURI, jsonContent);
		if(rawResult.isSucceeded()) {
			return new ObjectResult<OCRValue>(true, 
					JSON.parseObject(rawResult.getRawJSON(), OCRValue.class));
		}
		else {
			ObjectResult<OCRValue> result = 
					new ObjectResult<OCRValue>(false, null);
			result.setError(JSON.parseObject(rawResult.getRawJSON(), CognitiveWebAPIError.class));
			return result;
		}	
	}
}
