package dominio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;*/

public class Agendamento_DAO {
	
	public boolean adicionar(String cpf, String nome, String observacao, Timestamp data) {
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("agendamento-jpa");
			EntityManager em = emf.createEntityManager();
			
			Agendamento obj_agendamento = new Agendamento(null, cpf, nome, observacao, data);
			
			em.getTransaction().begin();
			em.persist(obj_agendamento);
			em.getTransaction().commit();
			
			em.close();
			emf.close();			
		}
		catch(Exception e) {
			System.err.println(e + "parei aqui");
			return false;
		}
		return true;
	}
	
	
	public boolean adicionar(Agendamento ag) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("agendamento-jpa");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(ag);
			em.getTransaction().commit();
			
			em.close();
			emf.close();
		}
		catch(Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
	
	
	public Agendamento consultaPorId(int id) {
		Agendamento ag;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("agendamento-jpa");
			EntityManager em = emf.createEntityManager();
			
			ag = em.find(Agendamento.class, id);
			
			em.close();
			emf.close();
			
			return ag;
		}
		catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
	
	public List<Agendamento> consultaPorNome(String nome) {
		
		String jpql = "SELECT a FROM Agendamento a WHERE a.nome = :nome";
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("agendamento-jpa");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			List<Agendamento> ag = em.createQuery(jpql, Agendamento.class).setParameter("nome", nome).getResultList();
			em.getTransaction().commit();
			
			em.close();
			emf.close();
			
			return ag;
		}
		catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
	
	public List<Agendamento> consultaPorCPF(String cpf) {
		
		String jpql = "SELECT a FROM Agendamento a WHERE a.cpf = :cpf";
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("agendamento-jpa");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			List<Agendamento> ag = em.createQuery(jpql, Agendamento.class).setParameter("cpf", cpf).getResultList();
			em.getTransaction().commit();
			
			em.close();
			emf.close();
			
			return ag;
		}
		catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
        
        public List<Agendamento> consultaCompleta() {
            
            String jpql = "SELECT a FROM Agendamento a";
            List<Agendamento> ag;
            
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("agendamento-jpa");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			ag = em.createQuery(jpql, Agendamento.class).getResultList();
			em.getTransaction().commit();
			
			em.close();
			emf.close();
            }
            catch(Exception e) {
                System.err.println(e);
                return null;
            }
            return ag;
        }
        
        public ArrayList<Long> consultaTodosTimestamps() {
            ArrayList<Long> lista = new ArrayList<>();          
            List<Agendamento> ag = consultaCompleta();
            for(int i = 0; i < ag.size(); i++) {
                lista.add(ag.get(i).getData().getTime());
            }
            
            return lista;
        }


	public boolean atualizar(int id, Agendamento novo_obj) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("agendamento-jpa");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			Agendamento ag = em.find(Agendamento.class, id);
			ag.setCpf(novo_obj.getCpf());
			ag.setData(novo_obj.getData());
			ag.setNome(novo_obj.getNome());
			ag.setObservacao(novo_obj.getObservacao());
			em.merge(ag);
			em.getTransaction().commit();
			
			em.close();
			emf.close();			
			
		}
		catch(Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
	
	
	public boolean remover(int id) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("agendamento-jpa");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			Agendamento ag = em.find(Agendamento.class, id);
			em.remove(ag);
			em.getTransaction().commit();
			
			em.close();
			emf.close();
		}
		catch(Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
	
}
