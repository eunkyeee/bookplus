package com.kh.myapp.student;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.Data;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestUpdateJdbcTemplate {

	private static final Logger logger=LoggerFactory.getLogger(TestUpdateJdbcTemplate.class);
	
	@Inject
	JdbcTemplate jt;
	
	StringBuffer sql;
	
	@BeforeEach //@Test 메소드 실행 전에 실행할 명렁어 정의
	void before() {
		sql=new StringBuffer();
	}
	
	@Test
	@Disabled
	void update() {
		int[] jumsu= {100,100,100};
		String id="id1";
		
		//update
		sql.append("update student set kor=?, eng=?, math=? where name=? ");
		
		int cnt=jt.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt=con.prepareStatement(sql.toString());
				pstmt.setInt(1, jumsu[0]);
				pstmt.setInt(2, jumsu[1]);
				pstmt.setInt(3, jumsu[2]);
				pstmt.setString(4, id);
				return pstmt;
			}
		});
		logger.info("변경건수: "+cnt);
		
		//select
		sql=new StringBuffer();
		sql.append("select kor,eng,math from student where id=? ");
		
		//case1)
		StudentDTO student=jt.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(StudentDTO.class),id);
		//case2)
//		StudentDTO student=jt.queryForObject(sql.toString(), new RowMapper<StudentDTO>() {
//			
//			@Override
//			public StudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
//				StudentDTO student= new StudentDTO();
//				student.setKor(Integer.parseInt(rs.getString("kor")));
//				student.setEng(Integer.parseInt(rs.getString("eng")));
//				student.setMath(Integer.parseInt(rs.getString("math")));
//				return student;
//			}
//		},id);
		
		boolean result=student.getKor()==jumsu[0]&&
									 student.getEng()==jumsu[1]&&
									 student.getMath()==jumsu[2];
		
		assertTrue(result);
	}
	
	@Test
	@Disabled
	void update2() {
		sql.append("update student set kor=?, eng=?, math=? where id=? ");

		int cnt=jt.update(sql.toString(), 90,90,90,"id2");
		logger.info("변경건수: "+cnt);
		
	}
	
	@Test
	@Disabled
	void update3() {
		sql.append("update student set kor=?, eng=?, math=? where id=? ");
		
		int cnt=jt.update(sql.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, 70);
				ps.setInt(2, 70);
				ps.setInt(3, 70);
				ps.setString(4, "id3");
			}
		});
				
		logger.info("변경건수: "+cnt);
		
	}
	
	@Test
	void update4() {
		sql.append("update student set kor=?, eng=?, math=? where id=? ");

		int cnt=jt.update(sql.toString(),
							new Object[] {"60","60","60","id4"}, 
							new int[] {Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.VARCHAR});
		logger.info("변경건수: "+cnt);

	}
	
	
	
}


@Data
class StudentDTO{
	private String id;
	private String name;
	private int kor;
	private int eng;
	private int math;
	
}




