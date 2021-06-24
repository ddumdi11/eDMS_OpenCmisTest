package de.cmis.test.MetadataAndTypes;

import java.io.IOException;
import java.util.Set;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.CreatablePropertyTypes;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetCreatablePropertyTypes {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CreatablePropertyTypes creatablePropertyTypes = session.getRepositoryInfo().getCapabilities()
				.getCreatablePropertyTypes();

		Set<PropertyType> propTypes = creatablePropertyTypes.canCreate();

		for (PropertyType prop : propTypes) {
			Tool.printAndLog(prop.toString());
		}

	}
}