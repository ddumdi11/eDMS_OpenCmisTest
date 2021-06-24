package de.cmis.test.Renditions;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Rendition;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
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

		Tool.printAndLog("bigHeight : " + bigHeight);
		Tool.printAndLog("bigLength : " + bigLength);
		Tool.printAndLog("bigWidth : " + bigWidth);
		Tool.printAndLog("contentURL : " + contentURL);
		Tool.printAndLog("height : " + height);
		Tool.printAndLog("kind : " + kind);
		Tool.printAndLog("length : " + length);
		Tool.printAndLog("mimeType : " + mimeType);
		Tool.printAndLog("documentId : " + documentId);
		Tool.printAndLog("streamId : " + streamId);
		Tool.printAndLog("title : " + title);
		Tool.printAndLog("width : " + width);

	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		//Document document = (Document) session.getObjectByPath("/sampleDoc.json");
		Document document = (Document) session.getObjectByPath("/07_S_Verweis.docx");

		List<Rendition> renditions = document.getRenditions();

		if (renditions == null) {
			Tool.printAndLog("No renditions are existed for this object");
			return;
		}

		for (Rendition rendition : renditions) {
			printRendition(rendition);
		}

	}

}