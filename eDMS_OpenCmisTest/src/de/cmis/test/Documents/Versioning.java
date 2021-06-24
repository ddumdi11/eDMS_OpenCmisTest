package de.cmis.test.Documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class Versioning {

	private static String inputFile1 = "./src/com/sample/util/Files/07_S_Verweis.docx";
	private static String inputFile2 = "./src/com/sample/util/Files/07_S_Verweis.docx";
	private static String documentName = "sample1.txt";

	private static Document createDocument(Session session) throws IOException {
		File inputFile = new File(inputFile1);

		String mimeType = Files.probeContentType(inputFile.toPath());

		try (InputStream inputStream = new FileInputStream(inputFile)) {
			ContentStream contentStream = session.getObjectFactory().createContentStream(inputFile.getName(),
					inputFile.length(), mimeType, inputStream);

			Map<String, String> properties = new HashMap<>();
			properties.put("cmis:objectTypeId", "cmis:document");
			properties.put("cmis:name", documentName);

			Folder rootFolder = session.getRootFolder();
			Document document = rootFolder.createDocument(properties, contentStream, null);

			return document;

		}
	}

	private static void printDocumentDetails(Document document) {
		Tool.printAndLog("name : " + document.getName());
		Tool.printAndLog("id : " + document.getId());
		Tool.printAndLog("version series id : " + document.getVersionSeriesId());
		Tool.printAndLog("Version Label : " + document.getVersionLabel());
		Tool.printAndLog("Checked out by : " + document.getVersionSeriesCheckedOutBy());
		Tool.printAndLog("Content length : " + document.getContentStreamLength());
	}

	private static Document updateAndCheckInDocument(Session session, ObjectId objectId) throws IOException {
		
		Document document = (Document) session.getObject(objectId);

		File inputFile = new File(inputFile2);
		String mimeType = Files.probeContentType(inputFile.toPath());

		try (InputStream inputStream = new FileInputStream(inputFile)) {
			ContentStream contentStream = session.getObjectFactory().createContentStream(inputFile.getName(),
					inputFile.length(), mimeType, inputStream);

			document.setContentStream(contentStream, true, true);
			ObjectId objId = document.checkIn(true, null, contentStream, "Checking in document");
			return (Document) session.getObject(objId);
		}

	}

	public static void main(String args[]) throws IOException {
		// Das Versioning ist nur beim Alfresco-Server möglich
		Session session = SessionSingleton.getInstance().getSession("Alfresco", "atom11");
		
		Document document = createDocument(session);

		Tool.printAndLog("*****************************************");
		Tool.printAndLog("Before checking in the document");
		Tool.printAndLog("*****************************************");

		printDocumentDetails(document);

		boolean isVersionable = document.isVersionable();

		if (!isVersionable) {
			Tool.printAndLog("Document is not versionable");
			return;
		}

		ObjectId objectId = document.checkOut();
		Document checkdInDocument = updateAndCheckInDocument(session, objectId);

		Tool.printAndLog("*****************************************");
		Tool.printAndLog("After checking in the document");
		Tool.printAndLog("*****************************************");
		printDocumentDetails(checkdInDocument);

	}
}