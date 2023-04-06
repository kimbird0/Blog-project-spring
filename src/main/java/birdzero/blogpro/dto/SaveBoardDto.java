package birdzero.blogpro.dto;

import birdzero.blogpro.model.Board;
import lombok.Builder;

public class SaveBoardDto {
    private String title;
    private String content;
    private String category;

    @Builder
    public SaveBoardDto(String title, String content, String category){
        this.title = title;
        this.content =content;
        this.category=category;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }

}
