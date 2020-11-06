package co.animalMate.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.animalMate.common.DAO;
import co.animalMate.vo.OwnerInsertVO;
import co.animalMate.vo.OwnerListVO;
import co.animalMate.vo.TradeBoardVO;


public class BoardDao extends DAO {
	private PreparedStatement psmt; // sql 명령문 실행
	private ResultSet rs; // select 후 결과셋 받기
	private OwnerListVO vo;
	private OwnerInsertVO insertvo;
	
	private final String SELECT =  "SELECT A.PRICE, A.WTIME, A.LOCATION2 , A.STATUS, B.TYPE, B.PIC"
									+ " FROM TRADEBOARD A"
									+ " INNER JOIN PET B  "
									+ "ON A.BUYER = B.ID ";
	

	//거래게시판 
	public List<OwnerListVO> selectAll() {
		List<OwnerListVO> list = new ArrayList<OwnerListVO>();
		try {
			psmt = conn.prepareStatement(SELECT);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new OwnerListVO();
				vo.setPic(rs.getString("pic"));
				vo.setType(rs.getString("type"));
				vo.setPrice(rs.getInt("price"));
				vo.setWtime(rs.getInt("wtime"));
				vo.setLocation2(rs.getString("location2"));
				vo.setStatus(rs.getString("status"));
				list.add(vo);
			}
		} catch (SQLException e) {//
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	
	private final String INSERT = "INSERT INTO TRADEBOARD "
			+ "(CODE,BUYER,TITLE,TTIME,PRICE,COMM,STATUS,"
			+ "STIME,ETIME,WTIME,TTYPE,LOCATION2) "
			+ "VALUES "
			+ "(BOARD_SEQ.NEXTVAL, ?,?,SYSDATE,?,?,?,"
			+ "?,?,?,?,?)";

	public int insert(OwnerInsertVO insertvo) {
		int n = 0;
		try {
		psmt = conn.prepareStatement(INSERT);
		psmt.setString(1,insertvo.getBuyer());
		psmt.setString(2,insertvo.getTitle());
		psmt.setInt(3, insertvo.getPrice());
		psmt.setString(4,insertvo.getComm());
		psmt.setString(5, insertvo.getStatus());
		psmt.setString(6, insertvo.getStime());
		psmt.setString(7, insertvo.getEtime());
		psmt.setInt(8, insertvo.getWtime());
		psmt.setString(9, insertvo.getTtype());
		psmt.setString(10, insertvo.getLocation2());
		

		
		
		n = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	
	
	// DB자원해제
	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
