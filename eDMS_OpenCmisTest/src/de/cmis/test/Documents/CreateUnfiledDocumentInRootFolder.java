package de.cmis.test.Documents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class CreateUnfiledDocumentInRootFolder {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		// Dokument löschen, falls vorhanden
		try {
			Document toDeleteDoc = (Document) session.getObjectByPath("/unfiledDocument.txt");
			System.out.println("Dokument vorhanden + wird gelöscht.");
			toDeleteDoc.delete();
			System.out.println("Dokument erfolgreich gelöscht.");
		} catch (Exception e) {
			System.out.println("Dokument entweder nicht vorhanden oder ein anderer Fehler ...");
		}

		boolean isUnfilingSupprted = session.getRepositoryInfo().getCapabilities().isUnfilingSupported();

		if (!isUnfilingSupprted) {
			System.out.println("unfiling documents are not supported");
			return;
		}

		System.out.println("unfiling doucments are supported by the repository");

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", "unfiledDocument.txt");

		ObjectId objId = session.createDocument(properties, null, null, null);
		System.out.println("Id : " + objId.getId());
	}
}