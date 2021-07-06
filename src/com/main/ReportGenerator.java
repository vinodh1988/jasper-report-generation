package com.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Person;
import com.model.PersonDAO;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportGenerator {
	 
	public static void main(String n[])  
	
	{
     /* Convert List to JRBeanCollectionDataSource */
		List<Person> list= PersonDAO.getPeople();
			
		System.out.println(list);
		
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);

     /* Map to hold Jasper report Parameters */
     Map<String, Object> parameters = new HashMap<String, Object>();
     parameters.put("PeopleBeanSource", itemsJRBean);
     
     //read jrxml file and creating jasperdesign object
     InputStream input;
	try {
		input = new FileInputStream(new File("E:\\adv-new-java\\JasperFirst\\people.jrxml"));
	
                         
     JasperDesign jasperDesign = JRXmlLoader.load(input);
     
     /*compiling jrxml with help of JasperReport class*/
     JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

     /* Using jasperReport object to generate PDF */
     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

     		
     /*call jasper engine to display report in jasperviewer window*/
     JasperViewer.viewReport(jasperPrint);
	}
	catch(Exception e) {
	 e.printStackTrace();	
	}
	}
}