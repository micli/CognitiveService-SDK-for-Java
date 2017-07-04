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

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import junit.framework.TestCase;
import org.junit.Test;

import com.github.micli.cognitiveservice.api.face.Person;
import com.github.micli.cognitiveservice.api.face.PersonEntity;
import com.github.micli.cognitiveservice.api.face.PersonGroup;
import com.github.micli.cognitiveservice.api.result.BooleanResult;
import com.github.micli.cognitiveservice.api.result.ObjectResult;
import com.github.micli.cognitiveservice.api.result.StringValueResult;

public class PersonTest {

	private Person person = null;
	private PersonGroup pg = null;
	public PersonTest() {
		try {
			person = new Person(new URI(AccessInfo.getFaceURI()), AccessInfo.getFaceAccessKey());
			pg = new PersonGroup(new URI(AccessInfo.getFaceURI()), AccessInfo.getFaceAccessKey());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAddandUpdatePerson() {
		// Step 1. Create a Person Group first.
		BooleanResult result1 = pg.addPersonGroup("myTestGroup", "");
		if(!result1.isSucceeded()) 
			fail("PersonGroup creation failed");
		// Step 2. Add a Person into Person Group.
		StringValueResult result2 = person.addPerson("myTestGroup", "Michael Li", "micl@microsoft.com");
		if(!result2.isSucceeded())
			fail("Person creation failed");
		// Step 3. Get Person information by Person Id.
		ObjectResult<PersonEntity> result3 = person.getPerson("myTestGroup", result2.getStringValue());
		if(!result3.isSucceeded())
			fail("Get Person information failed.");
		System.out.println(String.format("person name = %s \t userData = %s", 
				result3.getInstance().getName(), result3.getInstance().getUserData()));
		// Step 4. Update Person information.
		BooleanResult result4 = person.updatePerson("myTestGroup", result2.getStringValue(), "Michael Li (DX)", "li.zheng@microsoft.com");
		// Step 5. Get Person information again.
		ObjectResult<PersonEntity> result5 = person.getPerson("myTestGroup", result2.getStringValue());
		if(!result5.isSucceeded())
			fail("Get Person information failed.");
		System.out.println(String.format("person name = %s \t userData = %s", 
				result5.getInstance().getName(), result5.getInstance().getUserData()));	
		// Step 6. Delete a Person from Group.
		BooleanResult result6 = person.delPersonFromGroup("myTestGroup", result2.getStringValue());
		// Step 7. Delete Person Group finally.
		pg.deletePersonGroup("myTestGroup");
	}
	
	@Test
	public void testAddandUpdatePersonFace() throws URISyntaxException {
		pg.deletePersonGroup("myTestGroup1");	
		// Step 1. Create a Person Group first.
		BooleanResult result1 = pg.addPersonGroup("myTestGroup1", "");
		if(!result1.isSucceeded()) 
			fail("PersonGroup creation failed");
		// Step 2. Add a Person into Person Group.
		StringValueResult result2 = person.addPerson("myTestGroup1", "Satya Nadella", "satyan@microsoft.com");
		if(!result2.isSucceeded())
			fail("Person creation failed");
		// Step 3. Get Person information by Person Id.
		ObjectResult<PersonEntity> result3 = person.getPerson("myTestGroup1", result2.getStringValue());
		if(!result3.isSucceeded())
			fail("Get Person information failed.");
		System.out.println(String.format("person name = %s \t userData = %s", 
				result3.getInstance().getName(), result3.getInstance().getUserData()));
		// Step 4. Add a person face from local file.
		StringValueResult result4 = person.addPersonFaceFromLocalFile("myTestGroup1", 
				result2.getStringValue(), "", 
				"/Users/micl/Documents/CognitiveService-SDK-for-Java/api/src/test/test-materials/test-014.jpeg");
		System.out.println(String.format("faceId = %s", result4.getStringValue()));
		// Step 5. Add a person face from Internet.
		StringValueResult result5 = person.addPersonFaceFromURL("myTestGroup1", result2.getStringValue(), "", 
				new URI("https://micl.blob.core.windows.net/cstestimages/test-015.jpeg"));
		System.out.println(String.format("faceId = %s", result5.getStringValue()));
		// Step 6. Update a Person face from Internet.
		BooleanResult result6 = person.updatePersonFace("myTestGroup1", 
				result2.getStringValue(), result4.getStringValue(), "abc");
		if(!result6.isSucceeded())
			fail("Update face data failed.");
		// Step 6. Delete Person Group.
		pg.deletePersonGroup("myTestGroup1");		
	}
}
