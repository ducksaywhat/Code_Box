package guestboard;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseAccess
 */
@WebServlet("/DatabaseAccess")
public class guestboard101 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/guestboard?useSSL=true&useUnicode=true&characterEncoding=UTF-8";
    
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "wwx"; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public guestboard101() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title1 = "Guestboard1.0.1(Servlet)";
	String title2 = "基于Servlet的留言板";
        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
         "<html>\n" +
         "<head><title>" + title1 + "</title></head>\n" +
         "<body bgcolor=\"#f0f0f0\">\n" +
         "<h1 align=\"center\">" + title1 + "</h1>\n" +
         "<h2 align=\"center\">"+title2+"</h2>\n" +
         "<table border=\"1\" align=\"center\">\n" +
         "<tr bgcolor=\"#949494\">\n" +
         "  <th>No</th><th>日期</th><th>用户ID</th><th>用户类型</th>"
         +"<th>留言内容</th></tr>\n");
        try{
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");
            
            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行 SQL 查询
            stmt = conn.createStatement();
            String sql;
            sql = "select* from messages order by no desc LIMIT 77";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int no  = rs.getInt("no");
                String name = rs.getString("name");
                String type="未知用户";
		switch(rs.getInt("type"))
		{
		case 0:type="管理员";break;
		case 1:type="普通访客";break;
		case 2:type="普通用户";break;

		}
		String time=rs.getString("time");
		time=time.substring(0,time.length()-2);
		String message=rs.getString("message");
		out.println("<tr>\n" +
                "  <td>"+no+"</td>" +
                "  <td>"+time+"</td>" +
                "  <td>"+name+"</td>" +
                "  <td>"+type+"</td>" +
                "  <td>"+message+"</td>" +
		"</tr>\n");
    
            }
            out.println("</body></html>");

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 最后是用于关闭资源的块
            try{
                if(stmt!=null)
                stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
       
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
