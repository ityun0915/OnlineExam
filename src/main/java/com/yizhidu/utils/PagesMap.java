package com.yizhidu.utils;

import java.util.*;


public class PagesMap {
	
   
	public static Integer getEveryPageNumber() {
		ResourceBundle resb =ResourceBundle.getBundle("resource", Locale.getDefault());
		String everyPageNumber = resb.getString("everyPageNumber");
		return Integer.parseInt(everyPageNumber);
		
	}
	
	
	public static <E> Map<String,Object> getpagesUserMaps(List<E> list,int pn){
		
		 List<E> newList = new ArrayList<E>();
		 
	     if(getEveryPageNumber()*pn>list.size()) {
	        newList = list.subList(getEveryPageNumber()*pn-getEveryPageNumber(),list.size());
	     }else {
	        newList = list.subList(getEveryPageNumber()*pn-getEveryPageNumber(), getEveryPageNumber()*pn);
	     }
	        
	     Map<String,Object> map = new HashMap<String,Object>();
	        
	    int total = list.size();   
        int pageNum = pn;          
        int pages = total%getEveryPageNumber() == 0 ? (total/getEveryPageNumber()) : (total/getEveryPageNumber())+1;   
        int[] navigatepageNums = new int[pages];      
        for(int i=0;i<navigatepageNums.length;i++) {
        	navigatepageNums[i]=i+1;	        			
        }
        boolean hasNextPage = false;
        if(pn==pages) {
        	hasNextPage = false;
        }else {
        	hasNextPage = true;
        }
        boolean hasPreviousPage = false;
        if(pn==1) {
        	hasPreviousPage = false;
        }else {
        	hasPreviousPage = true;
        }
        map.put("total", total);
        map.put("pageNum", pageNum);
        map.put("pages", pages);
        map.put("navigatepageNums", navigatepageNums);
        map.put("hasNextPage", hasNextPage);
        map.put("list", newList);
	    map.put("hasPreviousPage", hasPreviousPage);
	    return map;
	}
}
