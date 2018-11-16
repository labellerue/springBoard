package kr.or.ddit.uploadFile.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.uploadFile.model.FilesVo;

@Repository
public class FilesDao implements FilesDaoInf {
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;

	@Override
	public List<FilesVo> selectFiles(int post_id) {
		
		List<FilesVo> filesList = template.selectList("files.selectFiles", post_id);
		
		return filesList;
	}

	@Override
	public int insertFiles(FilesVo filesVo) {
		
		return template.insert("files.insertFiles", filesVo);
	}

	@Override
	public int deleteFiles(int post_id) {
		
		return template.delete("files.deleteFiles", post_id);
	}

}
