package com.G01.onlineFishAuction.dataAccess;

import com.G01.onlineFishAuction.entities.Fish;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class HibernateFishRepository implements IFishRepository{
	private EntityManager entityManager;

	@Autowired
	public HibernateFishRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	@Override
	@Transactional
	public void recordFish(Fish fish) {
		Session session  = entityManager.unwrap(Session.class);
		session.saveOrUpdate(fish);
	}

	@Override
	@Transactional
	public void deleteFish(Fish fish) {
		Session session  = entityManager.unwrap(Session.class);
		Fish fishToDelete = session.get(Fish.class, fish.getId());
		session.delete(fishToDelete);
	}
	@Override
	@Transactional
	public List<Fish> getAll() {
		Session session  = entityManager.unwrap(Session.class);
		return session.createQuery("from Fish", Fish.class).getResultList();

	}

	@Override
	@Transactional
	public Fish getFish(String id) {
		Session session  = entityManager.unwrap(Session.class);
		return session.get(Fish.class, id);
	}

	@Override
	@Transactional
	public List<Fish> getAllFishForAuction(int id) {
		Session session  = entityManager.unwrap(Session.class);
		Integer objectId = new Integer(id);
		String hql = "from Fish where auctionid="+ objectId.toString();
		List<Fish> fish = session.createQuery(hql,Fish.class).getResultList();
		return fish;
	}




}
