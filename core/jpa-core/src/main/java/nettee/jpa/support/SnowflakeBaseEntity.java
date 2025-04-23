package nettee.jpa.support;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import nettee.hibenate.annotation.SnowflakeGenerated;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class SnowflakeBaseEntity implements Serializable {
    @Id
    @SnowflakeGenerated
    private Long id;
}
