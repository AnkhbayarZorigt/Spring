package cz.sda.store.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreGroupRepository extends JpaRepository<Category, Long> {
}
