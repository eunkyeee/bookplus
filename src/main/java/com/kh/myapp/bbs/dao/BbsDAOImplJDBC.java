package com.kh.myapp.bbs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.myapp.bbs.dto.BbsDTO;

@Repository
public class BbsDAOImplJDBC implements BbsDAO {

	private static Logger logger=LoggerFactory.getLogger(BbsDAOImplJDBC.class);
	
	@Inject
	JdbcTemplate jt;
	
	//게시글 작성
	@Override
	public int write(BbsDTO bbsDTO) {

		int cnt=0;
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into board ");
		sql.append("(bnum, btitle, bid, bnickname, bhit, bcontent, bgroup, bstep, bindent) ");
		sql.append("values(board_seq.nextval,?,?,?,0,?,board_seq.currval,0,0) ");
		
		cnt=jt.update(sql.toString(),bbsDTO.getBtitle(), bbsDTO.getBid(),bbsDTO.getBnickname(),bbsDTO.getBcontent());
		return cnt;
	}

	//게시글 수정
	@Override
	public int modify(BbsDTO bbsDTO) {

		int cnt=0;
		
		StringBuffer sql=new StringBuffer();
		sql.append("update board ");
		sql.append("set btitle= ? , budate=sysdate , bcontent= ? ");
		sql.append("where bnum=? ");
		
		cnt=jt.update(sql.toString(), 
									bbsDTO.getBtitle(),bbsDTO.getBcontent(),bbsDTO.getBnum());
		return cnt;
	}

	//게시글 삭제
	@Override
	public int delete(String bnum) {
		int cnt=0;
		
		StringBuffer sql=new StringBuffer();
		sql.append("delete from board where bnum=? ");
		cnt=jt.update(sql.toString(), bnum);
		return cnt;
	}

	//게시글 보기
	@Override
	public BbsDTO view(String bnum) {
		BbsDTO bbsDTO=new BbsDTO();
		StringBuffer sql=new StringBuffer();
		sql.append("select bnum,btitle,bid,bnickname,bcdate,budate, ");
		sql.append("bhit,bcontent,bgroup,bstep,bindent ");
		sql.append("from board ");
		sql.append("where bnum= ? ");
		
		bbsDTO=jt.queryForObject(sql.toString(), new BeanPropertyRowMapper<BbsDTO>(BbsDTO.class), bnum);
		
	//조회수 증가
		if(bbsDTO.getBid() !=null) {
			
		updateHit(bnum);
		}
		return bbsDTO;
	}
	
	//조회수 증가
	private void updateHit(String bnum) {
		StringBuffer sql=new StringBuffer();
		sql.append("update board set bhit=bhit+1 ");
		sql.append("where bnum=? ");
		
		jt.update(sql.toString(), bnum);
	}

	//게시글 목록 보기-전체
	@Override
	public List<BbsDTO> list() {
		List<BbsDTO> list=new ArrayList<>();
		
		StringBuffer sql=new StringBuffer();
		sql.append("select bnum,btitle,bid,bnickname,bcdate,budate, ");
		sql.append("bhit,bcontent,bgroup,bstep,bindent ");
		sql.append("from board ");
		
		list=jt.query(sql.toString(),new RowMapper<BbsDTO>() {

			@Override
			public BbsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
					BbsDTO bbsDTO=new BbsDTO();
					bbsDTO.setBnum(rs.getInt("bnum"));
					bbsDTO.setBtitle(rs.getString("btitle"));
					bbsDTO.setBid(rs.getString("bid"));
					bbsDTO.setBnickname(rs.getString("bnickname"));
					bbsDTO.setBcdate(rs.getDate("bcdate"));
					bbsDTO.setBudate(rs.getDate("budate"));
					bbsDTO.setBhit(rs.getInt("bhit"));
					bbsDTO.setBcontent(rs.getString("bcontent"));
					bbsDTO.setBstep(rs.getInt("bstep"));
					bbsDTO.setBindent(rs.getInt("bindent"));
				return bbsDTO;
			}
		});
		
