package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import vo.PhonebookVO;
@Repository
public class PhonebookDAO implements PhonebookDAOInter{
  
	Connection conn=null;
	PreparedStatement pstmt=null;
	String sql=null;
	
	public PhonebookDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test", "1111");
		}catch (Exception e) {
			System.out.println("connection 오류");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public int insert(PhonebookVO pb) {
		try {
			sql="insert into phonebook values(phonebook_idx_seq.nextval,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pb.getName());
			pstmt.setString(2, pb.getHp());
			pstmt.setString(3, pb.getMemo());
			int result=pstmt.executeUpdate();
			return result;
		}catch (Exception e) {
			System.out.println("insert 오류");
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<PhonebookVO> getList() {
		try {
			sql="select * from phonebook order by idx desc";
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			List<PhonebookVO> list=new ArrayList<PhonebookVO>();
			while(rs.next()) {
				PhonebookVO pb=new PhonebookVO();
				pb.setIdx(rs.getInt("idx"));
				pb.setName(rs.getString("name"));
				pb.setHp(rs.getString("hp"));
				pb.setMemo(rs.getString("memo"));
				list.add(pb);
			}
			return list;
			}catch(Exception e) {
				System.out.println("getList 오류");
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public List<PhonebookVO> find(String search) {
		try {
				sql="select * from phonebook where name like '%"+search+"%' OR hp like '%"+search+"%' OR memo like '%"+search+"%'";
				pstmt=conn.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				List<PhonebookVO> list=new ArrayList<PhonebookVO>();
				while(rs.next()) {
					int idx=rs.getInt("idx");
					String name=rs.getString("name");
					String hp=rs.getString("hp");
					String memo=rs.getString("memo");
					PhonebookVO imsi=new PhonebookVO(idx, name, hp, memo);
					list.add(imsi);
				}
				return list;
				}catch(Exception e) {
					System.out.println("find 오류");
					e.printStackTrace();
					return null;
				}
	}

	@Override
	public PhonebookVO findOne(int _idx) {
		try {
			sql="select * from phonebook where idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, _idx);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				int idx=rs.getInt("idx");
				String name=rs.getString("name");
				String hp=rs.getString("hp");
				String memo=rs.getString("memo");
				PhonebookVO imsi=new PhonebookVO(idx, name, hp, memo);
				return imsi;
			}
				return null;
			}catch(Exception e) {
				System.out.println("findOne 오류");
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public int update(PhonebookVO pb) {
		try {
			sql="update phonebook set name=?, hp=? ,memo=? where idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pb.getName());
			pstmt.setString(2, pb.getHp());
			pstmt.setString(3, pb.getMemo());
			pstmt.setInt(4, pb.getIdx());
			int result=pstmt.executeUpdate();
			return result;
			}catch(Exception e) {
				System.out.println("update 오류");
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public int delete(int idx) {
		try {
			sql="delete from phonebook where idx=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			int result=pstmt.executeUpdate();
			return result;
			}catch(Exception e) {
				System.out.println("delete 오류");
				e.printStackTrace();
				return 0;
			}
	}

	//@Test
	public void test() {
		PhonebookDAO dao=new PhonebookDAO();
		System.out.println(dao.conn);
		PhonebookVO pb=new PhonebookVO();
		pb.setName("test");
		pb.setHp("010-0000-0000");
		pb.setMemo("test 하는 중");
		System.out.println(dao.insert(pb));
		System.out.println(dao.find("test"));
	}
	
}
