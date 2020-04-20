package com.careme.model.command;

public class SearchBoardCommand {
	
		String search_option;
		String searchKeyword;
		
		int start_idx;
		int contentPerPage;
		
		public String getSearch_option() {
			return search_option;
		}
		public void setSearch_option(String search_option) {
			this.search_option = search_option;
		}
		public String getSearchKeyword() {
			return searchKeyword;
		}
		public void setSearchKeyword(String searchKeyword) {
			this.searchKeyword = searchKeyword;
		}
		public int getStart_idx() {
			return start_idx;
		}
		public void setStart_idx(int start_idx) {
			this.start_idx = start_idx;
		}
		public int getContentPerPage() {
			return contentPerPage;
		}
		public void setContentPerPage(int contentPerPage) {
			this.contentPerPage = contentPerPage;
		}
		@Override
		public String toString() {
			return "SearchBoardCommand [search_option=" + search_option + ", searchKeyword=" + searchKeyword
					+ ", start_idx=" + start_idx + ", contentPerPage=" + contentPerPage + "]";
		}
		
}
