package de.cmis.test.RepositoryCapabilities;

import java.util.Set;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.CreatablePropertyTypes;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckAllowablePropertyTypesWhileCreationOrUpdatingObjectTypeDefinitions {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CreatablePropertyTypes creatablePropertyTypes = repoInfo.getCapabilities().getCreatablePropertyTypes();

		Set<PropertyType> propertyTypes = creatablePropertyTypes.canCreate();

		System.out
				.println("Following are the available property types, while creating or updating property definitions");
		for (PropertyType propertyType : propertyTypes) {
			Tool.printAndLog(propertyType.toString());
		}
	}

}