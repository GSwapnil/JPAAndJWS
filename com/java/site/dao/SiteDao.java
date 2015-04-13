package com.java.site.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.java.site.model.Site;


@Path("/api/site")
public class SiteDao {

	EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("JPA&amp;JWS");
	EntityManager entityManager = null;

	/*
	 * Returns an instance of Site representing a record whose id is siteId
	 * 
	 * @param siteId : Site Id to be retrieved
	 */
	@GET
	@Path("/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("ID") int siteId)
	{
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Site site = entityManager.find(Site.class, siteId);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return site;
	}
	
	/*
	 * Returns a list of Site instances
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites()
	{
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createNamedQuery("Site.findAll");
		List<Site> sites = query.getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();

		return sites;
	}
	
	/*
	 * Updates the site record whose id is siteId and then returns all the sites
	 * 
	 * @param siteId : Site Id to be updated
	 * @param site : Site to be updated
	 * JSON instance of site containing new property values
	 */
	@SuppressWarnings("unchecked")
	@PUT
	@Path("/{ID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("ID") int siteId, Site site)
	{
		List<Site> siteList = new ArrayList<Site>();
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		site.setId(siteId);
		entityManager.merge(site);
		
		Query query = entityManager.createNamedQuery("Site.findAll");
		siteList = query.getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return siteList;
	}
	
	/*
	 * Removes a site record whose id is siteId and the returns a list of all the sites
	 * @param siteId : Site that we want to be removed
	 */
	@SuppressWarnings("unchecked")
	@DELETE
	@Path("/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(@PathParam("ID") int siteId)
	{
		List<Site> siteList = new ArrayList<Site>();
		
		Site site = null;
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		site = entityManager.find(Site.class, siteId);
		entityManager.remove(site);
		
		Query query = entityManager.createNamedQuery("Site.findAll");
		siteList = query.getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return siteList;
	}
	
	/*
	 * Inserts a new site record into the database and then returns a list of all the sites
	 * @param site : Site to be inserted
	 */
	@SuppressWarnings("unchecked")
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site)
	{
		List<Site> siteList = new ArrayList<Site>();
		entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(site);
		
		Query query = entityManager.createNamedQuery("Site.findAll");
		siteList = query.getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return siteList;

	}
}

