package de.cmis.test.MetadataAndTypes;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.NewTypeSettableAttributes;

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

		System.out.println("canSetControllableAcl : " + canSetControllableAcl);
		System.out.println("canSetControllablePolicy : " + canSetControllablePolicy);
		System.out.println("canSetCreatable : " + canSetCreatable);
		System.out.println("canSetDescription : " + canSetDescription);
		System.out.println("canSetDisplayName : " + canSetDisplayName);
		System.out.println("canSetFileable : " + canSetFileable);
		System.out.println("canSetFulltextIndexed : " + canSetFulltextIndexed);
		System.out.println("canSetId : " + canSetId);
		System.out.println("canSetIncludedInSupertypeQuery : " + canSetIncludedInSupertypeQuery);
		System.out.println("canSetLocalName : " + canSetLocalName);
		System.out.println("canSetLocalNamespace : " + canSetLocalNamespace);
		System.out.println("canSetQueryable : " + canSetQueryable);
		System.out.println("canSetQueryName : " + canSetQueryName);
	}
}