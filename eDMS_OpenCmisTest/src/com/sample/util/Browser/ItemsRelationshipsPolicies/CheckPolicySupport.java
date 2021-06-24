package com.sample.util.Browser.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class CheckPolicySupport {

 private static String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
 private static String repositoryId = "A1";

 private static Session getSession() {
  Map<String, String> parameters = new HashMap<>();
  parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

  parameters.put(SessionParameter.USER, "");
  parameters.put(SessionParameter.PASSWORD, "");

  parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);
  parameters.put(SessionParameter.BROWSER_URL, serverURL);

  SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
  return sessionFactory.createSession(parameters);
 }

 
 public static void main(String args[]) throws IOException {

  Session session = getSession();

  ItemIterable<ObjectType> objTypes = session.getTypeChildren(null, false);
  
  for(ObjectType objType : objTypes){
   if("cmis:policy".equals(objType.getId())){
    System.out.println("Policy objects are supported by the reposiotry");
   }
  }

 }

}