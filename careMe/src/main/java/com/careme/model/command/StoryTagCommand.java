package com.careme.model.command;

import java.util.Arrays;

public class StoryTagCommand {
	private String[] tags;
	
	private int tag_idx;
	private String tag_name;
	private int story_board_idx;
	private String del_yn;

	public String getTag_name() {
		return tag_name;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public int getTag_idx() {
		return tag_idx;
	}

	public void setTag_idx(int tag_idx) {
		this.tag_idx = tag_idx;
	}

	public int getStory_board_idx() {
		return story_board_idx;
	}

	public void setStory_board_idx(int story_board_idx) {
		this.story_board_idx = story_board_idx;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	@Override
	public String toString() {
		return "StoryTagCommand [tags=" + Arrays.toString(tags) + ", tag_idx=" + tag_idx + ", tag_name=" + tag_name
				+ ", story_board_idx=" + story_board_idx + ", del_yn=" + del_yn + "]";
	}
	
}
