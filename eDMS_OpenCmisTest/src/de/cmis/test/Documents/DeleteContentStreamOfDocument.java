package de.cmis.test.Documents;

import java.io.BufferedInputStream;
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
import org.apache.chemistry.opencmis.client.runtime.repository.ObjectFactoryImpl;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class DeleteContentStreamOfDocument {

	private static String localFile = "./src/com/sample/util/Files/app1.log";

	private static void setContentStream(Document document, File file) {
		/* Creating content stream */
		ObjectFactoryImpl objectFactory = new ObjectFactoryImpl();
		try (InputStream stream = new FileInputStream(file)) {
			String mimetype = Files.probeContentType(file.toPath());
			ContentStream contentStream = objectFactory.createContentStream(file.getName(), file.length(), mimetype,
					stream);
			document.setContentStream(contentStream, true, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void readTheContentsOfTheDocument(Document document) {
		ContentStream contentStream = document.getContentStream();

		if (contentStream == null) {
			Tool.printAndLog("*************************");
			Tool.printAndLog("Content stream is not associated with the document");
			Tool.printAndLog("*************************");
			return;
		}

		try (InputStream inputStream = contentStream.getStream();
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {

			byte[] buffer = new byte[65535];
			int bytesRead = -1;

			while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
				String str = new String(buffer, 0, bytesRead);
				System.out.print(str);
			}
		} catch (IOException e) {
			Tool.printAndLog("Error occurred while processing the file content");
			Tool.printAndLog(e.getMessage());
		}

	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		/* Create the document 'sampleDoc.json' */
		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", "sample.log");

		Folder rootFolder = session.getRootFolder();

		Document document = rootFolder.createDocument(properties, null, null);

		setContentStream(document, new File(localFile));

		Tool.printAndLog("Printing the contents of the document " + localFile);
		readTheContentsOfTheDocument(document);

		Tool.printAndLog("\nDeleting the content stream associated with this document");
		document.deleteContentStream(true);

		readTheContentsOfTheDocument(document);

	}

}