package giulio_marra.s6_l3.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AutoreDTO(@NotEmpty(message = "Inserire nome")
                        String nome,
                        @NotEmpty(message = "Inserire cognome")
                        String cognome,
                        @NotEmpty(message = "L'email è un dato obbligatorio!")
                        @Email(message = "L'email inserita non è valida!")
                        String email,
                        @NotNull(message = "Inserire una data")
                        LocalDate data_di_nascita) {
}
