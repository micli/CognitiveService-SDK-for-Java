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

public final class FaceDetectionData {

	private String faceId;
	private FaceRectangle faceRectangle;
	private FaceLandmarks faceLandmarks;
	private FaceAttributes faceAttributes;
	
	
	public String getFaceId() {
		return faceId;
	}
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	public FaceRectangle getFaceRectangle() {
		return faceRectangle;
	}
	public void setFaceRectangle(FaceRectangle faceRectangle) {
		this.faceRectangle = faceRectangle;
	}
	public FaceLandmarks getFaceLandmarks() {
		return faceLandmarks;
	}
	public void setFaceLandmarks(FaceLandmarks faceLandmarks) {
		this.faceLandmarks = faceLandmarks;
	}
	public FaceAttributes getFaceAttributes() {
		return faceAttributes;
	}
	public void setFaceAttributes(FaceAttributes faceAttributes) {
		this.faceAttributes = faceAttributes;
	}
	
	public FaceDetectionData() {
		
	}
}
