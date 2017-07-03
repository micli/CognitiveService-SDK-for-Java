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

//
// This faceLandmarks will be returns from cognitive service face detection API. 
// Due to this class will be instanced by Alibaba fastJSON, all of propperties of this class would not be create instance at constructor.
// It's really really ugly!
//
public final class FaceLandmarks {
	
	private Point pupilLeft;
	private Point pupilRight;
	private Point noseTip;
	private Point mouthLeft;
	private Point mouthRight;
	private Point eyebrowLeftOuter;
	private Point eyebrowLeftInner;
	private Point eyeLeftOuter;
	private Point eyeLeftTop;
	private Point eyeLeftBottom;
	private Point eyeLeftInner;
	private Point eyebrowRightInner;
	private Point eyebrowRightOuter;
	private Point eyeRightInner;
	private Point eyeRightTop;
	private Point eyeRightBottom;
	private Point eyeRightOuter;
	private Point noseRootLeft;
	private Point noseRootRight;
	private Point noseLeftAlarTop;
	private Point noseRightAlarTop;
	private Point noseLeftAlarOutTip;
	private Point noseRightAlarOutTip;
	private Point upperLipTop;
	private Point upperLipBottom;
	private Point underLipTop;
	private Point underLipBottom;
	public Point getPupilLeft() {
		return pupilLeft;
	}
	public void setPupilLeft(Point pupilLeft) {
		this.pupilLeft = pupilLeft;
	}
	public Point getPupilRight() {
		return pupilRight;
	}
	public void setPupilRight(Point pupilRight) {
		this.pupilRight = pupilRight;
	}
	public Point getNoseTip() {
		return noseTip;
	}
	public void setNoseTip(Point noseTip) {
		this.noseTip = noseTip;
	}
	public Point getMouthLeft() {
		return mouthLeft;
	}
	public void setMouthLeft(Point mouthLeft) {
		this.mouthLeft = mouthLeft;
	}
	public Point getMouthRight() {
		return mouthRight;
	}
	public void setMouthRight(Point mouthRight) {
		this.mouthRight = mouthRight;
	}
	public Point getEyebrowLeftOuter() {
		return eyebrowLeftOuter;
	}
	public void setEyebrowLeftOuter(Point eyebrowLeftOuter) {
		this.eyebrowLeftOuter = eyebrowLeftOuter;
	}
	public Point getEyebrowLeftInner() {
		return eyebrowLeftInner;
	}
	public void setEyebrowLeftInner(Point eyebrowLeftInner) {
		this.eyebrowLeftInner = eyebrowLeftInner;
	}
	public Point getEyeLeftOuter() {
		return eyeLeftOuter;
	}
	public void setEyeLeftOuter(Point eyeLeftOuter) {
		this.eyeLeftOuter = eyeLeftOuter;
	}
	public Point getEyeLeftTop() {
		return eyeLeftTop;
	}
	public void setEyeLeftTop(Point eyeLeftTop) {
		this.eyeLeftTop = eyeLeftTop;
	}
	public Point getEyeLeftBottom() {
		return eyeLeftBottom;
	}
	public void setEyeLeftBottom(Point eyeLeftBottom) {
		this.eyeLeftBottom = eyeLeftBottom;
	}
	public Point getEyeLeftInner() {
		return eyeLeftInner;
	}
	public void setEyeLeftInner(Point eyeLeftInner) {
		this.eyeLeftInner = eyeLeftInner;
	}
	public Point getEyebrowRightInner() {
		return eyebrowRightInner;
	}
	public void setEyebrowRightInner(Point eyebrowRightInner) {
		this.eyebrowRightInner = eyebrowRightInner;
	}
	public Point getEyebrowRightOuter() {
		return eyebrowRightOuter;
	}
	public void setEyebrowRightOuter(Point eyebrowRightOuter) {
		this.eyebrowRightOuter = eyebrowRightOuter;
	}
	public Point getEyeRightInner() {
		return eyeRightInner;
	}
	public void setEyeRightInner(Point eyeRightInner) {
		this.eyeRightInner = eyeRightInner;
	}
	public Point getEyeRightTop() {
		return eyeRightTop;
	}
	public void setEyeRightTop(Point eyeRightTop) {
		this.eyeRightTop = eyeRightTop;
	}
	public Point getEyeRightBottom() {
		return eyeRightBottom;
	}
	public void setEyeRightBottom(Point eyeRightBottom) {
		this.eyeRightBottom = eyeRightBottom;
	}
	public Point getEyeRightOuter() {
		return eyeRightOuter;
	}
	public void setEyeRightOuter(Point eyeRightOuter) {
		this.eyeRightOuter = eyeRightOuter;
	}
	public Point getNoseRootLeft() {
		return noseRootLeft;
	}
	public void setNoseRootLeft(Point noseRootLeft) {
		this.noseRootLeft = noseRootLeft;
	}
	public Point getNoseRootRight() {
		return noseRootRight;
	}
	public void setNoseRootRight(Point noseRootRight) {
		this.noseRootRight = noseRootRight;
	}
	public Point getNoseLeftAlarTop() {
		return noseLeftAlarTop;
	}
	public void setNoseLeftAlarTop(Point noseLeftAlarTop) {
		this.noseLeftAlarTop = noseLeftAlarTop;
	}
	public Point getNoseRightAlarTop() {
		return noseRightAlarTop;
	}
	public void setNoseRightAlarTop(Point noseRightAlarTop) {
		this.noseRightAlarTop = noseRightAlarTop;
	}
	public Point getNoseLeftAlarOutTip() {
		return noseLeftAlarOutTip;
	}
	public void setNoseLeftAlarOutTip(Point noseLeftAlarOutTip) {
		this.noseLeftAlarOutTip = noseLeftAlarOutTip;
	}
	public Point getNoseRightAlarOutTip() {
		return noseRightAlarOutTip;
	}
	public void setNoseRightAlarOutTip(Point noseRightAlarOutTip) {
		this.noseRightAlarOutTip = noseRightAlarOutTip;
	}
	public Point getUpperLipTop() {
		return upperLipTop;
	}
	public void setUpperLipTop(Point upperLipTop) {
		this.upperLipTop = upperLipTop;
	}
	public Point getUpperLipBottom() {
		return upperLipBottom;
	}
	public void setUpperLipBottom(Point upperLipBottom) {
		this.upperLipBottom = upperLipBottom;
	}
	public Point getUnderLipTop() {
		return underLipTop;
	}
	public void setUnderLipTop(Point underLipTop) {
		this.underLipTop = underLipTop;
	}
	public Point getUnderLipBottom() {
		return underLipBottom;
	}
	public void setUnderLipBottom(Point underLipBottom) {
		this.underLipBottom = underLipBottom;
	}
	
	public FaceLandmarks() {
		
	}
}
