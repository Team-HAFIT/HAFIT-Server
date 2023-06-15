package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.post.entity.PostFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostFileDTO{

    private String fileName;

    public PostFileDTO(PostFile postFile){
        this.fileName = postFile.getFileName();
    }
}
