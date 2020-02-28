package gzc.dao;

import java.util.List;

import gzc.entity.MyUser;

public interface UserDao {
	// 接口定义的方法名与Mapper映射id一致
	public MyUser selectUserById(Integer uid);

	public List<MyUser> selectAllUser();

	public int addUser(MyUser user);

	public int updateUser(MyUser user);

	public int deleteUser(Integer uid);
}
