package model;

import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import objects.RelatorioDiario;

public class RelatorioDiarioDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sensoriamento");
	EntityManager manager = factory.createEntityManager();

	public void atualizarRelatorio(RelatorioDiario relatorio) {
		manager.getTransaction().begin();
		RelatorioDiario relatorioDiario = manager.find(RelatorioDiario.class, relatorio.getId());
		relatorioDiario.setMortalidade(relatorio.getMortalidade());
		manager.getTransaction().commit();
	}

	public void inserirRelatorio(RelatorioDiario relatorio) {
		manager.getTransaction().begin();
		manager.persist(relatorio);
		manager.getTransaction().commit();
	}

	public RelatorioDiario getById(int id) {
		return manager.find(RelatorioDiario.class, id);
	}

	public List<RelatorioDiario> listarRelatorioDiario() {
		manager.getTransaction().begin();
		Query query = manager.createQuery("from RelatorioDiario");
		List<RelatorioDiario> lista = query.getResultList();
		manager.getTransaction().commit();
		return lista;
	}

	public Vector<RelatorioDiario> listarRelatorioDiarioVector() {
		manager.getTransaction().begin();
		Query query = manager.createQuery("from RelatorioDiario");
		List<RelatorioDiario> lista = query.getResultList();
		manager.getTransaction().commit();
		Vector<RelatorioDiario> vectoRelatorio = new Vector<RelatorioDiario>();
		for (RelatorioDiario relatorioDiario : lista) {
			vectoRelatorio.addElement(relatorioDiario);
		}
		return vectoRelatorio;
	}

	public void finalizar() {
		manager.close();
		factory.close();
	}

	public Vector<RelatorioDiario> getToModifyRelatoriosDiarios() {
		manager.getTransaction().begin();
		Query query = manager.createQuery("from RelatorioDiario where mortalidade =:parametro01");
		query.setParameter("parametro01", -1);
		List<RelatorioDiario> lista = query.getResultList();
		Vector<RelatorioDiario> vectoRelatorio = new Vector<RelatorioDiario>();
		for (RelatorioDiario relatorioDiario : lista) {
			vectoRelatorio.addElement(relatorioDiario);
		}
		manager.getTransaction().commit();
		return vectoRelatorio;
	}

	public RelatorioDiario getrelatoriosdiariosbydata(RelatorioDiario relatorioDiario) {
		Date inicio = (Date) relatorioDiario.getData().clone();
		inicio.setHours(0);
		inicio.setMinutes(0);
		inicio.setSeconds(0);
		Date fim = (Date) relatorioDiario.getData().clone();
		fim.setHours(23);
		fim.setMinutes(59);
		fim.setSeconds(59);
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(RelatorioDiario.class);
		criteria.add(Restrictions.between("data", inicio, fim));
		RelatorioDiario relatorioDiarioResult = (RelatorioDiario) criteria.list().get(0);
		return relatorioDiarioResult;
	}

}
