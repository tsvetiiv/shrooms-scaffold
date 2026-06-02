package com.shrooms.scaffold.repository.customRequest;

import com.shrooms.scaffold.model.entity.customrequest.CustomRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CustomRequestRepository extends JpaRepository<CustomRequest, UUID> {
}
