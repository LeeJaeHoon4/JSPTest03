package com.nowon.green.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.nowon.green.dto.MemoDTO;

/**
 * Servlet implementation class FrontController
 */
//@WebServlet("/memo/*")
public class FrontController extends HttpServlet {
	Connection conn =null;
	ServletContext sc = null;
	DataSource ds = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		sc = config.getServletContext();
//		conn = (Connection)sc.getAttribute("conn");
//		System.out.println(conn);
		ds = (DataSource) sc.getAttribute("ds");
	}

	private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 요청시 처리");
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 요청시 처리");
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("process 처리");
//		//ServletContext 객체를 통해 conn 객체를 불러와서 사용
//		ServletContext sc = getServletContext();
//		//넘어올때 Object 타입으로 넘어오기 때문에 원하는 객체 타입으로 캐스팅을 해줘야함
//		Connection conn = (Connection) sc.getAttribute("conn");
//		System.out.println(conn);
		String uri = request.getRequestURI();
//		/memo/list 로 요청이 발생 했을때 이 요청을 처리하는 비즈니스로직
		// 비즈니스 로직 : Service영역
		String path = null;
		String sql = null;
		PreparedStatement pstmt =null;
		if(uri.contains("/memo/list")) {
			List<MemoDTO> list = new ArrayList<>();
		 sql ="select * from memo where 1=1 order by no desc";
		ResultSet rs = null;
		try {
			 conn = ds.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 System.out.println("조회 처리 완료");
			 while(rs.next()) {
				 int no = rs.getInt("no");
				 String content = rs.getString("content");
				 String  writer = rs.getString("writer");
				 Timestamp cDate = rs.getTimestamp("created_date");
				 Timestamp uDate = rs.getTimestamp("updated_date");
				 MemoDTO dto = new MemoDTO();
				 dto.setNo(no);
				 dto.setContent(content);
				 dto.setWriter(writer);
				 dto.setCdate(cDate.toLocalDateTime());
				 dto.setUdate(uDate.toLocalDateTime());
				 list.add(dto);
			 }
			 System.out.println("list에 들어간 갯수"+list.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {if(rs!=null)rs.close();} catch (SQLException e) {}
			try {if(pstmt!=null)pstmt.close();} catch (SQLException e) {}
			try {if(conn!=null)conn.close();} catch (SQLException e) {}
		 }
		//list에 저장된 정보를 원하는 페이지로 넘김
				request.setAttribute("list", list);
				//응답할 페이지로 경로 설정해서 넘김
				path = "/WEB-INF/view/memo/list.jsp";
		}//if문끝
		else if(uri.contains("/memo/write")) {
			path = "/WEB-INF/view/memo/write.jsp";
		}//write page로 넘기기
		else if(uri.contains("/memo/new")) {//메모 저장버튼 클릭시 실행
			request.setCharacterEncoding("UTF-8");//한글 인코딩
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			try {
				conn =ds.getConnection();
				sql = "insert into memo values(seq_memo.nextval,?,?,systimestamp,systimestamp)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,content);
				pstmt.setString(2, writer);
				pstmt.executeUpdate();
				System.out.println("메모 저장 완료");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {if(pstmt!=null)pstmt.close();} catch (SQLException e) {}
				try {if(conn!=null)conn.close();} catch (SQLException e) {}
				
			}//try문 끝
			//저장이 완료되면 list페이지로 리턴
			//path = "/WEB-INF/views/list.jsp";<< 코드가 위에 list인 경우 코드가 중복 되기 때문에
			//response 객체의 sendRedirect("주소명") 기능을 사용한다.
			response.sendRedirect("./list");
			return;
			}//메모 저장 끝
		
		
		if(path != null)
		request.getRequestDispatcher(path).forward(request, response);
	}

}
