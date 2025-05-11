package nettee.main.sample.persistence;

import nettee.main.sample.entity.Sample;
import org.springframework.data.repository.CrudRepository;

public interface SampleRepository extends CrudRepository<Sample, Long> {
}