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
package com.github.micli.cognitiveservice.api.common;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class URIUtils {

	public static URI combine(URI uri, String relativeURI) {
		String theUri = uri.toString();
		return combine(theUri, relativeURI);
	}
	public static URI combine(String URI, String relativeURI) {
		URI newURI = null;
		try {
			StringBuffer buff = new StringBuffer(URI);
			if(URI.charAt(URI.length() - 1) != '/' && 
					relativeURI.charAt(0) != '/')
				buff.append("/");
			buff.append(relativeURI);
			newURI = new URI(buff.toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newURI;
	}
	public static String encode(String uri) {
		try {
			return URLEncoder.encode(uri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
