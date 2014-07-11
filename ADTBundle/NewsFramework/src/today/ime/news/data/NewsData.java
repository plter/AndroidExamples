package today.ime.news.data;

public class NewsData {
	
	public NewsData(String label,String[] content) {
		setLabel(label);
		setContent(content);
	}
	
	public NewsData(String label,String[] content,boolean selected) {
		setLabel(label);
		setContent(content);
		setSelected(selected);
	}
	
	
	public String getLabel() {
		return label;
	}
	
	public String[] getContent() {
		return content;
	}
	
	private void setLabel(String label) {
		this.label = label;
	}
	
	private void setContent(String[] content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return label;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	private boolean selected = false;
	private String label;
	private String[] content;
	
}
