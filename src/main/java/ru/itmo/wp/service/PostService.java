package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.repository.PostRepository;
import ru.itmo.wp.repository.TagRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public PostService(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post find(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void writeComment(User user, Post post, Comment comment) {
        post.addComment(comment);
        comment.setUser(user);
        comment.setPost(post);
        postRepository.save(post);
    }

    public Post getPostFromForm(PostCredentials postForm) {
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setText(postForm.getText());
        if (!postForm.getTagsString().trim().isEmpty()) {
            String[] tagNames = postForm.getTagsString().trim().split("\\s+");
            Set<Tag> tags = new LinkedHashSet<>();
            for (String tagName : tagNames) {
                Tag tag = new Tag();
                tag.setName(tagName);
                tags.add(tag);
            }

            tagRepository.saveAll(tags);
            post.setTags(tags);
        }
        return post;
    }
}
