package kr.ac.shinhan.csp;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/UserAccount")
public class UserAccountManagingService {
	
private static PersistenceManagerFactory PMF;
	
	public UserAccountManagingService()
	{
		PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(UserAccount account)
	{
		 Result result = new Result();
		
		 if(existAccount(account.getAccount()) == false)
		 {
			 PersistenceManager pm = PMF.getPersistenceManager();
			 javax.jdo.Transaction tx = pm.currentTransaction();
			 
			 tx.begin();
			 UserAccount retAccount = pm.makePersistent(account);
			 tx.commit();
			 
			 pm.close();
		
			 if(retAccount == null)
			 {
				 result.setMessage("InputFail");
				 result.setFail();
			 }
			 
			 else
			 {
				 result.setSuccess();
				 result.setMessage(retAccount.getKey().toString());
			 }
		 }
		 else
		 {
			 result.setMessage("Existing Account");
			 result.setFail();
		 }
		 
		 Response response = Response.ok().entity(result).build();
		 
		 return response;
	}
	
	@GET
	@Path("/HelloWorld")
	public Response test()
	{
		return Response.ok().entity("OK").build();
	}
	
	/*@POST
	@Path("/CheckAccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkAccoount(UserAccount account)
	{
		Result result = new Result();
		result.setFail("Invalid Account");
		
		boolean loginSuccess = false;

		PersistenceManager pm = PMF.getPersistenceManager();
		Query qry = pm.newQuery(UserAccount.class);
		qry.setFilter("account == accountParam");
		qry.declareParameters("String accountParam");
		
		try{
			List<UserAccount> userList = (List<UserAccount>) qry.execute(account.getAccount());
			if(!userList.isEmpty())
			{
				loginSuccess = userList.get(0).checkLogin(account.getAccount(), account.getPassword());
			}
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			qry.closeAll();
			pm.close();
		}
		
		if(loginSuccess)
		{
			result.setSuccess();
			result.setMessage("Success");
		}
		
		return Response.ok().entity(result).build();
	}*/
	
	private boolean existAccount(String account)
	{
		PersistenceManager pm = PMF.getPersistenceManager();
		
		Query qry = pm.newQuery(UserAccount.class);
		qry.setFilter("account == accountParam");
		qry.declareParameters("String accountParam");
		boolean result;
		
		try{
			List<UserAccount> userList = (List<UserAccount>) qry.execute(account);
			if(userList.isEmpty())
				result = false;
			else
				result = true;
		} catch(Exception e)
		{
			e.printStackTrace();
			result = true;
		}
		finally{
			qry.closeAll();
			pm.close();
		}
		
		return result;
	}
	
	@GET
	@Path("/GetUserAccount/{accountKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserAccount getUserAccount(@PathParam("accountKey") String accountKey)
	{
		return this.getUserAccountObject(Long.parseLong(accountKey));
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUserAccount(UserAccount inputAccount)
	{
		PersistenceManager pm = PMF.getPersistenceManager();
		Result result = new Result();

		UserAccount stored = pm.getObjectById(UserAccount.class,inputAccount.getKey());
		if (stored == null)
		{
			return Response.ok().entity(new Result(false, "Invalid Account")).build();
		}
		//inputAccount.setKey(stored.getKey());
		inputAccount.setPassword(stored.getPassword());
		
		UserAccount ret = pm.makePersistent(inputAccount);
		
		if(ret == null)
		{
			result.setFail();
			result.setMessage("Update Fail");
		}
		else
		{
			result.setSuccess();
		}
		
		return Response.ok().entity(result).build();
	}
	
	public UserAccount getUserAccountObject(long accountKey)
	{
		PersistenceManager pm = PMF.getPersistenceManager();
		
		UserAccount retUA = pm.getObjectById(UserAccount.class,accountKey);
		if (retUA == null)
		{
			return null;
		}
		
		//hide password
		retUA.setPassword("");

		pm.close();
		
		return retUA;
	}
	
	@DELETE
	@Path("/{accountKey}")
	public Response deleteUserAccount(@PathParam("accountKey") String accountKey)
	{
		Result result = new Result();
		
		UserAccount ua = getUserAccountObject(Long.parseLong(accountKey));
	
		if(ua == null)
		{
			result.setFail("Invalid Account");
		}
		else
		{
			PersistenceManager pm = PMF.getPersistenceManager();
			pm.makePersistent(ua);
			pm.deletePersistent(ua);
			result.setSuccess();
		}
		
		return Response.ok().entity(result).build();
	}
}

