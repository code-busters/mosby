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
}
