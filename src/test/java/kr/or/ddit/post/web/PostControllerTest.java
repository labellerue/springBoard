package kr.or.ddit.post.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import kr.or.ddit.test.ControllerTestConfig;

public class PostControllerTest extends ControllerTestConfig {

	@Test
	public void postPageListTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/post/postPageList")).andReturn();

		/***When***/
		String viewName = mvcResult.getModelAndView().getViewName();
		
		/***Then***/
		assertEquals("post/postPageList", viewName);
	}
	
	@Test
	public void detailPostTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/post/detailPost")).andReturn();
		
		/***When***/
		//String viewName = mvcResult.getModelAndView().getViewName();
		
		/***Then***/
		//assertEquals("post/detailPost", viewName);
	}
	
	

}
