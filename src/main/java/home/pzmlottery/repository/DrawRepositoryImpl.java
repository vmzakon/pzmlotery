package home.pzmlottery.repository;

import home.pzmlottery.model.Draw;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class DrawRepositoryImpl implements DrawRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveDraw(Draw draw) {
        entityManager.merge(draw);
    }

    @Override
    public Draw getDrawByStatus(Draw.Status status) {
        Draw draw;
        try {
            draw = entityManager.
                    createQuery("select d from Draw d WHERE d.status = :status", Draw.class)
                    .setParameter("status", status)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return draw;
    }
}
