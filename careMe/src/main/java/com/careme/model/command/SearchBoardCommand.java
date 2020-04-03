package com.careme.model.command;

public class SearchBoardCommand {
	
		String search_option;
		String searchKeyword;
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
		@Override
		public String toString() {
			return "SearchCommand [search_option=" + search_option + ", searchKeyword=" + searchKeyword + "]";
		}
		
}
