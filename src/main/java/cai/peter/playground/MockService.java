package cai.peter.playground;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Contact;

import org.apache.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MockService {
	/**
	 * Logger for this class
	 */
	private static final Logger	logger	= Logger.getLogger(MockService.class);

	@RequestMapping(method = RequestMethod.GET, value = "/speedcallservice/data")
	@ResponseBody
	public List<QuickDial> getSpeedCall(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		logger.info("getSpeedCall(request=" + request
				+ ", response="
				+ response
				+ ") - start");
        List<QuickDial> speedCall  = null;
//		try
		{
			speedCall = Arrays.asList(new QuickDial[]
			{ new QuickDial(0,
							"416-123-4560"),
					new QuickDial(	1,
									"416-123-4561"),
					new QuickDial(	2,
									"416-123-4562"),
					new QuickDial(	3,
									"416-123-4563"),
					new QuickDial(	4,
									"416-123-4564"),
					new QuickDial(	5,
									"416-123-4565"),
					new QuickDial(	6,
									"416-123-4566"),
					new QuickDial(	7,
									"416-123-4567"),
					new QuickDial(	8,
									"416-123-4568"),
					new QuickDial(	9,
									"416-123-4569"),
					new QuickDial(	99,
									"416-123-4599") });
		}
//		catch (Exception e)
//		{
//			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
//		}

		logger.info("getSpeedCall() - end - return value=" + speedCall);
		return speedCall;
	}

	@ResponseBody
	@RequestMapping(value="/speedcallservice/data", method=RequestMethod.DELETE)
	public boolean deleteSpeedCall(@RequestBody List<QuickDial> speedCall)
	{
		logger.info("deleteSpeedCall(speedCall=" + speedCall
				+ ") - start");
		logger.info("deleteSpeedCall() - end - return value=" + true);
		return true;
	}

	@ResponseBody
	@RequestMapping(value="/speedcallservice/data", method=RequestMethod.PUT)
	public boolean updateSpeedCall(@RequestBody List<QuickDial> speedCall)
	{
		logger.info("updateSpeedCall(speedCall=" + speedCall
				+ ") - start");
		logger.info("updateSpeedCall() - end - return value=" + true);
		return true;
	}

	@ResponseBody
	@RequestMapping(value="/speedcallservice/data", method=RequestMethod.POST)
	public boolean createSpeedCall(@RequestBody List<QuickDial> speedCall)
	{
		logger.info("createSpeedCall(speedCall=" + speedCall
				+ ") - start");
		logger.info("createSpeedCall() - end - return value=" + true);
		return true;
	}

	@RequestMapping(value="/callacceptance/data", method=RequestMethod.GET)
	@ResponseBody
	public CallAcceptance getCallAcceptance()
	{
		logger.info("getCallAcceptance() - start");
		CallAcceptance callAcceptance = new CallAcceptance(true, /*Arrays.asList(
		                                      "416-123-4560",
		                                      "416-123-4561",
		                                      "416-123-4562",
		                                      "416-123-4563",
		                                      "416-123-4564",
		                                      "416-123-4565",
		                                      "416-123-4566",
		                                      "416-123-4567",
		                                      "416-123-4568",
		                                      "416-123-4569"
		                                       )*/new ArrayList<String>());
		logger.info("getCallAcceptance() - end - return value=" + callAcceptance);
		return callAcceptance;
	}

	@ResponseBody
	@RequestMapping(value="/callacceptance/data", method=RequestMethod.POST)
	public boolean setCallAcceptance(@RequestBody CallAcceptance ca)
	{
		logger.info("createCallAcceptance(ca=" + ca
				+ ") - start");
		logger.info("createCallAcceptance() - end - return value=" + true);
		return true;
	}

	@RequestMapping(value="/error/500", method=RequestMethod.GET)
	@ResponseBody
	public boolean Http500Response()
	{
		throw new HttpMessageNotWritableException("Internal Error!");
	}

	@RequestMapping(value="/user/credential/sip", method=RequestMethod.POST)
	@ResponseBody
	public boolean updatePassword(@RequestBody String context)
	{
		logger.debug("updatePassword(context=" + context
				+ ") - start");
		return true;
	}

	@RequestMapping(value="/contact/data", method=RequestMethod.GET)
	@ResponseBody
	public List<Contact> getContacts()
	{
		logger.debug("getContacts() - start");
		List<Contact> result=Arrays.asList(
		                                   new Contact("Bob",true,"Bob","Thomas","415-555-3299","415-555-3299","415-555-3299"),
		                                   new Contact("Joe",true,"Joe","Brown","415-555-3299","415-555-3299","415-555-3299"),
		                                   new Contact("Pratt",false,"Pratt","Anthony","415-555-3299","415-555-3299","415-555-3299"),
		                                   new Contact("Angela",true,"Angela","Paul","415-555-3299","415-555-3299","415-555-3299"),
		                                   new Contact("Betty",false,"Betty","George","415-555-3299","415-555-3299","415-555-3299"),
		                                   new Contact("Melvin",false,"Melvin","Jacob","415-555-3299","415-555-3299","415-555-3299"),
		                                   new Contact("Brian",false,"Brian","Hewitt","415-555-3299","415-555-3299","415-555-3299")
		                                   );
		logger.debug("getContacts() - end - return value=" + result);
		return result;
	}

	@RequestMapping(value="/contact/data", method=RequestMethod.POST)
	@ResponseBody
	public boolean setContacts(@RequestBody List<Contact> contacts)
	{
		logger.debug("setContacts(contacts=" + contacts
				+ ") - start");
		logger.debug("setContacts(contacts=" + contacts
				+ ") - end - return value="
				+ true);
		return true;
	}

}
