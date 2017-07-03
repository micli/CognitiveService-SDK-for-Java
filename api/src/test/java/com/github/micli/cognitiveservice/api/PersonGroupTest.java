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

import java.net.URI;
import java.net.URISyntaxException;
import junit.framework.TestCase;
import org.junit.Test;

import com.github.micli.cognitiveservice.api.face.PersonGroup;
import com.github.micli.cognitiveservice.api.face.PersonGroupEntity;
import com.github.micli.cognitiveservice.api.face.TrainingStatus;
import com.github.micli.cognitiveservice.api.result.BooleanResult;
import com.github.micli.cognitiveservice.api.result.ObjectResult;



public class PersonGroupTest extends TestCase {
	private PersonGroup pg = null;
	
	public PersonGroupTest() {
		try {
			pg = new PersonGroup(new URI(AccessInfo.getURI()), AccessInfo.getAccessKey());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateGroup() throws URISyntaxException {
		BooleanResult result = pg.addPersonGroup("testGroupName3", "testdata-abc");
		if(!result.isSucceeded())
			fail("creation failed");		
		result = pg.updatePersonGroup("testGroupName3", "AAAA");
		if(!result.isSucceeded())
			fail("updated failed");
		ObjectResult<PersonGroupEntity> result1 = pg.getPersonGroup("testGroupName3");
		if(result1.getInstance().getPersonGroupId() == "")
			fail("Get person group failed");
		System.out.println(result1.getInstance().getPersonGroupId());
		System.out.println(result1.getInstance().getUserData());		
		result = pg.deletePersonGroup("testGroupName3");
		if(!result.isSucceeded())
			fail("delete failed");
		System.out.println("testUpdateGroup Completed");
	}	

	@Test
	public void testAddGroup() throws URISyntaxException {
		BooleanResult result = pg.addPersonGroup("testGroupName4", "testdata-abc");
		if(!result.isSucceeded())
			fail("creation failed");
		result = pg.deletePersonGroup("testGroupName4");
		if(!result.isSucceeded())
			fail("delete failed");
		System.out.println("testAddGroup Completed");
	}
	
	// This method can clear all test resources. Please uncomment this method and run when unit test occurs error.
	/*
	@Test
	public void clearAllGroupResources() throws URISyntaxException {
		BooleanResult result  = pg.delPersonGroup("testGroupName4");
		result  = pg.delPersonGroup("testGroupName3");
		result  = pg.delPersonGroup("testGroupName2");
		result  = pg.delPersonGroup("testGroupName1");
		result  = pg.delPersonGroup("testGroupName");		
		System.out.println("testdelGroup Completed");
	}
	*/
	/*
	@Test
	public void testGroupTrainingStatus() throws URISyntaxException {
		BooleanResult result = pg.addPersonGroup("testGroupName2", "testdata-abc");
		if(!result.isSucceeded())
			fail("creation failed");
		result = pg.trainPersonGroup("testGroupName2");
		if(!result.isSucceeded())
			fail("train failed");
		ObjectResult<TrainingStatus> result1 = pg.getPersonGroupTrainingStatus("testGroupName2");
		if(result1.getInstance().getStatus() == null)
			fail("Get training status failed");
		
		System.out.println("testGroupTrainingStatus Completed");	
	}
	*/
}
