package com.sample.util.Browser.Renditions;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Rendition;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class GetRenditionAttributes2 {

	
	private static void printRendition(Rendition rendition) {
		BigInteger bigHeight = rendition.getBigHeight();
		BigInteger bigLength = rendition.getBigLength();
		BigInteger bigWidth = rendition.getBigWidth();
		String contentURL = rendition.getContentUrl();
		long height = rendition.getHeight();

		String kind = rendition.getKind();
		long length = rendition.getLength();
		String mimeType = rendition.getMimeType();
		String documentId = rendition.getRenditionDocumentId();
		String streamId = rendition.getStreamId();
		String title = rendition.getTitle();
		long width = rendition.getWidth();

		System.out.println("bigHeight : " + bigHeight);
		System.out.println("bigLength : " + bigLength);
		System.out.println("bigWidth : " + bigWidth);
		System.out.println("contentURL : " + contentURL);
		System.out.println("height : " + height);
		System.out.println("kind : " + kind);
		System.out.println("length : " + length);
		System.out.println("mimeType : " + mimeType);
		System.out.println("documentId : " + documentId);
		System.out.println("streamId : " + streamId);
		System.out.println("title : " + title);
		System.out.println("width : " + width);

	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		//Document document = (Document) session.getObjectByPath("/sampleDoc.json");
		Document document = (Document) session.getObjectByPath("/07_S_Verweis.docx");

		List<Rendition> renditions = document.getRenditions();

		if (renditions == null) {
			System.out.println("No renditions are existed for this object");
			return;
		}

		for (Rendition rendition : renditions) {
			printRendition(rendition);
		}

	}

}