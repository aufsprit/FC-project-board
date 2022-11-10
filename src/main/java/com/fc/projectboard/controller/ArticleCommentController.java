package com.fc.projectboard.controller;

import com.fc.projectboard.dto.UserAccountDto;
import com.fc.projectboard.dto.request.ArticleCommentRequest;
import com.fc.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping ("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
        //TODO: 인증 정보를 넣어줘야 한다.
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                "aufsprit", "pw", "aufsprit@naver.com", null, null
        )));

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    //delete 에서도 PostMapping 인 이유는 form 태그에서 Get 메소드와 Post 메소드 두개 밖에 다루지 못하기 때문.
    //put 과 delete 가 있을 필요가 없다고..
    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
        articleCommentService.deleteArticleComment(commentId);


        return "redirect:/articles/" + articleId;
    }

}
