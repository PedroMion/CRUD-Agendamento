package dominio;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Agendamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String cpf;
	private String nome;
	private String observacao;
	private Timestamp data;
	
	public Agendamento() {}
	
	public Agendamento(Integer id, String cpf, String nome, String observacao, Timestamp data) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.observacao = observacao;
		this.data = data;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Agendamento [cpf=" + cpf + ", nome=" + nome + ", observacao=" + observacao + ", data=" + data + "]";
	}
	
	
	
}
