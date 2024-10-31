package svt;

import javax.faces.application.FacesMessage;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import svt.Members;

@Named("atmBean")
@SessionScoped	
public class AtmBean implements Serializable {
	
	//Action Strings:
	private static String LOGIN = "login";
	private static String ERROR = "error";
	private static String PROCEED = "proceed";
	private static String HOME = "home";
	private static String SUCCESS = "success";
	
	//Fields
	//private String memid = "Member2";
	Config config = ConfigProvider.getConfig();
	private String memid = config.getValue("config.test.memberid", String.class);
			
	private String ccnum = null;
	private Members myMember;
	private int points_add=0;
	private int points_wd=0;
	
	//swapped by Brian
	@PersistenceContext(unitName="GasNet_ATM_Module") //<<=== Should work, waiting on story 53829
	//@PersistenceContext(name="ATM/entitymanager")
	private EntityManager em2;
	
	public AtmBean(){
	}
	
	
	public String doLogin(){
		EntityManager em = null;
		try {
			//em = this.getManager();
			myMember = em2.find(Members.class, memid);
			//System.out.println("From JSFBean.java, JSF API Location: " + FacesContext.class.getProtectionDomain().getCodeSource()); 

			if(myMember == null){
				FacesContext.getCurrentInstance().addMessage("",  new FacesMessage(" Sorry, that member does not exist in the database."));
				return ERROR;
			}

		} 
		catch (EntityNotFoundException e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("",  new FacesMessage(" Sorry, that member does not exist in the database."));
			return ERROR;
		}
		catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("",  new FacesMessage(" Sorry, general exception ocurred, please check logs."));
			return ERROR;
		}
		
		return LOGIN;
	}
	
	public String killHttpSession() {
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		httpSession.invalidate();
		ConfigProviderResolver.instance().releaseConfig(config);
		return HOME;
	}
	
	public String addPoints(){
		EntityManager em;
		UserTransaction ut = null;
		InitialContext ic = null;
		try {
			ic = new InitialContext();
			ut = (UserTransaction) ic.lookup("java:comp/UserTransaction"); 
			ut.begin();
			//em = this.getManager();
			//System.out.println("Points selected is " + points_add);
			myMember.setGasPoints(myMember.getGasPoints()+points_add);
			//System.out.println("Is entity attached " + em.contains(myMember));
			em2.merge(myMember);
			points_add = 0;
			ut.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ut.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String usePoints(){
		EntityManager em;
		UserTransaction ut = null;
		InitialContext ic = null;
		try {
			ic = new InitialContext();
			ut = (UserTransaction) ic.lookup("java:comp/UserTransaction"); 
			ut.begin();
			//em = this.getManager();
			//System.out.println("Points selected is " + points_wd);
			myMember.setGasPoints(myMember.getGasPoints()-points_wd);
			//System.out.println("Is entity attached " + em.contains(myMember));
			em2.merge(myMember);
			points_wd = 0;
			ut.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ut.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String updateProfile(){
		EntityManager em;
		UserTransaction ut = null;
		InitialContext ic = null;
		try {
			ic = new InitialContext();
			ut = (UserTransaction) ic.lookup("java:comp/UserTransaction"); 
			ut.begin();
			//em = this.getManager();
			em2.merge(myMember);
			ut.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ut.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	
	private EntityManager getManager() throws Exception{
		EntityManager em = null;
		try {
			InitialContext ic;
			ic = new InitialContext();
			em = (EntityManager)ic.lookup("java:comp/env/ATM/entitymanager");
		}
		catch (NamingException e)
		{
			e.printStackTrace();
			throw e;
		}
		
		return em;

	}

	//getters
	public String getMemid() {return memid;}
	public Members getMyMember() {return myMember;}
	public String getCcnum() {
		StringBuilder temp = new StringBuilder(this.myMember.getCcNo());
		for(int i = 0; i < temp.length()-4; i++){
			temp.setCharAt(i, 'X');
		}
		return temp.toString();
	}
	public int getPoints_add() {return points_add;}
	public int getPoints_wd() {return points_wd;}
	
	//setters
	public void setMemid(String memid) {this.memid = memid;}
	public void setMyMember(Members myMember) {this.myMember = myMember;}
	public void setCcnum(String ccnum) {this.ccnum = ccnum;}
	public void setPoints_add(int points_add) {this.points_add = points_add;}
	public void setPoints_wd(int points_wd) {this.points_wd = points_wd;}


}
