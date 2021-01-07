package ie.gmit.sw;

import java.time.*;

public record JarFile(String name, LocalDateTime fcd, Status jar, Integer loc) {
}

