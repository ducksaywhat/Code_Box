package user;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class usersignup extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/users?useSSL=true&useUnicode=true&characterEncoding=UTF-8";
    
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "wwx"; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public usersignup() {
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
        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
         "<html>\n" +
         "<head>"+
	 "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/Ducksaywhat.css\" />"+
	 "<style type=\"text/css\">\n"+".greating{ color:#FF9C47; }\n"+"#Mtable{ width:700px;height:120px; }\n"
	 + "tr,td{ color:#383838; font-size:130%; }\n"+"tr{ height:50px; }\n"+"a{ font-size:150%;text-decoration:underline; }\n"+
	"</style>"+
	 "<title>" + "Ducksaywhat" + "</title></head>\n<body>");
	    String account =new String(request.getParameter("account").getBytes("ISO8859-1"),"UTF-8");
	    String password1 =new String(request.getParameter("password1").getBytes("ISO8859-1"),"UTF-8");
	    String password2 =new String(request.getParameter("password2").getBytes("ISO8859-1"),"UTF-8");
	    String mail =new String(request.getParameter("mail").getBytes("ISO8859-1"),"UTF-8");
	    String pattern="[a-zA-Z]\\S*";
	    if(account.length()>7)
	    {
			out.println("<div id=\"container\">");
		   	out.println("<div id=\"page\">");
			out.println("<img src=\"./images/Logo.png\">");
			out.println("<h1 class=\"greating\">用户名不符合规范</h1>");
			out.println("<h1 class=\"greating\">用户名长度要小于七位字符</h1>");
			out.println("<h1 class=\"greating\">请更换用户名</h1>");
			out.println("</div>");
			out.println("<div id=\"footer\"><p>联系方式 QQ: 892905369 | WeChat: doubixuan  Weibo: @王午宣 Copyright &copy; Wwx 2017</p></div>");
			out.println("</div>");
			out.println("</body></html>");
			return;
	    }
	    if(!Pattern.matches(pattern,password1)||(password1.length()<7||password1.length()>25))
		{
			out.println("<div id=\"container\">");
		   	out.println("<div id=\"page\">");
			out.println("<img src=\"./images/Logo.png\">");
			out.println("<h1 class=\"greating\">密码输入不符合规范</h1>");
			out.println("<h1 class=\"greating\">开头必须为大或小写字母,后由数字字母和基础字符随意搭配</h1>");
			out.println("<h1 class=\"greating\">总体长度要大于七位,并小于二十五位</h1>");
			out.println("<h1 class=\"greating\">请检查输入</h1>");
			out.println("</div>");
			out.println("<div id=\"footer\"><p>联系方式 QQ: 892905369 | WeChat: doubixuan  Weibo: @王午宣 Copyright &copy; Wwx 2017</p></div>");
			out.println("</div>");
			out.println("</body></html>");
			return;
		}
	    if(!password1.equals(password2))
		{
			out.println("<div id=\"container\">");
		   	out.println("<div id=\"page\">");
			out.println("<img src=\"./images/Logo.png\">");
			out.println("<h1 class=\"greating\">两次输入密码不一致</h1>");
			out.println("<h1 class=\"greating\">请重新输入</h1>");
			out.println("</div>");
			out.println("<div id=\"footer\"><p>联系方式 QQ: 892905369 | WeChat: doubixuan  Weibo: @王午宣 Copyright &copy; Wwx 2017</p></div>");
			out.println("</div>");
			out.println("</body></html>");
			return;
		}
		pattern="([a-zA-Z]|[0-9])+@([a-zA-Z]|[0-9])+.[a-zA-Z]+";
		if(!Pattern.matches(pattern,mail))
		{
			out.println("<div id=\"container\">");
			out.println("<div id=\"page\">");
			out.println("<img src=\"./images/Logo.png\">");
			out.println("<h1 class=\"greating\">邮箱输入格式不正确</h1>");
			out.println("<h1 class=\"greating\">请检查输入</h1>");
			out.println("</div>");
			out.println("<div id=\"footer\"><p>联系方式 QQ: 892905369 | WeChat: doubixuan  Weibo: @王午宣 Copyright &copy; Wwx 2017</p></div>");
			out.println("</div>");
			out.println("</body></html>");
			return;
		}
        try{
	    //打开SQL链接
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行 SQL 查询
            stmt = conn.createStatement();
      	    String sql=String.format("select id from accounts where name=\"%s\"",account);
            ResultSet rs = stmt.executeQuery(sql);
	    if(rs.next())
	{
			out.println("<div id=\"container\">");
			out.println("<div id=\"page\">");
			out.println("<img src=\"./images/Logo.png\">");
			out.println("<h1 class=\"greating\">该用户名已被注册</h1>");
			out.println("<h1 class=\"greating\">请使用其他用户名</h1>");
			out.println("</div>");
			out.println("<div id=\"footer\"><p>联系方式 QQ: 892905369 | WeChat: doubixuan  Weibo: @王午宣 Copyright &copy; Wwx 2017</p></div>");
			out.println("</div>");
			out.println("</body></html>");
			rs.close();
          	  	stmt.close();
          	  	conn.close();
			return;
	}
	 else
	{
			sql="select id from accounts order by id desc LIMIT 1";
	 		rs = stmt.executeQuery(sql);
			if(rs.next())
			{
			int id=0;
			id=rs.getInt("id")+1;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
		        String buildday= formatter.format(date);
			String buildIP=request.getRemoteAddr();
			sql=String.format("insert into accounts(id,password,name,mail,buildday,buildIP,exp,type,dueday,text,lastsign,logins) values(%d,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",0,1,\"%s\",\" \",\"%s\",0)",id,password1,account,mail,buildday,buildIP,"1000-1-1",buildday);
			if(stmt.executeUpdate(sql)==1)
			{
			out.println("<div id=\"container\">");	
			out.println("<div id=\"page\">");
			out.println("<img src=\"./images/Logo.png\">");
			out.println("<h1 class=\"greating\">账户注册成功！！</h1>");
			out.println("<h1 class=\"greating\">以下为您的注册信息，请牢记或截图留存</h1>");
			out.println("<table id=\"Mtable\" align=\"center\" border=\"1\"><tr bgcolor=\"#CAE1FF\"><th>ID</th><th>用户名</th><th>邮箱</th></tr><tr bgcolor=\"#F0FFFF\"><td>"+id+"</td><td>"+account+"</td><td>"+mail+"</td></tr></table>");
			out.println("<br/>");
			out.println("<a href=\"/login.html\">点我去登录</a>");
			out.println("</div>");
			out.println("<div id=\"footer\"><p>联系方式 QQ: 892905369 | WeChat: doubixuan  Weibo: @王午宣 Copyright &copy; Wwx 2017</p></div>");
			out.println("</div>");
			out.println("</body></html>");
			rs.close();
          	  	stmt.close();
          	  	conn.close();
			return;	
			}
			else
			{
			out.println("<div id=\"container\">");	
			out.println("<div id=\"page\">");
			out.println("<img src=\"./images/Logo.png\">");
			out.println("<h1 align=\"center\">服务器出现错误，请稍作等待再重试或联系作者</h1>");
			out.println("<h1 align=\"center\">为您造成的不便深表抱歉</h1>");
			out.println("</div>");
			out.println("<div id=\"footer\"><p>联系方式 QQ: 892905369 | WeChat: doubixuan  Weibo: @王午宣 Copyright &copy; Wwx 2017</p></div>");
			out.println("</div>");
			out.println("</body></html>");
			rs.close();
          	  	stmt.close();
          	  	conn.close();
			return;
			}

			}
			else
			{
			out.println("<div id=\"container\">");	
			out.println("<div id=\"page\">");
			out.println("<img src=\"./images/Logo.png\">");
			out.println("<h1 align=\"center\">服务器出现错误，请稍作等待再重试或联系作者</h1>");
			out.println("<h1 align=\"center\">为您造成的不便深表抱歉</h1>");
			out.println("</div>");
			out.println("<div id=\"footer\"><p>联系方式 QQ: 892905369 | WeChat: doubixuan  Weibo: @王午宣 Copyright &copy; Wwx 2017</p></div>");
			out.println("</div>");
			out.println("</body></html>");
			rs.close();
          	  	stmt.close();
          	  	conn.close();
			return;
			}

	}
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
