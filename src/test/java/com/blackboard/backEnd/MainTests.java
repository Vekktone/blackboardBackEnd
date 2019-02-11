package com.blackboard.backEnd;

import com.blackboard.backEnd.controller.StudentController;
import com.blackboard.backEnd.model.MailReturn;
import com.blackboard.backEnd.service.StudentService;
import com.blackboard.backEnd.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.mail.MessagingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
public class MainTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	StudentService studentService;

	@MockBean
    EmailService emailService;

	@Test
	public void contextLoads() {
		assertThat(true).isEqualTo(true);
	}

	@Test
	public void testJUnit() {
		String str = "Junit is working fine";
		assertEquals("Junit is working fine",str);

	}

	@Test
	public void testGet1Records() throws Exception {

		MvcResult result = mockMvc.perform(get("/customer/getRecords/1"))
				.andExpect(status().isOk()).andReturn();
//				.andExpect(content().json("[{'id':'3','address':'3208 Daniel Ave','city':'Dallas','email':'kathy@null.com','first_name':'Lee','last_name':'Kathy','state':'TX','zip':'75205-4909'}]"));
	}

	@Test
	public void testGet10Records() throws Exception {


		mockMvc.perform(get("/customer/getRecords/10"))
				.andExpect(status().isOk());
//				.andExpect(content().json("[{'id':'3','address':'3208 Daniel Ave','city':'Dallas','email':'kathy@null.com','first_name':'Lee','last_name':'Kathy','state':'TX','zip':'75205-4909'}]"));
	}

	@Test
	public void testSearch() throws Exception {


		mockMvc.perform(get("/customer/searchData/Lee"))
				.andExpect(status().isOk());
//				.andExpect(content().json("[{'id':'3','address':'3208 Daniel Ave','city':'Dallas','email':'kathy@null.com','first_name':'Lee','last_name':'Kathy','state':'TX','zip':'75205-4909'}]"));
	}

	@Test
    public void testEmailMessageWithNullList() throws MessagingException {
        MailReturn mailReturn = new MailReturn();
        mailReturn.setAddress("johnb@123.com");
        mailReturn.setSubjectM("a test subject");
        mailReturn.setBodyM("a test body");
        mailReturn.setFinalList(null);

        assertEquals(EmailService.constructMessage(mailReturn, null).getSubject(), mailReturn.getSubjectM());
    }

	}
