package de.cmis.test.Documents;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class getMimeTypeOfDocument {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Document document = (Document) session.getObjectByPath("/application.log");

		ContentStream contentStream = document.getContentStream();

		if (contentStream == null) {
			Tool.printAndLog("There is no content stream associated with this document");
			return;
		}

		String mimeType = contentStream.getMimeType();

		Tool.printAndLog("mimeType : " + mimeType);

	}

}