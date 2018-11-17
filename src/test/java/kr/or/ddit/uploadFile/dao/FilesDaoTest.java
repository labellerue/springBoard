package kr.or.ddit.uploadFile.dao;


import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.test.ServiceDaoTestConfig;
import kr.or.ddit.uploadFile.model.FilesVo;


public class FilesDaoTest extends ServiceDaoTestConfig {
	
	@Resource(name="filesDao")
	private FilesDaoInf filesDao;
	
	@Test
	public void insertFilesTest() {
		/***Given***/
		FilesVo filesVo = new FilesVo();
		filesVo.setFile_path("/fileupload/new.jpg");

		/***When***/
		//int insertFCnt = filesDao.insertFiles(filesVo);

		/***Then***/
		//assertEquals(1, insertFCnt);
	}

}
