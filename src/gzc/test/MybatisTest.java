package gzc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
			// SqlSession执行映射文件中定义的sql，并返回映射结果
			// gzc.mapper.MyUserMapper.selectUserById 为MyUserMapper.xml中的命名空间+SQL语句的id
			// 查询一个用户
			MyUser mu = ss.selectOne("gzc.mapper.MyUserMapper.selectUserById", 6);
			System.out.println(mu);
			// 添加一个用户
			MyUser newUser = new MyUser(7, "小红", "女");
			ss.insert("gzc.mapper.MyUserMapper.addUser", newUser);

			// 修改一个用户
			MyUser updatemu = new MyUser(3, "小明", "男");
			ss.update("gzc.mapper.MyUserMapper.updateUser", updatemu);

			// 删除一个用户
			ss.delete("gzc.mapper.MyUserMapper.deleteUser", 6);

			// 查找所有用户
			List<MyUser> myUsers = ss.selectList("gzc.mapper.MyUserMapper.selectAllUser");
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
