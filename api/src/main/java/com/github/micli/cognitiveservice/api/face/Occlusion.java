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

public final class Occlusion {
	
	private boolean foreheadOccluded;
	private boolean eyeOccluded;
	private boolean mouthOccluded;
	
	public boolean isForeheadOccluded() {
		return foreheadOccluded;
	}
	public void setForeheadOccluded(boolean foreheadOccluded) {
		this.foreheadOccluded = foreheadOccluded;
	}
	public boolean isEyeOccluded() {
		return eyeOccluded;
	}
	public void setEyeOccluded(boolean eyeOccluded) {
		this.eyeOccluded = eyeOccluded;
	}
	public boolean isMouthOccluded() {
		return mouthOccluded;
	}
	public void setMouthOccluded(boolean mouthOccluded) {
		this.mouthOccluded = mouthOccluded;
	}
	
	public Occlusion() {
		
	}
	public Occlusion(boolean foreheadOccluded, boolean eyeOccluded, boolean mouthOccluded) {
		this.foreheadOccluded = foreheadOccluded;
		this.eyeOccluded = eyeOccluded;
		this.mouthOccluded = mouthOccluded;
	}
}
