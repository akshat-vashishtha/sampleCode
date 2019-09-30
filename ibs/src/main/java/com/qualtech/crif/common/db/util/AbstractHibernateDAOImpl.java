package com.qualtech.crif.common.db.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.persister.entity.Joinable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

/**
 * The Class AbstractHibernateDAOImpl.
 * 
 * @param <T>
 *            the generic type
 *            
 */
public abstract class AbstractHibernateDAOImpl<T> 
{
	private static final Logger logger = Logger.getLogger(AbstractHibernateDAOImpl.class.getName());
	/** The template class. */
	protected Class<T> templateClass = getTemplateClass();

	/** The session factory. */
	@Autowired
	private LocalSessionFactoryBean sessionFactory;
	/*@Autowired
	private static SessionFactory   sessionFactory=null;*/

	//private static SessionFactory   staticSessionFactory=null;


	private Session session;

	/** The event publisher. */
	@Autowired
	protected ApplicationEventPublisher eventPublisher;

	/*
	protected SessionFactory getSessionFactory() 
	{
		try
		{
			//	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
			//	            configuration.getProperties()). buildServiceRegistry();
			if(sessionFactory==null)
			{
				//File f=new File("/opt/jdk1.7_workSpaceWeblogic/CrifService/src/com/qc/starter/common/db/util/hibernate.cfg.xml") ;  
				Configuration configuration = new Configuration();
				configuration.configure();
				configuration.addPackage("com.qualtech.crif.api.entity")
				.addAnnotatedClass(CriffDetailLogs.class)
				.addAnnotatedClass(CriffDrivedAttribute.class)
				.addAnnotatedClass(CriffLoanDetails.class)
				.addAnnotatedClass(CriffScore.class)
				.addAnnotatedClass(CriffScoreStatus.class)
//				.addAnnotatedClass(CrifSecurityUsers.class)
				.addAnnotatedClass(GrpResponseSummary.class)
//				.addAnnotatedClass(IndvEnquiryHistory.class)
				.addAnnotatedClass(IndvGrpDetails.class)
				.addAnnotatedClass(IndvIds.class)
				.addAnnotatedClass(IndvLoanDetails.class)
				.addAnnotatedClass(IndvRelation.class)
				.addAnnotatedClass(IndvResponseAddress.class)
				.addAnnotatedClass(IndvResponseDetails.class)
				.addAnnotatedClass(IndvResponseNormalSummary.class)
				.addAnnotatedClass(PrimaryAccountSummary.class)
//				.addAnnotatedClass(RequestResponseTrackerEntity.class)
				.addResource("CriffDetailLogs.hbm.xml")
				.addResource("CriffDrivedAttribute.hbm.xml")
				.addResource("CriffLoanDetails.hbm.xml")
				.addResource("CriffScore.hbm.xml")
				.addResource("CriffScoreStatus.hbm.xml")
//				.addResource("CrifSecurityUsers.hbm.xml")
				.addResource("GrpResponseSummary.hbm.xml")
//				.addResource("IndvEnquiryHistory.hbm.xml")
				.addResource("IndvGrpDetails.hbm.xml")
				.addResource("IndvIds.hbm.xml")
				.addResource("IndvLoanDetails.hbm.xml")
				.addResource("IndvRelation.hbm.xml")
				.addResource("IndvResponseAddress.hbm.xml")
				.addResource("IndvResponseDetails.hbm.xml")
				.addResource("IndvResponseNormalSummary.hbm.xml")
				.addResource("PrimaryAccountSummary.hbm.xml")
//				.addResource("RequestResponseTrackerEntity.hbm.xml")
				.configure();
				sessionFactory = configuration.buildSessionFactory();
			}	
		}
		catch(Exception ec)
		{
			logger.error("We are in Exception While tring to getSessionFactory : "+ec);//IndvResponseDetails
		}
		return sessionFactory;
	}
*/

	/**
	 * Gets the session.
	 * 
	 * @return the session
	 */
	protected Session getSession() 
	{
		if(session==null)
		{
			try 
			{
				session=sessionFactory.getObject().getCurrentSession();
			}
			catch (HibernateException e)
			{
				// TODO Auto-generated catch block
				logger.error("We are in Exception While tring to getSession : "+e);
			}
		}
		return session; 
	}

	/**
	 * Method to return the class of the domain object.
	 * 
	 * @return the domain class
	 */
	protected abstract Class<T> getTemplateClass();

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the domain object
	 */
	@SuppressWarnings("unchecked")
	public T load(final Long id) {
		return (T) getSession().load(templateClass, id);
	}

	/**
	 * Update.
	 * 
	 * @param t
	 *            the t
	 */
	public void update(final T t) {
		Transaction transct=getSession().getTransaction();
		transct.begin();
		getSession().update(t);
		transct.commit();
	}

