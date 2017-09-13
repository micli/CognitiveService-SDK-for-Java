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

import java.util.List;

public class Hair {

	private double bald;
	private Boolean invisible;
	private List<ColorValue> hairColor;
	
	public double getBald() {
		return bald;
	}
	public void setBald(double bald) {
		this.bald = bald;
	}
	public Boolean getInvisible() {
		return invisible;
	}
	public void setInvisible(Boolean invisible) {
		this.invisible = invisible;
	}
	public List<ColorValue> getHairColor() {
		return hairColor;
	}
	public void setHairColor(List<ColorValue> hairColor) {
		this.hairColor = hairColor;
	}
	
	public Hair() {
		
	}
}