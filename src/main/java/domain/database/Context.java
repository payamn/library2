package domain.database;




import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Context {
	private static SessionFactory  factory =HibernateUtil.getSessionFactory();
	private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();
	
	public static Session getSession()  {
		if (sessions.get() == null)
			sessions.set(factory.openSession());
		return sessions.get();
		
	}
	
	public static void closeSession() {
			
				sessions.get().close();
			
				sessions.set(null);
			
	}
	public static void beginTransaction(){
		getSession().beginTransaction();
	}
	public static void commitTransaction(){
		Session session=getSession();
		session.getTransaction().commit();
		
	}
}
