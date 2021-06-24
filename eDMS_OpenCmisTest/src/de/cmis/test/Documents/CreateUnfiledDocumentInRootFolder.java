package de.cmis.test.Documents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CreateUnfiledDocumentInRootFolder {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		// Dokument löschen, falls vorhanden
		try {
			Document toDeleteDoc = (Document) session.getObjectByPath("/unfiledDocument.txt");
			Tool.printAndLog("Dokument vorhanden + wird gelöscht.");
			toDeleteDoc.delete();
			Tool.printAndLog("Dokument erfolgreich gelöscht.");
		} catch (Exception e) {
			Tool.printAndLog("Dokument entweder nicht vorhanden oder ein anderer Fehler ...");
		}

		boolean isUnfilingSupprted = session.getRepositoryInfo().getCapabilities().isUnfilingSupported();

		if (!isUnfilingSupprted) {
			Tool.printAndLog("unfiling documents are not supported");
			return;
		}

		Tool.printAndLog("unfiling doucments are supported by the repository");

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", "unfiledDocument.txt");

		ObjectId objId = session.createDocument(properties, null, null, null);
		Tool.printAndLog("Id : " + objId.getId());
	}
}