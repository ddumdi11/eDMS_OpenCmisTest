package de.cmis.test.Extensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.CmisExtensionElementImpl;

import de.cmis.test.Tool;

public class CreateExtension {

	private static void printExtensions(List<CmisExtensionElement> extensions, String space) {

		for (CmisExtensionElement extension : extensions) {
			List<CmisExtensionElement> extensionChildren = extension.getChildren();

			if (extensionChildren == null || extensionChildren.isEmpty()) {
				String name = extension.getName();
				String value = extension.getValue();
				Tool.printAndLog(space + name + " : " + value);

			} else {
				Tool.printAndLog(space + extension.getName());
				printExtensions(extensionChildren, space + " ");
			}
		}
	}

	public static void main(String args[]) {
		CmisExtensionElement leafExtension1 = new CmisExtensionElementImpl("", "confidentialLevel", null,
				"confidential");
		CmisExtensionElement leafExtension2 = new CmisExtensionElementImpl("", "rootFolderId", null, "folder_1234");
		CmisExtensionElement leafExtension3 = new CmisExtensionElementImpl("", "classification", null, "public");

		List<CmisExtensionElement> list = new ArrayList<>();
		list.add(leafExtension1);
		list.add(leafExtension2);
		list.add(leafExtension3);

		CmisExtensionElement parentExtension = new CmisExtensionElementImpl("", "parentExtension", null, list);

		List<CmisExtensionElement> extensions = Collections.singletonList(parentExtension);

		printExtensions(extensions, " ");
	}
}