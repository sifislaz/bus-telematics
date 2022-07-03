package gr.upatras.bus.telematics.simulation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import gr.upatras.bus.telematics.bus.*;
import gr.upatras.bus.telematics.json.JSONHandler;
import gr.upatras.bus.telematics.route.Route;
import gr.upatras.bus.telematics.stop.Stop;


public class Simulation {
	
	
	public Simulation(){
		run();
	}
	

	BusService busService= new BusService() ;
	
	
	
	
	public void run() {
		 Scanner sc= new Scanner(System.in);
		 sc.nextInt();
		 
		 ArrayList<Integer> ids = new ArrayList<Integer>();
		 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		 context.scan("gr.upatras.bus.telematics.bus");
		 context.refresh();
		 BusService bs = context.getBean(BusService.class);
		 	ids=bs.getBusIds();
		 	System.out.println(ids);
		 	int index = (int)(Math.random() * ids.size());
		 	System.out.println(ids.get(index));
		 	Bus b1 = bs.getById(ids.get(index)); 
		 	
		 System.out.println(b1);
		// bs.getById(1008);
		 	b1.setRouteId(201);
		 	
		 	int counter_max=getNumOfStops( b1);
		 	int counter=0;
            	while(counter!=counter_max-1) {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	try {
            		
            		print_stops(b1,counter);
            		counter++;
					
            	}
            	catch (NullPointerException e) {
            		e.printStackTrace();
            	}
            	
            	
	}
            	System.out.println(b1.getRouteId());
       		 
            	bs.updateBus(1008,b1);    
            	}
	 
	 
	 // gets the number of stops 
	 public int getNumOfStops(Bus b) {
		 List<Integer> id_list = new ArrayList<Integer>();
		 int numofstops;
		  List<LinkedHashMap> stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops"+Integer.toString(b.getRouteId()) +".json");
		  for(LinkedHashMap m : stopsJSON) {
				int id = Integer.parseInt(m.get("id").toString());
				id_list.add(id);
		  }
		  numofstops=id_list.size();
		 return numofstops;
	 }
      
        //id,name,long,lat
	 //gets the stops from json starts a bus route and calculates the time needed from one stop to the other
   public void print_stops(Bus b,int n) {
	   List<Integer> id_list = new ArrayList<Integer>();
	   List<LinkedHashMap> stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops"+Integer.toString(b.getRouteId()) +".json");
	   Object long_or= stopsJSON.get(n).get("long");
	   Object lat_or= stopsJSON.get(n).get("lat");
	   Object name_or= stopsJSON.get(n).get("name");
	   b.setLongitude(Double.parseDouble(long_or.toString()));
	   b.setLatitude(Double.parseDouble(lat_or.toString()));
	   
	   

	   Object long_de= stopsJSON.get(n+1).get("long");
	   Object lat_de= stopsJSON.get(n+1).get("lat");
	   Object name_de= stopsJSON.get(n+1).get("name");
	   System.out.println("bus is at "+ name_or);
	   System.out.println("next station is "+ name_de);
	   
	  try {
		apiClass ap = new apiClass(long_or.toString()+","+lat_or.toString(),long_de.toString()+","+lat_de.toString());
		System.out.println("time to arrive "+ap.getTime());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
        

}
