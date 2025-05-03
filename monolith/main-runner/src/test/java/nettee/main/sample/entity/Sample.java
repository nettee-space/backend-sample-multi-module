package nettee.main.sample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import nettee.jpa.support.SnowflakeBaseEntity;

@Entity
@Table(name = "sample")
public class Sample extends SnowflakeBaseEntity {
}
