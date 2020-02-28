package gzc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import gzc.dao.UserDao;
import gzc.entity.MyUser;

public class MybatisTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// 读取配置文件mybatis-config.xml
			InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
			// 根据配置文件构建SqlSessionFactory
			SqlSessionFactory ssl = new SqlSessionFactoryBuilder().build(config);
			// 通过SqlSessionFactory创建SQLSession对象
			SqlSession ss = ssl.openSession();

			/*
			 * 方法一 : SqlSession执行映射文件中定义的sql，并返回映射结果
			 * gzc.mapper.MyUserMapper.selectUserById为MyUserMapper.xml中的命名空间+SQL语句的id 例如：
			 * MyUser mu = ss.selectOne("gzc.mapper.MyUserMapper.selectUserById", 6);
			 */

			/*
			 * 方法二 ： 通过SqlSession对象getMapper方法获得Mapper映射与Dao接口映射
			 * 该方法需要绑定dao的接口到Mapper的namespace中
			 */

			// 将dao接口方法与映射文件关联，返回接口对象
			UserDao userDao = ss.getMapper(UserDao.class);
			// 查询一个用户
			MyUser user = userDao.selectUserById(1);
			System.out.println(user);

			// 查找所有用户
			List<MyUser> myUsers = userDao.selectAllUser();
			for (MyUser myUser : myUsers) {
				System.out.println(myUser);
			}

			ss.commit();
			ss.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
