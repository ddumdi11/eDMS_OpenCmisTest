package de.cmis.test.Renditions;

import java.io.IOException;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Rendition;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetRenditionAttributes1 {

	

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Document document = (Document) session.getObjectByPath("/sampleDoc.json");

		List<Rendition> renditions = document.getRenditions();

		if (renditions == null) {
			Tool.printAndLog("No renditions are existed for this object");
			return;
		}

		for (Rendition rendition : renditions) {
			Tool.printAndLog(rendition.toString());
		}

	}

}