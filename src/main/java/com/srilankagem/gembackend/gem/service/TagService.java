package com.srilankagem.gembackend.gem.service;

import com.srilankagem.gembackend.common.exception.DuplicateResourceException;
import com.srilankagem.gembackend.common.exception.ResourceNotFoundException;
import com.srilankagem.gembackend.gem.dto.TagRequest;
import com.srilankagem.gembackend.gem.dto.TagResponse;
import com.srilankagem.gembackend.gem.models.Tag;
import com.srilankagem.gembackend.gem.repository.TagRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepo tagRepo;

    public TagResponse createTag(TagRequest request) {

        if (tagRepo.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException(
                    "Tag already exists with name: " + request.getName()
            );
        }

        Tag tag = Tag.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return toResponse(tagRepo.save(tag));
    }

    public TagResponse getTagById(Long id) {

        Tag tag = tagRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tag",
                                id.toString()
                        ));

        return toResponse(tag);
    }

    public Tag getTagEntityById(Long tagId) {
        return tagRepo.findById(tagId).orElseThrow(() -> new ResourceNotFoundException(tagId.toString() , "resource not found"));
    }

    public Page<TagResponse> getAllTags(
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return tagRepo.findAll(pageable)
                .map(this::toResponse);
    }

    public TagResponse updateTag(
            Long id,
            TagRequest request
    ) {

        Tag tag = tagRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tag",
                                id.toString()
                        ));

        if (!tag.getName().equalsIgnoreCase(request.getName())
                && tagRepo.existsByNameIgnoreCase(request.getName())) {

            throw new DuplicateResourceException(
                    "Tag already exists with name: "
                            + request.getName()
            );
        }

        tag.setName(request.getName());
        tag.setDescription(request.getDescription());

        return toResponse(tagRepo.save(tag));
    }

    public void deleteTag(Long id) {

        Tag tag = tagRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tag",
                                id.toString()
                        ));

        tagRepo.delete(tag);
    }

    private TagResponse toResponse(Tag tag) {

        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .description(tag.getDescription())
                .createdAt(tag.getCreatedAt())
                .build();
    }
}