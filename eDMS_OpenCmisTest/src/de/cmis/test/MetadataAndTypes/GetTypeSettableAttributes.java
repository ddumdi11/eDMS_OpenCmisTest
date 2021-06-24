package de.cmis.test.MetadataAndTypes;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.NewTypeSettableAttributes;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetTypeSettableAttributes {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		NewTypeSettableAttributes settableAttributes = session.getRepositoryInfo().getCapabilities()
				.getNewTypeSettableAttributes();

		boolean canSetControllableAcl = settableAttributes.canSetControllableAcl();
		boolean canSetControllablePolicy = settableAttributes.canSetControllablePolicy();
		boolean canSetCreatable = settableAttributes.canSetCreatable();
		boolean canSetDescription = settableAttributes.canSetDescription();
		boolean canSetDisplayName = settableAttributes.canSetDisplayName();
		boolean canSetFileable = settableAttributes.canSetFileable();
		boolean canSetFulltextIndexed = settableAttributes.canSetFulltextIndexed();
		boolean canSetId = settableAttributes.canSetId();
		boolean canSetIncludedInSupertypeQuery = settableAttributes.canSetIncludedInSupertypeQuery();
		boolean canSetLocalName = settableAttributes.canSetLocalName();
		boolean canSetLocalNamespace = settableAttributes.canSetLocalNamespace();
		boolean canSetQueryable = settableAttributes.canSetQueryable();
		boolean canSetQueryName = settableAttributes.canSetQueryName();

		Tool.printAndLog("canSetControllableAcl : " + canSetControllableAcl);
		Tool.printAndLog("canSetControllablePolicy : " + canSetControllablePolicy);
		Tool.printAndLog("canSetCreatable : " + canSetCreatable);
		Tool.printAndLog("canSetDescription : " + canSetDescription);
		Tool.printAndLog("canSetDisplayName : " + canSetDisplayName);
		Tool.printAndLog("canSetFileable : " + canSetFileable);
		Tool.printAndLog("canSetFulltextIndexed : " + canSetFulltextIndexed);
		Tool.printAndLog("canSetId : " + canSetId);
		Tool.printAndLog("canSetIncludedInSupertypeQuery : " + canSetIncludedInSupertypeQuery);
		Tool.printAndLog("canSetLocalName : " + canSetLocalName);
		Tool.printAndLog("canSetLocalNamespace : " + canSetLocalNamespace);
		Tool.printAndLog("canSetQueryable : " + canSetQueryable);
		Tool.printAndLog("canSetQueryName : " + canSetQueryName);
	}
}