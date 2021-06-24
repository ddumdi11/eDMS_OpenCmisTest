package de.cmis.test.Documents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.DocumentType;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetImportantPropertyValuesFromDocument {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		// Dokument löschen, falls vorhanden
		try {
			Document toDeleteDoc = (Document) session.getObjectByPath("/emptyDocument.txt");
			Tool.printAndLog("Dokument vorhanden + wird gelöscht.");
			toDeleteDoc.delete();
			Tool.printAndLog("Dokument erfolgreich gelöscht.");
		} catch (Exception e) {
			Tool.printAndLog("Dokument entweder nicht vorhanden oder ein anderer Fehler ...");
		}

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", "emptyDocument.txt");

		Folder rootFolder = session.getRootFolder();

		Document document = rootFolder.createDocument(properties, null, null);

		DocumentType documentType = document.getDocumentType();

		Tool.printAndLog("Is Document Versionable : " + documentType.isVersionable());
		Tool.printAndLog("Is Document controllable by ALC : " + documentType.isControllableAcl());
		Tool.printAndLog("Is Document controllable by policy : " + documentType.isControllablePolicy());
		Tool.printAndLog("Is Document fileable : " + documentType.isFileable());
		Tool.printAndLog("Is Document queryable : " + documentType.isQueryable());

	}

}