package main.java.com.mosby.controller.services;

import java.util.List;

import main.java.com.mosby.controller.dao.ReflectionDao;

public class ReadGenericObjectService<T> {
		private Class<T> type;
		private ReflectionDao<T> tDao = null;
		
		public ReadGenericObjectService(Class<T> type){
			this.type = type;
		}
		
		@SuppressWarnings("unchecked")
		public List<T> readList(){
			try {
				tDao = new ReflectionDao<>((Class<T>) type.newInstance().getClass());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			List<T> list = (List<T>) tDao.selectObjects("", null);
			return (List<T>) list;
		}
		
		@SuppressWarnings("unchecked")
		public List<T> readListByField(String fieldName, Object variable){
			try {
				tDao = new ReflectionDao<>((Class<T>) type.newInstance().getClass());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			List<T> list = (List<T>) tDao.selectObjects(fieldName, variable);
			return (List<T>) list;
		}

		@SuppressWarnings("unchecked")
		public T readById(int id){
			T type1 = (T) type;
			try {
				tDao = new ReflectionDao<>((Class<T>) type.newInstance().getClass());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			type1 = tDao.selectObjects("id", id).get(0);
			return type1;
		}
	
	
	
//	public List<EventCategory> readCategories (){
//		ReflectionDao<EventCategory> eventCatregoriesDao = new ReflectionDao<>((Class<EventCategory>) EventCategory.class);
//		List<EventCategory> list = eventCatregoriesDao.selectObjects("", null);
//		return list;
//	}
//	
//	public List<EventType> readTypes (){
//		ReflectionDao<EventType> eventTypeDao = new ReflectionDao<>((Class<EventType>) EventType.class);
//		List<EventType> list = eventTypeDao.selectObjects("", null);
//		return list;
//	}
//	
//	public List<Event> readEventList (){
//		ReflectionDao<Event> eventDao = new ReflectionDao<>((Class<Event>) Event.class);
//		List<Event> list = eventDao.selectObjects("", null);
//		return list;
//	}
	
	
//	public Event readById(int id){
//		ReflectionDao<Event> eventDao = new ReflectionDao<>((Class<Event>) Event.class);
//		Event event = eventDao.selectObjects("id", id).get(0); 
//		return event;
//	}
//	
//	public EventCategory readCategotyById(int id){
//		ReflectionDao<EventCategory> eventCategoryDao = new ReflectionDao<>((Class<EventCategory>) EventCategory.class);
//		EventCategory eventCategory = eventCategoryDao.selectObjects("id", id).get(0); 
//		return eventCategory;
//	}
//	
//	public EventType readTypeById(int id){
//		ReflectionDao<EventType> eventTypeDao = new ReflectionDao<>((Class<EventType>) EventType.class);
//		EventType eventType = eventTypeDao.selectObjects("id", id).get(0); 
//		return eventType;
//	}
	
}
