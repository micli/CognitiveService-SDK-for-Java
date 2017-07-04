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

package com.github.micli.cognitiveservice.api;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.micli.cognitiveservice.api.face.Face;
import com.github.micli.cognitiveservice.api.face.FaceAttributesOptions;
import com.github.micli.cognitiveservice.api.face.FaceDetectionData;
import com.github.micli.cognitiveservice.api.face.FaceGroupList;
import com.github.micli.cognitiveservice.api.face.FaceList;
import com.github.micli.cognitiveservice.api.face.IdentifyValue;
import com.github.micli.cognitiveservice.api.face.Person;
import com.github.micli.cognitiveservice.api.face.PersonEntity;
import com.github.micli.cognitiveservice.api.face.PersonGroup;
import com.github.micli.cognitiveservice.api.face.SimilarMode;
import com.github.micli.cognitiveservice.api.face.SimilarValue;
import com.github.micli.cognitiveservice.api.face.VerifyValue;
import com.github.micli.cognitiveservice.api.result.BooleanResult;
import com.github.micli.cognitiveservice.api.result.ObjectResult;
import com.github.micli.cognitiveservice.api.result.StringValueResult;


public final class FaceTest {

	private Face face;
	private PersonGroup pg;
	private Person p;
	private FaceList faceList;
	public FaceTest() {
		try {
			face = new Face(new URI(AccessInfo.getURI()), AccessInfo.getAccessKey());
			pg = new PersonGroup(new URI(AccessInfo.getURI()), AccessInfo.getAccessKey());
			p = new Person(new URI(AccessInfo.getURI()), AccessInfo.getAccessKey());
			faceList = new FaceList(new URI(AccessInfo.getURI()), AccessInfo.getAccessKey());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDetection() {
		List<FaceAttributesOptions> options = new ArrayList<FaceAttributesOptions>();
		// Below are full attributes.
		options.add(FaceAttributesOptions.Accessories);
		options.add(FaceAttributesOptions.Age);	
		options.add(FaceAttributesOptions.Blur);	
		options.add(FaceAttributesOptions.Emotion);	
		options.add(FaceAttributesOptions.Exposure);		
		options.add(FaceAttributesOptions.FacialHair);
		options.add(FaceAttributesOptions.Gender);	
		options.add(FaceAttributesOptions.Glasses);	
		options.add(FaceAttributesOptions.Hair);	
		options.add(FaceAttributesOptions.HeadPose);
		options.add(FaceAttributesOptions.Makeup);
		options.add(FaceAttributesOptions.Noise);
		options.add(FaceAttributesOptions.Occlusion);
		options.add(FaceAttributesOptions.Smile);
		try {
			ObjectResult<List<FaceDetectionData>> result1 = face.detectFromLocalFile(true, false, options, 
					"/Users/micl/Documents/CognitiveService-SDK-for-Java/api/src/test/test-materials/test-001.jpeg");
			if(!result1.isSucceeded())
				fail("Face Detection failed.");
			for(FaceDetectionData fd : result1.getInstance())
				System.out.println(JSON.toJSONString(fd));			
			
			ObjectResult<List<FaceDetectionData>> result2 = face.detectFromURL(true, true, options, 
					"https://micl.blob.core.windows.net/cstestimages/test-005.jpeg");
			if(!result2.isSucceeded())
				fail("Face Detection failed.");
			for(FaceDetectionData fd : result2.getInstance())
				System.out.println(JSON.toJSONString(fd));	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testFaceToFaceVerify() {
		// Step 1. Get faceId1 by Face Detect.
		ObjectResult<List<FaceDetectionData>> result1 = face.detectFromURL(true, false, null, 
				"https://micl.blob.core.windows.net/cstestimages/test-015.jpeg");
		if(!result1.isSucceeded())
			fail("face1 dectection failed.");
		// Step 2. Get faceId2 by Face Detect.
		ObjectResult<List<FaceDetectionData>> result2 = face.detectFromLocalFile(true, false, null, 
				"/Users/micl/Documents/CognitiveService-SDK-for-Java/api/src/test/test-materials/test-014.jpeg");
		if(!result2.isSucceeded())
			fail("face2 dectection failed." + result2.getError().getError().getMessage());
		// Step 3. Call Verify method.
		ObjectResult<VerifyValue> result3 = face.verify(
				result1.getInstance().get(0).getFaceId(), 
				result2.getInstance().get(0).getFaceId());
		// Step 4. Get verify result.
		if(!result3.getInstance().isIdentical())
			fail("face verification failed.");
		System.out.println(String.format("Confidence = %f", result3.getInstance().getConfidence()));
	}
	
	@Test
	public void testFaceToPersonVerify() {
		// Step 1. Get faceId1 by Face Detect.
		ObjectResult<List<FaceDetectionData>> result1 = face.detectFromURL(true, false, null, 
				"https://micl.blob.core.windows.net/cstestimages/test-015.jpeg");
		if(!result1.isSucceeded())
			fail("face1 dectection failed.");
		// Step 2. Create a Person Group.
		pg.deletePersonGroup("testGroup");
		BooleanResult result2 = pg.addPersonGroup("testGroup", "");
		if(!result2.isSucceeded())
			fail("PersonGroup creation failed.");
		// Step 3. Add a Person into Person Group.
		StringValueResult result3 = p.addPerson("testGroup", "Satya Nadella", "satyan@microsoft.com");
		if(!result3.isSucceeded())
			fail("Create Person failed.");
		// Step 4. Add a Face to Person.
		StringValueResult result4 = p.addPersonFaceFromLocalFile("testGroup", result3.getStringValue(), "", 
				"/Users/micl/Documents/CognitiveService-SDK-for-Java/api/src/test/test-materials/test-014.jpeg");
		// Step 5. Call Verify method.
		ObjectResult<VerifyValue> result5 = face.Verify(result1.getInstance().get(0).getFaceId(),
				"testGroup", result3.getStringValue());
		// Step 6. Get verify result.
		if(!result5.getInstance().isIdentical())
			fail("face verification failed.");
		System.out.println(String.format("Confidence = %f", result5.getInstance().getConfidence()));
	}
	
	@Test
	public void testFindSimilar1() {
		// Step 1. Get faceId1 by Face Detect.
		ObjectResult<List<FaceDetectionData>> result1 = face.detectFromURL(true, false, null, 
				"https://micl.blob.core.windows.net/cstestimages/test-015.jpeg");
		if(!result1.isSucceeded())
			fail("face1 dectection failed.");
		// Step 2. Get faceId2 by Face Detect.
		ObjectResult<List<FaceDetectionData>> result2 = face.detectFromLocalFile(true, false, null, 
				"/Users/micl/Documents/CognitiveService-SDK-for-Java/api/src/test/test-materials/test-014.jpeg");
		if(!result2.isSucceeded())
			fail("face2 dectection failed.");
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(result1.getInstance().get(0).getFaceId());
		arr.add(result2.getInstance().get(0).getFaceId());
		ObjectResult<List<SimilarValue>> result3 = face.findSimilar(result1.getInstance().get(0).getFaceId(),
				arr, 10, SimilarMode.matchFace);
		if(!result3.isSucceeded())
			fail("Find Similar failed.");
		for(SimilarValue s : result3.getInstance()) {
			System.out.println(String.format("Confidence = %f, ", s.getConfidence()));
		}
	}
	@Test
	public void testFindSimilar2() {
		// Step 1. Create Face List.
		String faceListId = UUID.randomUUID().toString();
		BooleanResult result1 = faceList.addFaceList(faceListId, "TestMyFaceList", "face++");
		if(!result1.isSucceeded())
			fail("Create Face List failed.");
		System.out.println(String.format("FaceListId = %s", faceListId));
		// Step 2. Add a face from Internet.
		StringValueResult result2 = faceList.addFaceToFaceListFromURI(
				faceListId, "https://micl.blob.core.windows.net/cstestimages/test-015.jpeg", 
				"This is second face that comes from Internet.", null);
		if(!result2.isSucceeded())
			fail("Add a face which is from Internet to Face List failed.");
		System.out.println(String.format("faceId2 = %s", result2.getStringValue()));
		// Step 2. Get faceId by Face Detect.
		ObjectResult<List<FaceDetectionData>> result3 = face.detectFromLocalFile(true, false, null, 
				"/Users/micl/Documents/CognitiveService-SDK-for-Java/api/src/test/test-materials/test-014.jpeg");
		if(!result3.isSucceeded())
			fail("face dectection failed.");
		ObjectResult<List<SimilarValue>> result4 = face.findSimilar(result3.getInstance().get(0).getFaceId(),
				faceListId, 10, SimilarMode.matchFace);
		if(!result4.isSucceeded())
			fail("Find Similar failed.");
		for(SimilarValue s : result4.getInstance()) {
			System.out.println(String.format("Confidence = %f, ", s.getConfidence()));
		}
	}
	
	@Test
	public void testGroup() {
		ObjectResult<List<FaceDetectionData>> result1 = face.detectFromURL(
				true, false, null, "https://micl.blob.core.windows.net/cstestimages/test-003.jpeg");
		ArrayList<String> faceIds = new ArrayList<String>();
		for(FaceDetectionData fd : result1.getInstance()) {
			faceIds.add(fd.getFaceId());
		}
		ObjectResult<FaceGroupList> result2 = face.group(faceIds);
		if(!result2.isSucceeded()) {
			fail("Grouping faces failed.");
		}
		List<List<String>> groups = result2.getInstance().getGroups();
		System.out.println(String.format("groups count = %d", groups.size()));
		List<String> messyGroup = result2.getInstance().getMessyGroup();
		System.out.println(String.format("Messy Group elements = %d", messyGroup.size()));
	}
	
	@Test
	public void testIdentify() {
		pg.deletePersonGroup("myTestGroup3");	
		// Step 1. Create a Person Group first.
		BooleanResult result1 = pg.addPersonGroup("myTestGroup3", "");
		if(!result1.isSucceeded()) 
			fail("PersonGroup creation failed");
		// Step 2. Add a Person into Person Group.
		StringValueResult result2 = p.addPerson("myTestGroup3", "Satya Nadella", "satyan@microsoft.com");
		if(!result2.isSucceeded())
			fail("Person creation failed");
		// Step 3. Get Person information by Person Id.
		ObjectResult<PersonEntity> result3 = p.getPerson("myTestGroup3", result2.getStringValue());
		if(!result3.isSucceeded())
			fail("Get Person information failed.");
		System.out.println(String.format("person name = %s \t userData = %s", 
				result3.getInstance().getName(), result3.getInstance().getUserData()));
		// Step 4. Add a person face from local file.
		StringValueResult result4 = p.addPersonFaceFromLocalFile("myTestGroup3", 
				result2.getStringValue(), "", 
				"/Users/micl/Documents/CognitiveService-SDK-for-Java/api/src/test/test-materials/test-014.jpeg");
		System.out.println(String.format("faceId = %s", result4.getStringValue()));
		// Step 5. Train the Person Group.
		pg.trainPersonGroup("myTestGroup3");
		ObjectResult<List<FaceDetectionData>> result5 = face.detectFromURL(true, 
				false, null, "https://micl.blob.core.windows.net/cstestimages/test-015.jpeg");
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(result5.getInstance().get(0).getFaceId());
		ObjectResult<List<IdentifyValue>> result6 = face.identify("myTestGroup3", arr, 1, 0.5f);
		if(!result6.isSucceeded())
			fail("Identify face failed." + result6.getError().getError().getMessage());
		pg.deletePersonGroup("myTestGroup3");	
	}
	
	@Test
	public void testJSONParse() {
		String jsonString = "[{\"faceId\":\"f2ab7468-5bfd-4f1b-a985-11da549f9bd9\",\"faceRectangle\":{\"top\":127,\"left\":250,\"width\":163,\"height\":163},\"faceAttributes\":{\"smile\":0.0,\"headPose\":{\"pitch\":0.0,\"roll\":0.0,\"yaw\":8.1},\"gender\":\"female\",\"age\":31.1,\"facialHair\":{\"moustache\":0.0,\"beard\":0.0,\"sideburns\":0.0},\"glasses\":\"NoGlasses\",\"emotion\":{\"anger\":0.091,\"contempt\":0.004,\"disgust\":0.694,\"fear\":0.0,\"happiness\":0.0,\"neutral\":0.002,\"sadness\":0.209,\"surprise\":0.0},\"blur\":{\"blurLevel\":\"low\",\"value\":0.0},\"exposure\":{\"exposureLevel\":\"goodExposure\",\"value\":0.71},\"noise\":{\"noiseLevel\":\"low\",\"value\":0.05},\"makeup\":{\"eyeMakeup\":true,\"lipMakeup\":true},\"accessories\":[],\"occlusion\":{\"foreheadOccluded\":false,\"eyeOccluded\":false,\"mouthOccluded\":false},\"hair\":{\"bald\":0.0,\"invisible\":false,\"hairColor\":[{\"color\":\"black\",\"confidence\":1.0},{\"color\":\"other\",\"confidence\":0.91},{\"color\":\"brown\",\"confidence\":0.24},{\"color\":\"gray\",\"confidence\":0.08},{\"color\":\"red\",\"confidence\":0.05},{\"color\":\"blond\",\"confidence\":0.01}]}}}]";
		JSONArray arr = JSON.parseArray(jsonString);
		FaceDetectionData fa = arr.getObject(0, FaceDetectionData.class);
		System.out.println(JSON.toJSONString(fa));
	}
}
