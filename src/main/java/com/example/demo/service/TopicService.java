package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Topic;
import com.example.demo.repository.TopicRepository;

@Service
public class TopicService {

	private static Logger LOGGER = Logger.getLogger(TopicService.class);

	@Autowired
	TopicRepository topicRepo;

	public String addTopicDemo(Topic topic) {
		Topic demoTopic = new Topic();
		for (int i = 5; i < 35; i++) {
			demoTopic = new Topic(i, "test_name" + i, "test_name" + i, i, i);
			topicRepo.save(demoTopic);
		}
		return "Successfully added all topics";
	}

	public Optional<Topic> getTopic(int topicId) {
		return topicRepo.findById(topicId);
	}

	public List<Topic> getAllTopics(int pageNumber, int numberOfResults) {
		Pageable sortedByTopicName = PageRequest.of(pageNumber, numberOfResults,
				Sort.by("id").and(Sort.by("topicName")));
		Slice<Topic> allProductsSortedByName = topicRepo.findAll(sortedByTopicName);
		List<Topic> demoTopicList = new ArrayList<Topic>();
		for (Topic topic : allProductsSortedByName) {
			demoTopicList.add(topic);
			System.out.println("***********" + topic.toString());
		}

		// Grouping by return Map<<String><List<Topic>>>
		demoTopicList.stream().collect(Collectors.groupingBy(Topic::getTopicName));

		return demoTopicList;
	}
}
