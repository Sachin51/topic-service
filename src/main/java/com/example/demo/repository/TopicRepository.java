package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
