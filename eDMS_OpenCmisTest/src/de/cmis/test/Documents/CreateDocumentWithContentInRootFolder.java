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
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

import de.cmis.test.Session.SessionSingleton;

public class CreateDocumentWithContentInRootFolder {

	public static void main(String args[]) throws IOException {
		File inputFile = new File("./src/com/sample/util/Files/07_S_Verweis.docx");

		String mimeType = Files.probeContentType(inputFile.toPath());

		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		// Dokument löschen, falls vorhanden
		try {
			Document toDeleteDoc = (Document) session.getObjectByPath("/07_S_Verweis.docx");
			System.out.println("Dokument vorhanden + wird gelöscht.");
			toDeleteDoc.delete();
			System.out.println("Dokument erfolgreich gelöscht.");
		} catch (Exception e) {
			System.out.println("Dokument entweder nicht vorhanden oder ein anderer Fehler ...");
		}

		InputStream inputStream = new FileInputStream(inputFile);
		ContentStream contentStream = session.getObjectFactory().createContentStream(inputFile.getName(),
				inputFile.length(), mimeType, inputStream);

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", inputFile.getName());

		Folder rootFolder = session.getRootFolder();
		Document document = rootFolder.createDocument(properties, contentStream, null);

		System.out.println("Name Of the Document " + document.getName());
		System.out.println("Path Of the Document " + document.getPaths().get(0));

	}
}