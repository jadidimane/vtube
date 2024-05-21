package com.example.vtube.dao.repository;

import com.example.vtube.dao.entities.User;
import com.example.vtube.dao.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Integer> {
    Page<Video> findByUploader(User uploader, Pageable pageable);
    Page<Video> findByTitleContains(String keyword, Pageable pageable);
    Video findByVideoName(String filename);
}
