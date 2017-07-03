# CognitiveService-SDK-for-Java
A project of Java encapsulates Cognitive Service REST API.
## Why I create this project ?
Yes, Cognitive Service offers REST API and language independent. But if you investigate these APIs will find that they are a little bit complicated. I create this project just want to simplify these APIs usage. And I also want to enable more and more developer developing more intelligence apps by Cognitive Service.


By now I just encapsulate only Face API. It's a little bit heavy workload for me. And It takes me more than 3 working days.
## Software License Agreement
MIT. It means that you can freely use this project in anywhere without any limitation. And It allows you could copy some piceses code into your code project. Please don't forget keep author information in your source code file.

## Environment
- [Eclipse](https://eclipse.org/)
- [Maven](http://maven.apache.org/)
- [Apache Http Client](http://hc.apache.org/httpclient-3.x/)
- [fastJSON](https://github.com/alibaba/fastJSON)

## Features

1.	Fully Encapsulated 

In this JAR package, all Face related APIs like Person and Person Group have been fully encapsulated. All response JSON data has been convert to Java Class to convenience developer using.

2.	Ease of use 

It's very easy to instance a Cognitive Service API class. You just only pass Cognitive Service endpoint and access key. The endpoint and key can be acquired from https://portal.azure.com and https://portal.azure.cn Beside that, It also ease of use for calling Cognitive Service API.
 for example, If you want to create a Person Group object and referenced [docs.microsoft.com](https://westus.dev.cognitive.microsoft.com/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395244) You will see this:
```java
public class JavaSample 
{
    public static void main(String[] args) 
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new 
            URIBuilder
            ("https://westus.api.cognitive.microsoft.com/face/v1.0/persongroups/{personGroupId}");


            URI uri = builder.build();
            HttpPut request = new HttpPut(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "{subscription key}");


            // Request body
            StringEntity reqEntity = new StringEntity("{body}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
```
    If you are using this SDK, It could be simplify to two lines as below code:
```java
PersonGroup pg = new PersonGroup("<ServiceEndPoint>", "<AccessKey>");
BooleanResult result = pg.addPersonGroup("testGroupName3", "testdata-abc");
```

3. It's easy to integrate JAR package into you Java project.

## Developing Guidance 

### Face Detections

The Cognitive Service detects human faces in an image and returns face locations, and optionally with faceIds, landmarks, and attributes. It's very basic operation about Fact API. In this SDK both support local file dectation and Internet resource detection. Any Face APIs denpends faceId.
```java
List<FaceAttributesOptions> options = new ArrayList<FaceAttributesOptions>();
// Below are full face attributes.
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
    // Detect face from local file.
    ObjectResult<List<FaceDetectionData>> result1 = 
        face.detectFromLocalFile(true, false, options, "<local file path>");
    if(!result1.isSucceeded())
        fail("Face Detection failed.");
    for(FaceDetectionData fd : result1.getInstance())
        System.out.println(JSON.toJSONString(fd));			
    // Detect face from Internet URI.
    ObjectResult<List<FaceDetectionData>> result2 = 
        face.detectFromURL(true, true, options, "<Internet resources URI>");
    if(!result2.isSucceeded())
        fail("Face Detection failed.");
    for(FaceDetectionData fd : result2.getInstance())
        System.out.println(JSON.toJSONString(fd));	
}
catch(Exception ex) {
    ex.printStackTrace();
}
```
The FaceDetectionData class is a very complicated. It contains many properties. The faceId, faceLandmarks and faceAttributes are key parts of this class. In these APIs, you can specify which part should be returned.

The faceAttributes has a lot of sub items. You have to pass a List object to specify which sub items should be contained in response JSON.

### Face Verification

There are two ways to verify whether two faces belong same guy: Face to Face verification and Face to Person verification. The Face to Face verification is much easier than Face to Person verification.
```java
// Step 1. Get faceId1 by Face Detect.
ObjectResult<List<FaceDetectionData>> result1 = 
        face.detectFromURL(true, false, null, "<Internet resource URI>");
if(!result1.isSucceeded())
    fail("face1 dectection failed.");
// Step 2. Get faceId2 by Face Detect.
ObjectResult<List<FaceDetectionData>> result2 = 
        face.detectFromLocalFile(true, false, null, "<local file path>");
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
```
Below code show you how to implement Face to Person verification.
```java
// Step 1. Get faceId1 by Face Detect.
ObjectResult<List<FaceDetectionData>> result1 = face.detectFromURL(true, false, null, 
        "<Internet resource URI>");
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
StringValueResult result4 = 
        p.addPersonFaceFromLocalFile("testGroup", result3.getStringValue(), "", "local file path");
// Step 5. Call Verify method.
ObjectResult<VerifyValue> result5 = face.Verify(result1.getInstance().get(0).getFaceId(),
        "testGroup", result3.getStringValue());
// Step 6. Get verify result.
if(!result5.getInstance().isIdentical())
    fail("face verification failed.");
System.out.println(String.format("Confidence = %f", result5.getInstance().getConfidence()));
```
### Group Person and Face List

Below code demostrate Person and Group APIs useage. Basiclly, Group is a container to store Person entites. The limitation is 1000 for each. Afer Group creation, you can create Person entity and update data.
```java
// Step 1. Create a Person Group first.
BooleanResult result1 = pg.addPersonGroup("myTestGroup", "");
if(!result1.isSucceeded()) 
    fail("PersonGroup creation failed");
// Step 2. Add a Person into Person Group.
StringValueResult result2 = person.addPerson("myTestGroup", "Michael Li", "micl#microsoft.com");
if(!result2.isSucceeded())
    fail("Person creation failed");
// Step 3. Get Person information by Person Id.
ObjectResult<PersonEntity> result3 = person.getPerson("myTestGroup", result2.getStringValue());
if(!result3.isSucceeded())
    fail("Get Person information failed.");
System.out.println(String.format("person name = %s \t userData = %s", 
        result3.getInstance().getName(), result3.getInstance().getUserData()));
// Step 4. Update Person information.
BooleanResult result4 = 
        person.updatePerson("myTestGroup", result2.getStringValue(), 
            "Michael Li (DX)", "li.zheng#microsoft.com");
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
```
For face list, please view below code to know how to use it.
```java
// Step 1. Create Face List.
String faceListId = UUID.randomUUID().toString();
BooleanResult result1 = facelist.addFaceList(faceListId, "TestMyFaceList", "face++");
if(!result1.isSucceeded())
    fail("Create Face List failed.");
System.out.println(String.format("FaceListId = %s", faceListId));
// Step 2. Add a face from local file.
StringValueResult result2 = facelist.addFaceToFaceListFromLocalFile(faceListId, 
        "<local file path>", 
        "This is first face. it comes from local file.", null);
if(!result2.isSucceeded())
    fail("Add Face which is from local file to face list failed.");
System.out.println(String.format("faceId1 = %s", result2.getStringValue()));
// Step 3. Add a face from Internet.
StringValueResult result3 = facelist.addFaceToFaceListFromURI(
        faceListId, "<Internet resource URI.>", 
        "This is second face that comes from Internet.", null);
if(!result3.isSucceeded())
    fail("Add a face which is from Internet to Face List failed.");
System.out.println(String.format("faceId2 = %s", result3.getStringValue()));
// Step 4. Delete Face List.
BooleanResult result4 = facelist.deleteFaceList(faceListId);
if(!result4.isSucceeded())
    fail("Delete Face list failed.");
```
For more details about APIs useage, please checke unit test code in this project.


## Next Plan

I will keep going on contriubte this project to encapsulate more and more Cognitive Service by Java Language. The high priority is Emotion API and Computer Vision API. Because these two API collection are both available on Global Azure and Mooncake in China as same as Face API.

**BTW:** *Java syntax is very very ugly.*