		return list;
	}

	//게시글 목록 보기-검색어 없는 경우
	@Override
	public List<BbsDTO> list(int startRec, int endRec) {

		List<BbsDTO> list = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t2.* ");
	  sql.append("from ( select row_number() over (order by bgroup desc, bstep asc) as num, t1.* ");
	  sql.append("         from board t1 ) t2 ");
	  sql.append("where num between ? and ? ");
	  
		list=jt.query(sql.toString(),new RowMapper<BbsDTO>() {

			@Override
			public BbsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
					BbsDTO bbsDTO=new BbsDTO();
					bbsDTO.setBnum(rs.getInt("bnum"));
					bbsDTO.setBtitle(rs.getString("btitle"));
					bbsDTO.setBid(rs.getString("bid"));
					bbsDTO.setBnickname(rs.getString("bnickname"));
					bbsDTO.setBcdate(rs.getDate("bcdate"));
					bbsDTO.setBudate(rs.getDate("budate"));
					bbsDTO.setBhit(rs.getInt("bhit"));
					bbsDTO.setBcontent(rs.getString("bcontent"));
					bbsDTO.setBstep(rs.getInt("bstep"));
					bbsDTO.setBindent(rs.getInt("bindent"));
				return bbsDTO;
			}
		},startRec,endRec);
		return list;
	}

	//게시글 목록 보기-검색어 있는 경우
	@Override
	public List<BbsDTO> list(int startRec, int endRec, String searchType, String keyword) {

		List<BbsDTO> list=new ArrayList<>();
		
		StringBuffer sql=new StringBuffer();
		
		sql.append("select t2.* ");
		sql.append("from (select row_number() over (order by bgroup desc, bstep asc) as num, t1.* ");
		sql.append("      from board t1 ");
		sql.append("      where bnum>0 ");
					switch(searchType) {
					//제목+내용
					case "TC":
						sql.append("and btitle like '%' || ? || '%' ");
						sql.append("or bcontent like '%' || ? || '%' ");
						break;
					//제목
					case "T":
						sql.append("and btitle like '%' || ? || '%' ");
						break;
					//내용
					case "C":
						sql.append("and bcontent like '%' || ? || '%' ");
						break;
					//작성자(닉네임)
					case "N":
						sql.append("and bnickname like '%' || ? || '%' ");
						break;
					//아이디
					case "I":
						sql.append("and bid like '%' || ? || '%' ");
						break;
					
					//제목+내용+작성자(닉네임)
					default:
						sql.append("and btitle like '%' || ? || '%' ");
						sql.append("or bcontent like '%' || ? || '%' ");
						sql.append("or bnickname like '%' || ? || '%' ");
						break;		
					}
					
		sql.append(" ) t2 ");
		sql.append("where num between ? and ? ");
		
		switch(searchType) {
		case "TC":
			list=jt.query(sql.toString(), 
										new BeanPropertyRowMapper<BbsDTO>(BbsDTO.class),
										keyword,keyword,startRec,endRec);
			break;
		case "T":
		case "C":
		case "N":
		case "I":
			list=jt.query(sql.toString(), 
					new BeanPropertyRowMapper<BbsDTO>(BbsDTO.class),
					keyword,startRec,endRec);
			break;
			default:
				list=jt.query(sql.toString(), 
						new BeanPropertyRowMapper<BbsDTO>(BbsDTO.class),
						keyword,keyword,keyword,startRec,endRec);
				break;
		}		
		return list;
	}

	//게시글 답글
	@Override
	public int reply(BbsDTO bbsDTO) {

		int cnt=0, cnt2=0;
		
		//1) 이전 답글 step업데이트
		cnt2=updateStep(bbsDTO.getBgroup(), bbsDTO.getBstep());
	
		//2) 답글 달기
		StringBuffer sql=new StringBuffer();
		sql.append("insert into board ");
		sql.append("(bnum, btitle, bid, bnickname, bhit, bcontent, bgroup, bstep, bindent) ");
		sql.append("values(board_seq.nextval,?,?,?,0,?,?,?,?) ");
		
		cnt=jt.update(sql.toString(), bbsDTO.getBtitle(),bbsDTO.getBid(),bbsDTO.getBnickname(),
							bbsDTO.getBcontent(),bbsDTO.getBgroup(),bbsDTO.getBstep() + 1,
							bbsDTO.getBindent() + 1);
		return cnt;
	}
	
	private int updateStep(int bgroup, int bstep) {
		int cnt=0;
		
		StringBuffer sql=new StringBuffer();
		sql.append("update board ");
		sql.append("set bstep = bstep + 1 ");
		sql.append("where bgroup=? and bstep>? ");
		
		cnt=jt.update(sql.toString(), bgroup,bstep);
		return cnt;
	}
	
	//게시글 총계-검색어 없는 경우
	@Override
	public int totalRec() {
		int cnt=0;
		StringBuffer sql=new StringBuffer();
		sql.append("select count(bnum) totalcnt from board ");
		
		cnt=jt.queryForObject(sql.toString(), Integer.class);
		
		
		return cnt;
	}

	//게시글 총계-검색어 있는 경우
	@Override
	public int searchTotalRec(String searchType, String keyword) {
		int totalRec=0;
		StringBuffer sql=new StringBuffer();
			
			sql.append("select count(bnum) totalRec ");
			sql.append("from (select row_number() over (order by bgroup desc, bstep asc) as num, t1.* ");
			sql.append("      from board t1 ");
			sql.append("      where bnum>0 ");
						switch(searchType) {
						//제목+내용
						case "TC":
							sql.append("and btitle like '%' || ? || '%' ");
							sql.append("or bcontent like '%' || ? || '%' ");
							break;
						//제목
						case "T":
							sql.append("and btitle like '%' || ? || '%' ");
							break;
						//내용
						case "C":
							sql.append("and bcontent like '%' || ? || '%' ");
							break;
						//작성자(닉네임)
						case "N":
							sql.append("and bnickname like '%' || ? || '%' ");
							break;
						//아이디
						case "I":
							sql.append("and bid like '%' || ? || '%' ");
							break;
						
						//제목+내용+작성자(닉네임)
						default:
							sql.append("and btitle like '%' || ? || '%' ");
							sql.append("or bcontent like '%' || ? || '%' ");
							sql.append("or bnickname like '%' || ? || '%' ");
							break;		
						}
						
			sql.append(" ) t2 ");
			
			switch(searchType) {
			case "TC":
				totalRec=jt.queryForObject(sql.toString(), 
																	 Integer.class,
																	 keyword,keyword);
				break;
			case "T":
			case "C":
			case "N":
			case "I":
				totalRec=jt.queryForObject(sql.toString(), 
						 											 Integer.class,
						 											 keyword);
				break;
			default:
				totalRec=jt.queryForObject(sql.toString(), 
						 											 Integer.class,
						 											 keyword,keyword,keyword);
				break;
			}
			
		return totalRec;
	}

}
