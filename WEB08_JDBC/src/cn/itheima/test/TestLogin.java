package cn.itheima.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestLogin {
	@Test
	public void testLogin() {
		
		try {
//			login("老王1", "123456");
			login2("老王1", "123456");
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	} 
	
	public void login(String username, String password) throws ClassNotFoundException, SQLException {
		//1.注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2.获取数据库连接
		String url = "jdbc:mysql://localhost:3306/web08";
		Connection connection = DriverManager.getConnection(url, "root", "123456");
		//3.创建执行SQL语句的对象
		Statement stmt = connection.createStatement();
		//4.书写SQL语句
		String sql = "SELECT * FROM tbl_user WHERE" + " uname='" + username + "' AND upassword='" + password + "'";
		//5.执行SQL
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			System.out.println("恭喜" + username + " 登录成功!");
		} else {
			 System.out.println("账户或密码错误!");
		}
		//6.关闭资源
		if (rs!=null) {
			rs.close();
		}
		if (stmt!=null) {
			stmt.close();
		}
		if (connection!=null) {
			connection.close();
		}
	}
	
	public void login2(String username,String password) throws ClassNotFoundException, SQLException {
		//1.注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2.获取数据库连接
		String url = "jdbc:mysql://localhost:3306/web08";
		Connection connection = DriverManager.getConnection(url, "root", "123456");
		//3.编写SQL语句
		String sql = "select * from tbl_user where uname=? and upassword=?";
		//4.创建预处理对象
		PreparedStatement pstmt = connection.prepareStatement(sql);
		//5.给占位符号设置参数
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		
		//6.执行查询操作
		ResultSet rs = pstmt.executeQuery();
		//7.对结果集进行处理
		if (rs.next()) {
			System.out.println("恭喜" + username + " 登录成功!");
		} else {
			 System.out.println("账户或密码错误!");
		}
		if (rs!=null) {
			rs.close();
		}
		if (pstmt!=null) {
			pstmt.close();
		}
		if (connection!=null) {
			connection.close();
		}
	}
}