	/**
	 * Merge.
	 * 
	 * @param t
	 *            the t
	 */
	public void merge(final T t) {
		Transaction transct=getSession().getTransaction();
		transct.begin();
		getSession().merge(t);
		transct.commit();

	}

	/**
	 * Save.
	 * 
	 * @param t
	 *            the t
	 * @return the long
	 */
	public Long save(final T t) {
		Long result=null;
		try{
			Transaction transct=getSession().getTransaction();
			transct.begin();
			result=(Long) getSession().save(t);
			transct.commit();
		}catch(Exception ee){
			ee.printStackTrace();
			result=(Long) getSession().save(t);
//			System.out.println("error ::"+ee.getMessage());
		}
		
		return result; 

	}

	/**
	 * Delete.
	 * 
	 * @param t
	 *            the t
	 */
	public void delete(final T t) {
		getSession().delete(t);
	}

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Query query = getSession().createQuery("from " + templateClass.getName());
		query.setCacheable(true);
		return (query.list());
	}

	/**
	 * Delete by id.
	 * 
	 * @param id
	 *            the id
	 */
	public void deleteById(final Long id) {
		Object obj = load(id);
		getSession().delete(obj);
	}

	/**
	 * Delete all.
	 */
	public void deleteAll() {
		Query query = getSession().createQuery(
				"delete from " + templateClass.getName());
		query.executeUpdate();
	}

	/**
	 * Truncate.
	 */
	public void truncate() {
		Query query = getSession().createSQLQuery(
				"TRUNCATE TABLE "
						+ Joinable.class.cast(
								getSession().getSessionFactory()
								.getClassMetadata(getTemplateClass()))
						.getTableName());
		query.executeUpdate();
	}

	/**
	 * Count.
	 * 
	 * @return the long
	 */
	public Long count() {
		Query query = getSession().createQuery(
				"select count(*) from " + templateClass.getName() + " x");
		Long count = (Long) query.list().get(0);
		return count;
	}

	/**
	 * Save or update.
	 * 
	 * @param object
	 *            the object
	 */
	public void saveOrUpdate(final T object) {
		Transaction transct=getSession().getTransaction();
		transct.begin();
		getSession().saveOrUpdate(object);
		transct.commit();

	}

	/**
	 * Gets the.
	 * 
	 * @param id
	 *            the id
	 * @return the domain object
	 */
	@SuppressWarnings("unchecked")
	public T get(final Long id) {
		return (T) getSession().get(templateClass, id);
	}

	/**
	 * Find all paginated and sorted raw.
	 * 
	 * @param page
	 *            the page
	 * @param size
	 *            the size
	 * @param sortBy
	 *            the sort by
	 * @param sortOrder
	 *            the sort order
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllPaginatedAndSortedRaw(final int page,
			final int size, final String sortBy, final String sortOrder) {
		Criteria criteria = createSortyByCriterion(sortBy, sortOrder);
		criteria.setFirstResult(page * size).setMaxResults(size);
		return criteria.list();
	}

	/**
	 * Find all sorted.
	 * 
	 * @param sortBy
	 *            the sort by
	 * @param sortOrder
	 *            the sort order
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllSorted(final String sortBy,
			final String sortOrder) {
		Criteria criteria = createSortyByCriterion(sortBy, sortOrder);
		return criteria.list();
	}

	/**
	 * Creates the sorty by criterion.
	 * 
	 * @param sortBy
	 *            the sort by
	 * @param sortOrder
	 *            the sort order
	 * @return the criteria
	 */
	private Criteria createSortyByCriterion(final String sortBy,
			final String sortOrder) {
		Criteria criteria = getSession().createCriteria(templateClass);
		criteria.setCacheable(true);
		if (null != sortBy)
			if (null != sortOrder && sortOrder.equalsIgnoreCase("desc")) {
				criteria.addOrder(Order.asc(sortBy));
			} else
				criteria.addOrder(Order.desc(sortBy));
		return criteria;
	}

	/**
	 * Batch create.
	 * 
	 * @param entityList
	 *            the entity list
	 * @param batchSize
	 *            the batch size
	 * @return the long
	 */
	public long batchCreate(final List<T> entityList, long batchSize) {
		Long insertedCount = 0L;
		for (int i = 0; i < entityList.size(); ++i) {
			save(entityList.get(i));
			if (++insertedCount % batchSize == 0) {
				flushAndClear();
			}
		}
		flushAndClear();
		return insertedCount;
	}

	/**
	 * Flush and clear.
	 */
	void flushAndClear() {
		if (getSession().isDirty()) {
			getSession().flush();
			getSession().clear();
		}
	}
}