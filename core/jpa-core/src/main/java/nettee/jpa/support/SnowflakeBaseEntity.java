package nettee.jpa.support;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import nettee.hibenate.annotation.SnowflakeGenerated;

import java.io.Serializable;

@Getter
@MappedSuperclass
public abstract class SnowflakeBaseEntity implements Serializable {
    @Id
    @SnowflakeGenerated
    private Long id;
}
