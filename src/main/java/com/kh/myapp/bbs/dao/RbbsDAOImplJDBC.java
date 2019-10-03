package com.kh.myapp.bbs.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.myapp.bbs.dto.RbbsDTO;

@Repository
public class RbbsDAOImplJDBC implements RbbsDAO {

	private static Logger logger=LoggerFactory.getLogger(RbbsDAOImplJDBC.class);
	
	@Inject
	JdbcTemplate jt;
	
	//댓글 등록
	@Override
	public int write(RbbsDTO rbbsDTO) {
		int cnt=0;
		StringBuffer sql=new StringBuffer();
		sql.append("insert into replyboard (rnum,bnum,rid,rnickname,rcontent,rgroup,rstep,rindent) ");
		sql.append("values(rboard_seq.nextval,?,?,?,?,rboard_seq.currval,0,0) ");
		
		//기존
//		sql.append("insert into replyboard (rnum,bnum,rid,rnickname,rcontent,rgroup) ");
//		sql.append("values(rboard_seq.nextval,?,?,?,?,rboard_seq.currval) ");
		
		//case1) 수동매핑
//		cnt=jt.update(sql.toString(),
//									new Object[] {rbbsDTO.getBnum(),rbbsDTO.getRid(),
//																rbbsDTO.getRnickname(),rbbsDTO.getRcontent()},
//									new int[] {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
		
		//case2)
		cnt=jt.update(sql.toString(),rbbsDTO.getBnum(),rbbsDTO.getRid(),
																rbbsDTO.getRnickname(),rbbsDTO.getRcontent());
		return cnt;
	}

	//댓글 목록
	@Override
	public List<RbbsDTO> list(String bnum) {

		List<RbbsDTO> alist=new ArrayList<>();
		StringBuffer sql=new StringBuffer();
		sql.append("select rnum,bnum,rid,rnickname,rcdate,rudate, ");
		sql.append("rcontent,rgood,rbad,rgroup,rstep,rindent ");
		sql.append("from(select * from replyboard ");
		sql.append("where bnum=? ");
		sql.append("order by rgroup desc,rstep asc ) ");
		sql.append("where rownum>=1 and rownum<25 ");
		
		//case1) 수동매핑
//		alist=jt.query(sql.toString(),new RowMapper<RbbsDTO>() {
//
//			@Override                          //rowNum:행번호
//			public RbbsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				RbbsDTO rbbsDTO=new RbbsDTO();
//				rbbsDTO.setRnum(rs.getInt("rnum"));
//				rbbsDTO.setBnum(rs.getInt("bnum"));
//				rbbsDTO.setRid(rs.getString("rid"));
//				rbbsDTO.setRnickname(rs.getString("rnickname"));
//				rbbsDTO.setRudate(rs.getTimestamp("rcdate"));
//				rbbsDTO.setRudate(rs.getTimestamp("rudate"));
//				rbbsDTO.setRcontent(rs.getString("rcontent"));
//				rbbsDTO.setRgood(rs.getInt("rgood"));
//				rbbsDTO.setRbad(rs.getInt("rbad"));
//				rbbsDTO.setRgroup(rs.getInt("rgroup"));
//				rbbsDTO.setRstep(rs.getInt("rstep"));
//				rbbsDTO.setRindent(rs.getInt("rindent"));
//				
//				return rbbsDTO;
//			}
//			
//		},bnum);
		
//		//case2) 람다식
//		alist=jt.query(sql.toString(),
//										(rs, rowNum) -> {
//											RbbsDTO rbbsDTO=new RbbsDTO();
//											rbbsDTO.setRnum(rs.getInt("rnum"));
//											rbbsDTO.setBnum(rs.getInt("bnum"));
//											rbbsDTO.setRid(rs.getString("rid"));
//											rbbsDTO.setRnickname(rs.getString("rnickname"));
//											rbbsDTO.setRudate(rs.getTimestamp("rcdate"));
//											rbbsDTO.setRudate(rs.getTimestamp("rudate"));
//											rbbsDTO.setRcontent(rs.getString("rcontent"));
//											rbbsDTO.setRgood(rs.getInt("rgood"));
//											rbbsDTO.setRbad(rs.getInt("rbad"));
//											rbbsDTO.setRgroup(rs.getInt("rgroup"));
//											rbbsDTO.setRstep(rs.getInt("rstep"));
//											rbbsDTO.setRindent(rs.getInt("rindent"));
//											
//											return rbbsDTO;
//		},bnum);
		
		//case3) 자동매핑
		alist=jt.query(sql.toString(), 
									 new BeanPropertyRowMapper<RbbsDTO>(RbbsDTO.class), 
									 bnum);
		return alist;
	}

