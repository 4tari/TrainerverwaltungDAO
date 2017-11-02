package dataLayer.dataAccessObjects.xml;

import businessObjects.ITrainer;
import dataLayer.businessObjects.Trainer;
import dataLayer.dataAccessObjects.ITrainerDao;
import exceptions.NoNextTrainerFoundException;
import exceptions.NoPreviousTrainerFoundException;
import exceptions.NoTrainerFoundException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrainerDaoXml implements ITrainerDao {
    @Override
    public ITrainer create() {           	
    	return new Trainer();
    }

    @Override
    public void delete(ITrainer trainer) {  	    	
        try {
            File fXmlFile = new File("trainer.xml" );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
                        
            String id = Integer.toString(trainer.getId());
            
            NodeList trainerElements = doc.getElementsByTagName("trainer");
            Node trainers = doc.getElementsByTagName("trainers").item(0);            
            
            boolean found = false;
            
            for(int i=0; i<trainerElements.getLength(); i++) { 
            	
            	if(id.equals(trainerElements.item(i).getAttributes().getNamedItem("id").getTextContent())) {
            		trainers.removeChild(trainerElements.item(i));
            		found = true;            		
            		break;
            	}
            }
            
            if (!found ) {
            	throw new NoTrainerFoundException();
            }
            
        	// write the content into xml file
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
        	Transformer transformer = transformerFactory.newTransformer();
        	DOMSource source = new DOMSource(doc);
        	StreamResult result = new StreamResult(fXmlFile);
        	transformer.transform(source, result);
                 
        } catch (Exception e) {
            e.printStackTrace();
        }          
    }
    

    @Override
    public ITrainer first() {
    	Trainer t = null;
	     
        try {
        	File fXmlFile = new File("trainer.xml" );
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
        	doc.getDocumentElement().normalize();

        	NodeList trainer = doc.getElementsByTagName("trainer");
        
        	NodeList childNotes = trainer.item(0).getChildNodes();
        	
        	t = new Trainer();
        	t.setId(Integer.parseInt(trainer.item(0).getAttributes().getNamedItem("id").getTextContent()));
        	t.setName(childNotes.item(0).getTextContent());
        	t.setAlter(Integer.parseInt(childNotes.item(1).getTextContent()));
        	t.setErfahrung(Integer.parseInt(childNotes.item(2).getTextContent()));
          

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
    	return t;
    }

    
    @Override
    public ITrainer last() {
    	Trainer t = null;
	     
        try {
        	File fXmlFile = new File("trainer.xml" );
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
        	doc.getDocumentElement().normalize();

        	NodeList trainer = doc.getElementsByTagName("trainer");
        
        	NodeList childNotes = trainer.item(trainer.getLength()-1).getChildNodes();
        	
        	t = new Trainer();
        	t.setId(Integer.parseInt(trainer.item(trainer.getLength()-1).getAttributes().getNamedItem("id").getTextContent()));
        	t.setName(childNotes.item(0).getTextContent());
        	t.setAlter(Integer.parseInt(childNotes.item(1).getTextContent()));
        	t.setErfahrung(Integer.parseInt(childNotes.item(2).getTextContent()));
          

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
    	return t;
    }

    @Override
    public ITrainer next(ITrainer trainer) {
    	Trainer t = null;    
    	
        try {

        	
            File fXmlFile = new File("trainer.xml" );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

        	t = new Trainer();            
            
            NodeList trainerElements = doc.getElementsByTagName("trainer");

        	int id = trainer.getId();             
            int nextIndex = trainerElements.getLength()-1;
            int nextId = Integer.parseInt(trainerElements.item(nextIndex).getAttributes().getNamedItem("id").getTextContent());
            int lastId = nextId;
                   
            for(int i=trainerElements.getLength()-1; i >= 0 ; i--) {        	       	
            	lastId = Integer.parseInt(trainerElements.item(i).getAttributes().getNamedItem("id").getTextContent());
            	
            	if(lastId > id) {
            		nextId = lastId;
            		nextIndex = i;
            	}
            	
            	else {
                	break;
            	}            	            	                       	            	                              
            }
             
            if(id == nextId) {
            	throw new NoNextTrainerFoundException();
            }
            
            NodeList childNotes = trainerElements.item(nextIndex).getChildNodes();

            t.setId(nextId);
            t.setName(childNotes.item(0).getTextContent());
            t.setAlter(Integer.parseInt(childNotes.item(1).getTextContent()));
            t.setErfahrung(Integer.parseInt(childNotes.item(2).getTextContent()));

        } 

        catch (Exception e) {
        	e.getMessage();
        	e.printStackTrace();
        }
        
        return t;        
    }
    

    @Override
    public ITrainer previous(ITrainer trainer) {
    	Trainer t = null;    
        	
        try {
            File fXmlFile = new File("trainer.xml" );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

        	t = new Trainer();            
            
        	int id = trainer.getId();
        	int prevId = -1;
        	int prevIndex = -1;
        	int lastId = -1;
        	
            NodeList trainerElements = doc.getElementsByTagName("trainer");
            for(int i=0; i<trainerElements.getLength(); i++) {        	       	
            	
            	lastId = Integer.parseInt(trainerElements.item(i).getAttributes().getNamedItem("id").getTextContent());            	
            	
            	if(lastId < id && lastId > prevId) {
            		prevId = lastId;
            		prevIndex = i;
            	} 
            	
            	else {
                   
           		
            		break;            		
            	}
            			
            }
            
    		if(prevIndex == -1) {
            	throw new NoPreviousTrainerFoundException();
            }
    		            		
    		NodeList childNotes = trainerElements.item(prevIndex).getChildNodes();
        	
    		t.setId(prevId);
    		t.setName(childNotes.item(0).getTextContent());
    		t.setAlter(Integer.parseInt(childNotes.item(1).getTextContent()));
    		t.setErfahrung(Integer.parseInt(childNotes.item(2).getTextContent()));            
           
                 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return t;        
    }

    
    
    @Override
    public void save(ITrainer trainer) {
        try {
            File fXmlFile = new File("trainer.xml");
            
            if(!fXmlFile.exists()) {    
            	DocumentBuilderFactory dbFactory =
            			DocumentBuilderFactory.newInstance();
            	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            	Document doc = dBuilder.newDocument();

            	Element rootElement = doc.createElement("trainers");
            	doc.appendChild(rootElement);
            	
        		Element trainerElement = doc.createElement("trainer");
        		rootElement.appendChild(trainerElement);        		
        		trainerElement.setAttribute("id", Integer.toString(trainer.getId()));
        		
                Element nameElement = doc.createElement("name");
                trainerElement.appendChild(nameElement);
                nameElement.appendChild(doc.createTextNode(trainer.getName()));
                
                Element alterElement = doc.createElement("alter");
                trainerElement.appendChild(alterElement);
                alterElement.appendChild(doc.createTextNode(Integer.toString(trainer.getAlter())));
                
                Element erfahrungElement = doc.createElement("erfahrung");
                trainerElement.appendChild(erfahrungElement);
                erfahrungElement.appendChild(doc.createTextNode(Integer.toString(trainer.getErfahrung())));
                
        		        	 
            	// write the content into xml file
            	TransformerFactory transformerFactory = TransformerFactory.newInstance();
            	Transformer transformer = transformerFactory.newTransformer();
            	DOMSource source = new DOMSource(doc);
            	StreamResult result = new StreamResult(fXmlFile);
            	transformer.transform(source, result);
            }
            
            else {
            	            	
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();

                NodeList nList = doc.getElementsByTagName("trainer");                             
                Node lastElement = nList.item(nList.getLength()-1);
                           
                Element trainerElement = doc.createElement("trainer");
        		lastElement.appendChild(trainerElement);        		
        		trainerElement.setAttribute("id", Integer.toString(trainer.getId()));
        		
                Element nameElement = doc.createElement("name");
                trainerElement.appendChild(nameElement);
                nameElement.appendChild(doc.createTextNode(trainer.getName()));
                
                Element alterElement = doc.createElement("alter");
                trainerElement.appendChild(alterElement);
                alterElement.appendChild(doc.createTextNode(Integer.toString(trainer.getAlter())));
                
                Element erfahrungElement = doc.createElement("erfahrung");
                trainerElement.appendChild(erfahrungElement);
                erfahrungElement.appendChild(doc.createTextNode(Integer.toString(trainer.getErfahrung())));                
                               
                NodeList rootElement = doc.getElementsByTagName("trainers");                
                rootElement.item(0).appendChild(trainerElement);

            	// write the content into xml file
            	TransformerFactory transformerFactory = TransformerFactory.newInstance();
            	Transformer transformer = transformerFactory.newTransformer();
            	DOMSource source = new DOMSource(doc);
            	StreamResult result = new StreamResult(fXmlFile);
            	transformer.transform(source, result);                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ITrainer> select() {
        List<ITrainer> list = new ArrayList<ITrainer>();
    	     
        try {
        	File fXmlFile = new File("trainer.xml" );
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
        	doc.getDocumentElement().normalize();



        	NodeList trainer = doc.getElementsByTagName("trainer");

        	for(int i=0; i<trainer.getLength(); i++) {        	 

        		Trainer t = new Trainer();            	

        		NodeList childNotes = trainer.item(i).getChildNodes();

        		t.setId(Integer.parseInt(trainer.item(i).getAttributes().getNamedItem("id").getTextContent()));
        		t.setName(childNotes.item(0).getTextContent());
        		t.setAlter(Integer.parseInt(childNotes.item(1).getTextContent()));
        		t.setErfahrung(Integer.parseInt(childNotes.item(2).getTextContent()));
        		
        		list.add(t);
        	}           

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
    	return list;
    }

    
    @Override
    public ITrainer select(int id) {
    	Trainer t = null;    	
    	
        try {
            File fXmlFile = new File("trainer.xml" );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

        	t = new Trainer();            
            
            NodeList trainer = doc.getElementsByTagName("trainer");
            for(int i=0; i<trainer.getLength(); i++) {        	       	
            	if(Integer.toString(id).equals(trainer.item(i).getAttributes().getNamedItem("id").getTextContent())) {
            		NodeList childNotes = trainer.item(i).getChildNodes();
            		
            		t.setId(id);
            		t.setName(childNotes.item(0).getTextContent());
            		t.setAlter(Integer.parseInt(childNotes.item(1).getTextContent()));
            		t.setErfahrung(Integer.parseInt(childNotes.item(2).getTextContent()));
            		
            		break;
            	}
            	
                if(i == trainer.getLength()-1) {
                	throw new NoTrainerFoundException();
                }            	
            	
            }
            

                 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return t;        
    }
}

