package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Dept;
import DTO.Emp;
import UTILS.SingletonHelper;

public class EmpDao {
	//1.전체조회
	public List<Emp> getEmpAllList(){
		List<Emp> emplist = new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			   String sql="select empno , ename, job, mgr, hiredate, sal, comm, deptno from emp";
			   pstmt = conn.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   
			   while(rs.next()) {
				   Emp emp = new Emp();
				   emp.setEmpno(rs.getInt("empno"));
				   emp.setEname(rs.getString("ename"));
				   emp.setJob(rs.getString("job"));
				   emp.setMgr(rs.getInt("mgr"));
				   emp.setHiredate(rs.getDate("hiredate"));
				   emp.setSal(rs.getInt("sal"));
				   emp.setComm(rs.getInt("comm"));
				   emp.setDeptno(rs.getInt("deptno"));
				   emplist.add(emp);
			   }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return emplist;
	}
	
	//조건조회
	public Emp getEmpListByEmp(int empno) {
		
		Emp emp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno , ename, job, mgr, hiredate, sal, comm, deptno from emp where empno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				emp = new Emp(); // 하나의 row 담기 위한 객체
				emp.setEmpno(rs.getInt("empno"));
				   emp.setEname(rs.getString("ename"));
				   emp.setJob(rs.getString("job"));
				   emp.setMgr(rs.getInt("mgr"));
				   emp.setHiredate(rs.getDate("hiredate"));
				   emp.setSal(rs.getInt("sal"));
				   emp.setComm(rs.getInt("comm"));
				   emp.setDeptno(rs.getInt("deptno"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		return emp;				
	}
	
	public int insertEmp(Emp emp) {
		Connection conn = null;
		 PreparedStatement pstmt=null;
		 int rowcount = 0;
		 
		 try {
			 conn = SingletonHelper.getConnection("oracle");
			 String sql="insert into emp(empno , ename, job, mgr, hiredate, sal, comm, deptno) values(?,?,?,?,?,?,?,?)";
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setInt(1, emp.getEmpno());
			 pstmt.setString(2, emp.getEname());
			 pstmt.setString(3, emp.getJob());
			 pstmt.setInt(4, emp.getMgr());
			 pstmt.setDate(5, emp.getHiredate());
			 pstmt.setInt(6, emp.getSal());
			 pstmt.setInt(7, emp.getComm());
			 pstmt.setInt(8, emp.getDeptno());
			 rowcount = pstmt.executeUpdate(); 
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }finally {
			 SingletonHelper.close(pstmt);
		 }
		return rowcount;
		
	}
	
	public int updateEmp(Emp emp) {
		Connection conn = null;
		PreparedStatement pstmt=null;
		int rowcount = 0;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			 String sql="update emp set ename=? , job=? , sal=? , hiredate=? where empno=?";
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, emp.getEname());
			 pstmt.setString(2, emp.getJob());
			 pstmt.setDate(3, emp.getHiredate());
			 
			 rowcount = pstmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		
		
		return rowcount;
	}
	
	public int deleteEmp(int empno) {
		 Connection conn = null;
		 PreparedStatement pstmt=null;
		 int rowcount = 0;
		 
		 try {
			 conn = SingletonHelper.getConnection("oracle");
			 String sql="delete from emp where empno=?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, empno);
			 rowcount = pstmt.executeUpdate();
		 }catch (Exception e) {
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }finally {
			 SingletonHelper.close(pstmt);
		 }
		 
		 return rowcount;
	}
	
}
