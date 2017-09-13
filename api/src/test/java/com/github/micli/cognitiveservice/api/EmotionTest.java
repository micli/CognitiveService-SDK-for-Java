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

package com.github.micli.cognitiveservice.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.micli.cognitiveservice.api.emotion.*;
import com.github.micli.cognitiveservice.api.face.FaceRectangle;
import com.github.micli.cognitiveservice.api.face.VerifyValue;
import com.github.micli.cognitiveservice.api.result.BooleanResult;
import com.github.micli.cognitiveservice.api.result.ObjectResult;
import com.github.micli.cognitiveservice.api.result.StringValueResult;


public class EmotionTest {

	private Emotion emotion;
	public EmotionTest() {
		try {
			emotion = new Emotion(new URI(AccessInfo.getEmotionURI()), AccessInfo.getEmotionAccessKey());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void EmotionURITest() {
		ObjectResult<List<EmotionValue>> result = 
				emotion.recognizeFromInternetURL(
						"https://micl.blob.core.windows.net/cstestimages/test-005.jpeg");
		ArrayList<FaceRectangle> faceRectList = new ArrayList<FaceRectangle>();
		for(EmotionValue ev : result.getInstance()) {
			// It's only for test.
			System.out.println(JSON.toJSON(ev));
			faceRectList.add(ev.getFaceRectangle());
			ObjectResult<List<EmotionValue>> result1 = 
					emotion.recognizeFromInternetURL(
							"https://micl.blob.core.windows.net/cstestimages/test-005.jpeg", faceRectList);
			for(EmotionValue ev1 : result1.getInstance()) {
				System.out.println(JSON.toJSON(ev1));
			}
		}
	}
	@Test
	public void EmotionLocalFileTest() {
		ObjectResult<List<EmotionValue>> result = 
				emotion.recognizeFromLocalFile(
						"/Users/micl/Documents/CognitiveService-SDK-for-Java/api/src/test/test-materials/test-005.jpeg");
		ArrayList<FaceRectangle> faceRectList = new ArrayList<FaceRectangle>();
		for(EmotionValue ev : result.getInstance()) {
			// It's only for test.
			System.out.println(JSON.toJSON(ev));
			faceRectList.add(ev.getFaceRectangle());
			ObjectResult<List<EmotionValue>> result1 = 
					emotion.recognizeFromLocalFile(
							"/Users/micl/Documents/CognitiveService-SDK-for-Java/api/src/test/test-materials/test-005.jpeg", faceRectList);
			for(EmotionValue ev1 : result1.getInstance()) {
				System.out.println(JSON.toJSON(ev1));
			}
		}
	}
}
