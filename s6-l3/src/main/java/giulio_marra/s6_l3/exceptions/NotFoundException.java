package giulio_marra.s6_l3.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID uuid) {
        super("Record con id " + uuid + " non trovato!");
    }
}
