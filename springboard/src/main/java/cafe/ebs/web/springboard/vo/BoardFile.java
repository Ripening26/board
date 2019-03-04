package cafe.ebs.web.springboard.vo;

public class BoardFile {
	private int boardfileNo;
	private int boardNo;
	private String fileName;
	private String fileExt;
	private String fileTpye;
	private long fileSize;
	public int getBoardfileNo() {
		return boardfileNo;
	}
	public void setBoardfileNo(int boardfileNo) {
		this.boardfileNo = boardfileNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public String getFileTpye() {
		return fileTpye;
	}
	public void setFileTpye(String fileTpye) {
		this.fileTpye = fileTpye;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	@Override
	public String toString() {
		return "boardFile [boardfileNo=" + boardfileNo + ", boardNo=" + boardNo + ", fileName=" + fileName + ", fileExt="
				+ fileExt + ", fileTpye=" + fileTpye + ", fileSize=" + fileSize + "]";
	}
	
}
