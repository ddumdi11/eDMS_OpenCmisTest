package de.cmis.test.Documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class UpdateContentOfDocument {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Document document = (Document) session.getObjectByPath("/07_S_Verweis.docx");

		Tool.printAndLog("Content stream length before updating : " + document.getContentStreamLength());

		File inputFile = new File("./src/com/sample/util/Files/Hardware 10_1_2020.docx");
		String mimeType = Files.probeContentType(inputFile.toPath());

		try (InputStream inputStream = new FileInputStream(inputFile)) {
			ContentStream contentStream = session.getObjectFactory().createContentStream(inputFile.getName(),
					inputFile.length(), mimeType, inputStream);

			document.setContentStream(contentStream, true, true);
		}

		document.refresh();
		Tool.printAndLog("Content stream length after updating : " + document.getContentStreamLength());

	}
}