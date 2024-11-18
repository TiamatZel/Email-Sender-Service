package entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toAddress;
    private String subject;
    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime sentAt;

    // Getters y setters
}

