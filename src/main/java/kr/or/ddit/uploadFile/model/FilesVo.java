package kr.or.ddit.uploadFile.model;

public class FilesVo {
	private int file_id;
	private String file_name;
	private String org_file_name;
	private String file_path;
	private String file_ext;
	private int post_id;
	
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getOrg_file_name() {
		return org_file_name;
	}
	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_ext() {
		return file_ext;
	}
	public void setFile_ext(String file_ext) {
		this.file_ext = file_ext;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	@Override
	public String toString() {
		return "FilesVo [file_id=" + file_id + ", file_name=" + file_name + ", org_file_name=" + org_file_name
				+ ", file_path=" + file_path + ", file_ext=" + file_ext + ", post_id=" + post_id + "]";
	}
	
	
}
