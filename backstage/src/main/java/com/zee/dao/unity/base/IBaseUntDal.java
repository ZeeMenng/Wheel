package com.zee.dao.unity.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zee.dao.base.IBaseDao;

public interface IBaseUntDal<T extends Serializable> extends IBaseDao {

	public int add(T t);

	public int addList(ArrayList<T> tList);

	public int addListWithDffOrAdd(ArrayList<T> tList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(T t);

	public int updateList(@Param("idList") ArrayList<String> idList, @Param("t") T t);

	public int updateListWithDff(ArrayList<T> tList);

	public int updateListWithDffOrAdd(ArrayList<T> tList);

	public T getModel(String id);

	public List<T> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
