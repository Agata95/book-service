package j25.cloud.cloud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

    private Long id;
    private String title;
    private String author;
    private int yearWritten;
    private int numberOfPages;
    private int numberOfAvailableCopies;
}
