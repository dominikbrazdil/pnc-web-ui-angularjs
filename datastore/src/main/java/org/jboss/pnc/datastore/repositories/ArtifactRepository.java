package org.jboss.pnc.datastore.repositories;

import org.jboss.pnc.model.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface ArtifactRepository extends JpaRepository<Artifact, Integer>, QueryDslPredicateExecutor<Artifact> {

    @Query("select u from Artifact u where u.buildRecord.id = ?1")
    List<Artifact> findByBuildRecord(Integer buildRecordId);

}
