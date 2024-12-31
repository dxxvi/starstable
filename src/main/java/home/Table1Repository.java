package home;

import jakarta.annotation.Nonnull;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Table1Repository extends JpaRepository<Table1, Table1Id> {
  @Nonnull
  Optional<Table1> findById(@Nonnull Table1Id id);
}
