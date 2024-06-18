package org.example.messages;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
class Message {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	private String summary;

	private String text;

	@OneToOne
	private MessageUser from;

	@OneToOne
	private MessageUser to;

	public Message() {
	}

	public Message(Long id, String summary, String text, MessageUser from, MessageUser to) {
		this.id = id;
		this.summary = summary;
		this.text = text;
		this.from = from;
		this.to = to;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public MessageUser getFrom() {
		return from;
	}

	public void setFrom(MessageUser from) {
		this.from = from;
	}

	public MessageUser getTo() {
		return to;
	}

	public void setTo(MessageUser to) {
		this.to = to;
	}
}
