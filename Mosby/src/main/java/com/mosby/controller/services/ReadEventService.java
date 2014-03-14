package main.java.com.mosby.controller.services;

import java.util.ArrayList;
import java.util.List;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;

public class ReadEventService {
	
// Generic read not works, try later	
//	public List<T> readList(){
//		Class<T> type = null;
//		ReflectionDao<T> tDao = new ReflectionDao<>((Class<T>) type.getClass());
//		List<T> list = (List<T>) tDao.selectObjects("", null);
//		return (List<T>) list;
//	}
	
	public List<EventCategory> readCategories (){
		ReflectionDao<EventCategory> eventCatregoriesDao = new ReflectionDao<>((Class<EventCategory>) EventCategory.class);
		List<EventCategory> list = eventCatregoriesDao.selectObjects("", null);
		return list;
	}
	
	public List<EventType> readTypes (){
		ReflectionDao<EventType> eventTypeDao = new ReflectionDao<>((Class<EventType>) EventType.class);
		List<EventType> list = eventTypeDao.selectObjects("", null);
		return list;
	}
	
	public List<Event> readEventList (){
		ReflectionDao<Event> eventDao = new ReflectionDao<>((Class<Event>) Event.class);
		List<Event> list = eventDao.selectObjects("", null);
		return list;
	}
	
	public Event readById(int id){
		ReflectionDao<Event> eventDao = new ReflectionDao<>((Class<Event>) Event.class);
		Event event = eventDao.selectObjects("id", id).get(0); 
		return event;
	}
	
	public EventCategory readCategotyById(int id){
		ReflectionDao<EventCategory> eventCategoryDao = new ReflectionDao<>((Class<EventCategory>) EventCategory.class);
		EventCategory eventCategory = eventCategoryDao.selectObjects("id", id).get(0); 
		return eventCategory;
	}
	
	public EventType readTypeById(int id){
		ReflectionDao<EventType> eventTypeDao = new ReflectionDao<>((Class<EventType>) EventType.class);
		EventType eventType = eventTypeDao.selectObjects("id", id).get(0); 
		return eventType;
	}
	
}
