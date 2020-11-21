package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int topicId;

	private String topicName;

	private String topicDescription;

	private int subscribers;

	private int discussedCount;

	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topic(int topicId, String topicName, String topicDescription, int subscribers, int discussedCount) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.topicDescription = topicDescription;
		this.subscribers = subscribers;
		this.discussedCount = discussedCount;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public int getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(int subscribers) {
		this.subscribers = subscribers;
	}

	public int getDiscussedCount() {
		return discussedCount;
	}

	public void setDiscussedCount(int discussedCount) {
		this.discussedCount = discussedCount;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", topicDescription=" + topicDescription
				+ ", subscribers=" + subscribers + ", discussedCount=" + discussedCount + "]";
	}
}
