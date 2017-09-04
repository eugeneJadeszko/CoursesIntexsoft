package by.intexsoft.library.model;

import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
public class AbstractEntity extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -883768942758378248L;

}
