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

public class Rectangle {
	
	public Rectangle() {
		
	}
	public Rectangle(int left, int top, int width, int height) {
		this.top = top;
		this.left = left;
		this.width = width;
		this.height = height;
	}
	
	private int left;	
	private int top;
	private int width;
	private int height;

	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public static Rectangle FromString(String valueString) {
		if(!valueString.isEmpty()) {
			String[] box = valueString.split(",", 0);
			if(box.length == 4) {
				int top, left, width, height;
				left = Integer.parseInt(box[0]);
				top = Integer.parseInt(box[1]);
				width = Integer.parseInt(box[2]);
				height = Integer.parseInt(box[3]);
				return new Rectangle(left, top, width, height);
			}
		}
		return null;
	}
	public static String toString(Rectangle rect) {
		if(null != rect)
			return String.format("%d,%d,%d,%d", rect.left, rect.top, rect.width, rect.height);
		else
			return "";
	}
}
