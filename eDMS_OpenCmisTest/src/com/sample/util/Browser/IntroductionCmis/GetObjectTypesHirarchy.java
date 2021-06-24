package com.sample.util.Browser.IntroductionCmis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetObjectTypesHirarchy {

 private static String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
 private static String repositoryId = "A1";
 private static final String NULL_STRING = null;

 public static Session getSession() {
  Map<String, String> parameters = new HashMap<>();
  parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

  parameters.put(SessionParameter.USER, "");
  parameters.put(SessionParameter.PASSWORD, "");

  parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);
  parameters.put(SessionParameter.BROWSER_URL, serverURL);

  SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
  return sessionFactory.createSession(parameters);
 }

 public static void printTypeAndParentType(List<Tree<ObjectType>> types) {

  for (Tree<ObjectType> typeTree : types) {
   ObjectType objType = typeTree.getItem();
   ObjectType parentType = objType.getParentType();

   StringBuilder builder = new StringBuilder("type : ").append(objType.getId()).append(" -> ");

   if (parentType == null) {
    builder.append("parentType : ").append(NULL_STRING);
   } else {
    builder.append("parentType : ").append(parentType.getId());
   }

   System.out.println(builder.toString());
   printTypeAndParentType(typeTree.getChildren());
  }

 }

 public static void main(String args[]) {
  Session session = getSession();

  List<Tree<ObjectType>> typeDescendants = session.getTypeDescendants(null, -1, false);

  printTypeAndParentType(typeDescendants);

 }

}