package com.shrooms.scaffold.repository.scaffold;

import com.shrooms.scaffold.model.entity.scaffold.Scaffold;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScaffoldRepository extends JpaRepository<Scaffold, UUID> {
    public Optional<Scaffold> findById(@NonNull UUID id);
}
