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

// Notice:
// Due to the limitation of Cognitive Service free access key(20 calls per minutes, 30K calls per month).
// You could not run below test cases at one go. It might cause some of below cases occurs error randomly.
// If you want to run below test cases together, you'd better acquire a business level key to access Cognitive Service.
// If you just want to konw how to call these APIs. You can run below cases one by one.
//

package com.github.micli.cognitiveservice.api;

// This is not an unit test class. This class only mantains Cognitive Service access information. 
// Please access Azure manage portal and acquire access service end point and access key. 
// Please guarantee service end point and key are both available before doing unite test.
public class AccessInfo {
	
	private static String _faceURI = "https://api.cognitive.azure.cn/face/v1.0";
	private static String _faceAccessKey = "<input your access key here>";
	private static String _emotionURI = "https://api.cognitive.azure.cn/emotion/v1.0";
	private static String _emotionAccessKey = "<input your access key here>";
	private static String _visionURI = "https://api.cognitive.azure.cn/vision/v1.0";
	private static String _visionAccessKey = "0d951eb370c14a84b134f209e7c29ba9";
	
	public static String getFaceURI() {
		return _faceURI;
	}
	public static String getFaceAccessKey() {
		return _faceAccessKey;
	}
	public static String getEmotionURI() {
		return _emotionURI;
	}
	public static String getEmotionAccessKey() {
		return _emotionAccessKey;
	}
	public static String getVisionURI() {
		return _visionURI;
	}
	public static String getVisionAccessKey() {
		return _visionAccessKey;
	}
}
