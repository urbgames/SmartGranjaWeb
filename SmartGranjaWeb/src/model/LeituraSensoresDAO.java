package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import objects.LeituraSensores;
import objects.ListaLeituraSensores;

public class LeituraSensoresDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sensoriamento");
	EntityManager manager = factory.createEntityManager();

	public void inserirLeitura(LeituraSensores leitura) {
		manager.getTransaction().begin();
		manager.persist(leitura);
		manager.getTransaction().commit();
	}

	public void finalizar() {
		manager.close();
		factory.close();
	}

	public List<ListaLeituraSensores> getIntervaloLeituraSensor(ListaLeituraSensores listaLeituraSensores) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(LeituraSensores.class);
		criteria.add(Restrictions.between("instante", listaLeituraSensores.getDataInicio(),
				listaLeituraSensores.getDataFim()));
		criteria.addOrder(Order.asc("instante"));
		List<ListaLeituraSensores> listaLeituraSensoresResultado = criteria.list();
		return listaLeituraSensoresResultado;
	}
}
