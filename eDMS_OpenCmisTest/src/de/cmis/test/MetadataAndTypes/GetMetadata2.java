package de.cmis.test.MetadataAndTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import de.cmis.test.Session.SessionSingleton;

public class GetMetadata2 {

	public static void printType(Tree<ObjectType> types, String space) {

		ObjectType objType = types.getItem();

		Map<String, PropertyDefinition<?>> propertyDefinitions = objType.getPropertyDefinitions();

		System.out.println("Property Definitions for " + objType.getId());
		System.out.println("****************************************************");
		for (String key : propertyDefinitions.keySet()) {
			PropertyDefinition<?> definition = propertyDefinitions.get(key);

			System.out.println("id : " + definition.getId() + ", displayName : " + definition.getDisplayName()
					+ ", propertyType : " + definition.getPropertyType() + ", defaultValue : "
					+ definition.getDefaultValue());

		}

		List<Tree<ObjectType>> objTypes = types.getChildren();

		for (Tree<ObjectType> objTypeTemp : objTypes) {
			printType(objTypeTemp, space + " ");
		}
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		List<Tree<ObjectType>> types = session.getTypeDescendants(null, -1, true);

		for (Tree<ObjectType> typeTemp : types) {
			printType(typeTemp, "");
		}

	}
}