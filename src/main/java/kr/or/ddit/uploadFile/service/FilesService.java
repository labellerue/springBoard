package kr.or.ddit.uploadFile.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.uploadFile.dao.FilesDaoInf;
import kr.or.ddit.uploadFile.model.FilesVo;

@Service
public class FilesService implements FilesServiceInf {
	
	@Resource(name="filesDao")
	private FilesDaoInf filesDao;

	@Override
	public List<FilesVo> selectFiles(int post_id) {
		return filesDao.selectFiles(post_id);
	}

	@Override
	public int insertFiles(FilesVo filesVo) {
		return filesDao.insertFiles(filesVo);
	}

	@Override
	public int deleteFiles(int post_id) {
		return filesDao.deleteFiles(post_id);
	}

}