	//댓글 목록
	@Override
	public List<RbbsDTO> list(String bnum, int startRec, int endRec) {
		List<RbbsDTO> alist=new ArrayList<>();
		StringBuffer sql=new StringBuffer();
		sql.append("select t2.*, ");
    sql.append("(select rnickname from replyboard where rnum = t2.rrnum and t2.rrnum > 0) rrnickname, ");
    sql.append("(select rid  from replyboard where rnum = t2.rrnum and t2.rrnum > 0) rrid	");	
		sql.append("from (select row_number() over (order by rgroup desc, rstep asc) as num,t1.* ");
		sql.append("		    from replyboard t1 ");
		sql.append("			 where bnum=? ) t2 ");
		sql.append("where num between ? and ? ");
		
		alist=jt.query(sql.toString(), 
									 new BeanPropertyRowMapper<RbbsDTO>(RbbsDTO.class), 
									 bnum,startRec,endRec);
		return alist;
	}

	//댓글 수정
	@Override
	public int modify(RbbsDTO rbbsDTO) {

		int cnt=0;
		StringBuffer sql=new StringBuffer();
		sql.append("update replyboard set rudate=sysdate, rcontent=? ");
		sql.append("where rnum=? ");
		
		cnt=jt.update(sql.toString(), 
									rbbsDTO.getRcontent(),rbbsDTO.getRnum());
		return cnt;
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) {
		int cnt=0;
		StringBuffer sql=new StringBuffer();
		sql.append("delete from replyboard where rnum=? ");
		cnt=jt.update(sql.toString(), rnum);
		return cnt;
	}

	//댓글 호감 비호감
	@Override
	public int goodOrBad(String rnum, String goodOrBad) {
		int cnt=0;
		StringBuffer sql=new StringBuffer();
		switch(goodOrBad) {
		case "good":
			sql.append("update replyboard set rgood = rgood+1 where rnum=? ");
			break;
		case "bad":
			sql.append("update replyboard set rbad=rbad+1 where rnum=? ");
			break;
			
			default:
				break;
		}
		
		cnt=jt.update(sql.toString(),rnum);
		
		return cnt;
	}

	//대댓글 등록
	@Override
	public int reply(RbbsDTO rbbsDTO) {
		int cnt=0, cnt2=0;
		
		//댓글대상 정보 읽어오기
			RbbsDTO originDTO=replyView(rbbsDTO.getRnum());
			logger.info("originDTO= "+originDTO.toString());
			
		//이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
			cnt=updateStep(originDTO.getRgroup(),originDTO.getRstep());
			
			StringBuffer sql=new StringBuffer();
			sql.append("insert into replyboard (rnum,bnum,rid,rnickname,rcontent,rgroup,rstep,rindent,rrnum) ");
			sql.append("values(rboard_seq.nextval,?,?,?,?,?,?,?,?) ");

			cnt2=jt.update(sql.toString(),originDTO.getBnum(),rbbsDTO.getRid(),
										 rbbsDTO.getRnickname(),rbbsDTO.getRcontent(),
										 originDTO.getRgroup(), //원글번호 = 원글 그룹
										 originDTO.getRstep()+1, //원글 그룹의 세로정렬(답글단계)
										 originDTO.getRindent()+1, //원글 그룹의 가로정렬(들여쓰기)
										 rbbsDTO.getRrnum()); //부모댓글번호 
			 
		return cnt2;
	}
	
//댓글대상 읽어오기
	private RbbsDTO replyView(int rnum) {
		RbbsDTO rbbsDTO = new RbbsDTO();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select bnum,rgroup, rstep, rindent from replyBoard where rnum = ?");
		
		rbbsDTO=jt.queryForObject(sql.toString(),new BeanPropertyRowMapper<RbbsDTO>(RbbsDTO.class),rnum);
		
		return rbbsDTO;
	}

	
	//댓글단계 업데이트
	private int updateStep(int rgroup, int rstep) {
		int cnt=0;
		
		StringBuffer sql=new StringBuffer();
		sql.append("update replyboard set rstep=rstep+1 where rgroup=? and rstep>? ");
	
		cnt=jt.update(sql.toString(),rgroup,rstep);
				return cnt;
	}
	
	//대댓글 총계
	@Override
	public int replyTotalRec(String bnum) {
		int totalRec = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) totalRec from replyboard where bnum=?");
		
		totalRec=jt.queryForObject(sql.toString(), Integer.class, bnum);
		return totalRec;
	}

}
