package gzc.dao;

import java.util.List;

import gzc.entity.MyUser;

public interface UserDao {

	public MyUser selectUserById(Integer uid);

	public List<MyUser> selectAllUser();

}
