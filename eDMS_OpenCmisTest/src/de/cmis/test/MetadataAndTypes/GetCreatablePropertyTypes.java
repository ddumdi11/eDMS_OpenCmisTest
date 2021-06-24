package de.cmis.test.MetadataAndTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.CreatablePropertyTypes;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;

import de.cmis.test.Session.SessionSingleton;

public class GetCreatablePropertyTypes {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CreatablePropertyTypes creatablePropertyTypes = session.getRepositoryInfo().getCapabilities()
				.getCreatablePropertyTypes();

		Set<PropertyType> propTypes = creatablePropertyTypes.canCreate();

		for (PropertyType prop : propTypes) {
			System.out.println(prop);
		}

	}
}